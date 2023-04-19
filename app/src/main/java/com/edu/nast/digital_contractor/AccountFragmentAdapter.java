package com.edu.nast.digital_contractor;

import android.icu.text.Transliterator;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AccountFragmentAdapter extends RecyclerView.Adapter<AccountFragmentAdapter.AccountFragmentViewHolder>{
    // creating a variable for array list and context.

    private ArrayList<HouseLandAccountClass> housesLandAccountArrayList;
    public AccountFragmentAdapter(ArrayList<HouseLandAccountClass> housesLandAccountArrayList) {
        this.housesLandAccountArrayList = housesLandAccountArrayList;
    }

    @NonNull
    @Override
    public AccountFragmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is to inflate our layout.

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.seller_house_cardview,parent,false);
        return new AccountFragmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountFragmentViewHolder holder, int position) {

        // setting data to our views of recycler view.


        HouseLandAccountClass modal = housesLandAccountArrayList.get(position);
        holder.rowTitle.setText(modal.getTitle());
        holder.rowPrice.setText(modal.getPrice());
        holder.rowLocation.setText(modal.getAddress());

    }

    @Override
    public int getItemCount() {
        // returning the size of array list.

        return housesLandAccountArrayList.size();

    }

    public class AccountFragmentViewHolder extends RecyclerView.ViewHolder{
        // creating variables for our views.

        TextView rowTitle;
        TextView  rowPrice;
        TextView  rowLocation;

        public AccountFragmentViewHolder(@NonNull View itemView) {
            super(itemView);

            // initializing our views with their ids.

            rowTitle=(TextView)  itemView.findViewById(R.id.rowTitle);
            rowPrice=(TextView)  itemView.findViewById(R.id.rowPrice);
            rowLocation=(TextView)  itemView.findViewById(R.id.rowLocation);


        }
    }

}
