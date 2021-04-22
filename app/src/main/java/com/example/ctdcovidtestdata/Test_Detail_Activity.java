package com.example.ctdcovidtestdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Test_Detail_Activity extends AppCompatActivity {
    private TextView PatientTypeText, NameText, SymptomsText, PhoneText, AddressText, TestDateText, ResultText;
    private ArrayList<testresult> testArray = new ArrayList<>();

    private String URL_SEARCH_ID = "https://ctd5758.000webhostapp.com/testdata.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test__detail_);

        PatientTypeText = findViewById(R.id.getPtype1);
        NameText        = findViewById(R.id.getPname1);
        SymptomsText        = findViewById(R.id.getPsymptoms1);
        PhoneText           = findViewById(R.id.getPphone1);
        AddressText         = findViewById(R.id.getPaddress1);
        TestDateText        = findViewById(R.id.getTestdate1);
        ResultText        = findViewById(R.id.getPResult1);

        getData();
    }

    public void getData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_SEARCH_ID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("-----------------> " + response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray testarray = jsonObject.getJSONArray("testresult");
                            for (int i = 0; i < testarray.length(); i++) {
                                JSONObject testresultobject = testarray.getJSONObject(i);
                                PatientTypeText.setText(testresultobject.getString("PatientType"));
                                NameText.setText(testresultobject.getString("Name"));
                                SymptomsText.setText(testresultobject.getString("Symptoms"));
                                PhoneText.setText(testresultobject.getString("Phone"));
                                AddressText.setText(testresultobject.getString("Address"));
                                TestDateText.setText(testresultobject.getString("TestDate"));
                                ResultText.setText(testresultobject.getString("Result"));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Human Error", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Connection Error", Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    public void BackMenu(View view) {
        Intent intent = new Intent(getApplicationContext(), Patient_Result_Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}