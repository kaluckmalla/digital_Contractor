package com.edu.nast.digital_contractor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        TextView date=findViewById(R.id.date);

        Date todayDate=new Date();
        date.setText(todayDate.toString());


        Button house_dashboard_btn=findViewById(R.id.house_dashboard_btn);
        Button land_dashboard_btn=findViewById(R.id.land_dashboard_btn);

        house_dashboard_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent db = new Intent(LauncherActivity.this, DashboardActivity.class);
                db.putExtra("dashboardMsg", "House");
                startActivity(db);
            }
        });
        land_dashboard_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent db = new Intent(LauncherActivity.this, DashboardActivity.class);
                db.putExtra("dashboardMsg", "Land");
                startActivity(db);

            }
        });

    }
}