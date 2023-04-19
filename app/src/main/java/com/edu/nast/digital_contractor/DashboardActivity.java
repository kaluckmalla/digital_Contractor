package com.edu.nast.digital_contractor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.edu.nast.digital_contractor.databinding.ActivityDashboardBinding;

public class DashboardActivity extends AppCompatActivity {
   ActivityDashboardBinding binding;//for binding fragment to this activity for navigation bar i.e. menu bar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
//for user session exist or not either seller or admin
        SharedPreferences shared = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@--------------For bottom navigation bar i.e. menu bar
        binding=ActivityDashboardBinding.inflate(getLayoutInflater());        setContentView(binding.getRoot());

        replaceFragment(new HomeFragment());//before starting dashboard call of home fragment

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                //For switching to same activity again i.e.Dashboard activity
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                //For switching to fragment on same activity i.e. withinDashboard activity
                case R.id.message:
                    replaceFragment(new MessageFragment());
                    break;
                case R.id.notification:
                    replaceFragment(new NotificationFragment());
                    break;
                //For switching to new activity
                case R.id.upload:
                    if(shared.contains("isAdminLogged")) {// for uploading from admin

                        //  selectedFragment = ItemOneFragment.newInstance();
                        Intent uploadactivity = new Intent(DashboardActivity.this, UploadActivity.class);
                        Toast.makeText(getApplicationContext(),"You are uploading from Admin.",Toast.LENGTH_SHORT).show();
                        uploadactivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(uploadactivity);
                    }
                    else if(shared.contains("isSellerLogged")) {// for uploading from seller

                        //  selectedFragment = ItemOneFragment.newInstance();
                        Intent uploadactivity = new Intent(DashboardActivity.this, UploadActivity.class);
                        uploadactivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(uploadactivity);
                    }
                    else{
                        //  selectedFragment = ItemOneFragment.newInstance();
                        Intent loginactivity = new Intent(DashboardActivity.this, LoginActivity.class);
                        Toast.makeText(getApplicationContext(),"Need to Login for upload.",Toast.LENGTH_SHORT).show();
                        loginactivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(loginactivity);
                    }
                    break;

                case R.id.account:
                    if(shared.contains("isAdminLogged")) {// for open admin account by checking session

                        //  selectedFragment = ItemOneFragment.newInstance();
                        Intent accountactivity = new Intent(DashboardActivity.this, SellerAccountActivity.class);
                        Toast.makeText(getApplicationContext(),"Admin Account.",Toast.LENGTH_SHORT).show();
                        accountactivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(accountactivity);
                    }
                      else if(shared.contains("isSellerLogged")) {// for open seller account  by checking session

                    //  selectedFragment = ItemOneFragment.newInstance();
                    Intent accountactivity = new Intent(DashboardActivity.this, SellerAccountActivity.class);
                        accountactivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(accountactivity);
                }
                      else{

                        //  selectedFragment = ItemOneFragment.newInstance();
                        Intent loginactivity = new Intent(DashboardActivity.this, LoginActivity.class);
                        Toast.makeText(getApplicationContext(),"To see your account Login first.",Toast.LENGTH_SHORT).show();
                        loginactivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(loginactivity);
                    }
                    break;
            }
            return true;
        });
    }
//For replacing dashboard layout by fragment layout in specified layout
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.dashboard_frame_layout,fragment);
        fragmentTransaction.commit();
    }
}