package com.example.inspectionapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class savedActivity extends AppCompatActivity {
    TextView txtRecommendation,txtFinding,txtNote;
    ImageView IVProblemPic;
    RecyclerViewAdapter adapter;
    ListItem list=new ListItem();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);

        txtRecommendation=findViewById(R.id.txtRecommendation);
        txtFinding=findViewById(R.id.txtFinding);
        txtNote=findViewById(R.id.txtNotes);
        IVProblemPic=findViewById(R.id.txtProblemPic);
       // myApp.getInstance().getChecklist().getlistItem();

        RecyclerView recyclerViewAdapter=(RecyclerView) findViewById(R.id.mysavedrecyclerview);
        recyclerViewAdapter.setLayoutManager(new LinearLayoutManager(this));
        adapter=new RecyclerViewAdapter(this, myApp.getInstance().getChecklist().getlistItem());
        recyclerViewAdapter.setAdapter(adapter);



    }
}
