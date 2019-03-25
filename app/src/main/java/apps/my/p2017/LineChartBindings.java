package apps.my.p2017;

import android.databinding.BindingAdapter;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.List;

public class LineChartBindings {

    @BindingAdapter("app:enableXAxis")
    public static void enableXAxis(LineChart lineChart, boolean enable) {

        lineChart.getXAxis().setEnabled(enable);
        lineChart.invalidate();

    }

    @BindingAdapter("app:entries")
    public static void enableXAxis(LineChart lineChart, List<Entry> entries) {

        lineChart.setData(new LineData(new LineDataSet(entries, "")));
        lineChart.moveViewToX(lineChart.getData().getDataSetByIndex(0).getEntryCount());

    }


}
