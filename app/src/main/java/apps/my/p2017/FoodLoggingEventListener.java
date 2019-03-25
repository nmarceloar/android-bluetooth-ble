package apps.my.p2017;


public interface FoodLoggingEventListener {

    public void onLoggedFood(String foodLogId);

    public void onFoodLoggingCanceled();

}
