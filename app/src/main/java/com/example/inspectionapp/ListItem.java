package com.example.inspectionapp;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class ListItem implements Parcelable {
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


    protected ListItem(Parcel in) {
        itemID = in.readString();
        itemName = in.readString();
        findings = in.readString();
        recommendations = in.readString();
        notes = in.readString();
        problemPic = in.readParcelable(Bitmap.class.getClassLoader());
        itemStatus = in.readString();
    }

    public static final Creator<ListItem> CREATOR = new Creator<ListItem>() {
        @Override
        public ListItem createFromParcel(Parcel in) {
            return new ListItem(in);
        }

        @Override
        public ListItem[] newArray(int size) {
            return new ListItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(itemID);
        dest.writeString(itemName);
        dest.writeString(findings);
        dest.writeString(recommendations);
        dest.writeString(notes);
        dest.writeParcelable(problemPic, flags);
        dest.writeString(itemStatus);
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
    public ListItem(String itemName,String itemStatus, String findings, String recommendations,String notes) {
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
