package apps.my.p2017;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class MyFoodFragment extends ListFragment {

    private FoodSelectionListener listener;

    private ListView listView;
    private Disposable d;

    public static MyFoodFragment newInstance() {

        final MyFoodFragment fragment = new MyFoodFragment();
        final Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_myfood, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        listView = (ListView) view.findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                TrackFood.FoodAdapter adapter = (TrackFood.FoodAdapter) adapterView.getAdapter();

                listener.onFoodSelected(adapter.getItem(i).id);

            }
        });

    }

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);

        final Fragment parent = getParentFragment();

        if (parent != null) {
            listener = (FoodSelectionListener) parent;
        } else {
            listener = (FoodSelectionListener) context;
        }
    }

    @Override
    public void onDetach() {
        listener = null;
        super.onDetach();
    }

    @Override
    public void onStop() {

        if (d != null)
            d.dispose();

        super.onStop();

    }

    @Override
    public void onStart() {

        String userId = getContext().getSharedPreferences("App", Context.MODE_PRIVATE)
                .getString("userId", null);

        super.onStart();

        d = App.instance.userService.food(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        food -> listView.setAdapter(new TrackFood.FoodAdapter(getActivity(), food)),
                        throwable -> Toast.makeText(getActivity(), throwable.getMessage(), Toast.LENGTH_SHORT).show()
                );


    }
}
