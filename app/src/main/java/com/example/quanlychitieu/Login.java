package com.example.quanlychitieu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    EditText username , password;
    Button buttonLogin , buttonSigup;
    private PreferenceManager preferenceManager;
    ArrayList<String> User = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        findView();
        preferenceManager = new PreferenceManager(getApplicationContext());

        username = (EditText) findViewById(R.id.usernameLogin);
        password = (EditText) findViewById(R.id.passwordLogin);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonSigup = (Button) findViewById(R.id.buttonSigup);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();
                if(user.isEmpty() || pass.isEmpty()){
                    Toast.makeText(Login.this, "Please, enter all", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkUserPass = MainActivity.mydb.checkUsernamePassword(user , pass);
                    if(checkUserPass == true) {
                        Toast.makeText(Login.this, "login Successfull", Toast.LENGTH_SHORT).show();
                        Cursor cursor = MainActivity.mydb.getData("SELECT * FROM users where username = "+user);
                        while (cursor.moveToNext()){
                            preferenceManager.putString("userId", cursor.getString(0));
                            preferenceManager.putString("username", cursor.getString(1));
                            preferenceManager.putString("email", cursor.getString(3));
                        }
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
    }
}