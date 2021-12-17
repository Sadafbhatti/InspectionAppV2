package com.example.inspectionapp;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Shop {
    String shopName;
    String technName;
    String inspectionDate;
    String shopEmail;
    String shopPhone;
    String shopAddress;

    private String pattern = "dd-MM-yyyy";
    public Shop() {

        String dateInString =new SimpleDateFormat(pattern).format(new Date());

        this.shopName = shopName;
        this.technName = technName;
        this.inspectionDate = inspectionDate;
        this.shopEmail = shopEmail;
        this.shopPhone = shopPhone;
        this.shopAddress=shopAddress;

        shopName="Inspire1 Auto Sales & Auto Repair Shop";
        inspectionDate=dateInString;
        technName="Ateeq Bhatti";
        shopEmail="inspire1autosales@gmail.com";
        shopPhone="416-735-3535";
        shopAddress="730 Warden Ave, Scarborogh ON";
    }

    public Shop(String shopName, String technName, String inspectionDate, String shopEmail, String shopPhone, String shopAddress) {
        this.shopName = shopName;
        this.technName = technName;
        this.inspectionDate = inspectionDate;
        this.shopEmail = shopEmail;
        this.shopPhone = shopPhone;
        this.shopAddress=shopAddress;

        shopName="Inspire1 Auto Sales & Auto Repair Shop";
        technName="Ateeq Bhatti";
        shopEmail="inspire1autosales@gmail.com";
        shopPhone="416-735-3535";
        shopAddress="730 Warden Ave, Scarborogh ON";
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getTechnName() {
        return technName;
    }

    public void setTechnName(String technName) {
        this.technName = technName;
    }

    public String getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(String inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public String getShopEmail() {
        return shopEmail;
    }

    public void setShopEmail(String shopEmail) {
        this.shopEmail = shopEmail;
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }



}
