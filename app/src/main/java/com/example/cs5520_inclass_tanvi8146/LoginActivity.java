package com.example.cs5520_inclass_tanvi8146;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private TextView txtLoginUsername;
    private TextView txtLoginPassword;
    private Button btnLoginLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Notes App Login");

        txtLoginUsername = findViewById(R.id.txtLoginUsername);
        txtLoginPassword = findViewById(R.id.txtLoginPassword);

        btnLoginLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}