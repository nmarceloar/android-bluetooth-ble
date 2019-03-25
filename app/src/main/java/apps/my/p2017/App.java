package apps.my.p2017;

import android.app.Application;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    static App instance;

    UserService userService;
    FoodService foodService;
    UnitService unitService;

    ActivitiesService activitiesService;

    private Retrofit retrofit;

    public static final String BASE_URL = "https://p2017-181016.appspot.com";


    @Override
    public void onCreate() {

        super.onCreate();

        instance = this;


        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Interceptor.Chain chain) throws IOException {

                        String token = getSharedPreferences("App", MODE_PRIVATE)
                                .getString("token", null);

                        if (token == null)
                            return chain.proceed(chain.request());

                        return chain.proceed(chain.request().newBuilder()
                                .header("Authorization", token).build());

                    }
                }).build();


        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

        userService = retrofit.create(UserService.class);
        foodService = retrofit.create(FoodService.class);
        activitiesService = retrofit.create(ActivitiesService.class);
        unitService = retrofit.create(UnitService.class);

    }
}
