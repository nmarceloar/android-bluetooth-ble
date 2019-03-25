package apps.my.p2017;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface UnitService {

    @GET("/api/units")
    public Observable<List<Unit>> findAll();


}
