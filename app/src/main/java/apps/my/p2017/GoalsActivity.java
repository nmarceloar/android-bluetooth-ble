package apps.my.p2017;

import android.app.Dialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.View;
import android.view.WindowManager;
import android.widget.SeekBar;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.Arrays;

import apps.my.p2017.databinding.ActivityGoalsActiityBinding;


public class GoalsActivity extends AppCompatActivity
        implements GoalsActivityListener {

    GoalsActivityViewModel viewModel;

    BottomSheetBehavior balanceBottomSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals_actiity);

        balanceBottomSheet = BottomSheetBehavior.from(findViewById(R.id.balanceBottomSheet));
        balanceBottomSheet.setState(BottomSheetBehavior.STATE_EXPANDED);

        ActivityGoalsActiityBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_goals_actiity);

        viewModel = new GoalsActivityViewModel();
        binding.setVm(viewModel);


//        macrosBottomSheet = BottomSheetBehavior.from(findViewById(R.id.macrosBottomSheet));
//        activityBottomSheet = BottomSheetBehavior.from(findViewById(R.id.activityBottomSheet));


//        macrosBottomSheet.setState(BottomSheetBehavior.STATE_HIDDEN);
//        activityBottomSheet.setState(BottomSheetBehavior.STATE_HIDDEN);


    }

    public void openBalance(View view) {


    }


    public void openHome(View view) {

        startActivity(new Intent(this, HomeActivity.class));

    }

}
