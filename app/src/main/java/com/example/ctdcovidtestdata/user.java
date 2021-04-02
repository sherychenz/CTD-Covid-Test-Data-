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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;




public class user {

    private static final String URL = "https://ctd5758.000webhostapp.com/logIn.php";
    private static final String URL_REGISTER = "https://ctd5758.000webhostapp.com/register.php";
    private String ID;
    private String UserName;
    private String Name;
    private String Phone;
    private String Address;
    private String Email;
    private String Password;
    private Context context;

    public user(){
    }

    public user(String ID, String UserName, String Name, String Phone, String Address, String Email, String Password){
        this.ID = ID;
        this.UserName = UserName;
        this.Name = Name;
        this.Phone = Phone;
        this.Address = Address;
        this.Email = Email;
        this.Password = Password;
    }

    public String getID() { return ID; }

    public void setID(String ID) { this.ID = ID; }

    public String getUserName() { return UserName; }

    public void setUserName(String userName) { UserName = userName; }

    public String getName() { return Name; }

    public void setName(String name) { Name = name; }

    public String getPhone() { return Phone; }

    public void setPhone(String phone) { Phone = phone; }

    public String getAddress() { return Address; }

    public void setAddress(String address) { Address = address; }

    public String getEmail() { return Email; }

    public void setEmail(String email) { Email = email; }

    public String getPassword() { return Password; }

    public void setPassword(String password) { Password = password; }

    public Context getContext() { return context; }

    public void setContext(Context context) { this.context = context; }

    public void login(final Context context, final String userName, final String password){
        if (!userName.isEmpty() && !password.isEmpty()){
            System.out.println(userName);
            System.out.println(password);

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

                                        if (object.get("Position").equals("Manager")){
                                            Intent intent = new Intent(context,TestCenterManagerMenuActivity.class);
                                            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                                            context.startActivity(intent);
                                        }
                                        else if (false){
                                            Toast.makeText(context, "Login Success! Welcome", Toast.LENGTH_LONG).show();
                                        }
                                        else{
                                            Toast.makeText(context, "Wait until the admin approved", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }
                            }
                            catch (JSONException x){
                                x.printStackTrace();
                                Toast.makeText(context, "Invalid Username or Password", Toast.LENGTH_LONG).show();
                            }
                        }
                    },
                    new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error){
                            Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
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
                        System.out.println(response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(context, "Register Success", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(context, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);

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