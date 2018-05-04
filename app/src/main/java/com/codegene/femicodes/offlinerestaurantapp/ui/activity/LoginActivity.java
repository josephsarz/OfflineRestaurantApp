package com.codegene.femicodes.offlinerestaurantapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.codegene.femicodes.offlinerestaurantapp.R;
import com.codegene.femicodes.offlinerestaurantapp.db.AppDatabase;
import com.codegene.femicodes.offlinerestaurantapp.model.User;
import com.codegene.femicodes.offlinerestaurantapp.util.CommonUtil;

import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.et_login_username)
    EditText mLoginUsername;
    @BindView(R.id.et_login_password)
    EditText mLoginPassword;
    @BindView(R.id.tv_login_error)
    TextView mLoginError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Login");
    }

    @OnClick(R.id.btn_login_submit)
    public void LoginUser() {

        if(CommonUtil.validate(mLoginUsername) && CommonUtil.validate(mLoginPassword)) {

            String username = mLoginUsername.getText().toString();
            String password = mLoginPassword.getText().toString();

            Executors.newSingleThreadExecutor().execute(() -> {
                try {
                    Log.d("status", "trying");
                    User user = AppDatabase.getDatabase(getApplicationContext()).mUserDao().getUserInfo(username);
                    runOnUiThread(() -> {
                        if (user == null) {
                            mLoginError.setText("username doesnt exists");

                        } else {
                            if (user.getPassword().equals(password)) {
                                mLoginError.setTextColor(getResources().getColor(R.color.colorPrimary));
                                mLoginError.setText("user exists");
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();

                            } else {
                                mLoginError.setText("incorrect password");

                            }
                        }
                    });
                } catch (Exception e) {
                    Log.d("status: ", e.getMessage());
                    runOnUiThread(() -> {
                        mLoginError.setTextColor(getResources().getColor(R.color.colorAccent));
                        mLoginError.setText(e.getMessage());
                    });

                }

            });
        }
    }

    @OnClick(R.id.tv_goto_register)
    public void OpenRegister(){
        startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
    }



}
