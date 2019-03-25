package apps.my.p2017;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


class LogsAdapter extends BaseExpandableListAdapter {

    private Context context;


    public LogsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getGroupCount() {

        return 4;

    }

    @Override
    public int getChildrenCount(int i) {

        return 3;

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
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        if (view == null)
            view = LayoutInflater.from(context).inflate(R.layout.group_view, viewGroup, false);

        return view;

    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        if (view == null)
            view = LayoutInflater.from(context).inflate(R.layout.item_view, viewGroup, false);

        return view;

    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}

public class ExpandableTest extends AppCompatActivity {

    LogsAdapter logsAdapter;
    ExpandableListView expandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_test);

        expandableListView = (ExpandableListView) findViewById(R.id.logs);
        expandableListView.setAdapter(new LogsAdapter(this));


    }


}
