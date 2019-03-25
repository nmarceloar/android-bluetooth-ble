package apps.my.p2017;


import android.databinding.ObservableField;
import android.databinding.ObservableFloat;
import android.databinding.ObservableInt;

import java.sql.Time;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BindingViewModel {

    private DateSelector dateSelector;
    private TimeSelector timeSelector;


    public final List<String> items = Arrays.asList("uno", "dos", "tres", "cuatro", "cinco");

    public final ObservableField<String> name = new ObservableField<>("");
    public final ObservableField<Boolean> loading = new ObservableField<>(true);
    public final ObservableFloat numeroFlotante = new ObservableFloat(0);
    public final ObservableInt numeroEntero = new ObservableInt(0);

    public final ObservableField<String> selectedDate = new ObservableField<>();
    public final ObservableField<String> selectedTime = new ObservableField<>();


    public final ObservableField<String> lastError = new ObservableField<>("");

    public BindingViewModel(DateSelector dateSelector, TimeSelector timeSelector) {
        this.dateSelector = dateSelector;
        this.timeSelector = timeSelector;
    }


    public void onStart() {

        Observable.timer(2000L, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> loading.set(false));


    }


    public void setSelectedDate(int i, int i1, int i2) {

        selectedDate.set(String.format("%s/%s/%s", i2, i1, i));


    }

    public void setSelectedTime(int i, int i1) {

        selectedTime.set(String.format("%ss:%ss", i, i1));

    }

    public void openDatePicker() {

        this.dateSelector.openDateDialog();


    }

    public void openTimePicker() {
        this.timeSelector.openTimeDialog();
    }
}
