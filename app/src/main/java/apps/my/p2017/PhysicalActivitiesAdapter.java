package apps.my.p2017;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

class PhysicalActivitiesAdapter extends ArrayAdapter<PhysicalActivity> {

    public PhysicalActivitiesAdapter(@NonNull Context context, @NonNull List<PhysicalActivity> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.physical_activity_item, parent, false);

        PhysicalActivity activity = getItem(position);

        TextView especifica = (TextView) convertView.findViewById(R.id.especifica);
        TextView categoria = (TextView) convertView.findViewById(R.id.categoria);
        ImageView icon = (ImageView) convertView.findViewById(R.id.icon);

        especifica.setText(activity.especifica);
        categoria.setText(activity.categoria);

        String mDrawableName = activity.icon;

        if (mDrawableName != null) {

            int resID = getContext().getResources().getIdentifier(mDrawableName, "drawable",
                    getContext().getPackageName());
            if (resID != 0)
                icon.setImageResource(resID);
            else
                icon.setImageResource(R.drawable.runner);


        } else {

            icon.setImageResource(R.drawable.runner);

        }

        return convertView;


    }
}
