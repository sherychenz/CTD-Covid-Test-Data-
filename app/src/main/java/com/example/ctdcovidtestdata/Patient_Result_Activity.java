package com.example.ctdcovidtestdata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class Patient_Result_Activity extends AppCompatActivity {
    RecyclerView ListTest;
    RecyclerView.LayoutManager layoutManager;
    private ArrayList<testresult> testArray = new ArrayList<>();
    public String patientID = user.userID;

    private String URL_SEARCH_ID = "https://ctd5758.000webhostapp.com/testdata.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__result_);
        ListTest = findViewById(R.id.ListTest);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        ListTest.setLayoutManager(layoutManager);
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
                                if (testresultobject.getString("UserID").equals(patientID)){
                                    testArray.add(new testresult(testresultobject.getString("testresultID"),
                                            testresultobject.getString("Result"),
                                            testresultobject.getString("Status"),
                                            testresultobject.getString("Name"),
                                            testresultobject.getString("TestDate"),
                                            testresultobject.getString("ResultDate")));
                                }
                            }
                            ListAdapter listAdapter = new ListAdapter(getApplicationContext(),testArray);
                            ListTest.setAdapter(listAdapter);
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
    public void SignOut(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}