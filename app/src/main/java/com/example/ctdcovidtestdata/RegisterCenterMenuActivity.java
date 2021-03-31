package com.example.ctdcovidtestdata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegisterCenterMenuActivity extends AppCompatActivity {
    private EditText userNameText, nameText, phoneText, addressText, emailText, passwordText;
    private String UserName,Name,Phone,Address,Email,Password;

    user user = new user();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_center_menu);

        userNameText    = findViewById(R.id.UserName);
        nameText        = findViewById(R.id.Name);
        phoneText       = findViewById(R.id.Phone);
        addressText     = findViewById(R.id.Address);
        emailText       = findViewById(R.id.Email);
        passwordText    = findViewById(R.id.Password);

    }
    public void Register(View view) {

    }
}