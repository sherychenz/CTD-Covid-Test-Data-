package com.example.ctdcovidtestdata;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class testresult {

    private static final String URL_updateresult = "https://ctd5758.000webhostapp.com/updateresult.php";
    private String testresultID;
    private String Result;
    private String Status;
    private String PatientName;
    private String TestDate;
    private String ResultDate;

    public testresult() {
    }
    public testresult(String testresultID, String Result, String Status, String PatientName, String TestDate, String ResultDate){
        this.testresultID = testresultID;
        this.Result = Result;
        this.Status = Status;
        this.PatientName = PatientName;
        this.TestDate = TestDate;
        this.ResultDate = ResultDate;
    }
    public String getTestresultID() { return testresultID; }

    public void setTestresultID(String testresultID) { this.testresultID = testresultID; }

    public String getResult() { return Result; }

    public void setResult(String Result) { Result = Result; }

    public String getStatus() { return Status; }

    public void setStatus(String Status) { Status = Status; }

    public String getPatientName() { return PatientName; }

    public void setPatientName(String PatientName) { PatientName = PatientName; }

    public String getTestDate() { return TestDate; }

    public void setTestDate(String TestDate) { TestDate = TestDate; }

    public String getResultDate() { return ResultDate; }

    public void setResultDate(String ResultDate) { ResultDate = ResultDate; }

    public void resultupdate(String Result, String Status){
    }

    public void UpdateResult(Context context , String Result, String Status, String Date, String testresultID){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_updateresult,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("---------------- " + response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(context, "Success Change", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(context, Tester_Menu_Activity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);

                        } catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put("testresultID", testresultID);
                params.put("status", Status);
                params.put("result", Result);
                params.put("date", Date);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}