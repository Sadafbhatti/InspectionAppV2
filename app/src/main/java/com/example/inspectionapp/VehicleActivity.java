package com.example.inspectionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import java.util.ArrayList;
import java.util.Objects;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
public class VehicleActivity extends AppCompatActivity {
    public static final String TAG=" ==== ACTIVITY=====";
    public TextInputEditText txtMake,txtModel,txtVin,txtMileagein,txtMileageout,txtTestdrive,txtPlate;
   // String query="5UXWX7C5*BA"; // must be at least 8-17 chars of vehicle+"?format=json";
    String vin,make,  model,  mileagein,  mileageout,  testdrive,  plate="Not available";
   // Vehicle myVehicle;
    NetworkManager myNetworkManager;

// have a select all button..initialize with OK status and he can update as
//he goes through it. He can update it to required or recomend and go into details
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);

        txtVin=findViewById(R.id.txtVin);
        txtMake=findViewById(R.id.txtMake);
        txtModel=findViewById(R.id.txtModel);
        txtMileagein=findViewById(R.id.txtMileagein);
        txtMileageout=findViewById(R.id.txtMileageout);
        txtTestdrive=findViewById(R.id.txtTestdrive);
        txtPlate=findViewById(R.id.txtPlate);

         myNetworkManager= ((myApp)getApplication()).getmyNetworkManager();
        txtVin.setText(((myApp)getApplication()).getmyVehicle().getVin());
        txtMake.setText(((myApp)getApplication()).getmyVehicle().getMake());
        txtModel.setText(((myApp)getApplication()).getmyVehicle().getModel());
        txtMileagein.setText(((myApp)getApplication()).getmyVehicle().getMileagein());
        txtMileageout.setText(((myApp)getApplication()).getmyVehicle().getMileageout());
        txtTestdrive.setText( ((myApp)getApplication()).getmyVehicle().getPlate());
        txtPlate.setText(((myApp)getApplication()).getmyVehicle().getTestdrive());
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchViewMenuItem = menu.findItem(R.id.searchView);

        SearchView searchView = (SearchView) searchViewMenuItem.getActionView();
        String searchFor = searchView.getQuery().toString();
        if (!searchFor.isEmpty()) {
            searchView.setIconified(false);
            searchView.setQuery(searchFor, false);
        }

        searchView.setQueryHint("Search for VIN#");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("query", query);
                // search for vin

                myNetworkManager.networkcall(query);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("query change", newText);
                if (newText.length() >= 5) {
                    Toast.makeText(getApplicationContext(), " in queryTextChange", Toast.LENGTH_SHORT).show();
                    // search for vin
                    myNetworkManager.networkcall(newText);
                    txtModel.setText(myNetworkManager.model);
                    txtMake.setText(myNetworkManager.make);
                    txtVin.setText(myNetworkManager.vin);
                }
                else {
                    Toast.makeText(getApplicationContext(), " in less than 5 XW queryTextChange", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
        return true;
    }

    public void OnClick(View view) {
         setVehicle();
         Intent toCustomer = new Intent(getApplicationContext(),CustomerActivity.class);
         startActivity(toCustomer);

    }

    private void setVehicle() {
        try {
            vin = (txtVin.getText()).toString();
            make = (txtMake.getText()).toString();
            model = (txtModel.getText()).toString();
            mileagein = (txtMileagein.getText()).toString();
            mileageout = (txtMileageout.getText()).toString();
            testdrive = (txtTestdrive.getText()).toString();
            plate = (txtPlate.getText()).toString();

            // myVehicle=new Vehicle(vin,make,model,mileagein,mileageout,testdrive,plate);
//            myVehicle=new Vehicle();
//            myVehicle.setVin(vin);
//            myVehicle.setMake(make);
//            myVehicle.setModel(model);
//            myVehicle.setMileagein(mileagein);
//            myVehicle.setMileageout(mileageout);
//            myVehicle.setPlate(testdrive);
//            myVehicle.setTestdrive(testdrive);

            ((myApp)getApplication()).getmyVehicle().setVin(vin);
            ((myApp)getApplication()).getmyVehicle().setMake(make);
            ((myApp)getApplication()).getmyVehicle().setModel(model);
            ((myApp)getApplication()).getmyVehicle().setMileagein(mileagein);
            ((myApp)getApplication()).getmyVehicle().setMileageout(mileageout);
            ((myApp)getApplication()).getmyVehicle().setPlate(plate);
            ((myApp)getApplication()).getmyVehicle().setTestdrive(testdrive);



        }
        catch (Exception e){
            System.out.println("Error: Fields are empty");
        }

    }

}
