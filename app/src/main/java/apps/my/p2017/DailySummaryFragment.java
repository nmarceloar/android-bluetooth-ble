package apps.my.p2017;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.icu.util.TimeUnit;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import apps.my.p2017.databinding.FragmentDailySummaryBinding;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class DailySummaryFragment extends Fragment {

    long date;
    private PieChart pieChart;
    private LineChart lineChart;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        FragmentDailySummaryBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_daily_summary, container, false);

        binding.setVm(new DailySummaryViewModel(date));

        View view = binding.getRoot();


        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pieChart = (PieChart) view.findViewById(R.id.pieChart);
        lineChart = (LineChart) view.findViewById(R.id.lineChart);

        PieDataSet dataSet = new PieDataSet(createPieEntries(), "");
        dataSet.setValueFormatter(new PercentFormatter());
        dataSet.setValueTextSize(12);
        dataSet.setValueTextColor(Color.WHITE);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.setData(new PieData(dataSet));
        pieChart.getDescription().setEnabled(false);
        pieChart.getLegend().setEnabled(false);


        LineDataSet lineDataSet = new LineDataSet(createLineEntries(), "");

        lineDataSet.setColors(new int[]{R.color.colorPrimary}, getContext());
        lineDataSet.setCircleColors(new int[]{R.color.colorPrimary}, getContext());
        lineDataSet.setCircleRadius(3);
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setDrawValues(false);

        lineChart.setData(new LineData(lineDataSet));
        lineChart.getAxisRight().setEnabled(false);

        lineChart.getLegend().setEnabled(false);
        lineChart.getDescription().setEnabled(false);

        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.getXAxis().setDrawGridLines(false);

        lineChart.getAxisLeft().setAxisMaximum(110);
        lineChart.getAxisLeft().setAxisMinimum(100);

        lineChart.getAxisLeft().setEnabled(false);


    }

    private List<Entry> createLineEntries() {
        return Arrays.asList(new Entry(0, 108),
                new Entry(1, 107),
                new Entry(2, 108),
                new Entry(3, 107),
                new Entry(4, 106),
                new Entry(5, 104),
                new Entry(6, 102)
        );
    }

    private List<PieEntry> createPieEntries() {
        return Arrays.asList(new PieEntry(20), new PieEntry(30), new PieEntry(50));
    }

    @Override
    public void onStart() {
        super.onStart();


    }


    public static DailySummaryFragment newInstance(long date) {

        DailySummaryFragment fragment = new DailySummaryFragment();

        Bundle args = new Bundle();
        args.putLong("date", date);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        date = getArguments().getLong("date", -1);

    }

}
