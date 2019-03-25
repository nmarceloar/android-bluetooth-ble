package apps.my.p2017;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import apps.my.p2017.databinding.ActivityEditOrRemoveExerciseBinding;

public class EditOrRemoveExercise extends AppCompatActivity {

    ActivityEditOrRemoveExerciseBinding binding;
    EditOrRemoveExerciseViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String id = getIntent().getStringExtra("activityId");

        viewModel = new EditOrRemoveExerciseViewModel(id);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_or_remove_exercise);
        binding.setVm(viewModel);

    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModel.onStart();

    }
}
