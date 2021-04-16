package com.example.ctdcovidtestdata;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Update_Result_Activity extends AppCompatActivity {

    private Spinner spinner1;
    private Spinner spinner2;

    ArrayList<String> PatientStatus = new ArrayList<>();
    ArrayList<String> PatientResult = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record__patient_);

        spinner1             = findViewById(R.id.spinner1);
        spinner2             = findViewById(R.id.spinner1);

        //DROPLIST STATUS
        PatientStatus.add("Pending");
        PatientStatus.add("Infected");
        PatientResult.add("Positive");
        PatientResult.add("Negative");

        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter1 = new ArrayAdapter (this, android.R.layout.simple_spinner_item, PatientStatus);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        Spinner spinner2 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter (this, android.R.layout.simple_spinner_item, PatientResult);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
    }

    public void BackMenu(View view) {
        Intent intent = new Intent(getApplicationContext(), Tester_Menu_Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
