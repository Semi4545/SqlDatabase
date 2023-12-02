package com.example.mysqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edt1, edt2, edt3;
    Button btnins, btnred, btnupd, btndle;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Myhelper helper = new Myhelper(this);
        SQLiteDatabase database = helper.getWritableDatabase();

        edt1 = findViewById(R.id.et1);
        edt2 = findViewById(R.id.et2);
        edt3 = findViewById(R.id.et3);

        btnins = findViewById(R.id.btnins);
        btnred = findViewById(R.id.btnred);
        btnupd = findViewById(R.id.btnupd);
        btndle = findViewById(R.id.btndle);


        btnins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username, userbranch, usersubject;

                username = edt1.getText().toString();
                userbranch = edt2.getText().toString();
                usersubject = edt3.getText().toString();

                if (username.equals("") || userbranch.equals("") || usersubject.equals("")){
                    Toast.makeText(MainActivity.this, "plz fill all fields ", Toast.LENGTH_SHORT).show();
                }else {
                   boolean i = helper.insert_data(username , userbranch,usersubject );
                   if (i==true){
                       Toast.makeText(MainActivity.this, "succesfully insert", Toast.LENGTH_SHORT).show();
                   }else {
                       Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();
                   }
                }
                edt1.setText("");
                edt2.setText("");
                edt3.setText("");
            }
        });

        btnred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Read_data.class);
                startActivity(intent);
            }
        });

        btnupd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username, userbranch, usersubject;

                username = edt1.getText().toString();
                userbranch = edt2.getText().toString();
                usersubject = edt3.getText().toString();

                boolean i =helper.update_data(username ,userbranch, usersubject );
                if (i==true){
                    Toast.makeText(MainActivity.this, "update", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(MainActivity.this, "faild", Toast.LENGTH_SHORT).show();
                }
                edt1.setText("");
                edt2.setText("");
                edt3.setText("");
            }


        });

        btndle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edt1.getText().toString();
                boolean i = helper.delete_data(username);
                if (i==true){
                    Toast.makeText(MainActivity.this, "successfully", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "faild to delete", Toast.LENGTH_SHORT).show();
                }
                edt1.setText("");
                edt2.setText("");
                edt3.setText("");
            }

        });
    }
}