package com.example.ctdcovidtestdata;

import android.content.Context;
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




public class user {

    private static final String URL_REGISTER = "https://ctd5758.000webhostapp.com/register.php";

    public void register(Context context , String name, String phone, String email, String address, String username, String password){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                    } catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(context, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
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
            params.put("username", username);
            params.put("name", name);
            params.put("phone", phone);
            params.put("address", address);
            params.put("email", email);
            params.put("password", password);
            return params;
        }
    };
    RequestQueue requestQueue = Volley.newRequestQueue(context);
    requestQueue.add(stringRequest);
    }
}