package com.edu.nast.digital_contractor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {
    EditText firstName,lastName,Address,phoneNumber,Email,Password;
    RequestBaseUrl requestBaseUrl=new RequestBaseUrl();
    String url=  requestBaseUrl.getRequestUrl();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //@@@@@@@@@@@@@@@@@@@@@@@@@------If already signup then calling to login----------------@@@@@@@@@@@@@@@@@@@@@
        TextView alreadyHaveAccountView=(TextView) findViewById(R.id.already_have_account_view);
        alreadyHaveAccountView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent l=new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(l);
                finish();
            }
        });

        //for Sign up
        firstName =findViewById(R.id.firstname);
        lastName = findViewById(R.id.lastname);
        Address = findViewById(R.id.address);
        phoneNumber = findViewById(R.id.phonenum);
        Email =findViewById(R.id.email);
        Password =findViewById(R.id.password);

        Button signupBtn=findViewById(R.id.sign_up_btn);


        //note  KKK---MMMM   ->for main coding      @@@@@@@@@@@@----@@@@@@@@   ->for sub coding

//KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK------------All about signup--------------MAMMOGRAMS
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validateFirstName() && validateLastName() && validateAddress() && validatePhoneNumber() && validateEmail() && validatePassword()){


                    //String request maa object pass garnu pareko le JSON  object maa convert gareko
                    //server maa receive garna sajilo hunx without using param and response maa String accept garna sakinx
                    try {
                        JSONObject jsonBody = new JSONObject();
                        //note key name chahi server ko corresponding class maa vaako table ko name sanga match hunu parx
                        jsonBody.put("userType","Seller");
                        jsonBody.put("firstName",firstName.getText().toString().trim());
                        jsonBody.put("lastName",lastName.getText().toString().trim());
                        jsonBody.put("address",Address.getText().toString().trim());
                        jsonBody.put("phoneNumber",phoneNumber.getText().toString().trim());
                        jsonBody.put("email",Email.getText().toString().trim());
                        jsonBody.put("password",Password.getText().toString().trim());

                        Date date=new Date();
                        jsonBody.put("date",date.toString());
                        jsonBody.put("status","0");
                        final String requestBody = jsonBody.toString();

                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url+"signup", new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                if(response.equals("phone already exist")){
                                    phoneNumber.requestFocus();
                                    phoneNumber.setError("Phone number already exist");
                                }
                                else if(response.equals("email already exist")){
                                    Email.requestFocus();
                                    Email.setError("Email already exist");
                                }
                                else if (response.equals("success")) {
                                    firstName.setText(" ");
                                    lastName.setText(" ");
                                    firstName.setText(" ");
                                    Address.setText(" ");
                                    phoneNumber.setText(" ");
                                    Email.setText(" ");
                                    Password.setText(" ");

                                    Intent l=new Intent(SignupActivity.this,LoginActivity.class);
                                    Toast.makeText(getApplicationContext(),"Registered successfully!"+response,Toast.LENGTH_SHORT).show();
                                    startActivity(l);
                                    finish();
                                }
                                else{
                                    Intent ee = new Intent(SignupActivity.this, ErrorExceptionActivity.class);
                                    Toast.makeText(getApplicationContext(),"Internal server error occur.",Toast.LENGTH_SHORT).show();
                                    ee.putExtra("EE", "Internal server error occur"+response.toString());
                                    startActivity(ee);
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Intent ee = new Intent(SignupActivity.this, ErrorExceptionActivity.class);
                                Toast.makeText(getApplicationContext(),"Volley signup Error occur ",Toast.LENGTH_SHORT).show();
                                ee.putExtra("EE", "Volley signup Error  occur"+error.toString());
                                startActivity(ee);
                            }
                        }) {
                            @Override
                            public String getBodyContentType() {
                                return "application/json; charset=utf-8";
                            }


                            //String request maa JSON object send gareko le yo sab important x
                            @Override
                            public byte[] getBody() throws AuthFailureError {
                                try {
                                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                                } catch (UnsupportedEncodingException uee) {


                                    Intent ee=new Intent(SignupActivity.this,ErrorExceptionActivity.class);
                                    Toast.makeText(getApplicationContext(),"Signup Exception occur : Unsupported Encoding while trying to get the bytes of %s using ",Toast.LENGTH_SHORT).show();
                                    ee.putExtra("EE","Signup Exception occur : Unsupported Encoding while trying to get the bytes of %s using "+uee.toString());
                                    startActivity(ee);
                                    // VolleyLog.wtf("%s", requestBody, "utf-8");//CC
                                    return null;
                                }
                            }
//Server response ko status  null x ki xaina bhanera check gareko
//                            @Override
//                            protected Response<String> parseNetworkResponse(NetworkResponse response) {
//                                String responseString = "";
//                                if (response != null) {
//                                    responseString = String.valueOf(response.statusCode);
//                                    // can get more details such as response.headers
//                                }
//                                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
//                            }
                        };
//for volley request
                        RequestQueue requestQueue = Volley.newRequestQueue(SignupActivity.this);

                        requestQueue.add(stringRequest);
                    } catch (JSONException e) {
                        Intent ee=new Intent(SignupActivity.this,ErrorExceptionActivity.class);
                        Toast.makeText(getApplicationContext(),"Signup JSON Exception occur : ",Toast.LENGTH_SHORT).show();
                        ee.putExtra("EE","Signup Exception occur : Unsupported Encoding while trying to get the bytes of %s using "+e.toString());
                        startActivity(ee);


                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Sorry ! Check information again",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    //@@@@@@@@@@@@@@@@@@@@@@@@@------validation of signup input data----------------@@@@@@@@@@@@@@@@@@@@@
    private Boolean validateFirstName() {
        String fn=firstName.getText().toString().trim();


        if (fn.isEmpty()) {
            firstName.requestFocus();
            firstName.setError("Field cannot be empty");
            return false;
        }

        else {
            firstName.setError(null);
            return true;
        }
    }

    private Boolean validateLastName() {
        String ln=lastName.getText().toString().trim();


        if (ln.isEmpty()) {
            lastName.requestFocus();
            lastName.setError("Field cannot be empty");
            return false;
        }

        else {
            lastName.setError(null);
            return true;
        }
    }
    private Boolean validateAddress() {

        String a=Address.getText().toString().trim();

        if (a.isEmpty()) {
            Address.requestFocus();
            Address.setError("Field cannot be empty");
            return false;
        }
        else {
            Address.setError(null);
            return true;
        }
    }
    private Boolean validatePhoneNumber() {

        String pn=phoneNumber.getText().toString().trim();

        if (pn.isEmpty()) {
            phoneNumber.requestFocus();
            phoneNumber.setError("Field cannot be empty");
            return false;
        }
        else if(!pn.matches("^[+]?[0-9]{10,11}$")) {
            phoneNumber.requestFocus();
            phoneNumber.setError("Enter valid phone number correct format : 98xxxxxx67");
            return false;
        }
        else {
            phoneNumber.setError(null);
            return true;
        }
    }

    private Boolean validateEmail() {

        String e=Email.getText().toString().trim();

        if (e.isEmpty()) {
            Email.requestFocus();
            Email.setError("Field cannot be empty");
            return false;
        }
        else if(!e.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
            Email.requestFocus();
            Email.setError("Enter valid email. Like : example@gmail.com");
            return false;
        }
        else {
            Email.setError(null);
            return true;
        }
    }
    private Boolean validatePassword() {
        String p=Password.getText().toString().trim();


        if (p.isEmpty()) {
            Password.requestFocus();
            Password.setError("Field cannot be empty");
            return false;
        }
        else if(p.length()<4) {
            Password.requestFocus();
            Password.setError("Minimum 4 character required");
            return false;
        }
        else {
            Password.setError(null);
            return true;
        }
    }
}

//@@@@@@@@@@@@@@@@@@@@@@@@@------Optional code  for Register----------------@@@@@@@@@@@@@@@@@@@@@
/*
    private void Register(){

        //For sending post request
        // Parameters to pass as POST request
        JSONObject js = new JSONObject();
        try {
            js.put("userType","Seller");
            js.put("firstName",firstName.getText().toString().trim());
            js.put("lastName",lastName.getText().toString().trim());
            js.put("address",Address.getText().toString().trim());
            js.put("phoneNumber",phoneNumber.getText().toString().trim());
            js.put("email",Email.getText().toString().trim());
            js.put("password",Password.getText().toString().trim());


        } catch (JSONException e) {
            Intent ee=new Intent(TestSignup.this,ErrorExceptionActivity.class);
            Toast.makeText(getApplicationContext(),"Signup JSONException occur :",Toast.LENGTH_SHORT).show();
            ee.putExtra("EE","Signup JSONException occur :"+e.toString());
            startActivity(ee);
        }

        // Make request for JSONObject
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST, url+"signup", js,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Intent l=new Intent(TestSignup.this,LoginActivity.class);
                        Toast.makeText(getApplicationContext(),"Registered successfully!"+response,Toast.LENGTH_SHORT).show();
                        startActivity(l);
                        finish();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Intent ee = new Intent(TestSignup.this, ErrorExceptionActivity.class);
                Toast.makeText(getApplicationContext(),"Volley Error occur for register ",Toast.LENGTH_SHORT).show();
                ee.putExtra("EE", "Volley Error occur for register"+error.toString());
                startActivity(ee);
            }
        }) {
            // Passing some request headers
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

        };

//for volley request
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonObjReq);
    }

*/