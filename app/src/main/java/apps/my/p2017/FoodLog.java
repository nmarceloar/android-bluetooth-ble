package apps.my.p2017;

import java.util.List;


public class FoodLog {

    public String id;

    public Long date;
    public FoodLogType logtype;
    public String mUserId;
    public Food food;
    public Float quantity;

    public String foodId;


    public float getTotalCalories() {

        return this.food.calorias * (this.quantity / this.food.cantidad);

    }

    public float getTotalProtein() {

        return this.food.proteinas * (this.quantity / this.food.cantidad);

    }

    public float getTotalCarbs() {

        return this.food.carbo * (this.quantity / this.food.cantidad);

    }

    public float getTotalFat() {

        return this.food.grasas * (this.quantity / this.food.cantidad);

    }

    public void setQuantity(float quantity) {

        this.quantity = quantity;

    }


}
