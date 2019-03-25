package apps.my.p2017;

import android.content.Context;
import android.databinding.ObservableFloat;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by standard on 10/4/2017.
 */


class MSpinnerAdapter extends ArrayAdapter<FoodLogType> {

    public MSpinnerAdapter(@NonNull Context context, @NonNull List<FoodLogType> objects) {
        super(context, 0, objects);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null)
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.kilombo, parent, false);

        TextView textView = (TextView) convertView.findViewById(R.id.textView);
        textView.setText(getItem(position).toString());

        return convertView;

    }
}


public class DatePickerViewModel {

    public final ObservableInt intNumber = new ObservableInt();
    public final ObservableFloat floatNumber = new ObservableFloat();
    public final List<PieEntry> data = new ArrayList<>(Arrays.asList(new PieEntry(20, "asdasd"),
            new PieEntry(30, "asdasd"),
            new PieEntry(50, "asdasd")));

    public final List<FoodLogType> items =
            Arrays.asList(FoodLogType.values());


    public DatePickerViewModel() {

    }

}
