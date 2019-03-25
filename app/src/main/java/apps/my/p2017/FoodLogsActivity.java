package apps.my.p2017;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import apps.my.p2017.databinding.ActivityFoodLogsBinding;


public class FoodLogsActivity extends AppCompatActivity {

    ActivityFoodLogsBinding binding;
    FoodLogsViewModel viewModel;

    ExpandableListView expandableListView;

    long date;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        userID = getSharedPreferences("App", MODE_PRIVATE)
                .getString("userId", null);

        date = getIntent().getLongExtra("date", 0);

        viewModel = new FoodLogsViewModel(userID, date);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_food_logs);
        binding.setVm(viewModel);

        expandableListView = binding.foodLogs;
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {

                Intent intent = new Intent(FoodLogsActivity.this, LogFoodActivity.class);
                intent.putExtra("edit", true);
                startActivity(intent);

                return true;

            }
        });

        Intent intent = new Intent(this, TrackFood.class);
        intent.putExtra("date", date);
        startActivity(intent);


    }

    @Override
    protected void onStart() {

        super.onStart();
        viewModel.onStart();


    }
}
