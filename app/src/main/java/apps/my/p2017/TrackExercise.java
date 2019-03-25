package apps.my.p2017;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TrackExercise extends AppCompatActivity implements MapViewFragment.OnFragmentInteractionListener, RealTimeStatisticsFragment.OnFragmentInteractionListener {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_exercise);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new RealAdapter(getSupportFragmentManager()));


    }

    @Override
    public void onFragmentInteraction(int position) {

        viewPager.setCurrentItem((position + 1) % 2, true);

    }

    class RealAdapter extends FragmentPagerAdapter {

        public RealAdapter(FragmentManager fm) {

            super(fm);

        }

        @Override
        public Fragment getItem(int position) {

            return position == 0 ? RealTimeStatisticsFragment.newInstance() : MapViewFragment.newInstance();

        }

        @Override
        public int getCount() {
            return 2;
        }
    }


}
