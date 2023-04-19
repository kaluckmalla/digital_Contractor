package com.edu.nast.digital_contractor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HouseDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_details);

        TextView houseTitle=findViewById(R.id.houseTitle);
        TextView price=findViewById(R.id.price);
        TextView area=findViewById(R.id.area);
        TextView buildInArea=findViewById(R.id.buildInArea);
        TextView facingDirection=findViewById(R.id.facingDirection);
        TextView buildingAge=findViewById(R.id.buildingAge);
        TextView noOfFloors=findViewById(R.id.noOfFloors);
        TextView totalRooms=findViewById(R.id.totalRooms);
        TextView bedRooms=findViewById(R.id.bedRooms);
        TextView province=findViewById(R.id.province);
        TextView district=findViewById(R.id.district);
        TextView tole=findViewById(R.id.tole);
        TextView name=findViewById(R.id.name);
        TextView contactPhoneNumber=findViewById(R.id.contactPhoneNumber);
        TextView email=findViewById(R.id.email);

        Bundle extras = getIntent().getExtras();

        houseTitle.setText(extras.getString("houseTitle"));
        price.setText("NPR. "+extras.getString("price"));
        area.setText("Total area : "+extras.getString("area")+" "+extras.getString("areaUnit"));
        buildInArea.setText("Build in area : "+extras.getString("buildInArea")+" "+extras.getString("buildInAreaUnit"));
        facingDirection.setText("Facing direction : "+extras.getString("facingDirection"));
        buildingAge.setText("Building age : "+extras.getString("buildingAge"));
        noOfFloors.setText("Total floors : "+extras.getString("noOfFloors"));
        totalRooms.setText("Total rooms : "+extras.getString("totalRooms"));
        bedRooms.setText("Bed rooms : "+extras.getString("bedRooms")+", Bath rooms : "+extras.getString("bathRooms")+", Kitchen rooms : "+extras.getString("kitchenRooms"));
        province.setText(extras.getString("province"));
        district.setText("Property address : "+extras.getString("administration")+", "+extras.getString("wardNo")+", "+extras.getString("district"));
        tole.setText("Tole : "+extras.getString("tole")+", City : "+extras.getString("city"));
        name.setText("Name : "+extras.getString("name"));
        contactPhoneNumber.setText("Phone number : "+extras.getString("contactPhoneNumber"));
        email.setText("Email : "+extras.getString("email"));


    }
}