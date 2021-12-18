package com.example.inspectionapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;

import org.bouncycastle.util.test.Test;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import retrofit2.http.Tag;

public class ListItemActivity extends AppCompatActivity {

    int selectedItemIndex;
    TextInputEditText txtFinding,txtRecommendation,txtNotes;
    TextView txtitemnametitle;
    Bitmap problemPic;
    String ItemName,ItemStatus,finding,recomendation,notes;
    String TAG="MY TAG";
    // two Buttons
    Button btnSelectImage,btnSave;
    // One Preview Image
    ImageView IVPreviewImage;

    // constant to compare
    // the activity result code
    int REQUEST_CODE=200;
    public static int RC_PHOTO_PICKER = 0;
    int SELECT_PICTURE = 200;
    private File imageFile;
    private long maxImageSize=100; //100kb
    private ImageSwitcher imagePost;
    private Uri imageUri, selectedImageUri ;
    Bitmap bitmap;

    ActivityResultLauncher<Intent>  openGalleryForImageResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        Uri uri = data.getData();
                        //ImageView imageView = R.id.img;
                        IVPreviewImage.setImageURI(uri);

                     //--   Glide.with(IVPreviewImage.getContext()).asBitmap().load(uri).into(IVPreviewImage);
                    //    if(data != null && data.getExtras() != null){
                        //   Bitmap photoBitmap = (Bitmap) data.getExtras().get("data");
                        // bitmap = convertBitmap(uri.toString());

                       // if (photoBitmap != null) {
                             //   myApp.getInstance().getChecklist().listItem.get(selectedItemIndex).setProblemPic(bitmap);
                               // problemPic=bitmap;


                                InputStream imageStream = null;
                                try{
                                    imageStream = getContentResolver().openInputStream(uri);
                                    problemPic = BitmapFactory.decodeStream(imageStream);
                                }
                                catch (FileNotFoundException e){
                                    e.printStackTrace();
                                }
                        try {
                            imageStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }}
                  //  }
              //  }
            });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);

        //permission for external storage for file
        ActivityCompat.requestPermissions(ListItemActivity.this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                REQUEST_CODE);
       setContentToItems(selectedItemIndex);

       // IVPreviewImage.setImageBitmap(problemPic);


        btnSave=findViewById(R.id.btnSave);
        txtFinding=(TextInputEditText)(findViewById(R.id.txtFinding));
        txtRecommendation=(TextInputEditText)(findViewById(R.id.txtRecommendation));
        txtNotes=(TextInputEditText)(findViewById(R.id.txtNotes));
            // register the UI widgets with their appropri""ate IDs
        btnSelectImage = findViewById(R.id.btnUploadPic);
        IVPreviewImage=(ImageView) findViewById(R.id.img);

        // IVPreviewImage.setImageBitmap(convertImageViewToBitmap(IVPreviewImage));
//        if (IVPreviewImage.getDrawable()!=null) {
//            problemPic = ((BitmapDrawable) IVPreviewImage.getDrawable()).getBitmap();
//        }
        txtitemnametitle=(TextView) findViewById(R.id.txtitemnametitle);

        Intent fromChecklistintent = getIntent();

        if (savedInstanceState!=null){
            selectedItemIndex= savedInstanceState.getInt("currentIndex");
            // sets if any data saved in obj
      //      setContentToItems(selectedItemIndex);
        }else {
            selectedItemIndex = fromChecklistintent.getIntExtra("selectedIndex", 0);
            ItemName = fromChecklistintent.getStringExtra("ItemName");
            ItemStatus = fromChecklistintent.getStringExtra("ItemStatus");
            txtitemnametitle.setText(ItemName);
            setContentToItems(selectedItemIndex);

        }


        // handle the Choose Image button to trigger
        // the image chooser function
        btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGalleryForImage(v);

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finding=txtFinding.getText().toString();
                recomendation=txtRecommendation.getText().toString();
                notes=txtNotes.getText().toString();

                savetoObj(selectedItemIndex);
                Toast.makeText(ListItemActivity.this, "You selected an image!", Toast.LENGTH_LONG).show();

                Intent intent=new Intent(getApplication(),savedActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setContentToItems(int selectedItemIndex) {
    try {
       // taking whatever obj has saved already and setting it to the fields
        txtFinding.setText(Objects.requireNonNull(myApp.getInstance().getChecklist().listItem.get(selectedItemIndex).getFindings()));
        txtRecommendation.setText(Objects.requireNonNull(myApp.getInstance().getChecklist().listItem.get(selectedItemIndex).getRecommendations()));
        txtNotes.setText(Objects.requireNonNull(myApp.getInstance().getChecklist().listItem.get(selectedItemIndex).getNotes()));
       IVPreviewImage.setImageBitmap(Objects.requireNonNull(myApp.getInstance().getChecklist().listItem.get(selectedItemIndex).getProblemPic()));

    }
    catch (Exception e){
        System.out.println("*****Error setting data in memory ****"+e);
    }
    }

    private Bitmap convertBitmap(final String uri)
    {
        final Bitmap[] bitmap = {null};
        Thread task = new Thread()
        {
            @Override
            public void run()
            {
                try {
                    bitmap[0] =   Glide.with(getApplicationContext()).asBitmap().load(Uri.parse(uri)).into(100,100).get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        };

        task.start();
        return bitmap[0];
    }
    // this function is triggered when
    // the Select Image Button is clicked
    public void openGalleryForImage(View view) {
        //Create Intent
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/jpg");
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        //Launch activity to get result
        openGalleryForImageResultLauncher.launch(intent);
    }

    // this function is triggered when user
    // selects the image from the imageChooser
    private void savetoObj(int selectedItemIndex) {

        // saving UI info to the obj
        Toast.makeText(this, " Info being saved:"+selectedItemIndex+finding+recomendation+notes, Toast.LENGTH_SHORT).show();
        try {
            // taking input edits from user inputtextfields and save to obj
            myApp.getInstance().getChecklist().listItem.get(selectedItemIndex).setNotes(notes);
            myApp.getInstance().getChecklist().listItem.get(selectedItemIndex).setRecommendations(recomendation);
            myApp.getInstance().getChecklist().listItem.get(selectedItemIndex).setFindings(finding);
            myApp.getInstance().getChecklist().listItem.get(selectedItemIndex).setItemStatus(ItemStatus);
            myApp.getInstance().getChecklist().listItem.get(selectedItemIndex).setProblemPic(problemPic);



        }catch (Exception e){
            e.printStackTrace();
        }

         //instead of dissmiss call listernet to call back to notify and refresh activity ..after call listner dismiss

    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("currentIndex",selectedItemIndex);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }
}
