package apps.my.p2017;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class ProfileActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    DatePickerDialog datePickerDialog;


    TextView dob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        dob = (TextView) findViewById(R.id.dob);

        datePickerDialog = new DatePickerDialog(this, this, 2017, 3, 2);


    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {


        DateFormat d = new SimpleDateFormat("dd/MM/yyyy");

        dob.setText(d.format(new GregorianCalendar(i, i1, i2).getTime()));


    }

    public void openDatePicker(View view) {

        datePickerDialog.show();


    }

    public void openGoals(View view) {

        startActivity(new Intent(this, ConfigActivity.class));

    }
}
