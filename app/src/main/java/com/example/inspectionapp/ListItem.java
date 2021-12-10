package com.example.inspectionapp;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class ListItem {
    String itemID;
    String itemName;
    String findings;
    String recommendations;
    String notes;
    Bitmap problemPic;
    String itemStatus;
    //public ArrayList<ListItem> listItem;


    public ListItem() {
    }

    public enum itemStatus {
        OK,
        RECOMMENDED,
        REQUIRED
    }

    public ListItem(String itemName,String itemStatus) {
        this.itemID=itemID;
        this.itemName = itemName;
        this.itemStatus = itemStatus;
        this.findings = findings;
        this.recommendations = recommendations;
        this.notes = notes;
        this.problemPic = problemPic;
    }
    public ListItem(String itemName, String findings, String recommendations, String notes) {
        this.itemName = itemName;
        this.findings = findings;
        this.recommendations = recommendations;
        this.notes = notes;
        this.problemPic = problemPic;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public String getFindings() {
        return findings;
    }

    public void setFindings(String findings) {
        this.findings = findings;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Bitmap getProblemPic() {
        return problemPic;
    }

    public void setProblemPic(Bitmap problemPic) {
        this.problemPic = problemPic;
    }


    @Override
    public String toString() {
        return  ""+ itemName;

    }
}
