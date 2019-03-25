package apps.my.p2017;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import apps.my.p2017.databinding.ActivityDataBindingTutorialBinding;


interface DateSelector {

    public void openDateDialog();

}

interface TimeSelector {

    public void openTimeDialog();

}

public class DataBindingTutorial extends AppCompatActivity
        implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener
        , DateSelector, TimeSelector {

    MapView mapView;

    BindingViewModel bindingViewModel;

    private TimePickerDialog timePickerDialog;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindingViewModel = new BindingViewModel(this, this);

        ActivityDataBindingTutorialBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_data_binding_tutorial);
        binding.setVm(bindingViewModel);

        setSupportActionBar(binding.toolbar);


        timePickerDialog = new TimePickerDialog(this, this, 0, 0, true);
        datePickerDialog = new DatePickerDialog(this, this, 2017, 9, 1);


    }

    @Override
    protected void onStart() {

        super.onStart();
        bindingViewModel.onStart();

    }


    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

        bindingViewModel.setSelectedDate(i, i1, i2);


    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {

        bindingViewModel.setSelectedTime(i, i1);

    }


    @Override
    public void openDateDialog() {

        datePickerDialog.show();

    }

    @Override
    public void openTimeDialog() {

        timePickerDialog.show();

    }
}
