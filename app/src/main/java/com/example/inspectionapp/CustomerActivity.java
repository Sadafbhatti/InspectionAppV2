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
    Customer myCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        txtCustname=findViewById(R.id.txtCustname);
        txtCustphone=findViewById(R.id.txtCustphone);
        txtCustemail=findViewById(R.id.txtCustemail);
        txtCustreason=findViewById(R.id.txtCustreason);

        //set data from application
        txtCustname.setText(((myApp)getApplication()).getmyCustomer().getCustName());
        txtCustphone.setText(((myApp)getApplication()).getmyCustomer().getCustPhone());
        txtCustemail.setText(((myApp)getApplication()).getmyCustomer().getCustEmail());
        txtCustreason.setText(((myApp)getApplication()).getmyCustomer().getCustReasonForInspection());

    }

    public void setCustomer() {

        try {
            custName = Objects.requireNonNull(txtCustname.getText()).toString();
            custPhone = Objects.requireNonNull(txtCustphone.getText()).toString();
            custEmail = Objects.requireNonNull(txtCustemail.getText()).toString();
            custReasonForInspection = Objects.requireNonNull(txtCustreason.getText()).toString();
           // myCustomer= new Customer(custName,custPhone,custEmail,custReasonForInspection);

            ((myApp)getApplication()).getmyCustomer().setCustName(custName);
            ((myApp)getApplication()).getmyCustomer().setCustPhone(custPhone);
            ((myApp)getApplication()).getmyCustomer().setCustEmail(custEmail);
            ((myApp)getApplication()).getmyCustomer().setCustReasonForInspection(custReasonForInspection);

        }
        catch (Exception e){
            System.out.println("Error: Fields are empty");
        }

    }
    public void OnClick(View view) {
        setCustomer();
        Intent toShop = new Intent(CustomerActivity.this,ShopActivity.class);
        startActivity(toShop);
    }

}






