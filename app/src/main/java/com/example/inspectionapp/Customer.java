package com.example.inspectionapp;

public class Customer {
    String custName;
    String custPhone;
    String custEmail;
    String custReasonForInspection;

    Customer(){

    }
    public Customer(String custName, String custPhone, String custEmail, String custReasonForInspection) {
        this.custName = custName;
        this.custPhone = custPhone;
        this.custEmail = custEmail;
        this.custReasonForInspection = custReasonForInspection;
    }

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


}
