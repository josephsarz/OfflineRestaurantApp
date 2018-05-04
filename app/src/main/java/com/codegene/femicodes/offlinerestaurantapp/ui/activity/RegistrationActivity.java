package com.codegene.femicodes.offlinerestaurantapp.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codegene.femicodes.offlinerestaurantapp.R;
import com.codegene.femicodes.offlinerestaurantapp.db.AppDatabase;
import com.codegene.femicodes.offlinerestaurantapp.model.User;
import com.codegene.femicodes.offlinerestaurantapp.util.CommonUtil;

import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrationActivity extends AppCompatActivity {

    @BindView(R.id.et_register_email)
    EditText mRegisterEmail;
    @BindView(R.id.et_register_username)
    EditText mRegisterUsername;
    @BindView(R.id.et_register_address)
    EditText mRegisterAddress;
    @BindView(R.id.et_register_phone)
    EditText mRegisterPhone;
    @BindView(R.id.et_register_password)
    EditText mRegisterPassword;
    @BindView(R.id.tv_register_error)
    TextView mRegisterError;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Register");

    }


    @OnClick(R.id.btn_register_submit)
    public void createUser(){

        if(CommonUtil.validate(mRegisterUsername) && CommonUtil.validate(mRegisterEmail) &&
                CommonUtil.validate(mRegisterPassword) && CommonUtil.validate(mRegisterAddress) && CommonUtil.validate(mRegisterPhone)) {
            String username = mRegisterUsername.getText().toString();
            String email = mRegisterEmail.getText().toString();
            String password = mRegisterPassword.getText().toString();
            String address = mRegisterAddress.getText().toString();
            String phone = mRegisterPhone.getText().toString();

            user = new User(username, password, email, address, phone);

            Executors.newSingleThreadExecutor().execute(() -> {
                try {
                    AppDatabase.getDatabase(getApplicationContext()).mUserDao().addUser(user);
                    user = AppDatabase.getDatabase(getApplicationContext()).mUserDao().getUserInfo(username);
                    runOnUiThread(() -> {
                        if (user == null) {
                            Toast.makeText(this, "something went wrong!", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                            finish();
                        }
                    });
                }catch (Exception e){
                    Log.d("status: ", e.getMessage());
                    runOnUiThread(() -> {
                        mRegisterError.setTextColor(getResources().getColor(R.color.colorAccent));
                        mRegisterError.setText("Email or username already exists");
                    });

                }

            });

        }

    }

    @OnClick(R.id.tv_move_to_login)
    public void OpenLogin(){
        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
    }


}
