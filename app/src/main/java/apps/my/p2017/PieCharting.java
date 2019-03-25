package apps.my.p2017;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.databinding.ObservableFloat;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import apps.my.p2017.databinding.ActivityPieChartingBinding;


public class PieCharting extends AppCompatActivity {


    private PieChartingViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityPieChartingBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_pie_charting);
        vm = new PieChartingViewModel();
        binding.setVm(vm);

    }

    @Override
    protected void onStart() {
        super.onStart();
        vm.onStart();

    }

    @Override
    protected void onStop() {
        vm.onStop();
        super.onStop();
    }
}
