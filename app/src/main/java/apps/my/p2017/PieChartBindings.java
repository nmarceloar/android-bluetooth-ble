package apps.my.p2017;


import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.Arrays;

public class PieChartBindings {

    @BindingAdapter(value = {"app:proteinas", "app:hidratos", "app:grasas", "app:total"}, requireAll = false)
    public static void setComposicion(PieChart pieChart,
                                      float proteinas, float hidratos, float grasas, float total) {

        pieChart.setCenterText(String.format("%.1f%n%s", total, "Calorías"));
        pieChart.setDrawCenterText(true);
        pieChart.setCenterTextTypeface(Typeface.DEFAULT_BOLD);
        pieChart.setCenterTextSize(16);

        pieChart.setEntryLabelTypeface(Typeface.DEFAULT_BOLD);

        PieDataSet dataSet = new PieDataSet(
                Arrays.asList(
                        new PieEntry(proteinas),
                        new PieEntry(hidratos),
                        new PieEntry(grasas)), "");

        dataSet.setValueLineColor(Color.BLACK);
        dataSet.setValueLineWidth(1f);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        dataSet.setValueTypeface(Typeface.DEFAULT_BOLD);

        dataSet.setValueFormatter(new PercentFormatter());

        dataSet.setColors(new int[]{R.color.protein, R.color.carbs, R.color.fat}, pieChart.getContext());

        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(14);
        pieChart.setData(
                new PieData(dataSet)
        );


        pieChart.setDrawEntryLabels(false);
        pieChart.setEntryLabelColor(R.color.colorPrimary);
        pieChart.setEntryLabelTextSize(14);
        pieChart.setUsePercentValues(false);


        pieChart.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        pieChart.getDescription().setEnabled(false);


        pieChart.getLegend().setEnabled(false);
        pieChart.getLegend().setTextSize(16);
        pieChart.getLegend().setOrientation(Legend.LegendOrientation.VERTICAL);
        pieChart.getLegend().setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        pieChart.getLegend().setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        pieChart.getLegend().setFormToTextSpace(10);
        pieChart.getLegend().setForm(Legend.LegendForm.SQUARE);
        pieChart.getLegend().setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);
        pieChart.getLegend().setStackSpace(50);
        pieChart.getLegend().setXEntrySpace(15);
        pieChart.getLegend().setYEntrySpace(15);


        pieChart.invalidate();


    }


}
