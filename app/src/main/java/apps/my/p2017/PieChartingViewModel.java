package apps.my.p2017;

import android.databinding.ObservableFloat;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


class Randoms {

    public static float randomFloatBetween(float min, float max) {

        return Double.valueOf(min + Math.random() * (max - min)).floatValue();

    }


    public static int randomIntBetween(int min, int max) {

        return Double.valueOf(min + Math.random() * (max - min)).intValue();

    }
}

public class PieChartingViewModel {

    public final ObservableFloat proteinas = new ObservableFloat(20);
    public final ObservableFloat hidratos = new ObservableFloat(50);
    public final ObservableFloat grasas = new ObservableFloat(30);

    Disposable d;
    private ScheduledExecutorService ex;

    public void onStart() {

        ex = Executors.newSingleThreadScheduledExecutor();

        ex.scheduleAtFixedRate(this::updateValues, 1000, 1000, TimeUnit.MILLISECONDS);


    }

    private void updateValues() {
        proteinas.set(Randoms.randomFloatBetween(0, 100));
        hidratos.set(Randoms.randomFloatBetween(0, 100));
        grasas.set(Randoms.randomFloatBetween(0, 100));
    }

    public void onStop() {

        ex.shutdownNow();


    }

}
