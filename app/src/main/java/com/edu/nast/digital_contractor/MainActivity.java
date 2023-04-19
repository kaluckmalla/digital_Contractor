package com.edu.nast.digital_contractor;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import kotlin.jvm.internal.Ref;

public class MainActivity extends AppCompatActivity{
private Button uploadBn,chooseBn;
private EditText NAME;
private ImageView imgView;
    RequestBaseUrl requestBaseUrl=new RequestBaseUrl();
    String url=  requestBaseUrl.getRequestUrl();

    private String encodeImageString;



private Bitmap bitmap;
private final int IMG_REQUEST=1;
ActivityResultLauncher<String> mGetContent;



@Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ImageView imgView = (ImageView) findViewById(R.id.imageView);
   // EditText NAME = (EditText) findViewById(R.id.name);
    Button chooseBn = (Button) findViewById(R.id.choseImage);
    Button uploadBn = (Button) findViewById(R.id.uploadImage);






    mGetContent=registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
        @Override
        public void onActivityResult(Uri result) {
            imgView.setImageURI(result);

                // perform desired operations using the result Uri
            Log.d("reeeeeeeee", "onActivityResult: "+result);

            if (result != null) {
                try {

                    InputStream inputStream=getContentResolver().openInputStream(result);
                    bitmap = BitmapFactory.decodeStream(inputStream);
                    imgView.setImageBitmap(bitmap);

String path=result.getPath();
                    String filename=path.substring(path.lastIndexOf("/")+1);

                    Log.d("reeeeeeeee", "path"+path);
                    Log.d("reeeeeeeee", "path"+filename);


                  //  getSelectedImageName(result);
                    encodeBitmapImage(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();

                }

            }
            else {
            Log.d("reeeeeeeee", "onActivityResult: the result is null for some reason");
        }
        }
    });

chooseBn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        mGetContent.launch("image/*");

    }
});







//    chooseBn.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Dexter.withActivity(MainActivity.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener
//                    (new PermissionListener() {
//                         @Override
//                         public void onPermissionGranted(PermissionGrantedResponse response) {
//                             Intent intent = new Intent(Intent.ACTION_PICK);
//                             intent.setType("image/*");
//                             startActivityForResult(Intent.createChooser(intent, "Choose image"), IMG_REQUEST);
//                         }
//
//                         @Override
//                         public void onPermissionDenied(PermissionDeniedResponse response) {
//
//                         }
//
//                         @Override
//                         public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
//                             token.continuePermissionRequest();
//                         }
//                     }
//                    );
//        }
//    });

    uploadBn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        uploadDataToDB();
    }
});

}

//@@@@@@@@@@@@-------for chooseImage button result----------------@@@@@@@@@@@@@@@@@@@@@
 @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data) {

     super.onActivityResult(requestCode, resultCode, data);
     if (requestCode == IMG_REQUEST && requestCode == RESULT_OK && data != null) {
         Uri filepath = data.getData();
         try {

InputStream inputStream=getContentResolver().openInputStream(filepath);
             bitmap = BitmapFactory.decodeStream(inputStream);
             imgView.setImageBitmap(bitmap);
encodeBitmapImage(bitmap);
         } catch (IOException e) {
             e.printStackTrace();

         }

     }
 }

 private void encodeBitmapImage(Bitmap bitmap){
     ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
     bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
     byte[] bytesofimage=byteArrayOutputStream.toByteArray();
     encodeImageString=android.util.Base64.encodeToString(bytesofimage,Base64.DEFAULT);

 }

////for finding name of image
//    private String getSelectedImageName(Uri result){
//        Uri returnUri = result.getData();
//        if(returnUri != null){
//            Cursor returnCursor =
//                    getContentResolver().query(returnUri, null, null, null, null);
//            /*
//             * Get the column indexes of the data in the Cursor,
//             * move to the first row in the Cursor, get the data,
//             * and display it.
//             */
//            int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
//            String fileName = returnCursor.getString(nameIndex);
//
//            return fileName ;
//
//        }else{
//            return "No data found"
//        }
//    }

    //@@@@@@@@@@@@-------for upload button result----------------@@@@@@@@@@@@@@@@@@@@@
private  void uploadDataToDB(){
    // NAME = (EditText) findViewById(R.id.name);

   // final String name=NAME.getText().toString().trim();



    StringRequest stringRequest=new StringRequest(Request.Method.POST, url+"upload", new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {

                Toast.makeText(getApplicationContext(),"response success",Toast.LENGTH_SHORT).show();
            Log.d("reeeeeeeee", "responseeeee");


        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            error.printStackTrace();
            Log.d("reeeeeeeee", "error     volley");

            Toast.makeText(getApplicationContext(),"Error Volley"+error,Toast.LENGTH_SHORT).show();

        }
    })
    {
        // Passing some request headers
        @Override
        public Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> param = new HashMap<String, String>();
            param.put("name", "hello");
            param.put("image", encodeImageString);

           //param.put("Content-Type", "multipart/form-data; charset=utf-8");
            return param;
        }

    };

//for volley request
    RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
    requestQueue.add(stringRequest);

}
}

/*
        chooseBn.setOnClickListener(this);
        uploadBn.setOnClickListener(this);


        mGetContent=registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
imgView.setImageURI(result);
            }
        });




    }

    @Override
    public void onClick(View v) {
        switch ((v.getId())) {
            case R.id.choseImage:
                mGetContent.launch("image/*");

                break;
        }
}

*/
//    private void selectImage(){
//        Intent intent=new Intent();
//        intent.setType("image/*");
//        intent.setAction((Intent.ACTION_GET_CONTENT));
//        startActivityForResult(Intent.createChooser(intent, "Select Picture"), IMG_REQUEST);
//
//    }

        // @Override
//    protected void onActivityResult(int requestCode,int resultCode,Intent data){
//        super.onActivityResult(requestCode,resultCode,data);
//
//        if(requestCode==IMG_REQUEST && requestCode==RESULT_OK && data!=null){
//            Uri path=data.getData();
//            try {
//
//
//                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
//                imgView.setImageBitmap(bitmap);
//
//            } catch(IOException e){
//                e.printStackTrace();
//            }
//        }
