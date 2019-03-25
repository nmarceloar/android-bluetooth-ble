package apps.my.p2017;

import android.net.wifi.hotspot2.pps.Credential;

import java.util.List;
import java.util.Observable;

import io.reactivex.ObservableSource;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by standard on 9/28/2017.
 */


public interface UserService {

    @GET("/api/food")
    public io.reactivex.Observable<List<Food>> allFood();

    @POST("/api/musers/login")
    public io.reactivex.Observable<LoginData> login(@Body Credentials credentials);

    @POST("/api/musers/{userId}/food")
    public io.reactivex.Observable<Food> createFood(@Path("userId") String userId, @Body Food food);

    @GET("/api/food/searchQuery")
    public io.reactivex.Observable<List<Food>> searchFood(
            @Query("userId") String userId,
            @Query("query") String query);


    @GET("/api/musers/{userId}/food")
    public io.reactivex.Observable<List<Food>> food(@Path("userId") String userId);


    @POST("/api/musers/logout")
    public io.reactivex.Observable<Response<Void>> logout();

    @POST("/api/musers/{id}/foodlogs")
    public io.reactivex.Observable<FoodLog> addFoodLog(
            @Path("id") String userId,
            @Body FoodLog foodLog
    );

    @GET("/api/musers/{userId}/foodlogs")
    public io.reactivex.Observable<List<FoodLog>> allFooLogs(
            @Path("userId") String userId);


    @POST("/api/musers/{userId}/foodlogs/{foodLogId}/pairs")
    public io.reactivex.Observable<FoodLogPair> addFoodLogPair(
            @Path("userId") String userId,
            @Path("foodLogId") String foodLogId,
            @Body FoodLogPair foodLogPair
    );

    @GET("/api/musers/{userId}/recentActivities")
    public io.reactivex.Observable<List<PhysicalActivity>> recentActivities(
            @Path("userId") String userId
    );

    @GET("/api/musers/{userId}/foodLogs")
    public io.reactivex.Observable<List<FoodLog>> foodLogsByDate(
            @Path("userId") String userId,
            @Query("filter[where][date]") Long date);

}
