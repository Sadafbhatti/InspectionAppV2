package com.example.inspectionapp;

import android.graphics.Bitmap;

public class ItemResultDetails {
    String findings;
    String recommendations;
    String notes;
    Bitmap problemPic;

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

}