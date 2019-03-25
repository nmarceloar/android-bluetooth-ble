package apps.my.p2017;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import apps.my.p2017.databinding.ActivityMacroGoalsBinding;

public class MacroGoalsActivity extends AppCompatActivity {

    ActivityMacroGoalsBinding binding;
    MacroGoalsViewModel macroGoalsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        macroGoalsViewModel = new MacroGoalsViewModel();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_macro_goals);
        binding.setVm(macroGoalsViewModel);

    }

}
