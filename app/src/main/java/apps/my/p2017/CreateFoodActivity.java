package apps.my.p2017;

import android.databinding.DataBindingUtil;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import apps.my.p2017.databinding.ActivityCreateFoodBinding;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CreateFoodActivity extends AppCompatActivity {

    private CreateFoodViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityCreateFoodBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_create_food);

        viewModel = new CreateFoodViewModel();
        binding.setVm(viewModel);

        setSupportActionBar(binding.toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_create_food, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        viewModel.createFood();
        return true;

    }


}
