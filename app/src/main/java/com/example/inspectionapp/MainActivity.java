package com.example.inspectionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void startClicked(View view) {
        Intent toVehicleInput = new Intent(getApplicationContext(),VehicleActivity.class);
        startActivity(toVehicleInput);

    }

    public void roomdbClicked(View view) {

        Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
        startActivity(intent);

    }
}