package apps.my.p2017;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieEntry;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;


public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {

    private PieChart pieChart;
    private LineChart lineChart;

    private ProgressBar progressBar;

    Calendar c1, c2;

    FloatingActionsMenu floatingActionsMenu;


    ViewPager viewPager;
    NavigationView navigationView;
    private DateFormat dateFormat;
    private long selectedDate;
    private DailySummaryPager adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        floatingActionsMenu = (FloatingActionsMenu) findViewById(R.id.floatingMenu);


        dateFormat = DateFormat.getDateInstance();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        c1 = new GregorianCalendar(2017, 0, 1);

        c2 = Calendar.getInstance();
        c2.setTimeInMillis(DateUtils.date());

        viewPager = (ViewPager) findViewById(R.id.viewPager);

        adapter = new DailySummaryPager(getSupportFragmentManager());

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);

        viewPager.setCurrentItem(viewPager.getAdapter().getCount());


    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {

            drawer.closeDrawer(GravityCompat.START);

        } else {

            super.onBackPressed();

        }

    }

    @Override
    protected void onStart() {

        super.onStart();

        navigationView.setCheckedItem(R.id.nav_camera);

        c2 = Calendar.getInstance();
        c2.setTimeInMillis(DateUtils.date());

        viewPager.getAdapter().notifyDataSetChanged();


//        this.pieChart.setData(new PieData(new PieDataSet(createEntries(), "Categoria")));
//
//        LineDataSet data = new LineDataSet(createLineEntries(), "data");
//        data.setDrawCircleHole(false);
//        data.setDrawCircles(true);
//        data.setCircleRadius(3);
//        data.setCircleColor(Color.parseColor("#3F51B5"));
//        data.setDrawValues(false);
//        data.setColor(Color.parseColor("#3F51B5"));
//
//        this.lineChart.setData(new LineData(Arrays.<ILineDataSet>asList(
//                data
//
//        )));
//
//
//        this.lineChart.getLegend().setEnabled(false);
//        this.lineChart.getDescription().setEnabled(false);
//
//        this.lineChart.getXAxis().setDrawAxisLine(true);
//        this.lineChart.getXAxis().setAxisLineWidth(1);
//        this.lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
//        this.lineChart.getXAxis().setAxisLineColor(Color.LTGRAY);
//
//        this.lineChart.getXAxis().setDrawGridLines(false);
//        this.lineChart.getXAxis().setDrawLabels(false);
//
//
//        this.lineChart.getAxisLeft().setDrawAxisLine(true);
//        this.lineChart.getAxisLeft().setDrawGridLines(false);
//        this.lineChart.getAxisLeft().setDrawLabels(false);
//        this.lineChart.getAxisLeft().setDrawZeroLine(true);
//        this.lineChart.getAxisLeft().setDrawTopYLabelEntry(true);
//
//        this.lineChart.getAxisRight().setDrawAxisLine(false);
//        this.lineChart.getAxisRight().setDrawGridLines(false);
//        this.lineChart.getAxisRight().setDrawLabels(false);
//

    }

    private List<Entry> createLineEntries() {

        final List<Entry> entries = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            entries.add(new Entry(
                    Float.valueOf(i),
                    Double.valueOf(100 + Math.random() * (108 - 100)).floatValue())
            );

        }

        return entries;

    }


    private List<PieEntry> createEntries() {

        return Arrays.asList(
                new PieEntry(20, "Proteinas"),
                new PieEntry(30, "Hidratos"),
                new PieEntry(50, "Grasas"));

    }


    public void trackFood(View view) {

        startActivity(new Intent(this, FoodLogsActivity.class));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_camera) {

            startActivity(new Intent(this, Main2Activity.class));


        } else if (id == R.id.nav_gallery) {

            startActivity(new Intent(this, YourDevicesActivity.class));

        } else if (id == R.id.nav_slideshow) {


        } else if (id == R.id.nav_manage) {

            startActivity(new Intent(this, WeighStatusActivity.class));

        } else if (id == R.id.nav_share) {


        } else if (id == R.id.goals) {

            startActivity(new Intent(this, ConfigActivity.class));

        } else if (id == R.id.nav_send) {


            App.instance.userService.logout()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Response<Void>>() {
                        @Override
                        public void accept(Response<Void> voidResponse) throws Exception {

                            getSharedPreferences("App", MODE_PRIVATE)
                                    .edit()
                                    .clear()
                                    .commit();

                            finish();


                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {

                            Toast.makeText(
                                    HomeActivity.this, throwable.getMessage(),
                                    Toast.LENGTH_SHORT).show();

                        }
                    });


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;

    }

    public void openLogWeight(View view) {


        floatingActionsMenu.collapse();

    }

    public void openLogFood(View view) {

        Intent intent = new Intent(this, FoodLogsActivity.class);
        intent.putExtra("date", selectedDate);

        startActivity(intent);

        floatingActionsMenu.collapse();


    }

    public void openLogActivity(View view) {

        Intent intent = new Intent(this, PickYourExercise.class);
        intent.putExtra("date", selectedDate);

        startActivity(intent);

        floatingActionsMenu.collapse();

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(c1.getTimeInMillis());
        c.add(Calendar.DATE, position);

        selectedDate = c.getTimeInMillis();

        getSupportActionBar().setTitle(dateFormat.format(new Date(c.getTimeInMillis())));


    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void openTrackExercise(View view) {

        startActivity(new Intent(this, RealTimeHeartRateActivity.class));
        floatingActionsMenu.collapse();

    }


    class DailySummaryPager extends FragmentStatePagerAdapter {

        public DailySummaryPager(FragmentManager fm) {

            super(fm);

        }

        @Override
        public Fragment getItem(int position) {

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(c1.getTimeInMillis());
            c.add(Calendar.DATE, position);

            return DailySummaryFragment.newInstance(c.getTimeInMillis());

        }

        @Override
        public int getCount() {

            return Long.valueOf(DateUtils.daysBetween(c1, c2) + 1).intValue();

        }

    }

}
