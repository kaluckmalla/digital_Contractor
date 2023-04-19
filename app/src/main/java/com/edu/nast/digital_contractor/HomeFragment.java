package com.edu.nast.digital_contractor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    View view;

    //for search data
    SearchView searchView;
    ListView listView;
    ArrayAdapter<String > adapter;
    ArrayList<String> data=new ArrayList<String>();//for list view data

    //for recycler view
    private ArrayList<HousePojoClass> housesArrayList;
    private ArrayList<landPojoClass> landArrayList;

    RequestBaseUrl requestBaseUrl=new RequestBaseUrl();
  String url=  requestBaseUrl.getRequestUrl();


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Code for login button
        // Inflate the layout for this fragment


         view = inflater.inflate(R.layout.fragment_home, container, false);
        TextView loginView = (TextView) view.findViewById(R.id.go_login);

        //@@@@@@@@@@@@@@@@@@@@@@@@@@----------for retrieving session for sending Active text in login textview if user already login

        SharedPreferences shared = this.getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        if (shared.contains("isAdminLogged")) {//admin
            loginView.setText("AActive");

            loginView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "Admin active now. Go to Account for logout.", Toast.LENGTH_SHORT).show();


                }
            });
        } else if (shared.contains("isSellerLogged")) {//seller
            loginView.setText("SActive");

            loginView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "You are active now. Go to Account for logout.", Toast.LENGTH_SHORT).show();
                }
            });
        } else {//if session of any user not exist
            loginView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent l = new Intent(getActivity(), LoginActivity.class);
                    startActivity(l);

                }
            });
        }


//@@@@@@@@@@@@@@@@@-----------------for Recycler view showing on dashboard as home fragment  by loading houses or land from database
//for loading home/land properties in recycler view
//taking put extra message from launcher activity and showing related property in dashboard by loading home fragment
String extraMsg=null;
        Intent i = getActivity().getIntent();

         extraMsg = i.getStringExtra("dashboardMsg");
if(extraMsg==null){
    Toast.makeText(getActivity(),"Data not available. You are entering without launcher activity. ",Toast.LENGTH_SHORT).show();
}
        //for house
else if(extraMsg.equals("House")){
        // Initialize a new JsonArrayRequest instance
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url+"gethouseproperty",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        if(response.length()>0){

                        housesArrayList = new ArrayList<>();

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
                                String houseId = responseObj.getString("houseId");
                                long houseOwnerPhone = responseObj.getLong("houseOwnerPhone");
                                String houseTitle = responseObj.getString("houseTitle");
                                String price = responseObj.getString("price");
                                String area = responseObj.getString("area");
                                String areaUnit = responseObj.getString("areaUnit");
                                String buildInArea = responseObj.getString("buildInArea");
                                String buildInAreaUnit = responseObj.getString("buildInAreaUnit");
                                String facingDirection = responseObj.getString("facingDirection");
                                String buildingAge = responseObj.getString("buildingAge");
                                String noOfFloors = responseObj.getString("noOfFloors");
                                String totalRooms = responseObj.getString("totalRooms");
                                String bedRooms = responseObj.getString("bedRooms");
                                String bathRooms = responseObj.getString("bathRooms");
                                String kitchenRooms = responseObj.getString("kitchenRooms");
                                String province = responseObj.getString("province");
                                String district = responseObj.getString("district");
                                String administration = responseObj.getString("administration");
                                String wardNo = responseObj.getString("wardNo");
                                String tole = responseObj.getString("tole");
                                String city = responseObj.getString("city");
                                String name = responseObj.getString("name");
                                String contactPhoneNumber = responseObj.getString("contactPhoneNumber");
                                String email = responseObj.getString("email");
                                String expiryDate = responseObj.getString("expiryDate");
                                String date = responseObj.getString("date");



                                housesArrayList.add(new HousePojoClass( houseId,  houseOwnerPhone,  houseTitle,  price,  area,  areaUnit,  buildInArea,  buildInAreaUnit,  facingDirection,  buildingAge,  noOfFloors,  totalRooms,  bedRooms,  bathRooms,  kitchenRooms,  province,  district,  administration,  wardNo,  tole,  city,  name,  contactPhoneNumber,  email,  expiryDate,  date));

                                //setting data on adapter to show recycler view one by one
                                RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dashboardRecycleView);
                                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                                recyclerView.setLayoutManager((gridLayoutManager));

                                recyclerView.setAdapter(new HomeFragmentAdapter(housesArrayList,getContext()));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                        else if (response.length() == 0) {
                            Toast.makeText(getActivity(), "House property not found..", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Intent ee = new Intent(getActivity(), ErrorExceptionActivity.class);
                            Toast.makeText(getActivity(), "House data retrieving server Error occur ", Toast.LENGTH_SHORT).show();
                            ee.putExtra("EE", "House data retrieving server Error  occur" + response.toString());
                            startActivity(ee);
                        }

                    }


                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Intent ee = new Intent(getActivity(), ErrorExceptionActivity.class);
                        Toast.makeText(getActivity(),"Volley recycler house view Error occur ",Toast.LENGTH_SHORT).show();
                        ee.putExtra("EE", "Volley recycler house view Error occur "+error.toString());
                        startActivity(ee);

                    }
                }
        );
    // Add JsonArrayRequest to the RequestQueue
    Volley.newRequestQueue(getActivity()).add(jsonArrayRequest);

}
//for land
        else if(extraMsg.equals("Land")){

             // Initialize a new JsonArrayRequest instance
    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
            Request.Method.GET,
            url+"getlandproperty",
            null,
            new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    if(response.length()>0){

                        landArrayList = new ArrayList<>();

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
                                String landId = responseObj.getString("landId");
                                long landOwnerPhone = responseObj.getLong("landOwnerPhone");;
                                String landTitle = responseObj.getString("landTitle");
                                String price = responseObj.getString("price");
                                String area = responseObj.getString("area");
                                String areaUnit = responseObj.getString("areaUnit");
                                String facingDirection = responseObj.getString("facingDirection");
                                String province = responseObj.getString("province");
                                String district = responseObj.getString("district");
                                String administration = responseObj.getString("administration");
                                String wardNo = responseObj.getString("wardNo");
                                String tole = responseObj.getString("tole");
                                String city = responseObj.getString("city");
                                String name = responseObj.getString("name");
                                String contactPhoneNumber = responseObj.getString("contactPhoneNumber");
                                String email = responseObj.getString("email");
                                String expiryDate = responseObj.getString("expiryDate");
                                String date = responseObj.getString("date");



                                landArrayList.add(new landPojoClass( landId,  landOwnerPhone,  landTitle, price, area, areaUnit, facingDirection, province,
                                        district, administration, wardNo, tole, city, name, contactPhoneNumber, email, expiryDate, date));

                                    //setting data on adapter to show recycler view one by one
                                    RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dashboardRecycleView);
                                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                                recyclerView.setLayoutManager((gridLayoutManager));

                                recyclerView.setAdapter(new HomeFragmentAdapter1(landArrayList,getContext()));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                    else if (response.length() == 0) {
                        Toast.makeText(getActivity(), "Land property not found..", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Intent ee = new Intent(getActivity(), ErrorExceptionActivity.class);
                        Toast.makeText(getActivity(), "land data retrieving server Error occur ", Toast.LENGTH_SHORT).show();
                        ee.putExtra("EE", "land data retrieving server Error  occur" + response.toString());
                        startActivity(ee);
                    }

                }


            },
            new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error){
                    Intent ee = new Intent(getActivity(), ErrorExceptionActivity.class);
                    Toast.makeText(getActivity(),"Volley recycler land view Error occur ",Toast.LENGTH_SHORT).show();
                    ee.putExtra("EE", "Volley recycler land view Error occur "+error.toString());
                    startActivity(ee);

                }
            }
    );
    // Add JsonArrayRequest to the RequestQueue
    Volley.newRequestQueue(getActivity()).add(jsonArrayRequest);

}


        //@@@@@@@@@@@@@@@@@@@@@@@-----------------for searchbar with listview-----------------@@@@@@@@@@@@@@@@

       searchView = view.findViewById(R.id.searchView);
        listView = view.findViewById(R.id.lv1);
       listView.setVisibility(View.GONE);

        data = new ArrayList<>();
        data.add("Achham");
        data.add("Baitadi");
        data.add("Bajhang");
        data.add("Bajura");
        data.add("Dadeldhura");
        data.add("Darchula");
        data.add("Doti");
        data.add("Kailali");
        data.add("Kanchanpur");

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 listView.setVisibility(View.VISIBLE);

        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                if(data.contains(query)){
                    adapter.getFilter().filter(query);

                }else{
                    Toast.makeText(getActivity(), "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //    adapter.getFilter().filter(newText);
                return false;
            }
        });
            }
        });
        return view;
    }
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@---Above method Implementation LIST---------@@@@@@@@@@@@@@@@@@


}