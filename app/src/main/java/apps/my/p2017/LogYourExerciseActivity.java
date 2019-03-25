package apps.my.p2017;

import android.app.TimePickerDialog;
import android.databinding.DataBindingUtil;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.TimePicker;

import apps.my.p2017.databinding.ActivityLogYourExerciseBinding;

public class LogYourExerciseActivity extends AppCompatActivity implements TimeSelector, TimePickerDialog.OnTimeSetListener {

    TimePickerDialog timePickerDialog;

    LogYourExerciseViewModel viewModel;
    ActivityLogYourExerciseBinding binding;

    private String activityId;
    private long selectedDate;
    private TimePickerDialog startPicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startPicker = new TimePickerDialog(this, this, 0, 0, true);

        activityId = getIntent().getStringExtra("activityId");
        selectedDate = getIntent().getLongExtra("date", 0);

        if (activityId == null)
            throw new RuntimeException("ActivityId No puede ser null");

        binding = DataBindingUtil.setContentView(this, R.layout.activity_log_your_exercise);
        viewModel = new LogYourExerciseViewModel(activityId, selectedDate, this);
        binding.setVm(viewModel);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    protected void onStart() {
        super.onStart();

        viewModel.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_log_your_exercise, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void openTimeDialog() {


        startPicker.show();


    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {

        viewModel.onTimeSelected(hour, minute);


    }


}
