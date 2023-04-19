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

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationFragmentViewHolder>{
    // creating a variable for array list and context.

    private ArrayList<NotificationPojoClass> notificationArrayList;
    private Context context;

    public NotificationAdapter(ArrayList<NotificationPojoClass> notificationArrayList,Context context) {
        this.notificationArrayList = notificationArrayList;

        this.context = context;

    }

    @NonNull
    @Override
    public NotificationFragmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is to inflate our layout.

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.notification_row_cardview,parent,false);
        return new NotificationFragmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationFragmentViewHolder holder, int position) {

        // setting data to our views of recycler view.


        NotificationPojoClass modal = notificationArrayList.get(position);

        holder.rowNotificationDate.setText(modal.getDate());
        holder.rowNotification.setText(modal.getNotification());


    }

    @Override
    public int getItemCount() {
        // returning the size of array list.

        return notificationArrayList.size();

    }

    public class NotificationFragmentViewHolder extends RecyclerView.ViewHolder{
        // creating variables for our views.

        TextView rowNotificationDate;
        TextView  rowNotification;
        public CardView cardView;

        public NotificationFragmentViewHolder(@NonNull View itemView) {
            super(itemView);

            // initializing our views with their ids.

            rowNotificationDate=(TextView)  itemView.findViewById(R.id.rowNotificationDate);
            rowNotification=(TextView)  itemView.findViewById(R.id.rowNotification);
            cardView = (CardView)itemView.findViewById(R.id.notificationFragmentCardView);


        }
    }

}
