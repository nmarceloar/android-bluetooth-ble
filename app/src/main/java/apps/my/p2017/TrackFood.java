package apps.my.p2017;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class TrackFood extends AppCompatActivity implements FoodSelectionListener, ViewPager.OnPageChangeListener {

    private FoodPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    FloatingActionButton fab;
    private int selectedPage;

    private long date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_food);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        date = getIntent().getLongExtra("date", 0);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        mSectionsPagerAdapter = new FoodPagerAdapter(getSupportFragmentManager(), this);

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(this);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_track_food, menu);

        return super.onCreateOptionsMenu(menu);

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


    public void logFood(View v) {

        if (selectedPage == 1)
            startActivity(new Intent(this, CreateFoodActivity.class));

        else if (selectedPage == 2)
            startActivity(new Intent(this, CreateFoodMenuActivity.class));


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1 && resultCode == RESULT_OK) {

            this.finish();

        }


    }

    @Override
    public void onFoodSelected(String foodId) {

        Intent intent = new Intent(this, LogFoodActivity.class);
        intent.putExtra("foodId", foodId);
        intent.putExtra("date", date);

        startActivityForResult(intent, 1);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        selectedPage = position;

        if (position == 0)
            fab.setVisibility(View.GONE);

        else
            fab.setVisibility(View.VISIBLE);


    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    public static class FoodAdapter extends ArrayAdapter<Food> {

        List<Food> foods;

        public FoodAdapter(@NonNull Context context, List<Food> foods) {
            super(context, 0, foods);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            if (convertView == null)
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.food_item, parent, false);

            Food item = getItem(position);

            TextView nombre = (TextView) convertView.findViewById(R.id.nombre);
            TextView marca = (TextView) convertView.findViewById(R.id.marca);
            TextView descripcion = (TextView) convertView.findViewById(R.id.descripcion);
            TextView unidadesYCalorias = (TextView) convertView.findViewById(R.id.unidadesYcalorias);

            nombre.setText(item.nombre);
            marca.setText(item.marca);
            descripcion.setText(item.descripcion);
            unidadesYCalorias.setText(
                    String.format("%s %s, %s Cal", item.cantidad, item.unit.name, item.calorias)
            );

            if (item.marca == null || item.marca.isEmpty())
                marca.setVisibility(View.GONE);

            if (item.descripcion == null || item.descripcion.isEmpty())
                marca.setVisibility(View.GONE);

            return convertView;

        }
    }

    public class FoodPagerAdapter extends FragmentStatePagerAdapter {

        private FoodSelectionListener listener;

        public FoodPagerAdapter(FragmentManager fm, FoodSelectionListener listener) {

            super(fm);
            this.listener = listener;

        }

        @Override
        public Fragment getItem(int position) {

            if (position == 0)
                return RecentFoodFragment.newInstance();
            else
                return MyFoodFragment.newInstance();

        }

        @Override
        public int getCount() {
            return 3;

        }
    }


}
