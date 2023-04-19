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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ManageAppActivity extends AppCompatActivity {
    RequestBaseUrl requestBaseUrl=new RequestBaseUrl();
    String url=  requestBaseUrl.getRequestUrl();
    EditText noticeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_app);

        TextView totalUser=findViewById(R.id.totalUser);
TextView totalHouseUploaded=findViewById(R.id.totalHouseUploaded);
        TextView totalLandUploaded=findViewById(R.id.totalLandUploaded);
        EditText noticeText=findViewById(R.id.notificationText);
        Button addNoticeBtn=findViewById(R.id.add_notification_btn);

//@@@@@@@@@@@@@@@@@@@@@@@@@@@------------------for user count
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url+"usercount", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
 if(response.length()>0){
     totalUser.setText("Total Users : "+response);
 }
 else{
     Intent ee = new Intent(ManageAppActivity.this, ErrorExceptionActivity.class);
     Toast.makeText(getApplicationContext(),"server Count user Error occur",Toast.LENGTH_SHORT).show();
     ee.putExtra("EE", "server Count user Error occur");
     startActivity(ee);
 }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Intent ee = new Intent(ManageAppActivity.this, ErrorExceptionActivity.class);
                Toast.makeText(getApplicationContext(),"Volley user count Error occur",Toast.LENGTH_SHORT).show();
                ee.putExtra("EE", "Volley user count Error occur"+error.toString());
                startActivity(ee);
            }
        })
        ;

//for volley request
        RequestQueue requestQueue= Volley.newRequestQueue(ManageAppActivity.this);
        requestQueue.add(stringRequest);

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@for house count

        StringRequest stringRequest1=new StringRequest(Request.Method.GET, url+"housecount", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.length()>0){
                    totalHouseUploaded.setText("Total house properties : "+response);
                }
                else{
                    Intent ee = new Intent(ManageAppActivity.this, ErrorExceptionActivity.class);
                    Toast.makeText(getApplicationContext(),"server Count user Error occur",Toast.LENGTH_SHORT).show();
                    ee.putExtra("EE", "server Count user Error occur");
                    startActivity(ee);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Intent ee = new Intent(ManageAppActivity.this, ErrorExceptionActivity.class);
                Toast.makeText(getApplicationContext(),"Volley user count Error occur",Toast.LENGTH_SHORT).show();
                ee.putExtra("EE", "Volley user count Error occur"+error.toString());
                startActivity(ee);
            }
        })
                ;

//for volley request
        RequestQueue requestQueue1= Volley.newRequestQueue(ManageAppActivity.this);
        requestQueue1.add(stringRequest1);

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@for land count

        StringRequest stringRequest2=new StringRequest(Request.Method.GET, url+"landcount", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.length()>0){
                    totalLandUploaded.setText("Total land properties : "+response);
                }
                else{
                    Intent ee = new Intent(ManageAppActivity.this, ErrorExceptionActivity.class);
                    Toast.makeText(getApplicationContext(),"server Count user Error occur",Toast.LENGTH_SHORT).show();
                    ee.putExtra("EE", "server Count user Error occur");
                    startActivity(ee);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Intent ee = new Intent(ManageAppActivity.this, ErrorExceptionActivity.class);
                Toast.makeText(getApplicationContext(),"Volley user count Error occur",Toast.LENGTH_SHORT).show();
                ee.putExtra("EE", "Volley user count Error occur"+error.toString());
                startActivity(ee);
            }
        })
                ;

//for volley request
        RequestQueue requestQueue2= Volley.newRequestQueue(ManageAppActivity.this);
        requestQueue2.add(stringRequest2);


        //@@@@@@@@@@@@@@@@@@@@@@@-----Adding notification-------------@@@@@@@@@@@@@@@@
        addNoticeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences shared = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                String adminPhoneSession = (shared.getString("adminphoneKey", ""));


                if (validateTextField()) {
//String request maa object pass garnu pareko le JSON  object maa convert gareko
                    //server maa receive garna sajilo hunx without using param and response maa String accept garna sakinx
                    try {
                        JSONObject jsonBody = new JSONObject();
                        //note key name chahi server ko corresponding class maa vaako table ko name sanga match hunu parx
                        jsonBody.put("adminPhone",adminPhoneSession);
                        jsonBody.put("notification",noticeText.getText().toString().trim());

                        Date date=new Date();
                        jsonBody.put("date",date.toString());
                        final String requestBody = jsonBody.toString();

                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url+"notification", new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                if(response.equals("success")){
                                    noticeText.setText(" ");
                                    Toast.makeText(getApplicationContext(),"Notification added successfully!"+response,Toast.LENGTH_SHORT).show();

                                }

                                else{
                                    Intent ee = new Intent(ManageAppActivity.this, ErrorExceptionActivity.class);
                                    Toast.makeText(getApplicationContext(),"Internal server error occur.",Toast.LENGTH_SHORT).show();
                                    ee.putExtra("EE", "Internal server error occur"+response.toString());
                                    startActivity(ee);
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Intent ee = new Intent(ManageAppActivity.this, ErrorExceptionActivity.class);
                                Toast.makeText(getApplicationContext(),"Volley notification Error occur ",Toast.LENGTH_SHORT).show();
                                ee.putExtra("EE", "Volley notification Error  occur"+error.toString());
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


                                    Intent ee=new Intent(ManageAppActivity.this,ErrorExceptionActivity.class);
                                    Toast.makeText(getApplicationContext(),"Notification Exception occur : Unsupported Encoding while trying to get the bytes of %s using ",Toast.LENGTH_SHORT).show();
                                    ee.putExtra("EE","Notification Exception occur : Unsupported Encoding while trying to get the bytes of %s using "+uee.toString());
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
                        RequestQueue requestQueue = Volley.newRequestQueue(ManageAppActivity.this);

                        requestQueue.add(stringRequest);
                    } catch (JSONException e) {
                        Intent ee=new Intent(ManageAppActivity.this,ErrorExceptionActivity.class);
                        Toast.makeText(getApplicationContext(),"Notification JSON Exception occur : ",Toast.LENGTH_SHORT).show();
                        ee.putExtra("EE","Notification Exception occur : Unsupported Encoding while trying to get the bytes of %s using "+e.toString());
                        startActivity(ee);

                    }

                }
                else{
                    Toast.makeText(getApplicationContext(),"Sorry ! Check information again",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    private Boolean validateTextField() {
        EditText noticeText=findViewById(R.id.notificationText);

        String n=noticeText.getText().toString().trim();


        if (n.isEmpty()) {
            noticeText.requestFocus();
            noticeText.setError("Field cannot be empty");
            return false;
        }

        else {
            noticeText.setError(null);
            return true;
        }
    }
}