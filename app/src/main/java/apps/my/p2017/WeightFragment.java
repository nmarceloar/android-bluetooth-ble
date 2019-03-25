package apps.my.p2017;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.ChartHighlighter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class WeightFragment extends Fragment {

    LineChart lineChart;

    List<Entry> weightLogs;

    public WeightFragment() {
        // Required empty public constructor
    }

    public static WeightFragment newInstance() {
        WeightFragment fragment = new WeightFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weight, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lineChart = view.findViewById(R.id.lineChart);

        weightLogs = createEntries();

        LineDataSet lineDataSet = new LineDataSet(weightLogs, "");

        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setCircleRadius(3f);

        lineDataSet.setCircleColors(new int[]{R.color.colorPrimary}, getContext());

        lineDataSet.setDrawVerticalHighlightIndicator(true);
        lineDataSet.setDrawHorizontalHighlightIndicator(false);

        lineDataSet.setColors(new int[]{R.color.colorPrimary}, getContext());

        lineDataSet.setDrawValues(false);

        lineChart.setData(new LineData(lineDataSet));

        lineChart.getXAxis().setEnabled(true);
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.getXAxis().setDrawLabels(true);
        lineChart.getXAxis().setDrawAxisLine(true);
        lineChart.getXAxis().setDrawGridLines(false);


        lineChart.setVisibleXRangeMaximum(7);


        lineChart.getAxisLeft().setDrawGridLines(true);
        lineChart.getAxisLeft().setDrawAxisLine(false);
        lineChart.getAxisLeft().setDrawLabels(true);
        lineChart.getAxisLeft().setGranularity(2);
        lineChart.getAxisLeft().setAxisMaximum(108);
        lineChart.getAxisLeft().setAxisMinimum(102);


        lineChart.getAxisRight().setEnabled(false);

        lineChart.setPadding(30, 30, 30, 30);


        lineChart.getLegend().setEnabled(false);
        lineChart.getDescription().setEnabled(false);


        lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {


            }

            @Override
            public void onNothingSelected() {

            }
        });


    }

    private List<Entry> createEntries() {

        final List<Entry> entries = new ArrayList<>();

        for (int i = 0; i < 28; i++) {

            entries.add(new Entry(i, Randoms.randomFloatBetween(102, 108)));

        }

        return entries;


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}

