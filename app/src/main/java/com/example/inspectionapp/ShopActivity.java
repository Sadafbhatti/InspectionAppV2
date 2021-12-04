package com.example.inspectionapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import java.util.Objects;


public class ShopActivity extends AppCompatActivity{
    public String shopName,  technName,  inspectionDate,  shopEmail,  shopPhone, shopAddress;
    public TextInputEditText txtshopName,  txttechnName,  txtinspectionDate,  txtshopEmail,  txtshopPhone,txtShopaddress;
    public  Shop myShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        txtshopName=findViewById(R.id.txtshopName);
        txttechnName=findViewById(R.id.txttechnName);
        txtinspectionDate=findViewById(R.id.txtinspectionDate);
        txtshopEmail=findViewById(R.id.txtshopEmail);
        txtshopPhone=findViewById(R.id.txtshopPhone);
        txtShopaddress=findViewById(R.id.txtShopaddress);

        myShop=new Shop();
        txtshopName.setText(myShop.getShopName());
        txttechnName.setText(myShop.getTechnName());
        txtinspectionDate.setText(myShop.getInspectionDate());
        txtshopEmail.setText(myShop.getShopEmail());
        txtshopPhone.setText(myShop.getShopPhone());
        txtShopaddress.setText(myShop.getShopName());

    }
    public void setShop() {
        try {


            shopName = Objects.requireNonNull(txtshopName.getText()).toString();
            technName = Objects.requireNonNull(txttechnName.getText()).toString();
            inspectionDate = Objects.requireNonNull(txtinspectionDate.getText()).toString();
            shopEmail = Objects.requireNonNull(txtshopEmail.getText()).toString();
            shopPhone = Objects.requireNonNull(txtshopPhone.getText()).toString();
            shopAddress = Objects.requireNonNull(txtShopaddress.getText()).toString();
            myShop= new Shop(shopName,technName,inspectionDate,shopEmail,shopPhone,shopAddress);

        }
        catch (Exception e){
            System.out.println("Error: Fields are empty");
        }

    }
    public void OnClick(View view) {
        setShop();
       Intent toChecklist = new Intent(getApplicationContext(),ChecklistActivity.class);
       startActivity(toChecklist);

    }

}






