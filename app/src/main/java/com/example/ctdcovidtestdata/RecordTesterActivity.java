package com.example.ctdcovidtestdata;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RecordTesterActivity extends AppCompatActivity {

    private EditText UserNameText, NameText, PhoneText, AddressText, PasswordText;
    private String UserName, Name, Phone, Address, Password;
    private String TestCentreID = com.example.ctdcovidtestdata.user.testCentreID;

    user user = new user();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_tester);

        UserNameText        = findViewById(R.id.UserName);
        NameText            = findViewById(R.id.Name);
        PhoneText           = findViewById(R.id.Phone2);
        AddressText         = findViewById(R.id.Address);
        PasswordText        = findViewById(R.id.Password);
    }

    public void RecordTester(View view) {
        UserName = UserNameText.getText().toString().trim();
        Name = NameText.getText().toString().trim();
        Phone = PhoneText.getText().toString().trim();
        Address = AddressText.getText().toString().trim();
        Password = PasswordText.getText().toString().trim();

        user.recordTester(getApplicationContext(), Name, TestCentreID,Phone, Address, UserName, Password);
    }

    public void BackMenu(View view) {
        Intent intent = new Intent(getApplicationContext(), TestCenterManagerMenuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}