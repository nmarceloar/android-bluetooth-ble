package apps.my.p2017;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

public class AlwaysExpandedListView extends ExpandableListView {

    public AlwaysExpandedListView(Context context) {
        super(context);
    }

    public AlwaysExpandedListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AlwaysExpandedListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AlwaysExpandedListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void setAdapter(ExpandableListAdapter adapter) {
        super.setAdapter(adapter);

        this.expandGroups();
        this.setOnGroupClickListener((expandableListView, view, i, l) -> true);

    }

    private void expandGroups() {

        for (int i = 0; i < this.getExpandableListAdapter().getGroupCount(); i++) {
            this.expandGroup(i);
        }
    }
}
