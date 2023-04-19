package com.edu.nast.digital_contractor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.edu.nast.digital_contractor.databinding.ActivityDashboardBinding;
import com.edu.nast.digital_contractor.databinding.ActivityUploadBinding;

public class UploadActivity extends AppCompatActivity {
    ActivityUploadBinding binding;//for binding fragment to this activity for top home and property upload navigation bar i.e. menu bar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);


        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@--------------For to navigation bar i.e. menu bar
        binding= ActivityUploadBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new LandUploadF());//before starting dashboard call of home fragment//default fragment over upload

        binding.topNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                //For switching to same activity again i.e.Dashboard activity
                case R.id.land_item:
                    replaceFragment(new LandUploadF());
                    break;
                //For switching to fragment on same activity i.e. withinDashboard activity
                case R.id.home_item:
                    replaceFragment(new HomeUploadF());
                    break;
            }
            return true;
        });


    }
    //For replacing dashboard layout by fragment layout in specified layout
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.upload_frame_layout,fragment);
        fragmentTransaction.commit();
    }
}