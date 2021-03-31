package com.example.ctdcovidtestdata;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterCenterMenuActivity extends AppCompatActivity {
    private EditText UserNameText, NameText, EmailText, PhoneText, AddressText, PasswordText;
    private String UserName, Name, Email, Phone, Address, Password;

    user user = new user();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_center_menu);

        UserNameText        = findViewById(R.id.UserName);
        NameText            = findViewById(R.id.Name);
        EmailText           = findViewById(R.id.Email);
        PhoneText           = findViewById(R.id.Phone);
        AddressText         = findViewById(R.id.Address);
        PasswordText        = findViewById(R.id.Password);
    }
}