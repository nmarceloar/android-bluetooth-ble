package apps.my.p2017;

import android.content.Intent;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView;

import java.util.List;

import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;


class RxUtils {


    public static PublishSubject<String> fromView(SearchView searchView) {

        final PublishSubject<String> subject = PublishSubject.<String>create();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return true;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                subject.onNext(newText);
                return true;
            }
        });

        return subject;


    }
}

public class SearchExerciseActivity extends AppCompatActivity {

    private Disposable d;
    private CompositeDisposable disposables = new CompositeDisposable();

    ListView searchResults;
    private SearchView searchView;

    PublishSubject<String> queries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_exercise);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        searchResults = (ListView) findViewById(R.id.searchResults);
        ViewCompat.setNestedScrollingEnabled(searchResults, true);

        searchResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                PhysicalActivitiesAdapter adapter = (PhysicalActivitiesAdapter) adapterView.getAdapter();

                final Intent intent = new Intent(SearchExerciseActivity.this, EditOrRemoveExercise.class);
                intent.putExtra("activityId", adapter.getItem(i).id);

                startActivity(intent);


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_exercises, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchItem.getActionView();

        searchItem.expandActionView();

        d = RxSearchView.queryTextChanges(searchView)
                .skip(1)
                .map(new Function<CharSequence, String>() {
                    @Override
                    public String apply(CharSequence charSequence) throws Exception {
                        return charSequence.toString();
                    }
                })
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        return s.length() > 2;
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .switchMap(new Function<String, ObservableSource<List<PhysicalActivity>>>() {
                    @Override
                    public ObservableSource<List<PhysicalActivity>> apply(String charSequence) throws Exception {
                        return App.instance.activitiesService.findByQuery(charSequence);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<PhysicalActivity>>() {
                    @Override
                    public void accept(List<PhysicalActivity> physicalActivities) throws Exception {

                        searchResults.setAdapter(new PhysicalActivitiesAdapter(SearchExerciseActivity.this,
                                physicalActivities));

                        ViewCompat.setNestedScrollingEnabled(searchResults, true);


                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        Toast.makeText(SearchExerciseActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });


        disposables.add(d);


        return super.onCreateOptionsMenu(menu);

    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onStop() {
        super.onStop();
        disposables.dispose();
    }
}
