package com.example.ctdcovidtestdata;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
    private static final String URL_patientdata ="https://ctd5758.000webhostapp.com/userdata.php";
    private Spinner spinner1;
    private Spinner spinner2;

    ArrayList<String> PatientStatus = new ArrayList<>();
    ArrayList<String> PatientResult = new ArrayList<>();

    TextView patientType, patientName, patientSymptoms, patientPhone, patientAddress, patientTestDate;
    String testresultID;


    testresult testresult = new testresult();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record__patient_);

        patientType = findViewById(R.id.getPtype);
        patientName = findViewById(R.id.getPname);
        patientSymptoms = findViewById(R.id.getPsymptoms);
        patientPhone = findViewById(R.id.getPphone);
        patientAddress = findViewById(R.id.getPaddress);
        patientTestDate = findViewById(R.id.getTestdate);

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

    public void displayResult(String value){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_patientdata,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray testkitarray = jsonObject.getJSONArray("Testresult");
                            for (int i = 0; i < testkitarray.length(); i++) {
                                JSONObject testresultobject = testkitarray.getJSONObject(i);
                                if (value.equals(testresultobject.getString("UserID"))){
                                    patientType.setText(testresultobject.getString("PatientType"));
                                    patientName.setText(testresultobject.getString("Name"));
                                    patientSymptoms.setText(testresultobject.getString("Symptoms"));
                                    patientPhone.setText(testresultobject.getString("Phone"));
                                    patientAddress.setText(testresultobject.getString("Address"));
                                    patientTestDate.setText(testresultobject.getString("TestDate"));
                                    testresultID = testresultobject.getString("ID");
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

    public void BackMenu(View view) {
        Intent intent = new Intent(getApplicationContext(), Tester_Menu_Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
