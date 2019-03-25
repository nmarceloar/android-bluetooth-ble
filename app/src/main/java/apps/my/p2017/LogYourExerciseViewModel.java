package apps.my.p2017;


import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ObservableLong;

import java.util.Calendar;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LogYourExerciseViewModel {

    public final ObservableBoolean loading = new ObservableBoolean(true);


    public final ObservableField<PhysicalActivity> selectedActivity =
            new ObservableField<>();

    public final ObservableLong selectedDate = new ObservableLong(0);
    public final ObservableLong selectedStart = new ObservableLong(0);
    public final ObservableLong selectedDuration = new ObservableLong(0);

    private boolean startTimeDialogOpened = false;
    private boolean durationTimeDialogOpened = false;

    public final ObservableInt hour = new ObservableInt(0);
    public final ObservableInt minute = new ObservableInt(0);


    TimeSelector timeSelector;
    String activityId;


    long startTime;
    long duration;


    public LogYourExerciseViewModel(String activityId, long selectedDate, TimeSelector timeSelector) {

        this.selectedDate.set(selectedDate);
        this.selectedStart.set(selectedDate);
        this.activityId = activityId;
        this.timeSelector = timeSelector;

    }

    public void onTimeSelected(int hour, int minute) {

        if (startTimeDialogOpened) {

            updateStartTime(hour, minute);
            startTimeDialogOpened = false;

        } else {

            updateDurationTime(hour, minute);
            durationTimeDialogOpened = false;

        }


    }

    private void updateDurationTime(int hour, int minute) {

        this.hour.set(hour);
        this.minute.set(minute);

        selectedDuration.set(hour * 60 * 60 * 1000 + minute * 60 * 1000);

    }

    private void updateStartTime(int hour, int minute) {

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(selectedDate.get());
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, minute);

        selectedStart.set(c.getTimeInMillis());

    }

    public void openStartTimeDialog() {

        startTimeDialogOpened = true;
        timeSelector.openTimeDialog();

    }

    public void openDurationTimeDialog() {

        durationTimeDialogOpened = true;
        timeSelector.openTimeDialog();

    }


    public void onStart() {

        App.instance.activitiesService.findById(activityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::setSelectedActivity,
                        this::showError
                );

    }

    private void showError(Throwable throwable) {


    }

    private void setSelectedActivity(PhysicalActivity physicalActivity) {
        this.selectedActivity.set(physicalActivity);
        loading.set(false);
    }
}

