package com.example.quanlychitieu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    EditText username , password;
    Button buttonLogin , buttonSigup;
    myDB mydb;
    ArrayList<String> User = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        findView();
        username = (EditText) findViewById(R.id.usernameLogin);
        password = (EditText) findViewById(R.id.passwordLogin);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonSigup = (Button) findViewById(R.id.buttonSigup);
        mydb = new myDB(this);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();
                if(user.isEmpty() || pass.isEmpty()){
                    Toast.makeText(Login.this, "Please, enter all", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkUserPass = mydb.checkUsernamePassword(user , pass);
                    if(checkUserPass == true) {
                        Toast.makeText(Login.this, "login Successfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext() , Money_earned.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Login.this, "Invalid Credential", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        buttonSigup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext() , MainActivity.class);
                startActivity(intent);
            }
        });

    }
    private void findView() {
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonSigup = (Button) findViewById(R.id.buttonSigup);
        mydb = new myDB(this);
    }
}