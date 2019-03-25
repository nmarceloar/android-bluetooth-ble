package apps.my.p2017;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;


public class RealTimeStatisticsFragment extends Fragment {

    OnFragmentInteractionListener mListener;
    Chronometer chronometer;

    public RealTimeStatisticsFragment() {
    }

    public static RealTimeStatisticsFragment newInstance() {
        RealTimeStatisticsFragment fragment = new RealTimeStatisticsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_real_time_statistics, container, false);

    }

    @Override
    public void onStop() {
        super.onStop();
        chronometer.stop();
    }

    @Override
    public void onResume() {

        super.onResume();
        chronometer.start();

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        chronometer = (Chronometer) view.findViewById(R.id.chrono);

        view.findViewById(R.id.fab).setOnClickListener(view1 -> mListener.onFragmentInteraction(0));


    }

    public void onButtonPressed(Uri uri) {

        if (mListener != null) {
            mListener.onFragmentInteraction(0);
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(int position);
    }

}
