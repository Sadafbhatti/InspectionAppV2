package com.example.inspectionapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Objects;


public class ChecklistActivity extends AppCompatActivity {
  //  public ArrayList<Checklist.itemList> mylist;
    public  Checklist myChecklist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);

    }
    public void setShop() {
        try {
            //label
            //textfield
            myChecklist= new Checklist();

        }
        catch (Exception e){
            System.out.println("Error: Fields are empty");
        }

    }
    public void OnClick(View view) {
      //  setChecklist();
        //Intent toResults = new Intent(getApplicationContext(),InspectionResultActivity.class);
      //  startActivity(toResults);

    }

}






