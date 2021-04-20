package com.example.ctdcovidtestdata;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
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

public class Update_Result_Activity extends AppCompatActivity {
    private static final String URL_patientdata ="https://ctd5758.000webhostapp.com/testdata.php";

    private String updatestatus;
    private String updatetest;

    private Spinner spinner1;
    private Spinner spinner2;

    private String resultID = Search_Test_Activity.resultID;

    ArrayList<String> PatientStatus = new ArrayList<>();
    ArrayList<String> PatientResult = new ArrayList<>();

    TextView patientType, patientName, patientSymptoms, patientPhone, patientAddress, patientTestDate;
    String testresultID;
    testresult testresult = new testresult();
    String Testdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__result_);

        patientType = findViewById(R.id.getPtype);
        patientName = findViewById(R.id.getPname);
        patientSymptoms = findViewById(R.id.getPsymptoms);
        patientPhone = findViewById(R.id.getPphone);
        patientAddress = findViewById(R.id.getPaddress);
        patientTestDate = findViewById(R.id.getTestdate);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/YYYY");
        String date = simpleDateFormat.format(Calendar.getInstance().getTime());
        Testdate = date;
        System.out.println("----------------> Today"+ Testdate);

        spinner1             = findViewById(R.id.spinner1);
        spinner2             = findViewById(R.id.spinner2);

        //DROPLIST STATUS
        PatientStatus.add("Pending");
        PatientStatus.add("Complete");
        PatientResult.add("Positive");
        PatientResult.add("Negative");

        //Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter1 = new ArrayAdapter (this, android.R.layout.simple_spinner_item, PatientStatus);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        //Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter (this, android.R.layout.simple_spinner_item, PatientResult);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        System.out.println("-----------------> Result ID " + resultID);
        displayResult(resultID);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = parent.getItemAtPosition(position).toString();
                updatestatus = value;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = parent.getItemAtPosition(position).toString();
                updatetest = value;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
    public void displayResult(String value){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_patientdata,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("-----------------> " + response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray testkitarray = jsonObject.getJSONArray("testresult");
                            for (int i = 0; i < testkitarray.length(); i++) {
                                JSONObject testresultobject = testkitarray.getJSONObject(i);
                                if (value.equals(testresultobject.getString("testresultID"))){
                                    patientType.setText(testresultobject.getString("PatientType"));
                                    patientName.setText(testresultobject.getString("Name"));
                                    patientSymptoms.setText(testresultobject.getString("Symptoms"));
                                    patientPhone.setText(testresultobject.getString("Phone"));
                                    patientAddress.setText(testresultobject.getString("Address"));
                                    patientTestDate.setText(testresultobject.getString("TestDate"));
                                   // testresultID = testresultobject.getString("ID");
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
                Toast.makeText(getApplicationContext(), "Connection Error", Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    public void UpdateResult(View view) {
        testresult testresult = new testresult();

        System.out.println("-------------> " + resultID);

        testresult.UpdateResult(getApplicationContext(),updatetest,updatestatus, Testdate, resultID);
    }

    public void BackMenu(View view) {
        Intent intent = new Intent(getApplicationContext(), Tester_Menu_Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}