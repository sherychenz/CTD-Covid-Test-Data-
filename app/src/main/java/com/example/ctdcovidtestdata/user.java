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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class user {

    private static final String URL = "";
    private static final String URL_REGISTER = "";

    public void login(final Context context, final String userName, final String password){
        if (!userName.isEmpty() && !password.isEmpty()){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                    new Response.Listener<String>(){
                        @Override
                        public void onResponse(String response){
                            System.out.println(response);
                            try{
                                JSONObject jsonObject = new JSONObject(response);
                                String success = jsonObject.getString("success");
                                JSONArray jsonArray = jsonObject.getJSONArray("login");

                                if(success.equals("1")){
                                    for(int i=0; i < jsonArray.length();i++){
                                        JSONObject object = jsonArray.getJSONObject(i);
                                        if (object.get("position").equals("Manager")){
//                                            if(object.get(status).equals("p")){
//                                                Toast.makeText(context,"Login Success! Welcomed", Toast.LENGTH_LONG).show();
//                                            }
//                                            else {
//                                                Toast.makeText(context, "Login Success! Welcome 2", Toast.LENGTH_LONG).show();
//                                            }
//                                            Toast.makeText(context, "Login Success! Welcome", Toast.LENGTH_LONG).show();
                                        }
                                        else if (false){
                                            Toast.makeText(context, "Login Success! Welcome 4", Toast.LENGTH_LONG).show();
                                        }
                                        else{
                                            Toast.makeText(context, "Login Success! Welcome 5", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }
                            }
                            catch (JSONException x){
                                x.printStackTrace();
                                Toast.makeText(context, "Invalid Username or Password 1", Toast.LENGTH_LONG).show();
                            }
                        }
                    },
                    new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error){
                            Toast.makeText(context, "Invalid Username or Password 2", Toast.LENGTH_LONG).show();
                        }
                    }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError{
                    Map<String, String> params = new HashMap<>();
                    params.put("userName", userName);
                    params.put("password", password);
                    return params;
                }
//                protected Map<String, String> getParams() throw AuthFailureError{
//                    Map<String, String> params = new HashMap<>();
//                    params.put("userName", userName);
//                    params.put("password", password);
//                    return params;
//                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(stringRequest);
        }
        else{
            Toast.makeText(context, "Username and Password can not be empty!", Toast.LENGTH_SHORT).show();
        }
    }



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