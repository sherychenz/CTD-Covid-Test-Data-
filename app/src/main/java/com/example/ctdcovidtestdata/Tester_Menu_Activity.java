package com.example.ctdcovidtestdata;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Tester_Menu_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tester__menu_);
    }

    public void RecordPatient(View view) {
        Intent intent = new Intent(getApplicationContext(), Record_Patient_Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

//    public void UpdateResult(View view) {
  //      Intent intent = new Intent(getApplicationContext(), Update_Result_Activity.class);
    //    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      //  startActivity(intent);
   // }
}