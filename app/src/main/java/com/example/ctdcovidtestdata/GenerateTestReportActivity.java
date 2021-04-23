package com.example.ctdcovidtestdata;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class GenerateTestReportActivity extends AppCompatActivity {
    RecyclerView ListGenerate;
    RecyclerView.LayoutManager layoutManager;
    private ArrayList<testresult> testArray = new ArrayList<>();
    public String patientID = user.userID;
    public String testCentreID = user.testCenterID;

    private String URL_SEARCH_ID = "https://ctd5758.000webhostapp.com/testdata.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_test_report);
        ListGenerate = findViewById(R.id.ListGenerate);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        ListGenerate.setLayoutManager(layoutManager);
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
                                if (testresultobject.getString("TestCentreID").equals(testCentreID)){
                                    testArray.add(new testresult(testresultobject.getString("testresultID"),
                                            testresultobject.getString("Result"),
                                            testresultobject.getString("Status"),
                                            testresultobject.getString("Name"),
                                            testresultobject.getString("TestDate"),
                                            testresultobject.getString("ResultDate")));
                                }
                            }
                            ListAdapter listAdapter = new ListAdapter(getApplicationContext(),testArray);
                            ListGenerate.setAdapter(listAdapter);
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

}