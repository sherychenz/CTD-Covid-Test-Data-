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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class testkit {

    private static final String URL_testkitname = "https://ctd5758.000webhostapp.com/testkitname.php";
    private static final String URL_stockupdate = "https://ctd5758.000webhostapp.com/stockupdate.php";

    private String ID;
    private String TestKitName;
    private String Stock;

    public testkit() {
    }

    public testkit(String ID, String TestKitName, String Stock){
        this.ID = ID;
        this.TestKitName = TestKitName;
        this.Stock = Stock;
    }

    public String getID() { return ID; }

    public void setID(String ID) { this.ID = ID; }

    public String getTestKitName() { return TestKitName; }

    public void setTestKitName(String TestKitName) { TestKitName = TestKitName; }

    public String getStock() { return Stock; }

    public void setStock (String Stock) { Stock = Stock; }

    public void managetestkit(String testKitName, String Stock){

    }

    public void addNewTestKit(Context context ,final String testKitName,final String Stock){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_testkitname,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(context, "Added Success", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(context, ManageTestKitStockActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);

                        } catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(context, "Added Fail", Toast.LENGTH_SHORT).show();
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
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("testkitname", testKitName);
                params.put("stock", Stock);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public void UpdateStock(Context context , String stock){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_stockupdate,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(context, "Success Change", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(context, MainActivity.class);
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
                params.put("stock", stock);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

}