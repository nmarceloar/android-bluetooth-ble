package apps.my.p2017;

import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView;

import org.reactivestreams.Subscription;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.subscriptions.ArrayCompositeSubscription;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;


class RxSearch {

    public static Observable<String> fromSearchView(final SearchView searchView) {

        final BehaviorSubject<String> subject = BehaviorSubject.create();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                subject.onComplete();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!newText.isEmpty()) {
                    subject.onNext(newText);
                }
                return true;
            }
        });

        return subject;

    }
}

public class FoodSearch extends AppCompatActivity {

    ListView foodList;

    SearchView searchView;
    MenuItem searchItem;

    String userId;

    CompositeDisposable disposable = new CompositeDisposable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_search);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        userId = getSharedPreferences("App", MODE_PRIVATE)
                .getString("userId", null);

        foodList = findViewById(R.id.foodList);

    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onDestroy() {

        disposable.dispose();
        super.onDestroy();
    }

    private Disposable subscribeToSearchViewEvents() {

        return RxSearch.fromSearchView(searchView)
                .debounce(200, TimeUnit.MILLISECONDS)
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        return !s.isEmpty();
                    }
                })
                .switchMap(new Function<String, ObservableSource<List<Food>>>() {
                    @Override
                    public ObservableSource<List<Food>> apply(String s) throws Exception {

                        return App.instance.userService.searchFood(userId, s);

                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Food>>() {
                    @Override
                    public void accept(List<Food> foods) throws Exception {

                        foodList.setAdapter(new TrackFood.FoodAdapter(FoodSearch.this, foods));

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        Toast.makeText(FoodSearch.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }

    @Override
    protected void onStop() {
        disposable.dispose();
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_food, menu);

        searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchItem.expandActionView();
        searchView.requestFocus();

        disposable.add(subscribeToSearchViewEvents());

        return super.onCreateOptionsMenu(menu);

    }

}
