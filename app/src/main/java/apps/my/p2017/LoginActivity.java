package apps.my.p2017;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    UserService userService;


    AppCompatButton button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        // dependencies

        userService = App.instance.userService;


        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login();

            }
        });


    }

    public void login() {

        String username = ((TextInputEditText) findViewById(R.id.username)).getText().toString();
        String password = ((TextInputEditText) findViewById(R.id.password)).getText().toString();

        System.out.println(username + " " + password);

        Credentials credentials = new Credentials(username, password);


        userService.login(credentials)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginData>() {
                    @Override
                    public void accept(LoginData loginData) throws Exception {

                        getSharedPreferences("App", MODE_PRIVATE)
                                .edit()
                                .putString("token", loginData.id)
                                .putString("userId", loginData.userId)
                                .commit();

                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));

                        finish();


                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        Toast.makeText(LoginActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT)
                                .show();

                    }
                });


    }

    public void register(View view) {

        startActivity(new Intent(this, RegisterActivity.class));

    }
}
