package com.example.inspectionapp;

import android.app.Activity;
import androidx.appcompat.app.AppCompatActivity;
        import androidx.room.Database;
        import androidx.room.Room;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    EditText name, phone, email;
    Button save, getData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        save = findViewById(R.id.btn_save);
        getData = findViewById(R.id.btn_getData);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), GetData.class));
            }
        });

    }

    private void saveData() {

        String name_txt = name.getText().toString().trim();
        String phone_txt = phone.getText().toString().trim();
        String email_txt = email.getText().toString().trim();


        Customer model = new Customer();
        model.setCustName(name_txt);
        model.setCustEmail(email_txt);
        model.setCustPhone(phone_txt);
        DatabaseClass.getDatabase(getApplicationContext()).getDao().insertAllData(model);

        name.setText("");
        phone.setText("");
        email.setText("");
        Toast.makeText(this, "Data Successfully Saved", Toast.LENGTH_SHORT).show();


    }
}