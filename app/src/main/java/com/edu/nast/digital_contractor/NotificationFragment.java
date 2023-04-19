package com.edu.nast.digital_contractor;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NotificationFragment extends Fragment {

    //for recycler view
    private ArrayList<NotificationPojoClass> notificationArrayList;


    RequestBaseUrl requestBaseUrl=new RequestBaseUrl();
    String url=  requestBaseUrl.getRequestUrl();

    public NotificationFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view= inflater.inflate(R.layout.fragment_notification, container, false);


        // Initialize a new JsonArrayRequest instance
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url+"notification",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response.length()>0){

                            notificationArrayList = new ArrayList<>();

                            // Process the JSON
                            for (int i = 0; i < response.length(); i++) {
                                // creating a new json object and
                                // getting each object from our json array.
                                try {
                                    // below line we are creating a new array list
                                    // we are getting each json object.
                                    JSONObject responseObj = response.getJSONObject(i);

                                    // now we get our response from API in json object format.

                                    // similarly we are extracting all the strings from our json object.
                                    String adminPhone = responseObj.getString("adminPhone");
                                    String Notification = responseObj.getString("notification");
                                    String Date = responseObj.getString("date");

                                    notificationArrayList.add(new NotificationPojoClass( adminPhone,  Notification,  Date));

                                    //setting data on adapter to show recycler view one by one
                                    RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.notificationRecycleView);

                                    LinearLayoutManager layoutManager
                                            = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
                                    recyclerView.setLayoutManager((layoutManager));

                                    recyclerView.setAdapter(new NotificationAdapter(notificationArrayList,getContext()));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                        else if (response.length() == 0) {
                            Toast.makeText(getActivity(), "No notification published yet.", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Intent ee = new Intent(getActivity(), ErrorExceptionActivity.class);
                            Toast.makeText(getActivity(), "Notification retrieving server Error occur ", Toast.LENGTH_SHORT).show();
                            ee.putExtra("EE", "Notification retrieving server Error  occur" + response.toString());
                            startActivity(ee);
                        }
                    }

                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Intent ee = new Intent(getActivity(), ErrorExceptionActivity.class);
                        Toast.makeText(getActivity(),"Volley recycler notification view Error occur ",Toast.LENGTH_SHORT).show();
                        ee.putExtra("EE", "Volley recycler notification view Error occur "+error.toString());
                        startActivity(ee);

                    }
                }
        );
        // Add JsonArrayRequest to the RequestQueue
        Volley.newRequestQueue(getActivity()).add(jsonArrayRequest);

        return view;
    }
}