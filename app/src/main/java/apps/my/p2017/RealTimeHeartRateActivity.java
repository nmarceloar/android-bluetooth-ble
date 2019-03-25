package apps.my.p2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RealTimeHeartRateActivity extends AppCompatActivity {

    LineChart lineChart;

    private Disposable d;

    private LineData lineData;
    private LineDataSet lineDataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_time_heart_rate);

        lineChart = (LineChart) findViewById(R.id.lineChart);

        lineDataSet = new LineDataSet(new ArrayList<>(), "");
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineData = new LineData(lineDataSet);

        lineChart.setData(lineData);

        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.getAxisRight().setDrawGridLines(false);


    }

    @Override
    protected void onResume() {
        super.onResume();


        d = io.reactivex.Observable.intervalRange(0, 100, 0, 1, TimeUnit.SECONDS)
                .map(idx -> new Entry(idx, Randoms.randomFloatBetween(102, 106)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(entry -> {

                    lineDataSet.addEntry(entry);
                    lineData.notifyDataChanged();
                    lineChart.notifyDataSetChanged();

                    lineChart.setVisibleXRangeMaximum(20);
                    lineChart.moveViewToX(lineDataSet.getEntryCount());

                });


    }

    @Override
    protected void onPause() {

        d.dispose();
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
