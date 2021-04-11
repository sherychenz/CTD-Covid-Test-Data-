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
    private static final String URL_TESTCENTRE = "https://ctd5758.000webhostapp.com/testcentreregis.php";
    private static final String URL_RECORD_TESTER = "https://ctd5758.000webhostapp.com/recordtester.php";
    private static final String URL_TESTCENTRE_DATA = "ctd5758.000webhostapp.com/CentreData.php";
    private static final String URL_UPDATE_MANAGER = "ctd5758.000webhostapp.com/testcentreupdate.php";
    private static final String URL_MANAGER_DATA = "ctd5758.000webhostapp.com/userdata.php";

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
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_TESTCENTRE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(context, "Register Success", Toast.LENGTH_LONG).show();

                            //testCenterManager(context, name, phone,email,address,username,password);
                            managerData(context,username,name);

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
                params.put("testcentrename", name);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, URL_REGISTER,
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
        RequestQueue requestQueue1 = Volley.newRequestQueue(context);
        requestQueue1.add(stringRequest1);
    }

    public void getTestCenter(Context context , String name, String managerID){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_TESTCENTRE_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray testCentreArray = jsonObject.getJSONArray("Testcebtre");
                            for (int i = 0; i < testCentreArray.length(); i++){
                                JSONObject testCentreObj = testCentreArray.getJSONObject(i);
                                if (testCentreObj.getString("TestCemtreName").equals(name)){
                                    managerUpdate(context,testCentreObj.getString("ID"),managerID);
                                }
                            }
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
        );
    }

    public void managerData(Context context, String managerName, String name){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_MANAGER_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray userArray = jsonObject.getJSONArray("UserData");
                            for (int i = 0; i < userArray.length(); i++){
                                JSONObject userObj = userArray.getJSONObject(i);
                                if (managerName.equals(userObj.getString("UserName"))){
                                    getTestCenter(context,name,userObj.getString("ID"));
                                }

                            }
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
        );
    }

    public void managerUpdate(Context context , String testCenterID, String managerID){
                    StringRequest stringRequest1 = new StringRequest(Request.Method.POST, URL_UPDATE_MANAGER,
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
                params.put("ID", managerID);
                params.put("testcentreid", testCenterID);
                return params;
            }
        };
        RequestQueue requestQueue1 = Volley.newRequestQueue(context);
        requestQueue1.add(stringRequest1);
    }

    public void recordTester(Context context , String name, String phone, String address, String username, String password){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_RECORD_TESTER,
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
                params.put("password", password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}