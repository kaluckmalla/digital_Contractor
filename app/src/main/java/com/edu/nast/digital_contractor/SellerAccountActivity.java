package com.edu.nast.digital_contractor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.io.InputStream;

public class SellerAccountActivity extends AppCompatActivity {
    //for navigation
DrawerLayout drawerLayout;
NavigationView navigationView;
Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_account);

        loadFragment(new SellerAccountMainF());//for setting up default fragment on sellerAccountActivity



        drawerLayout=findViewById(R.id.sellDrawerLayout);
        navigationView=findViewById(R.id.sellerNavigationView);
        toolbar=findViewById(R.id.sellToolBar);


        //@@@@@@@@@@@@@@@@@@@@@@@@____________________Code for navigation drawer-----------------@@@@@@@@@@@@
        toolbar.setTitle("");       //for removing default toolbar name

        setSupportActionBar(toolbar);//setting toolbar
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.OpenDrawer,R.string.CloseDrawer);//for toggle or open or close navigation view

drawerLayout.addDrawerListener(toggle);
toggle.syncState();

navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

          int id=item.getItemId();
          if (id==R.id.settingSell){
              Toast.makeText(getApplicationContext(),"Hi I m Setting",Toast.LENGTH_SHORT).show();
             //loadFragment(new SellerAccountMainF());

          }
          else if(id==R.id.shareSell){
              Toast.makeText(getApplicationContext(),"Hi I m Share",Toast.LENGTH_SHORT).show();

          }
          else if(id==R.id.feedbackSell){
              Toast.makeText(getApplicationContext(),"Hi I m Feedback",Toast.LENGTH_SHORT).show();

          }
          else if(id==R.id.logoutSell){
              //for removing session
              SharedPreferences shared = getSharedPreferences("MyPrefs", MODE_PRIVATE);

              if(shared.contains("isAdminLogged")) {// for uploading from admin
                  SharedPreferences.Editor editor = shared.edit();
                  editor.clear();
                  editor.apply();
                  Toast.makeText(getApplicationContext(), "Admin logout successfully !", Toast.LENGTH_SHORT).show();
                  finish();
              }
              else if(shared.contains("isSellerLogged")){
                  SharedPreferences.Editor editor = shared.edit();
                  editor.clear();
                  editor.apply();
                  Toast.makeText(getApplicationContext(), "Owner logout successfully !", Toast.LENGTH_SHORT).show();
                  finish();
              }

          }
          else{
              Toast.makeText(getApplicationContext(),"Nothing",Toast.LENGTH_SHORT).show();

          }
          drawerLayout.closeDrawer(GravityCompat.START);


        return true;
    }
});

        //@@@@@@@@@@@@@@@@@@@@@@@@____________________Code for Seller  profile-----------------@@@@@@@@@@@@

//
//        //getting id from another layout for setting up phone number
//        View inflatedView = getLayoutInflater().inflate(R.layout.seller_toolbar, null);
//
//        SharedPreferences shared = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//        String s1 = (shared.getString("sellerphoneKey", ""));
//
//        TextView phone = (TextView) inflatedView.findViewById(R.id.sellerSessionPhone);
//        phone.setText("+977 "+s1);
//
//        ImageView profileImg=(ImageView) inflatedView.findViewById(R.id.sellerPP);
////
////        Drawable myDrawable = getResources().getDrawable(R.drawable.d_con);
////        profileImg.setImageDrawable(myDrawable);
//
//        phone.setVisibility(View.VISIBLE);
//        profileImg.setVisibility(View.VISIBLE);
//
//        toolbar.addView(inflatedView);
    }
        /*

        TextView t = findViewById(R.id.textView1);


        TextView t1 = findViewById(R.id.textView2);
        TextView t2 = findViewById(R.id.textView4);
        TextView t3 = findViewById(R.id.textView5);
        TextView t4 = findViewById(R.id.textView6);
        TextView t5 = findViewById(R.id.textView9);
        TextView t6 = findViewById(R.id.textView10);
        TextView t7 = findViewById(R.id.textView11);
        Button logoutBtn = findViewById(R.id.logout);


        SharedPreferences shared = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String s1 = (shared.getString("sellerphoneKey", ""));
        String s2 = (shared.getString("sellertypeKey", ""));
        String s3 = (shared.getString("sellerfirstnameKey", ""));
        String s4 = (shared.getString("sellerlastnameKey", ""));
        String s5 = (shared.getString("selleraddressKey", ""));
        String s6 = (shared.getString("selleremailKey", ""));
        String s7 = (shared.getString("sellerpassKey", ""));


        t.setText("Welcome Seller :"+s1);

        t1.setText(s1);
        t2.setText(s2);
        t3.setText(s3);
        t4.setText(s4);
        t5.setText(s5);
        t6.setText(s6);
        t7.setText(s7);

            logoutBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences.Editor editor = shared.edit();

                    editor.clear();
                    editor.apply();
                    Toast.makeText(getApplicationContext(),"Logout successfully !",Toast.LENGTH_SHORT).show();
                    finish();

                }
            });
*/


    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@-----ABOVE METHODS IMPLEMENTATION-----------------------@@@@@@@@@@@@@@@@@@@@@@@
    //-----------------------------------for navigation drawer

    //When user  goes back press on navigation drawer

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();

        }
    }


//For replacing dashboard layout by fragment layout in specified layout
    private void loadFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.sellContainer,fragment);
        fragmentTransaction.commit();
    }
}