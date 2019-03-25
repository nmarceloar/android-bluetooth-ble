package apps.my.p2017;

import java.util.List;

/**
 * Created by standard on 9/28/2017.
 */

public class FoodLogPair {

    String id;

    String foodLogId;
    String foodId;

    Float cantidad;

    Food food;

    public float getTotalCalories() {
        return cantidad * (food.calorias / food.cantidad);
    }

    public float getTotalProtein() {

        return cantidad * (food.proteinas / food.cantidad);

    }

    public float getTotalCarbs() {

        return cantidad * (food.carbo / food.cantidad);

    }

    public float getTotalFat() {

        return cantidad * (food.grasas / food.cantidad);

    }


}
