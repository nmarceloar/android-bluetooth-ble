package apps.my.p2017;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import apps.my.p2017.databinding.ActivityBullshitBinding;

public class Bullshit extends AppCompatActivity {

    ActivityBullshitBinding binding;
    BullshitViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_bullshit);
        binding.setVm(new BullshitViewModel());

    }

}
