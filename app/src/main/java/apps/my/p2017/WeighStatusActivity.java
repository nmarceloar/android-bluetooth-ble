package apps.my.p2017;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WeighStatusActivity extends AppCompatActivity {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weigh_status);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new WeightAdapter(getSupportFragmentManager()));


    }


    class WeightAdapter extends FragmentPagerAdapter {

        public WeightAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return position == 0 ? WeightFragment.newInstance() : WeightFragment.newInstance();

        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
