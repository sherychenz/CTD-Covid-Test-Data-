package com.example.ctdcovidtestdata;

import androidx.appcompat.app.AppCompatActivity;

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

public class ManageTestKitStockActivity extends AppCompatActivity {

    private static final String URL_testkitdata = "https://ctd5758.000webhostapp.com/testkitdata.php";

    ArrayList<String>TestKit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_test_kit_stock);

        TestKit = new ArrayList<>();
        getData(TestKit);

    }

    public void getData(ArrayList<String> testKitArray){
                StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_testkitdata,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray testkitarray = jsonObject.getJSONArray("Testkit");
                            for (int i = 0; i < testkitarray.length(); i++) {
                                JSONObject testkitobject = testkitarray.getJSONObject(i);
                                testKitArray.add(testkitobject.getString("TestKitName"));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Human Error", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    public void BackMenu(View view) {
        Intent intent = new Intent(getApplicationContext(), TestCenterManagerMenuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void AddNewKkit(View view) {
        Intent intent = new Intent(getApplicationContext(), NewTestKitActivity.class);
        startActivity(intent);
    }
}