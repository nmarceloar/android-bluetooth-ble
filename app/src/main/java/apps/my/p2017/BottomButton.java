package apps.my.p2017;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Calendar;

public class BottomButton extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

    private AppCompatAutoCompleteTextView autoCompleteTextView;

    private AppCompatSpinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_button);

        autoCompleteTextView = (AppCompatAutoCompleteTextView) findViewById(R.id.autoComplete);
        spinner = (AppCompatSpinner) findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                Arrays.asList("Marcelo", "Maximiliano", "Nelson", "Termo", "Alcohol")
        );
        autoCompleteTextView.setAdapter(adapter);
        spinner.setAdapter(adapter);

        autoCompleteTextView.setOnItemClickListener(this);
        spinner.setOnItemSelectedListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Toast.makeText(this, String.format("%d %d", i, l), Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        Toast.makeText(this, String.format("%d %d", i, l), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

        Toast.makeText(this, String.format("%s", "Nothing"), Toast.LENGTH_SHORT).show();

    }


}
