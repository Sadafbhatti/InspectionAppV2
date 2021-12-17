package com.example.inspectionapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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

import com.google.android.material.textfield.TextInputEditText;

import org.bouncycastle.util.test.Test;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Objects;

import retrofit2.http.Tag;

public class ListItemActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 200;
    int selectedItemIndex;
    TextInputEditText txtFinding,txtRecommendation,txtNotes;
    TextView txtitemnametitle;
    Bitmap ProblemImgbitmap,problemPic;
    String ItemName,ItemStatus, picturePath,finding,recomendation,notes;
    String TESTNOTES;
    //Checklist Checklist.ListItem;
    String TAG="MY TAG";

    // One Button
    Button btnSelectImage,btnSave;

    // One Preview Image
    ImageView IVPreviewImage;

    // constant to compare
    // the activity result code
    int SELECT_PICTURE = 200;
    private File imageFile;
    private long maxImageSize=100; //100kb
    private ImageSwitcher imagePost;
    private Uri imageUri, selectedImageUri ;


//    ActivityResultLauncher<Intent> launchCameraActivity = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            new ActivityResultCallback<ActivityResult>() {
//                @Override
//                public void onActivityResult(ActivityResult result) {
//                    if (result.getResultCode() == Activity.RESULT_OK) {
//                        Intent data = result.getData();
//                        Bitmap photoBitmap;
//                        if(data != null && data.getExtras() != null){
//                            photoBitmap = (Bitmap) data.getExtras().get("data");
//                            if (photoBitmap != null) {
//                                ((myApp)getApplication()).getChecklist().listItem.get(selectedItemIndex).setProblemPic(photoBitmap);
//                                ((myApp)getApplication()).getChecklist().listItem.get(selectedItemIndex).setProblemPic(ProblemImgbitmap);
//                                // dataModel.setPhoto(ImageUtil.convert(photoBitmap));
//                               // imageTaken.setVisibility(View.VISIBLE);
//                                //imageTaken.setImageBitmap(photoBitmap);
//                            }
//
//                        }
//                    }
//                }
//            });

//    ActivityResultLauncher<Intent> launchCameraAndGalleryActivity = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            new ActivityResultCallback<ActivityResult>() {
//                @Override
//                public void onActivityResult(ActivityResult result) {
//                    if (result.getResultCode() == Activity.RESULT_OK) {
//
//                        Intent data = result.getData();
//                        Uri imageUri;
//                        if (data != null) {
//                            imageUri = data.getData();
//                            InputStream imageStream;
//                            try {
//                                imageStream = getContentResolver().openInputStream(imageUri);
//                                Bitmap photoBitmap = BitmapFactory.decodeStream(imageStream);
//                               // dataModel.setOtherImage(ImageUtil.convert(photoBitmap));
//                               // documentImageTaken.setVisibility(View.VISIBLE);
//                               // documentImageTaken.setImageBitmap(photoBitmap);
//                            }catch (FileNotFoundException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                }
//            });




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);

        //permission for external storage for file
        ActivityCompat.requestPermissions(ListItemActivity.this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                REQUEST_CODE);
        setContentToItems(selectedItemIndex);


        btnSave=findViewById(R.id.btnSave);
        txtFinding=(TextInputEditText)(findViewById(R.id.txtFinding));
        txtRecommendation=(TextInputEditText)(findViewById(R.id.txtRecommendation));
        txtNotes=(TextInputEditText)(findViewById(R.id.txtNotes));
            // register the UI widgets with their appropri""ate IDs
        btnSelectImage = findViewById(R.id.btnUploadPic);
        IVPreviewImage=(ImageView) findViewById(R.id.img);

        // IVPreviewImage.setImageBitmap(convertImageViewToBitmap(IVPreviewImage));
        if (IVPreviewImage.getDrawable()!=null) {
            ProblemImgbitmap = ((BitmapDrawable) IVPreviewImage.getDrawable()).getBitmap();
        }
        txtitemnametitle=(TextView) findViewById(R.id.txtitemnametitle);

        Intent fromChecklistintent = getIntent();

        if (savedInstanceState!=null){
            selectedItemIndex= savedInstanceState.getInt("currentIndex");
            TESTNOTES=savedInstanceState.getString("TESTNOTES");
            txtNotes.setText(TESTNOTES);

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
                imageChooser();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finding=txtFinding.getText().toString();
                recomendation=txtRecommendation.getText().toString();
                notes=txtNotes.getText().toString();
                TESTNOTES=notes;
                problemPic=ProblemImgbitmap;

                savetoObj(selectedItemIndex);
                // image quality reduced
                Toast.makeText(ListItemActivity.this, "before image is reduced to lower res!", Toast.LENGTH_LONG).show();
                saveBitmapToFile();

            }
        });


    }

    private void setContentToItems(int selectedItemIndex) {
    try {
       // taking whatever obj has saved already and setting it to the fields
//        txtFinding.setText(Objects.requireNonNull((myApp)getApplication()).getChecklist().listItem.get(selectedItemIndex).getFindings());
//        txtRecommendation.setText(Objects.requireNonNull((myApp)getApplication()).getChecklist().listItem.get(selectedItemIndex).getRecommendations());
//        txtNotes.setText(Objects.requireNonNull((myApp)getApplication()).getChecklist().listItem.get(selectedItemIndex).getNotes());
//        IVPreviewImage.setImageBitmap(Objects.requireNonNull((myApp)getApplication()).getChecklist().listItem.get(selectedItemIndex).getProblemPic());
        txtFinding.setText( ((myApp)getApplication()).getChecklist().listItem.get(selectedItemIndex).getFindings());
        txtRecommendation.setText(((myApp)getApplication()).getChecklist().listItem.get(selectedItemIndex).getRecommendations());
        txtNotes.setText(((myApp)getApplication()).getChecklist().listItem.get(selectedItemIndex).getNotes());
        IVPreviewImage.setImageBitmap(Objects.requireNonNull((myApp)getApplication()).getChecklist().listItem.get(selectedItemIndex).getProblemPic());


    }
    catch (Exception e){
        System.out.println("*****Error setting data in memory ****"+e);
    }
    }

    // this function is triggered when
    // the Select Image Button is clicked
    void imageChooser() {
        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }


    // this function is triggered when user
    // selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {

                // Get the url of the image from data
                selectedImageUri = data.getData();
                String picturePath = getPath( ListItemActivity.this, selectedImageUri );
                Log.d("Picture Path", picturePath);
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    IVPreviewImage.setImageURI(selectedImageUri);
                }

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                    // Log.d(TAG, String.valueOf(bitmap));

                    ImageView imageView = (ImageView) findViewById(R.id.img);
                    imageView.setImageBitmap(bitmap);
                    imageView.setVisibility(View.VISIBLE);
                }
                catch (Exception e){
                    System.out.println(e);
                }
            }
        }
    }

    public static String getPath(Context context, Uri uri ) {
        String result = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = context.getContentResolver( ).query( uri, proj, null, null, null );
        if(cursor != null){
            if ( cursor.moveToFirst( ) ) {
                int column_index = cursor.getColumnIndexOrThrow( proj[0] );
                result = cursor.getString( column_index );
            }
            cursor.close( );
        }
        if(result == null) {
            result = "Not found";
        }
        return result;
    }
    private void savetoObj(int selectedItemIndex) {

        // saving UI info to the obj
        Toast.makeText(this, " Info being taken from fields into obj:"+selectedItemIndex+finding+recomendation+notes, Toast.LENGTH_SHORT).show();

        // taking input edits from user inputtextfields and save to obj
        ((myApp)getApplication()).getChecklist().listItem.get(selectedItemIndex).setNotes(notes);
        ((myApp)getApplication()).getChecklist().listItem.get(selectedItemIndex).setRecommendations(recomendation);
        ((myApp)getApplication()).getChecklist().listItem.get(selectedItemIndex).setFindings(finding);
        ((myApp)getApplication()).getChecklist().listItem.get(selectedItemIndex).setItemStatus(ItemStatus);
        ((myApp)getApplication()).getChecklist().listItem.get(selectedItemIndex).setProblemPic(problemPic);
        System.out.println("***ListItemActivity savetoObj *****"+((myApp)getApplication()).getChecklist().listItem.get(selectedItemIndex).getNotes());
        System.out.println("***ListItemActivity savetoObj *****"+((myApp)getApplication()).getChecklist().listItem.get(selectedItemIndex).getRecommendations());

        // myList.get(3).setEmail("new email");

         //instead of dissmiss call listernet to call back to notify and refresh activity ..after call listner dismiss

    }

    //This is the number of pixels that the width and height of the image will be allotted
//Note that the number of pixels implies (but does not exclusively determine) the file size of the image
    private int REQUIRED_SIZE = 720;
    //Method allows image files to be compressed to a standard maximum size
    public void saveBitmapToFile(){

        try {
            imageUri=selectedImageUri;
            imageFile=new File(selectedImageUri.getPath());
            Toast.makeText(ListItemActivity.this, "2-before image is reduced to lower res!"+imageUri, Toast.LENGTH_LONG).show();

            //suggested using this line but not sure how to use it
           // InputStream inputStream = getContentResolver().openInputStream(imageUri);

            //Initialize BitmapFactory options to downsize the image
            BitmapFactory.Options o = new BitmapFactory.Options();
            //Image dimensions do not change with change in pixel density
            o.inJustDecodeBounds = true;

            //Retrieve file and decode into bitmap, then close the stream when decode complete
            FileInputStream inputStream = new FileInputStream(imageFile);
            BitmapFactory.decodeStream(inputStream, null, o);
            inputStream.close();

            //Start with 1:1 scale size
            int scale = 1;

            while (o.outWidth / scale / 2 >= REQUIRED_SIZE && o.outHeight / scale / 2 >= REQUIRED_SIZE) {
                //Find the correct scale value. It should be the power of 2.
                scale *= 2;
            }
            Toast.makeText(ListItemActivity.this, "3-before image is reduced to lower res!", Toast.LENGTH_LONG).show();

            //Create new Bitmap options instance to adjust the image scale...(1/2)
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            //...then apply with new input stream
            //InputStream inputStream = getContentResolver().openInputStream(uri);
            inputStream = new FileInputStream(imageFile);
            Bitmap selectedBitmap = BitmapFactory.decodeStream(inputStream, null, o2);
            inputStream.close();

            Toast.makeText(ListItemActivity.this, "4-before image is reduced to lower res!", Toast.LENGTH_LONG).show();

            //Overwrite original file (type 'File') then compress the output file
            imageFile.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            selectedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

            //maxImageSize is arbitrary file size (byte) limit
            if(imageFile.length() < maxImageSize) {

                //Set image in place and get the uri
               // imagePost.setImageURI(imageUri); original
               // String path = MediaStore.Images.Media.insertImage(ListItemActivity.this.getContentResolver(), imageFile, imageFileName, null);
                imageUri = Uri.parse(picturePath);


                IVPreviewImage.setImageURI(imageUri);
                Toast.makeText(ListItemActivity.this, "Your image is reduced to lower res!", Toast.LENGTH_LONG).show();

                //Reset in case decreased previously
                REQUIRED_SIZE = 720;

            } else {

                Toast.makeText(ListItemActivity.this, "Your image is " + (imageFile.length() - maxImageSize) / 1000 + "kb too large to upload. Reattempting...", Toast.LENGTH_LONG).show();
                REQUIRED_SIZE = REQUIRED_SIZE - 240;

                //Loop method until goal file size achieved
                saveBitmapToFile();
            }

        } catch (Exception ignored) {
            System.out.println("image resolution error exception:"+ignored);
            Toast.makeText(ListItemActivity.this, "Exception ignored!", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("currentIndex",selectedItemIndex);
        savedInstanceState.putString("TESTNOTES",TESTNOTES);
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }
}

/*
* package com.streamliners.myecomadmin;

public class MainActivity extends AppCompatActivity {

    public static int RC_PHOTO_PICKER = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

  public void openSomeActivityForResult(View view) {
    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
          new ActivityResultContracts.StartActivityForResult(),
          new ActivityResultCallback<ActivityResult>() {
              @Override
              public void onActivityResult(ActivityResult result) {
                  if (result.getResultCode() == Activity.RESULT_OK) {
                      // There are no request codes
                      Intent data = result.getData();
                      Uri uri = data.getData();
                      ImageView imageView = R.id.img;
                      imageView.setImageURI(uri);
                  }
              }
          });

        //Create Intent
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/jpg");
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        //Launch activity to get result
        someActivityResultLauncher.launch(intent);
  }

}
*
*
*   //  void imageChooser1(){
//
//        I launch the activities like this:
//
//        Intent photoIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//        launchCameraAndGalleryActivity.launch(photoIntent );
//
//        Intent galleryIntent= new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        launchCameraActivity.launch(galleryIntent);
//
//    }
* */