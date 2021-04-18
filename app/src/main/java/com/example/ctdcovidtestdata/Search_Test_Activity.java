package com.example.ctdcovidtestdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

import javax.xml.transform.ErrorListener;

public class Search_Test_Activity extends AppCompatActivity {

    private String URL_SEARCH_ID = "https://ctd5758.000webhostapp.com/testdata.php";
    private EditText searchID;
    private String testID;
    public static String resultID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__test_);

        searchID = findViewById(R.id.SearchID);
    }

    public void SearchTest(final String testID){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_SEARCH_ID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("=============" + response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray ResultArray = jsonObject.getJSONArray("testresult");
                            for (int i = 0; i < ResultArray.length(); i++) {
                                JSONObject ResultObject = ResultArray.getJSONObject(i);
                                if (ResultObject.getString("testresultID").equals(testID)) {
                                    resultID = ResultObject.getString("testresultID");
                                    Intent intent = new Intent(getApplicationContext(), Update_Result_Activity.class);
                                    startActivity(intent);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Test Not Found", Toast.LENGTH_LONG).show();
                        }
                    }
                    },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(getApplicationContext(), "Connection Error", Toast.LENGTH_LONG).show();
                }
            });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    public void SearchTest(View view) {
        testID = searchID.getText().toString();
        if (!testID.isEmpty()) {
            SearchTest(testID);
        }else{
            searchID.setError("User can't be empty");
        }

    }
}