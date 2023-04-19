package com.edu.nast.digital_contractor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TestImg extends AppCompatActivity {
    RequestBaseUrl requestBaseUrl=new RequestBaseUrl();
    String url=  requestBaseUrl.getRequestUrl();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_img);
        ImageView img=findViewById(R.id.imageView);
        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest=new StringRequest(Request.Method.GET, url+"getImg", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("respooooooo","msg : "+response);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Intent ee = new Intent(TestImg.this, ErrorExceptionActivity.class);
                        Toast.makeText(getApplicationContext(),"Volley login Error occur",Toast.LENGTH_SHORT).show();
                        ee.putExtra("EE", "Volley login Error occur"+error.toString());
                        startActivity(ee);
                    }
                })
//                {
//                    // Passing some request headers
//                    @Override
//                    public Map<String, String> getParams() throws AuthFailureError {
//                        Map<String, String> param = new HashMap<String, String>();
//                        param.put("phonenumber",phoneNumber.getText().toString().trim());
//                        param.put("password", Password.getText().toString().trim());
//
//                        return param;
//                    }
//
//                }
               ;

//for volley request
                RequestQueue requestQueue= Volley.newRequestQueue(TestImg.this);
                requestQueue.add(stringRequest);
            }
        });
    }
}