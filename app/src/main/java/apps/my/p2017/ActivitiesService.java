package apps.my.p2017;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by standard on 10/3/2017.
 */

public interface ActivitiesService {

    @GET("/api/activities")
    public Observable<List<PhysicalActivity>> findAll();

    @GET("/api/searchActivities")
    public Observable<List<PhysicalActivity>> findByQuery(
            @Query("query") final String query
    );

    @GET("/api/activities/{id}")
    public Observable<PhysicalActivity> findById(@Path("id") String activityId);


    @PUT("/api/activities/{id}")
    public Observable<PhysicalActivity> updateActivity(@Path("id") String activityId,
                                                       @Body PhysicalActivity physicalActivity);

}
