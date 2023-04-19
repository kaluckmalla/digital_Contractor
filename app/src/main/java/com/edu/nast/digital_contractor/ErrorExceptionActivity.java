package com.edu.nast.digital_contractor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ErrorExceptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_exception);
        // Set your parent view
        //setContentView(R.layout.activity_signup);


        Intent i = getIntent();
        String s = i.getStringExtra("EE");


        TextView tv=findViewById(R.id.errorException);

        tv.setText(s);
        Log.d("exer","dd");

    }
}