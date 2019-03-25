package apps.my.p2017;


import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class Main2ViewModel {

    public final ObservableField<List<Entry>> entries = new ObservableField<>(new ArrayList<>());
    public final ObservableBoolean enableXAxis = new ObservableBoolean(false);

    public void onCreate() {
    }

    public void onStart() {

        entries.set(createRandomEntries(20));

    }

    private List<Entry> createRandomEntries(int count) {

        return Observable.range(0, count)
                .map(index -> new Entry(index, Randoms.randomFloatBetween(100, 102)))
                .toList(count)
                .blockingGet();


    }

}
