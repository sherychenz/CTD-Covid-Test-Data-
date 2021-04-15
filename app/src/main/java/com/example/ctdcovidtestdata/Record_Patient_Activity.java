package com.example.ctdcovidtestdata;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import static com.example.ctdcovidtestdata.user.testCenterID;

public class Record_Patient_Activity extends AppCompatActivity {

    private EditText SymptomsText, UserNameText, NameText, PhoneText, AddressText, PasswordText;
    private String Symptoms, UserName, Name, Phone, Address, Password;
    private Spinner spinner;

    ArrayList<String>PatientType = new ArrayList<>();

    user user = new user();

    private String testCentreID = testCenterID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record__patient_);

        SymptomsText        = findViewById(R.id.Symptoms);
        UserNameText        = findViewById(R.id.UserName);
        NameText            = findViewById(R.id.Name);
        PhoneText           = findViewById(R.id.Phone);
        AddressText         = findViewById(R.id.Address);
        PasswordText        = findViewById(R.id.Password);
        spinner             = findViewById(R.id.spinner);

        //DROPLIST

        PatientType.add("Close Contact");
        PatientType.add("Infected");
        PatientType.add("Quarantined");
        PatientType.add("Returnee");
        PatientType.add("Suspected");

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter (this, android.R.layout.simple_spinner_item, PatientType);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void RecordPatient(View view) {
        Symptoms = SymptomsText.getText().toString().trim();
        UserName = UserNameText.getText().toString().trim();
        Name = NameText.getText().toString().trim();
        Phone = PhoneText.getText().toString().trim();
        Address = AddressText.getText().toString().trim();
        Password = PasswordText.getText().toString().trim();

        if (Symptoms.isEmpty() && UserName.isEmpty() && Name.isEmpty() && Phone.isEmpty() && Address.isEmpty() && Password.isEmpty()) {
            SymptomsText.setError("Can't be Empty");
            UserNameText.setError("Can't be Empty");
            NameText.setError("Can't be Empty");
            PhoneText.setError("Can't be Empty");
            AddressText.setError("Can't be Empty");
            PasswordText.setError("Can't be Empty");
        } else if(Symptoms.isEmpty()){
            SymptomsText.setError("Can't be Empty");
        }else if(UserName.isEmpty()){
            UserNameText.setError("Can't be Empty");
        }else  if(Name.isEmpty()){
            NameText.setError("Can't be Empty");
        }else  if(Phone.isEmpty()){
            PhoneText.setError("Can't be Empty");
        }else  if(Address.isEmpty()){
            AddressText.setError("Can't be Empty");
        }else if (Password.isEmpty()){
            PasswordText.setError("Can't be Empty");
        }else{
            System.out.println("=====================" + testCenterID);
            user.recordPatient(getApplicationContext(), Symptoms, Name, testCentreID, Phone, Address, UserName, Password);
        }
    }

    public void BackMenu(View view) {
        Intent intent = new Intent(getApplicationContext(), Tester_Menu_Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}