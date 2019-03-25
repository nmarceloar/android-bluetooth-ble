package apps.my.p2017;

import android.databinding.ObservableField;
import android.databinding.ObservableFloat;

import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class EditOrRemoveExerciseViewModel {

    String activityId;


    public final ObservableField<PhysicalActivity> activity
            = new ObservableField<>(null);


    public final ObservableFloat speed = new ObservableFloat(0);
    public final ObservableFloat speedMin = new ObservableFloat(0);
    public final ObservableFloat speedMax = new ObservableFloat(0);


    public EditOrRemoveExerciseViewModel(String activityId) {
        this.activityId = activityId;
    }

    public void onStart() {


        App.instance.activitiesService.findById(activityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setActivity, this::showError);

    }

    private void showError(Throwable throwable) {

    }

    private void setActivity(PhysicalActivity physicalActivity) {

        activity.set(physicalActivity);

    }

    public void update() {

        if (speed.get() != 0) {

            activity.get().speed = speed.get();

        } else {

            activity.get().speedMax = speedMax.get();
            activity.get().speedMin = speedMin.get();

        }

        App.instance.activitiesService.updateActivity(activityId, activity.get())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::done, this::showError);


    }

    private void done(PhysicalActivity physicalActivity) {


    }


}
