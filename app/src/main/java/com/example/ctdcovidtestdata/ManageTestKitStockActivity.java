package com.example.ctdcovidtestdata;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
    private EditText Stock;
    private String updatestock;
    private String testKitID;

    ArrayList<String>TestKit;

    Spinner testKitSp;
    TextView testKitName, testKitStock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_test_kit_stock);

        TestKit = new ArrayList<>();
        // update
        Stock        = findViewById(R.id.updatestock);

        //spinner
        testKitSp = findViewById(R.id.KitSpinner);
        testKitName = findViewById(R.id.getName);
        testKitStock = findViewById(R.id.getTotal);
//        getData(TestKit);
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
                                TestKit.add(testkitobject.getString("TestKitName"));
                            }
                            ArrayAdapter<String> vKitAdpter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, TestKit);
                            vKitAdpter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            testKitSp.setAdapter(vKitAdpter);
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

        testKitSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = parent.getItemAtPosition(position).toString();
                displayData(value);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void displayData(String value){
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
                                if (value.equals(testkitobject.getString("TestKitName"))){
                                    testKitName.setText(testkitobject.getString("TestKitName"));
                                    testKitStock.setText(testkitobject.getString("Stock"));
                                    testKitID = testkitobject.getString("ID");
                                }
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

//    public void getData(ArrayList<String> testKitArray){
//                StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_testkitdata,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        System.out.println(response);
//                        try {
//                            JSONObject jsonObject = new JSONObject(response);
//                            JSONArray testkitarray = jsonObject.getJSONArray("Testkit");
//                            for (int i = 0; i < testkitarray.length(); i++) {
//                                JSONObject testkitobject = testkitarray.getJSONObject(i);
//                                testKitArray.add(testkitobject.getString("TestKitName"));
//                                ArrayAdapter<String> vKitAdpter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, testKitArray);
//                                vKitAdpter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                                testKitSp.setAdapter(vKitAdpter);
//
//                            }
//
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            Toast.makeText(getApplicationContext(), "Human Error", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        requestQueue.add(stringRequest);
//    }

    public void BackMenu(View view) {
        Intent intent = new Intent(getApplicationContext(), TestCenterManagerMenuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void AddNewKkit(View view) {
        Intent intent = new Intent(getApplicationContext(), NewTestKitActivity.class);
        startActivity(intent);
    }

    public void UpdateStock(View view) {
        updatestock = Stock.getText().toString().trim();

        testkit testkit = new testkit();

        System.out.println("-------------> " + testKitID);

        testkit.UpdateStock(getApplicationContext(), updatestock, testKitID);
    }
}