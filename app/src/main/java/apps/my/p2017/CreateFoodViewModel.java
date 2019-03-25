package apps.my.p2017;

import android.databinding.ObservableField;
import android.databinding.ObservableFloat;
import android.databinding.ObservableInt;

import java.lang.annotation.AnnotationFormatError;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class CreateFoodViewModel {

    public final ObservableField<String> nombre = new ObservableField<>("");
    public final ObservableField<String> marca = new ObservableField<>("");
    public final ObservableField<String> descripcion = new ObservableField<>("");

    public final ObservableFloat cantidad = new ObservableFloat(0);

    public final ObservableFloat proteinas = new ObservableFloat(0);
    public final ObservableFloat hidratos = new ObservableFloat(0);
    public final ObservableFloat fibra = new ObservableFloat(0);
    public final ObservableFloat grasas = new ObservableFloat(0);

    public ObservableField<List<Unit>> unidades = new ObservableField<>(Collections.emptyList());
    public final ObservableInt selectedUnitIndex = new ObservableInt(0);

    public CreateFoodViewModel() {

        App.instance.unitService.findAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::setUnits,
                        this::showError

                );


    }

    private void showError(Throwable throwable) {

    }

    private void setUnits(List<Unit> units) {
        this.unidades.set(units);
    }

    public void createFood() {


    }

    public void cancelFoodCreation() {
    }


    public void updateTotals() {

    }


}

