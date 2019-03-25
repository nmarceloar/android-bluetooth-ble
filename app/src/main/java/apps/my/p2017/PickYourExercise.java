package apps.my.p2017;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PickYourExercise extends AppCompatActivity implements ExerciseSelectedListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    long selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_your_exercise);

        selectedDate = getIntent().getLongExtra("date", 0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(PickYourExercise.this, SearchExerciseActivity.class));
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pick_your_exercise, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onExerciseSelected(String exerciseId) {

        Intent i = new Intent(this, LogYourExerciseActivity.class);
        i.putExtra("activityId", exerciseId);
        i.putExtra("date", selectedDate);

        startActivity(i);


    }

    public static class AllExercisesFragment extends Fragment {

        ListView lv;

        private Disposable d;
        private CompositeDisposable disposables = new CompositeDisposable();
        private String userId;

        private ExerciseSelectedListener listener;


        public AllExercisesFragment() {
        }

        public static AllExercisesFragment newInstance() {

            AllExercisesFragment fragment = new AllExercisesFragment();

            Bundle args = new Bundle();
            fragment.setArguments(args);

            return fragment;

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {


            return inflater.inflate(R.layout.fragment_pick_your_exercise, container, false);

        }


        @Override
        public void onAttach(Context activity) {
            super.onAttach(activity);

            listener = (ExerciseSelectedListener) activity;

        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            lv = (ListView) view.findViewById(R.id.listView);

            userId = getActivity().getSharedPreferences("App", MODE_PRIVATE)
                    .getString("userId", null);


        }

        @Override
        public void onStop() {
            if (disposables != null)
                disposables.dispose();
            super.onStop();
        }

        @Override
        public void onStart() {
            super.onStart();

            d = App.instance.activitiesService.findAll()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<List<PhysicalActivity>>() {
                        @Override
                        public void accept(List<PhysicalActivity> physicalActivities) throws Exception {

                            lv.setAdapter(new PhysicalActivitiesAdapter(getActivity(), physicalActivities));
                            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                    PhysicalActivitiesAdapter adapter =
                                            (PhysicalActivitiesAdapter) lv.getAdapter();

                                    listener.onExerciseSelected(adapter.getItem(i).id);

                                }
                            });

                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {

                            handleError(throwable);

                        }
                    });

            disposables.add(d);


        }

        private void handleError(Throwable throwable) {

            Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return AllExercisesFragment.newInstance();

        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
