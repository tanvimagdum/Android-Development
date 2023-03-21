package com.example.cs5520_inclass_tanvi8146;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/*
 * Tanvi Prashant Magdum
 * Vivek Dantu
 * Assignment 07
 */


public class InClass07Activity extends AppCompatActivity {

    private Button btnLogin;
    private Button btnSignUp;
    private static boolean authenticated = false;
    private static String authToken = null;
    private static User user = null;
    private static Notes notes = null;
    private static boolean posted = false;
    private static boolean deleted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class07);
        setTitle("Notes App");

        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(InClass07Activity.this, LoginActivity.class);
                startActivity(intent1);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(InClass07Activity.this, SignUpActivity.class);
                startActivity(intent2);
            }
        });
    }

}