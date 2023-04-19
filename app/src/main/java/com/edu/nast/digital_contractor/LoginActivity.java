package com.edu.nast.digital_contractor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText phoneNumber,Password;
    RequestBaseUrl requestBaseUrl=new RequestBaseUrl();
    String url=  requestBaseUrl.getRequestUrl();

    //For user session
    // creating constant keys for shar
    // ed preferences.

    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //@@@@@@@@@@@@@@@@@@@@@@@@@------calling signup activity from login activity----------------@@@@@@@@@@@@@@@@@@@@@

        TextView signupView=(TextView) findViewById(R.id.sign_up_view);
        signupView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent su = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(su);
            }
        });


        phoneNumber = findViewById(R.id.phonenum);
        Password =findViewById(R.id.password);


        Button loginBtn=findViewById(R.id.log_in_btn);

        // initializing our shared preferences. FOR SESSION
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        //KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK------------All about login--------------MAMMOGRAMS

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validatePhoneNumber() && validatePassword()){

                    //@@@@@@@@@@@@@@@@@@@@@@@@@------Sending String request For phone number exist or not----------------@@@@@@@@@@@@@@@@@@@@@

                    StringRequest stringRequest=new StringRequest(Request.Method.POST, url+"login", new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {


                                //response -> format maa aaux :  "{"name": "kalak malla", "technology": "java"}";

                                //converting above format string to JSONObject
                                if(!response.equals("Wrong password") && !response.equals("Phone number not match") && response!=null ){

                                    String data = response;
                                    JSONObject json = new JSONObject(data);

                                SharedPreferences.Editor editor = sharedpreferences.edit();//for session

                                if(json.getString("admin").equals("A")  && json.getString("seller").equals("0")){//correspond check gareko  because aayeko string maa admin and seller both are present

                                    // STORING the data which  in shared preferences. i.e.  seller data
                                    editor.putBoolean("isAdminLogged",true);
                                    editor.putString("adminphoneKey", phoneNumber.getText().toString().trim());
                                    editor.putString("admintypeKey", "Admin");
                                    editor.putString("adminfirstnameKey", json.getString("firstname"));
                                    editor.putString("adminlastnameKey",json.getString("lastname"));
                                    editor.putString("adminaddressKey", json.getString("address"));
                                    editor.putString("adminemailKey", json.getString("email"));
                                    editor.putString("adminpassKey",Password.getText().toString().trim());

                                    //to save our data with key and value.
                                    editor.apply();

phoneNumber.setText(" ");
Password.setText(" ");

                                    Intent d=new Intent(LoginActivity.this,DashboardActivity.class);
                                    Toast.makeText(getApplicationContext(),"Hi, "+json.getString("firstname") ,Toast.LENGTH_SHORT).show();
                                    d.putExtra("dashboardMsg", "House");
                                    startActivity(d);
                                }

                                else if(json.getString("seller").equals("S") && json.getString("admin").equals("0")){//correspond check gareko  because aayeko string maa admin and seller both are present


                                    // STORING the data which  in shared preferences. i.e.  seller data

                                    editor.putBoolean("isSellerLogged",true);
                                    editor.putString("sellerphoneKey", phoneNumber.getText().toString().trim());
                                    editor.putString("sellertypeKey", "Seller");
                                    editor.putString("sellerfirstnameKey", json.getString("firstname"));

                                    editor.putString("sellerlastnameKey", json.getString("lastname"));
                                    editor.putString("selleraddressKey", json.getString("address"));
                                    editor.putString("selleremailKey", json.getString("email"));
                                    editor.putString("sellerpassKey",Password.getText().toString().trim());

                                    //to save our data with key and value.
                                    editor.apply();
                                    phoneNumber.setText(" ");
                                    Password.setText(" ");
                                    Intent d=new Intent(LoginActivity.this,DashboardActivity.class);
                                    Toast.makeText(getApplicationContext(),"Login Successfully ! (Seller)",Toast.LENGTH_SHORT).show();
                                    d.putExtra("dashboardMsg", "House");
                                    startActivity(d);
                                    finish();
                                }
                                }
                                else  if(response.equals("Wrong password")){
                                    Password.requestFocus();
                                    Password.setError(response.toString());
                                }
                                else if(response.equals("Phone number not match")){
                                    phoneNumber.requestFocus();
                                    phoneNumber.setError(response.toString());
                                }
                                else{
                                    Intent ee = new Intent(LoginActivity.this, ErrorExceptionActivity.class);
                                    Toast.makeText(getApplicationContext(),"Unknown Error Occur",Toast.LENGTH_SHORT).show();
                                    ee.putExtra("EE", "Unknown Error Occur");
                                    startActivity(ee);
                                }

                            } catch (Exception e) {

                                Intent ee=new Intent(LoginActivity.this,ErrorExceptionActivity.class);
                                Toast.makeText(getApplicationContext(),"Login Exception occur  :",Toast.LENGTH_SHORT).show();
                                ee.putExtra("EE","Login Exception occur :"+e.toString());
                                startActivity(ee);

                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Intent ee = new Intent(LoginActivity.this, ErrorExceptionActivity.class);
                            Toast.makeText(getApplicationContext(),"Volley login Error occur",Toast.LENGTH_SHORT).show();
                            ee.putExtra("EE", "Volley login Error occur"+error.toString());
                            startActivity(ee);
                        }
                    })
                    {
                        // Passing some request headers
                        @Override
                        public Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> param = new HashMap<String, String>();
                            param.put("phonenumber",phoneNumber.getText().toString().trim());
                            param.put("password", Password.getText().toString().trim());

                            return param;
                        }

                    };

//for volley request
                    RequestQueue requestQueue= Volley.newRequestQueue(LoginActivity.this);
                    requestQueue.add(stringRequest);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Sorry ! Check information again",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }



    //@@@@@@@@@@@@@@@@@@@@@@@@@------validation of login input data----------------@@@@@@@@@@@@@@@@@@@@@
    private Boolean validatePhoneNumber() {

        String pn = phoneNumber.getText().toString().trim();

        if (pn.isEmpty()) {
            phoneNumber.requestFocus();
            phoneNumber.setError("Enter your phone number");
            return false;
        }

        else {
            phoneNumber.setError(null);
            return true;
        }
    }
    private Boolean validatePassword() {

        String p = Password.getText().toString().trim();

        if (p.isEmpty()) {
            Password.requestFocus();
            Password.setError("Enter your password");
            return false;
        }

        else {
            Password.setError(null);
            return true;
        }
    }

}