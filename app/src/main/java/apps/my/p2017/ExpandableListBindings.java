package apps.my.p2017;

import android.databinding.BindingAdapter;
import android.widget.ExpandableListView;

import java.util.List;

import io.reactivex.Observable;


public class ExpandableListBindings {

    @BindingAdapter("app:groupedFoodLogs")
    public static void setGroups(ExpandableListView expandableListView, List<List<FoodLog>> groupedFoodLogs) {

        expandableListView.setAdapter(
                new FoodLogsAdapter(expandableListView.getContext(), groupedFoodLogs)
        );

    }


}
