package com.example.inspectionapp;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateData extends AppCompatActivity {


    EditText name, phone, email;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        update = findViewById(R.id.btn_update);


        //GET DATA
        name.setText(getIntent().getExtras().getString("name"));
        email.setText(getIntent().getExtras().getString("email"));
        phone.setText(getIntent().getExtras().getString("phone"));
        final String id = getIntent().getExtras().getString("id");

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_txt = name.getText().toString().trim();
                String phone_txt = phone.getText().toString().trim();
                String email_txt = email.getText().toString().trim();
                if (name_txt.equals("") || phone_txt.equals("") || email_txt.equals("")) {
                    Toast.makeText(UpdateData.this, "All Field is required ....", Toast.LENGTH_SHORT).show();
                } else {
                    DatabaseClass.getDatabase(getApplicationContext()).getDao().updateData(name_txt, phone_txt, email_txt, Integer.parseInt(id));
                    finish();

                }


            }
        });
    }
}