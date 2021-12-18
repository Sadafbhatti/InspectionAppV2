package com.example.inspectionapp;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.example.inspectionapp.VehicleActivity.TAG;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.android.material.textfield.TextInputEditText;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import org.w3c.dom.Document;
import org.w3c.dom.Text;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import androidx.annotation.RequiresApi;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class ChecklistActivity extends AppCompatActivity implements View.OnClickListener{
    ListView simpleList;
    TextView taskname;
    Button taskstatus, btnfinish;
    CheckListActivityAdapter customAdapter;
    private int selectedItemIndex =-100;
    final static String SENDGRID_API_KEY ="5H2orXwOSVq5CrWP9H6ANw";


    // Create lanucher variable inside onAttach or onCreate or global
    ActivityResultLauncher<Intent> launchlistItems = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        // your operation....
                    }
                }
            });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);
        setContentfromObj();

        btnfinish= findViewById(R.id.btnfinish);
        // part of list_row_items button called btnStatus and textview txtItemName
        taskstatus = (Button)findViewById(R.id.btnStatus);
        taskname = (TextView)findViewById(R.id.txtItemName);
        simpleList = (ListView) findViewById(R.id.mylistview);

        customAdapter = new CheckListActivityAdapter(getApplicationContext(),myApp.getInstance().getChecklist().listItem);
        simpleList.setAdapter(customAdapter);
        System.out.println("*********Before onItemClick: I got here ********************* ");
        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               // Object item = customAdapter.getItem(i);
                String info=myApp.getInstance().getChecklist().listItem.get(i).getItemName();
                String status=myApp.getInstance().getChecklist().listItem.get(i).getItemStatus();
                System.out.println("info for setText error:"+info);
                    selectedItemIndex = i;
                    Intent toItemInList = new Intent(getApplicationContext(), ListItemActivity.class);
                    toItemInList.putExtra("selectedIndex", selectedItemIndex);
                    toItemInList.putExtra("ItemName", myApp.getInstance().getChecklist().listItem.get(i).getItemName());
                    toItemInList.putExtra("ItemStatus", myApp.getInstance().getChecklist().listItem.get(i).getItemStatus());
                    //startActivity(toItemInList);
                  //  Intent intent = new Intent(getApplicationContext(), ListItemActivity.class));
                    launchlistItems.launch(toItemInList);
            }
        });

        //registering finish button onclick event
        btnfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //convert all data to a PDF page and send email

               // convertDataToPDFReport();
                // and send email
                try {
                    composeEmail();
                    Toast.makeText(getApplicationContext(), "A report was emailed to you!", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "There was an error trying to Email you!", Toast.LENGTH_SHORT).show();

                }

                //save all data to a room database

            }
        });
    }

    private void setContentfromObj() {
        taskstatus = (Button)findViewById(R.id.btnStatus);
    }

    public void composeEmail() throws IOException  {
        String fromShop=myApp.getInstance().getmyShop().getShopEmail();
        String shopsubject="Your Vehicle Inspection Report by Inspire1";
        String toaddress=myApp.getInstance().getmyCustomer().getCustEmail();
        String ccaddress="sadafbhatti@gmail.com";
        String body=myApp.getInstance().getmyVehicle().toString()+myApp.getInstance().getmyCustomer().toString()+myApp.getInstance().getmyShop()+myApp.getInstance().getChecklist().toString();

            Email from = new Email(fromShop);
            String subject = shopsubject;
            Email to = new Email(toaddress);
            Content content = new Content("text/plain", "Thank you for bringing your Business to our shop!"+body);
            Mail mail = new Mail(from, subject, to, content);

            // SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
            SendGrid sg = new SendGrid(SENDGRID_API_KEY);

            Request request= new Request();
            try {
                request.setMethod(Method.POST);
                request.setEndpoint("mail/send");
                request.setBody(mail.build());
                Response response = sg.api(request);
                System.out.println(response.getStatusCode());
                System.out.println(response.getBody());
                System.out.println(response.getHeaders());
            } catch (IOException ex) {
                System.out.println("Error sending using sendgrid :"+ex);
                throw ex;
            }
        }



//
//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
//        intent.putExtra(Intent.EXTRA_EMAIL, addresse);
//        intent.putExtra(Intent.EXTRA_CC,ccaddress);
//        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
//        intent.putExtra(Intent.EXTRA_TEXT   , body);
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
//


//    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//    private void convertDataToPDFReport() throws FileNotFoundException {
//         String vin;  //"vin":"JNKCA31A61T027494",
//         String make; //   "make":"Infiniti",
//         String model; //    "model":"I30",
//         String mileagein;
//         String mileageout;
//         String testdrive;
//         String plate;
//         //createPdf(vin,make,model,mileagein,mileageout,testdrive,plate);
//
//         File pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS.toString());
//        File file=new File(pdfPath,"myPDF.pdf");
//        OutputStream os=new FileOutputStream(file);





    /** A callback method, which is executed when the activity is recreated
     * ( eg :  Configuration changes : portrait -> landscape )
     */
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//
//        taskstatus = (Button)findViewById(R.id.btnStatus);
//        taskname = (TextView)findViewById(R.id.txtItemName);
//        taskstatus.setText(savedInstanceState.getString("btnstatus"));
//        taskname.setText(savedInstanceState.getString("btnitemname"));
//
//        super.onRestoreInstanceState(savedInstanceState);
//    }

    @Override
    protected void onResume() {
        super.onResume();
        customAdapter.notifyDataSetChanged();
    }
    /** A callback method, which is executed when this activity is about to be killed
     * This is used to save the current state of the activity
     * ( eg :  Configuration changes : portrait -> landscape )
     */

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList("mylistitems",myApp.getInstance().getChecklist().listItem);

        taskstatus = (Button)findViewById(R.id.btnStatus);
        taskname = (TextView)findViewById(R.id.txtItemName);
//        outState.putParcelableArrayList();taskstatus.setText(savedInstanceState.getString("btnstatus"));
//        outState.getSerializable(taskname.getText(savedInstanceState.getString("btnitemname"));
//
        super.onSaveInstanceState(outState);
        outState.putInt("selectedindex", selectedItemIndex);
        //create an app level class that contains all those objects ..when finished acces class  store checklist NOT IN STATIC MODE FOR EMAIL CREATE AN INTENT -- EMAIL CODE rANIA WILL SEND LINK
        //COMPOSE EMAIL WITH OPTIONAL ATTACHMENTS ..SEND TO MULTIPLE USERS..IN LINK
        //}
        //
    }
    @Override
    public void onClick(View v) {

    }
}




