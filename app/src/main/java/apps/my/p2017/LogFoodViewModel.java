package apps.my.p2017;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableFloat;
import android.databinding.ObservableInt;
import android.databinding.ObservableLong;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LogFoodViewModel {

    FoodLoggingEventListener listener;

    private UserService userService = App.instance.userService;
    private FoodService foodService = App.instance.foodService;

    private String foodId;
    private String userId;

    public final ObservableBoolean loading = new ObservableBoolean(true);

    public final ObservableLong selectedDate = new ObservableLong(0);

    public final ObservableField<Food> selectedFood = new ObservableField<>();
    public final List<FoodLogType> types = Arrays.asList(FoodLogType.values());

    public final ObservableFloat cantidad = new ObservableFloat(0);

    public final ObservableInt selectedIndex = new ObservableInt(0);

    public final ObservableFloat total = new ObservableFloat(0);
    public final ObservableFloat proteinas = new ObservableFloat(0);
    public final ObservableFloat hidratos = new ObservableFloat(0);
    public final ObservableFloat grasas = new ObservableFloat(0);
    private FoodLog foodLog;

    public LogFoodViewModel(long date, String foodId, String userId, FoodLoggingEventListener logFoodActivity) {

        this.listener = logFoodActivity;

        selectedDate.set(date);
        this.foodId = foodId;
        this.userId = userId;

    }

    private void updateUI() {

        loading.set(false);

    }


    private void handleError(Throwable throwable) {


    }

    private void setFood(Food food) {

        this.selectedFood.set(food);

        foodLog = new FoodLog();
        foodLog.food = this.selectedFood.get();


        cantidad.set(food.cantidad);

    }

    public void updateTotals() {

        if (foodLog == null)
            return;

        foodLog.setQuantity(cantidad.get());

        total.set(foodLog.getTotalCalories());
        proteinas.set((foodLog.getTotalProtein() / foodLog.getTotalCalories()) * 100);
        hidratos.set((foodLog.getTotalCarbs() / foodLog.getTotalCalories()) * 100);
        grasas.set((foodLog.getTotalFat() / foodLog.getTotalCalories()) * 100);


    }

    public void logFood() {

        FoodLog fl = new FoodLog();

        fl.date = selectedDate.get();
        fl.logtype = types.get(selectedIndex.get());
        fl.foodId = selectedFood.get().id;
        fl.quantity = cantidad.get();

        userService.addFoodLog(userId, fl)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::onFoodLogCreated,
                        this::handleFoodLogError
                );

    }

    private void handleFoodLogError(Throwable throwable) {

    }

    private void onFoodLogCreated(FoodLog foodLog) {

        this.listener.onLoggedFood(foodLog.id);


    }


    public void cancelLog() {

        listener.onFoodLoggingCanceled();

    }

    public void onStart() {


        foodService.findById(foodId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::setFood, // ok
                        this::handleError, // error
                        this::updateUI // done
                );


    }

}
