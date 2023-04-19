package com.edu.nast.digital_contractor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;

public class LandUploadF extends Fragment implements AdapterView.OnItemSelectedListener {
    View view;
    //for feature image
    private final int IMG_REQUEST=1;
    private Bitmap bitmap;
    private String encodedFeatureImg;
    private String imgName;
    ActivityResultLauncher<String> mGetContent;

    RequestBaseUrl requestBaseUrl=new RequestBaseUrl();
    String url=  requestBaseUrl.getRequestUrl();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      view = inflater.inflate(R.layout.fragment_land_upload, container, false);

        //@@@@@@@@@@@@@@@@@@@@@@@@@@@--------------All For  Land Upload Design--------------------@@@@@@@@@@@@@@@@@

        //getting session for phone
        SharedPreferences shared = this.getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        EditText landId=(EditText) view.findViewById(R.id.landId);
        TextView landOwnerPhone=(TextView) view.findViewById(R.id.phoneNumber);

        if(shared.contains("isAdminLogged")) {//admin
            String phoneSession = (shared.getString("adminphoneKey", ""));
            landOwnerPhone.setText(phoneSession);
        }

        if(shared.contains("isSellerLogged")) {//seller
            String phoneSession = (shared.getString("sellerphoneKey", ""));
            landOwnerPhone.setText(phoneSession);
        }

        Spinner au=view.findViewById(R.id.areaUnit);
        Spinner fd= view.findViewById(R.id.facingDirection);
        Spinner p= view.findViewById(R.id.province);
        Spinner d=view.findViewById(R.id.district);
        Spinner a= view.findViewById(R.id.administration);
        Spinner wn= view.findViewById(R.id.wardNo);
        Spinner ed=view.findViewById(R.id.expiryDate);

//for image
        ImageView featureImg= view.findViewById(R.id.featureImg);
        Button featureImgBtn=view.findViewById(R.id.feature_img_btn);

        Button uploadLandProperty=(Button) view.findViewById(R.id.land_upload);


//for showing spinner item list
        ArrayAdapter<String> areaUnitAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.house_land_areaUnit));
        ArrayAdapter<String> facing_direction = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.facing_direction));
        ArrayAdapter<String> province = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.province));
        ArrayAdapter<String> district = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.district));
        ArrayAdapter<String> adminstrstion = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.administration));
        ArrayAdapter<String> ward_num = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.ward_num));
        ArrayAdapter<String> expiry_date = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.expiry_date));


        // Drop down layout style - list view with radio button
        //  areaUnitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        au.setAdapter(areaUnitAdapter);
        fd.setAdapter(facing_direction);
        p.setAdapter(province);
        d.setAdapter(district);
        a.setAdapter(adminstrstion);
        wn.setAdapter(ward_num);
        ed.setAdapter(expiry_date);
//@@@@@@@@@------only for checking below 13 code they can be remove
        //when any item is selected in spinner for areaUnit
        au.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0){
                    // get spinner value
                }else{
                    // show toast select gender
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //when any item is selected in spinner for facingDirection
        fd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0){
                    // get spinner value
                }else{
                    // show toast select gender
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //when any item is selected in spinner for province
        p.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0){
                    // get spinner value
                }else{
                    // show toast select gender
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //when any item is selected in spinner for destrict
        d.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0){
                    // get spinner value
                }else{
                    // show toast select gender
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //when any item is selected in spinner for destrict
        a.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0){
                    // get spinner value
                }else{
                    // show toast select gender
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //when any item is selected in spinner for wardNumber
        wn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0){
                    // get spinner value
                }else{
                    // show toast select gender
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //when any item is selected in spinner for expirtDate
        ed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0){
                    // get spinner value
                }else{
                    // show toast select gender
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@--------------All Code For  Upload house property-------------@@@@@@@@@@


        //___________________________picking image from gallery_____________________________________

        //feature image button
        featureImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                               mGetContent.launch("image/*");


            }
        });



        mGetContent=registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                featureImg.setImageURI(result);

                // perform desired operations using the result Uri

                if (result != null) {
                    try {

                        InputStream inputStream=getActivity().getContentResolver().openInputStream(result);
                        bitmap = BitmapFactory.decodeStream(inputStream);
                        featureImg.setImageBitmap(bitmap);

                        String  imgPath = getRealPathFromURI(result);
                        imgName=imgPath.substring(imgPath.lastIndexOf("/")+1);//finding real image name from realpath

                        //  getSelectedImageName(result);
                        encodeBitmapImage(bitmap);
                    } catch (IOException e) {
                        Intent ee=new Intent(getActivity(),ErrorExceptionActivity.class);
                        Toast.makeText(getContext(),"Land Upload Exception on onActivityResult method occur",Toast.LENGTH_SHORT).show();
                        ee.putExtra("EE","Land Upload Exception on onActivityResult method occur "+e.toString());
                        startActivity(ee);
                    }

                }
                else {

                    Toast.makeText(getContext(),"Image not selected",Toast.LENGTH_SHORT).show();

                }
            }
        });

//upload property button
        uploadLandProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadDataToDB();
            }
        });


        return  view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@--Above Method Implementation--@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    //finding real image path from selected image
    public String getRealPathFromURI (Uri contentUri) {
        String path = null;
        String[] proj = { MediaStore.MediaColumns.DATA };
        Cursor cursor = getActivity().getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path = cursor.getString(column_index);

        }
        cursor.close();
        return path;
    }
    //___________________for feature button____________________
    private void encodeBitmapImage(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] bytesofimage=byteArrayOutputStream.toByteArray();
        encodedFeatureImg=android.util.Base64.encodeToString(bytesofimage, Base64.DEFAULT);

    }

    //________________for upload button_______________________________
    private  void uploadDataToDB() {

        //String request maa object pass garnu pareko le JSON  object maa convert gareko
        //server maa receive garna sajilo hunx without using param and response maa String accept garna sakinx
        try {
            JSONObject jsonBody = new JSONObject();
            //note key name chahi server ko corresponding class maa vaako table ko name sanga match hunu parx


            EditText landId= view.findViewById(R.id.landId);
            TextView landOwnerPhone=view.findViewById(R.id.phoneNumber);
            EditText landTitle= view.findViewById(R.id.landTitle);
            EditText price= view.findViewById(R.id.price);
            EditText area= view.findViewById(R.id.area);
            Spinner au=view.findViewById(R.id.areaUnit);
            Spinner fd= view.findViewById(R.id.facingDirection);
            Spinner p= view.findViewById(R.id.province);
            Spinner d= view.findViewById(R.id.district);
            Spinner a= view.findViewById(R.id.administration);
            Spinner wn= view.findViewById(R.id.wardNo);
            EditText tole= view.findViewById(R.id.tole);
            EditText city= view.findViewById(R.id.city);
            EditText contactName= view.findViewById(R.id.contactName);
            EditText contactPhoneNumber= view.findViewById(R.id.contactPhoneNumber);
            EditText email= view.findViewById(R.id.email);
            Spinner ed= view.findViewById(R.id.expiryDate);


            jsonBody.put("landId", landId.getText().toString().trim());
            jsonBody.put("landOwnerPhone", landOwnerPhone.getText().toString().trim());
            jsonBody.put("landTitle", landTitle.getText().toString().trim());
            jsonBody.put("price", price.getText().toString().trim());
            jsonBody.put("area", area.getText().toString().trim());
            jsonBody.put("areaUnit", au.getSelectedItem().toString().trim());
            jsonBody.put("facingDirection", fd.getSelectedItem().toString().trim());
            jsonBody.put("province", p.getSelectedItem().toString().trim());
            jsonBody.put("district", d.getSelectedItem().toString().trim());
            jsonBody.put("administration",a.getSelectedItem().toString().trim());
            jsonBody.put("wardNo", wn.getSelectedItem().toString().trim());
            jsonBody.put("tole", tole.getText().toString().trim());
            jsonBody.put("city", city.getText().toString().trim());
            jsonBody.put("name", contactName.getText().toString().trim());
            jsonBody.put("contactPhoneNumber", contactPhoneNumber.getText().toString().trim());
            jsonBody.put("email", email.getText().toString().trim());
            jsonBody.put("expiryDate", ed.getSelectedItem().toString().trim());

            jsonBody.put("imageName", imgName);
            jsonBody.put("encodedImage", encodedFeatureImg);

            Date date=new Date();
            jsonBody.put("date",date.toString());

            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url + "landupload", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Toast.makeText(getActivity(), "response success"+response, Toast.LENGTH_SHORT).show();
                    if(response.toString().equals("land already uploaded")){
                        landId.requestFocus();
                        landId.setError("This land property already uploaded. Try another.");

                    }
                    else if(response.toString().equals("success")){
                        Intent l=new Intent(getActivity(),DashboardActivity.class);
                        Toast.makeText(getContext().getApplicationContext(),"Land uploaded successfully!. Check your profile."+response,Toast.LENGTH_SHORT).show();
                        l.putExtra("dashboardMsg", "Land");
                        startActivity(l);
                        getActivity().finish();
                    }
                    else{
                        Intent ee = new Intent(getActivity(), ErrorExceptionActivity.class);
                        Toast.makeText(getActivity(), "Land Upload server Error occur ", Toast.LENGTH_SHORT).show();
                        ee.putExtra("EE", "Land Upload server Error  occur" + response.toString());
                        startActivity(ee);
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Intent ee = new Intent(getActivity(), ErrorExceptionActivity.class);
                    Toast.makeText(getActivity(), "Volley Land Upload Error occur ", Toast.LENGTH_SHORT).show();
                    ee.putExtra("EE", "Volley Land Upload Error  occur" + error.toString());
                    startActivity(ee);
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }


                //String request maa JSON object send gareko le yo sab important x
                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {


                        Intent ee = new Intent(getActivity(), ErrorExceptionActivity.class);
                        Toast.makeText(getActivity(), "Land Upload Exception occur : Unsupported Encoding while trying to get the bytes of %s using ", Toast.LENGTH_SHORT).show();
                        ee.putExtra("EE", "Land Upload Exception occur : Unsupported Encoding while trying to get the bytes of %s using " + uee.toString());
                        startActivity(ee);
                        // VolleyLog.wtf("%s", requestBody, "utf-8");//CC
                        return null;
                    }
                }
//Server response ko status  null x ki xaina bhanera check gareko
//                            @Override
//                            protected Response<String> parseNetworkResponse(NetworkResponse response) {
//                                String responseString = "";
//                                if (response != null) {
//                                    responseString = String.valueOf(response.statusCode);
//                                    // can get more details such as response.headers
//                                }
//                                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
//                            }
            };
//for volley request
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

            requestQueue.add(stringRequest);
        } catch (JSONException e) {
            Intent ee = new Intent(getActivity(), ErrorExceptionActivity.class);
            Toast.makeText(getActivity(), "Land Upload JSON Exception occur : ", Toast.LENGTH_SHORT).show();
            ee.putExtra("EE", "Land Upload  Exception occur : Unsupported Encoding while trying to get the bytes of %s using " + e.toString());
            startActivity(ee);


        }
    }

}