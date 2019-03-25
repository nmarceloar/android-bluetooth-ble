package apps.my.p2017;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface FoodService {

    @GET("/api/food/{id}")
    public Observable<Food> findById(@Path("id") String id);
}

