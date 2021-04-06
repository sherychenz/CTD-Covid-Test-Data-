package com.example.ctdcovidtestdata;

import android.os.Bundle;
import android.view.View;
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


    public void Register(View view) {
        UserName = UserNameText.getText().toString().trim();
        Name = NameText.getText().toString().trim();
        Email = EmailText.getText().toString().trim();
        Phone = PhoneText.getText().toString().trim();
        Address = AddressText.getText().toString().trim();
        Password = PasswordText.getText().toString().trim();


        if (UserName.isEmpty() && Name.isEmpty() && Email.isEmpty() && Phone.isEmpty() && Address.isEmpty() && Password.isEmpty()){
            UserNameText.setError("Can't be Empty");
            NameText.setError("Can't be Empty");
            EmailText.setError("Can't be Empty");
            PhoneText.setError("Can't be Empty");
            AddressText.setError("Can't be Empty");
            PasswordText.setError("Can't be Empty");
        }else  if(UserName.isEmpty()){
            UserNameText.setError("Can't be Empty");
        }else  if(Name.isEmpty()){
            NameText.setError("Can't be Empty");
        }else  if(Email.isEmpty()){
            EmailText.setError("Can't be Empty");
        }else  if(Phone.isEmpty()){
            PhoneText.setError("Can't be Empty");
        }else  if(Address.isEmpty()){
            AddressText.setError("Can't be Empty");
        }else if (Password.isEmpty()){
            PasswordText.setError("Can't be Empty");
        }else{
            user.register(getApplicationContext(), Name, Phone, Email, Address, UserName, Password);
        }

    }
}