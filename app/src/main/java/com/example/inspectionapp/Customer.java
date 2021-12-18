package com.example.inspectionapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "customer")
public class Customer {
    //Primary key
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int key;

    @ColumnInfo(name = "name")
    String custName;
    @ColumnInfo(name = "phone")
    String custPhone;
    @ColumnInfo(name = "email")
    String custEmail;
    @ColumnInfo(name = "reason")
    String custReasonForInspection;



    Customer(){

    }
    public Customer(String custName, String custPhone, String custEmail, String custReasonForInspection) {
        this.custName = custName;
        this.custPhone = custPhone;
        this.custEmail = custEmail;
        this.custReasonForInspection = custReasonForInspection;
    }

    public int getKey() {
        return key;
    }
    public void setKey(int key) { this.key=key; }


    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    public String getCustReasonForInspection() {
        return custReasonForInspection;
    }

    public void setCustReasonForInspection(String custReasonForInspection) {
        this.custReasonForInspection = custReasonForInspection;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custName='" + custName + '\'' +
                ", custPhone='" + custPhone + '\'' +
                ", custEmail='" + custEmail + '\'' +
                ", custReasonForInspection='" + custReasonForInspection + '\'' +
                '}';
    }
}
