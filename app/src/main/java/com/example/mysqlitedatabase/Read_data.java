package com.example.mysqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Read_data extends AppCompatActivity {
    TextView txt3;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_data);

        txt3 = findViewById(R.id.textview4);

        Myhelper helper = new Myhelper(this);
        Cursor row = helper.read_data();

        if (row.getCount()==0){
            Toast.makeText(this, "no record found", Toast.LENGTH_SHORT).show();
        }

        StringBuilder builder = new StringBuilder();

        while (row.moveToNext()){
            builder.append("Name : "+ row.getString(1) +"\n");
            builder.append("Branch : "+row.getString(2) +"\n");
            builder.append("Subject : "+row.getString(3)+"\n\n");

        }
        txt3.setText(builder.toString());
    }
}