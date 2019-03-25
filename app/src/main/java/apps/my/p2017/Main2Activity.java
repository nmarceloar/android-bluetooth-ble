package apps.my.p2017;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import apps.my.p2017.databinding.ActivityMain2Binding;

public class Main2Activity extends AppCompatActivity {

    ActivityMain2Binding binding;
    Main2ViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        vm = new Main2ViewModel();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main2);
        binding.setVm(vm);


    }

    @Override
    protected void onStart() {
        super.onStart();
        vm.onStart();
    }

}
