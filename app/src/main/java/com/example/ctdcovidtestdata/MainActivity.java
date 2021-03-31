package com.example.ctdcovidtestdata;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText UserNameTx, PasswordTx;
    private String UserName, Password;

    user user = new user();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserNameTx = findViewById(R.id.UserName);
        PasswordTx = findViewById(R.id.Password);
    }

    public void Register(View view){
        Intent intent = new Intent(getApplicationContext(),RegisterCenterMenuActivity.class);
        startActivity(intent);
    }

    public void Login(View view){
        UserName = UserNameTx.getText().toString().trim();
        Password = PasswordTx.getText().toString().trim();

        user.login(getApplicationContext(), UserName, Password);
    }
}