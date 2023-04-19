package com.edu.nast.digital_contractor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class LandDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land_details);

        TextView landTitle=findViewById(R.id.landTitle);
        TextView price=findViewById(R.id.price);
        TextView area=findViewById(R.id.area);
        TextView facingDirection=findViewById(R.id.facingDirection);
        TextView province=findViewById(R.id.province);
        TextView district=findViewById(R.id.district);
        TextView tole=findViewById(R.id.tole);
        TextView name=findViewById(R.id.name);
        TextView contactPhoneNumber=findViewById(R.id.contactPhoneNumber);
        TextView email=findViewById(R.id.email);

        Bundle extras = getIntent().getExtras();

        landTitle.setText(extras.getString("landTitle"));
        price.setText("NPR. "+extras.getString("price"));
        area.setText("Total area : "+extras.getString("area")+" "+extras.getString("areaUnit"));
        facingDirection.setText("Facing direction : "+extras.getString("facingDirection"));
         province.setText(extras.getString("province"));
        district.setText("Property address : "+extras.getString("administration")+", "+extras.getString("wardNo")+", "+extras.getString("district"));
        tole.setText("Tole : "+extras.getString("tole")+", City : "+extras.getString("city"));
        name.setText("Name : "+extras.getString("name"));
        contactPhoneNumber.setText("Phone number : "+extras.getString("contactPhoneNumber"));
        email.setText("Email : "+extras.getString("email"));


    }
}