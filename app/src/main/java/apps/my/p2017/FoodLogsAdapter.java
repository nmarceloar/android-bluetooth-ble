package apps.my.p2017;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;

import java.util.List;

import apps.my.p2017.databinding.FoodLogGroupBinding;
import apps.my.p2017.databinding.FoodLogItemBinding;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class FoodLogsAdapter extends BaseExpandableListAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<List<FoodLog>> groups;

    public FoodLogsAdapter(Context context, List<List<FoodLog>> groupedFoodLogs) {

        this.context = context;
        groups = groupedFoodLogs;
        inflater = LayoutInflater.from(context);


    }


    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return groups.get(i).size();
    }

    @Override
    public Object getGroup(int i) {
        return null;
    }

    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {

        return i * (groups.size()) + i1;

    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        FoodLogGroupBinding binding = DataBindingUtil.getBinding(view);

        if (binding == null) {

            binding = DataBindingUtil.inflate(inflater, R.layout.food_log_group, viewGroup, false);

        }

        binding.setVm(FoodLogGroupViewModel.fromList(this.groups.get(i)));
        binding.executePendingBindings();

        return binding.getRoot();


    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        FoodLogItemBinding binding = DataBindingUtil.getBinding(view);

        if (binding == null) {

            binding = DataBindingUtil.inflate(inflater, R.layout.food_log_item, viewGroup, false);

        }

        binding.setVm(this.groups.get(i).get(i1));
        binding.executePendingBindings();

        return binding.getRoot();

    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

}
