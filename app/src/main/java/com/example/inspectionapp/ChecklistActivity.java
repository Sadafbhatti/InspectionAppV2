package com.example.inspectionapp;

import static com.example.inspectionapp.VehicleActivity.TAG;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ChecklistActivity extends AppCompatActivity implements View.OnClickListener{
    Checklist mychecklist;
    ListView simpleList;
    TextView taskname;
    Button taskstatus, btnfinish;
    CheckListActivityAdapter customAdapter;
    private int selectedItemIndex =-100;

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

        btnfinish= findViewById(R.id.btnfinish);
        // part of list_row_items button called btnStatus and textview txtItemName
        taskstatus = (Button)findViewById(R.id.btnStatus);
        taskname = (TextView)findViewById(R.id.txtItemName);
        simpleList = (ListView) findViewById(R.id.mylistview);

        customAdapter = new CheckListActivityAdapter(getApplicationContext(),((myApp)getApplication()).getChecklist().listItem);
        simpleList.setAdapter(customAdapter);
        System.out.println("*********Before onItemClick: I got here ********************* ");
        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               // Object item = customAdapter.getItem(i);
                String info=((myApp)getApplication()).getChecklist().listItem.get(i).getItemName();
                String status=((myApp)getApplication()).getChecklist().listItem.get(i).getItemStatus();
                System.out.println("info for setText error:"+info);

                try {
                 //   taskname.setText( "info");
                 //   taskstatus.setText("status");
                    System.out.println("im skipping setting taskname and task status");
                }
                catch (Exception e){
                    System.out.println("*********Caught error with setting text *********"+e);
                }
        finally{
                    selectedItemIndex = i;
                    Intent toItemInList = new Intent(getApplicationContext(), ListItemActivity.class);
                    toItemInList.putExtra("selectedIndex", selectedItemIndex);
                    toItemInList.putExtra("ItemName", ((myApp)getApplication()).getChecklist().listItem.get(i).getItemName());
                    toItemInList.putExtra("ItemStatus", ((myApp)getApplication()).getChecklist().listItem.get(i).getItemStatus());
                    //startActivity(toItemInList);

                  //  Intent intent = new Intent(getApplicationContext(), ListItemActivity.class));
                    launchlistItems.launch(toItemInList);

                }
            }
        });

        //registering finish button onclick event
        btnfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //convert all data to a PDF page and send email

                convertDataToPDFReport();

                // and send email
                //save all data to a room database

            }
        });
    }

    private void convertDataToPDFReport() {

    }


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
        outState.putParcelableArrayList("mylistitems",((myApp)getApplication()).getChecklist().listItem);

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


