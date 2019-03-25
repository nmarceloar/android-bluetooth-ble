package apps.my.p2017;


import android.databinding.ObservableField;
import android.databinding.ObservableFloat;

import java.util.List;
import java.util.Observable;

public class FoodLogGroupViewModel {

    private List<FoodLog> logs;

    public final ObservableFloat total = new ObservableFloat(0);
    public String title;

    private FoodLogGroupViewModel(List<FoodLog> logs) {

        io.reactivex.Observable.fromIterable(logs)
                .map(fl -> fl.getTotalCalories())
                .reduce((a, b) -> a + b)
                .subscribe(suma -> total.set(suma));

        title = logs.get(0).logtype.toString();

    }

    public static FoodLogGroupViewModel fromList(List<FoodLog> logs) {

        return new FoodLogGroupViewModel(logs);


    }


}
