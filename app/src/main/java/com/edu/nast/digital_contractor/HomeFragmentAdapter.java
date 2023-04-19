package com.edu.nast.digital_contractor;

import android.content.Context;
import android.content.Intent;
import android.icu.text.Transliterator;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeFragmentAdapter extends RecyclerView.Adapter<HomeFragmentAdapter.HomeFragmentViewHolder>{
    // creating a variable for array list and context.

    private ArrayList<HousePojoClass> housesArrayList;
    private Context context;

    public HomeFragmentAdapter(ArrayList<HousePojoClass> housesArrayList,Context context) {
        this.housesArrayList = housesArrayList;

        this.context = context;

    }

    @NonNull
    @Override
    public HomeFragmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is to inflate our layout.

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.main_fragment_card_view,parent,false);
        return new HomeFragmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeFragmentViewHolder holder, int position) {

        // setting data to our views of recycler view.


        HousePojoClass modal = housesArrayList.get(position);

        holder.rowTitle.setText(modal.getHouseTitle());
        holder.rowPrice.setText("NPR. : "+modal.getPrice());
        holder.rowLocation.setText(modal.getAdministration()+"-"+modal.getWardNo()+", "+modal.getDistrict());


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,HouseDetailsActivity.class);
                intent.putExtra("houseId",modal.getHouseId());
                intent.putExtra("houseTitle",modal.getHouseTitle());
                intent.putExtra("price",modal.getPrice());
                intent.putExtra("area",modal.getArea());
                intent.putExtra("areaUnit",modal.getAreaUnit());
                intent.putExtra("buildInArea",modal.getBuildInArea());
                intent.putExtra("buildInAreaUnit",modal.getBuildInAreaUnit());
                intent.putExtra("facingDirection",modal.getFacingDirection());
                intent.putExtra("buildingAge",modal.getBuildingAge());
                intent.putExtra("noOfFloors",modal.getNoOfFloors());
                intent.putExtra("totalRooms",modal.getTotalRooms());
                intent.putExtra("bedRooms",modal.getBedRooms());
                intent.putExtra("bathRooms",modal.getBathRooms());
                intent.putExtra("kitchenRooms",modal.getKitchenRooms());
                intent.putExtra("province",modal.getProvince());
                intent.putExtra("district",modal.getDistrict());
                intent.putExtra("administration",modal.getAdministration());
                intent.putExtra("wardNo",modal.getWardNo());
                intent.putExtra("tole",modal.getTole());
                intent.putExtra("city",modal.getCity());
                intent.putExtra("name",modal.getName());
                intent.putExtra("contactPhoneNumber",modal.getContactPhoneNumber());
                intent.putExtra("email",modal.getEmail());


                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        // returning the size of array list.

        return housesArrayList.size();

    }

    public class HomeFragmentViewHolder extends RecyclerView.ViewHolder{
        // creating variables for our views.

        TextView rowTitle;
        TextView  rowPrice;
        TextView  rowLocation;
        public CardView cardView;

        public HomeFragmentViewHolder(@NonNull View itemView) {
            super(itemView);

            // initializing our views with their ids.

            rowTitle=(TextView)  itemView.findViewById(R.id.rowTitle);
            rowPrice=(TextView)  itemView.findViewById(R.id.rowPrice);
            rowLocation=(TextView)  itemView.findViewById(R.id.rowLocation);
            cardView = (CardView)itemView.findViewById(R.id.mainFragmentCardView);


        }
    }

}
