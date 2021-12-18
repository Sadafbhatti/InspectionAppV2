package com.example.inspectionapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import java.util.Objects;

public class CustomerActivity extends AppCompatActivity{
    String custName,  custPhone,  custEmail,  custReasonForInspection;
    public TextInputEditText txtCustname,txtCustphone,txtCustemail,txtCustreason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        txtCustname=findViewById(R.id.txtCustname);
        txtCustphone=findViewById(R.id.txtCustphone);
        txtCustemail=findViewById(R.id.txtCustemail);
        txtCustreason=findViewById(R.id.txtCustreason);

        //set data from application
        txtCustname.setText(myApp.getInstance().getmyCustomer().getCustName());
        txtCustphone.setText(myApp.getInstance().getmyCustomer().getCustPhone());
        txtCustemail.setText(myApp.getInstance().getmyCustomer().getCustEmail());
        txtCustreason.setText(myApp.getInstance().getmyCustomer().getCustReasonForInspection());

    }

    public void setCustomer() {

        try {
            custName = Objects.requireNonNull(txtCustname.getText()).toString();
            custPhone = Objects.requireNonNull(txtCustphone.getText()).toString();
            custEmail = Objects.requireNonNull(txtCustemail.getText()).toString();
            custReasonForInspection = Objects.requireNonNull(txtCustreason.getText()).toString();
           // myCustomer= new Customer(custName,custPhone,custEmail,custReasonForInspection);

            myApp.getInstance().getmyCustomer().setCustName(custName);
            myApp.getInstance().getmyCustomer().setCustPhone(custPhone);
            myApp.getInstance().getmyCustomer().setCustEmail(custEmail);
            myApp.getInstance().getmyCustomer().setCustReasonForInspection(custReasonForInspection);

        }
        catch (Exception e){
            System.out.println("Error: one of the required fields are empty!");
        }

    }
    public void OnClick(View view) {
        setCustomer();
        Intent toShop = new Intent(CustomerActivity.this,ShopActivity.class);
        startActivity(toShop);
    }

}






