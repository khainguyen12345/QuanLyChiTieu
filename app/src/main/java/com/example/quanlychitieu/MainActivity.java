package com.example.quanlychitieu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username , password ,number , email;
    Button sigin , sigup;
    myDB mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();

        sigup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String num = number.getText().toString().trim();
                String em = email.getText().toString().trim();
                if(user.isEmpty() || pass.isEmpty() || num.isEmpty() || em.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please, enter all", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkUser = mydb.checkUser(user);
                    if(checkUser == true) {
                       Boolean insert =  mydb.insertData(user , pass ,num , em);
                       if(insert == true) {
                           Toast.makeText(MainActivity.this, "Sig In Successfull", Toast.LENGTH_SHORT).show();
                           Intent intent = new Intent(getApplicationContext() ,Money_earned.class);
                           startActivity(intent);
                       }else{
                           Toast.makeText(MainActivity.this, "Sig in failed", Toast.LENGTH_SHORT).show();
                       }
                    }else{
                        Toast.makeText(MainActivity.this, "User Already Exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        sigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Cursor cursor = mydb.getData("select * from account where acc = ?" , "");
//                while(cursor.moveToNext()) {
//                    String acc = cursor.getColumnName(2);
//                    Toast.makeText(MainActivity.this, acc, Toast.LENGTH_SHORT).show();
//                }
                    Intent intent = new Intent(getApplicationContext() , Login.class);
                    startActivity(intent);
            }
        });
    }
    public void findView () {
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        number = (EditText) findViewById(R.id.number);
        email = (EditText) findViewById(R.id.email);
        sigin = (Button) findViewById(R.id.sigin);
        sigup = (Button) findViewById(R.id.sigup);
        mydb = new myDB(this);
    }
}