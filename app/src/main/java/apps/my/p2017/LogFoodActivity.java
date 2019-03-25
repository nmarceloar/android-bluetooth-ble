package apps.my.p2017;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableFloat;
import android.databinding.ObservableInt;
import android.provider.ContactsContract;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import apps.my.p2017.databinding.ActivityLogFoodBinding;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class LogFoodActivity extends AppCompatActivity implements FoodLoggingEventListener {

    ActivityLogFoodBinding binding;
    LogFoodViewModel logFoodViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        long date = getIntent().getLongExtra("date", 0);
        String foodId = getIntent().getStringExtra("foodId");
        String userId = getSharedPreferences("App", MODE_PRIVATE)
                .getString("userId", null);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_log_food);
        logFoodViewModel = new LogFoodViewModel(date, foodId, userId, this);
        binding.setVm(logFoodViewModel);

        setSupportActionBar(binding.toolbar);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.logfood_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        logFoodViewModel.logFood();
        return true;


    }

    @Override
    public void onBackPressed() {

        logFoodViewModel.cancelLog();


    }

    @Override
    public void onLoggedFood(String foodLogId) {

        Toast.makeText(this, "" + foodLogId, Toast.LENGTH_SHORT).show();

        setResult(RESULT_OK);
        finish();

    }

    @Override
    public void onFoodLoggingCanceled() {

        setResult(RESULT_CANCELED);
        finish();

    }

    @Override
    protected void onStart() {
        super.onStart();
        logFoodViewModel.onStart();
    }
}
