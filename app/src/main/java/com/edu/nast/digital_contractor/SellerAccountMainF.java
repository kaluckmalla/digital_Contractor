package com.edu.nast.digital_contractor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
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

public class SellerAccountMainF extends Fragment {
View view;

    //for single user home and land recycler view
    ArrayAdapter<String > adapter;
    private ArrayList<HouseLandAccountClass> housesLandAccountArrayList;


    RequestBaseUrl requestBaseUrl=new RequestBaseUrl();
    String url=  requestBaseUrl.getRequestUrl();

    public SellerAccountMainF() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_seller_account_main, container, false);

TextView resultHomeData=view.findViewById(R.id.houseResult);
        TextView resultLandData=view.findViewById(R.id.landResult);

        TextView usertype=view.findViewById(R.id.userType);
        TextView userPhone=view.findViewById(R.id.userPhone);
        Button adminControl=view.findViewById(R.id.adminControl);


        adminControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent db = new Intent(getActivity(), ManageAppActivity.class);
                startActivity(db);
            }
        });

        //for session phone and usertype
        SharedPreferences shared = this.getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String adminPhoneSession = (shared.getString("adminphoneKey", ""));
        String adminTypeSession = (shared.getString("admintypeKey", ""));
        String sellerPhoneSession = (shared.getString("sellerphoneKey", ""));
        String sellerTypeSession = (shared.getString("sellertypeKey", ""));

        if (shared.contains("isAdminLogged")) {//admin
            usertype.setText("User : "+adminTypeSession);
            userPhone.setText("Phone No. : "+adminPhoneSession);
            adminControl.setVisibility(View.VISIBLE);

        }
        if (shared.contains("isSellerLogged")) {//seller

            usertype.setText("User : "+sellerTypeSession);
            userPhone.setText("Phone No. : "+sellerPhoneSession);
            adminControl.setVisibility(View.GONE);

        }


        if (shared.contains("isAdminLogged")) {
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@--------------for Admin house load in recycler view of profile---------@@@@@@@@@@


            // Initialize a new JsonArrayRequest instance
            JsonArrayRequest jsonHouseArrayRequest = new JsonArrayRequest(
                    Request.Method.GET,
                    url + "getsingleuserhouseproperty/"+adminPhoneSession,
                    null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            if (response.length() > 0) {

                                housesLandAccountArrayList = new ArrayList<>();

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
                                        //String courseName = responseObj.getString("courseName");
                                        String houseTitle = responseObj.getString("houseTitle");
                                        String price = responseObj.getString("price");
                                        String district = responseObj.getString("district");
                                        String administration = responseObj.getString("administration");
                                        String wardNo = responseObj.getString("wardNo");

                                        String fulladdress = administration + "- " + wardNo + ", " + district;

                                        housesLandAccountArrayList.add(new HouseLandAccountClass(houseTitle, price, fulladdress));

                                        //setting data on adapter to show recycler view one by one
                                        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.profileHouseRecycleView);

                                        LinearLayoutManager layoutManager
                                                = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
                                        recyclerView.setLayoutManager((layoutManager));


                                        recyclerView.setAdapter(new AccountFragmentAdapter(housesLandAccountArrayList));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            } else if (response.length() == 0) {
                                resultHomeData.setText("Houses property not uploaded yet");
                            } else {

                                Intent ee = new Intent(getActivity(), ErrorExceptionActivity.class);
                                Toast.makeText(getActivity(), "Single user House data retrieving server Error occur ", Toast.LENGTH_SHORT).show();
                                ee.putExtra("EE", "Single user houses data retrieving server Error  occur" + response.toString());
                                startActivity(ee);
                            }

                        }


                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Intent ee = new Intent(getActivity(), ErrorExceptionActivity.class);
                            Toast.makeText(getActivity(), "Volley recycler single user house view Error occur ", Toast.LENGTH_SHORT).show();
                            ee.putExtra("EE", "Volley recycler single user house view Error occur " + error.toString());
                            startActivity(ee);

                        }
                    }
            );
            // Add JsonArrayRequest to the RequestQueue
            Volley.newRequestQueue(getActivity()).add(jsonHouseArrayRequest);


            //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@--------------for Admin land load in recycler view of profile---------@@@@@@@@@@
            // Initialize a new JsonArrayRequest instance
            JsonArrayRequest jsonLandArrayRequest = new JsonArrayRequest(
                    Request.Method.GET,
                    url + "getsingleuserlandproperty/"+adminPhoneSession,
                    null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            if (response.length() > 0) {

                                housesLandAccountArrayList = new ArrayList<>();

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
                                        //String courseName = responseObj.getString("courseName");
                                        String houseTitle = responseObj.getString("landTitle");
                                        String price = responseObj.getString("price");
                                        String district = responseObj.getString("district");
                                        String administration = responseObj.getString("administration");
                                        String wardNo = responseObj.getString("wardNo");

                                        String fulladdress = administration + "- " + wardNo + ", " + district;

                                        housesLandAccountArrayList.add(new HouseLandAccountClass(houseTitle, price, fulladdress));

                                        //setting data on adapter to show recycler view one by one
                                        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.profileLandRecycleView);

                                        LinearLayoutManager layoutManager
                                                = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
                                        recyclerView.setLayoutManager((layoutManager));


                                        recyclerView.setAdapter(new AccountFragmentAdapter(housesLandAccountArrayList));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            } else if (response.length() == 0) {
                                resultLandData.setText("Land property not uploaded yet");
                            } else {

                                Intent ee = new Intent(getActivity(), ErrorExceptionActivity.class);
                                Toast.makeText(getActivity(), "Single user Land data retrieving server Error occur ", Toast.LENGTH_SHORT).show();
                                ee.putExtra("EE", "Single user Land data retrieving server Error  occur" + response.toString());
                                startActivity(ee);
                            }

                        }


                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Intent ee = new Intent(getActivity(), ErrorExceptionActivity.class);
                            Toast.makeText(getActivity(), "Volley recycler single user house view Error occur ", Toast.LENGTH_SHORT).show();
                            ee.putExtra("EE", "Volley recycler single user house view Error occur " + error.toString());
                            startActivity(ee);

                        }
                    }
            );
            // Add JsonArrayRequest to the RequestQueue
            Volley.newRequestQueue(getActivity()).add(jsonLandArrayRequest);
        }





        if (shared.contains("isSellerLogged")) {
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@--------------for Seller house load in recycler view of profile---------@@@@@@@@@@


            // Initialize a new JsonArrayRequest instance
            JsonArrayRequest jsonHouseArrayRequest = new JsonArrayRequest(
                    Request.Method.GET,
                    url + "getsingleuserhouseproperty/"+sellerPhoneSession,
                    null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            if (response.length() > 0) {

                                housesLandAccountArrayList = new ArrayList<>();

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
                                        //String courseName = responseObj.getString("courseName");
                                        String houseTitle = responseObj.getString("houseTitle");
                                        String price = responseObj.getString("price");
                                        String district = responseObj.getString("district");
                                        String administration = responseObj.getString("administration");
                                        String wardNo = responseObj.getString("wardNo");

                                        String fulladdress = administration + "- " + wardNo + ", " + district;

                                        housesLandAccountArrayList.add(new HouseLandAccountClass(houseTitle, price, fulladdress));

                                        //setting data on adapter to show recycler view one by one
                                        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.profileHouseRecycleView);

                                        LinearLayoutManager layoutManager
                                                = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
                                        recyclerView.setLayoutManager((layoutManager));


                                        recyclerView.setAdapter(new AccountFragmentAdapter(housesLandAccountArrayList));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            } else if (response.length() == 0) {
                                resultHomeData.setText("Houses property not uploaded yet");
                            } else {

                                Intent ee = new Intent(getActivity(), ErrorExceptionActivity.class);
                                Toast.makeText(getActivity(), "Single user House data retrieving server Error occur ", Toast.LENGTH_SHORT).show();
                                ee.putExtra("EE", "Single user houses data retrieving server Error  occur" + response.toString());
                                startActivity(ee);
                            }

                        }


                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Intent ee = new Intent(getActivity(), ErrorExceptionActivity.class);
                            Toast.makeText(getActivity(), "Volley recycler single user house view Error occur ", Toast.LENGTH_SHORT).show();
                            ee.putExtra("EE", "Volley recycler single user house view Error occur " + error.toString());
                            startActivity(ee);

                        }
                    }
            );
            // Add JsonArrayRequest to the RequestQueue
            Volley.newRequestQueue(getActivity()).add(jsonHouseArrayRequest);


            //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@--------------for Seller land load in recycler view of profile---------@@@@@@@@@@
            // Initialize a new JsonArrayRequest instance
            JsonArrayRequest jsonLandArrayRequest = new JsonArrayRequest(
                    Request.Method.GET,
                    url + "getsingleuserlandproperty/"+sellerPhoneSession,
                    null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            if (response.length() > 0) {

                                housesLandAccountArrayList = new ArrayList<>();

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
                                        //String courseName = responseObj.getString("courseName");
                                        String houseTitle = responseObj.getString("landTitle");
                                        String price = responseObj.getString("price");
                                        String district = responseObj.getString("district");
                                        String administration = responseObj.getString("administration");
                                        String wardNo = responseObj.getString("wardNo");

                                        String fulladdress = administration + "- " + wardNo + ", " + district;

                                        housesLandAccountArrayList.add(new HouseLandAccountClass(houseTitle, price, fulladdress));

                                        //setting data on adapter to show recycler view one by one
                                        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.profileLandRecycleView);

                                        LinearLayoutManager layoutManager
                                                = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
                                        recyclerView.setLayoutManager((layoutManager));


                                        recyclerView.setAdapter(new AccountFragmentAdapter(housesLandAccountArrayList));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            } else if (response.length() == 0) {
                                resultLandData.setText("Land property not uploaded yet");
                            } else {

                                Intent ee = new Intent(getActivity(), ErrorExceptionActivity.class);
                                Toast.makeText(getActivity(), "Single user Land data retrieving server Error occur ", Toast.LENGTH_SHORT).show();
                                ee.putExtra("EE", "Single user Land data retrieving server Error  occur" + response.toString());
                                startActivity(ee);
                            }

                        }


                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Intent ee = new Intent(getActivity(), ErrorExceptionActivity.class);
                            Toast.makeText(getActivity(), "Volley recycler single user house view Error occur ", Toast.LENGTH_SHORT).show();
                            ee.putExtra("EE", "Volley recycler single user house view Error occur " + error.toString());
                            startActivity(ee);

                        }
                    }
            );
            // Add JsonArrayRequest to the RequestQueue
            Volley.newRequestQueue(getActivity()).add(jsonLandArrayRequest);
        }


        return view;
    }


}