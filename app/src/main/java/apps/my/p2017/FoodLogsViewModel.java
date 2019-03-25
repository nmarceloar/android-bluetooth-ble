package apps.my.p2017;

import android.databinding.ObservableField;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.schedulers.Schedulers;

public class FoodLogsViewModel {

    private Long date;
    private String userId;

    private UserService userService = App.instance.userService;

    public final ObservableField<List<List<FoodLog>>> foodLogs =
            new ObservableField<>(Collections.emptyList());

    public FoodLogsViewModel(long date) {

        this.date = date; // autoboxing

    }

    public FoodLogsViewModel(String userID, long date) {
        this(date);
        this.userId = userID;

    }


    public void onStart() {

        System.out.println("buscando");
        userService.foodLogsByDate(userId, this.date) // autounboxing
                .flatMap(list -> Observable.fromIterable(list))
                .groupBy(foodLog -> foodLog.logtype)
                .sorted((t1, t2) -> t1.getKey().ordinal() < t2.getKey().ordinal() ? -1 : 1)
                .flatMap(group -> group.toList().toObservable())
                .filter(list -> !list.isEmpty())
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::updateList,
                        this::showError
                );

    }

    private void showError(Throwable throwable) {

    }


    private void updateList(List<List<FoodLog>> foodLogs) {

        System.out.println(foodLogs);

        this.foodLogs.set(foodLogs);


    }

    public void openFoodLogDetails(String foodLogId) {


    }


    public void onFoodLogSelected(int i, int i1) {


    }


}
