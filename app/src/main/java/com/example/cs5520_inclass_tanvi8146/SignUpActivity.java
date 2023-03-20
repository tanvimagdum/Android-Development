package com.example.cs5520_inclass_tanvi8146;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SignUpActivity extends AppCompatActivity {

    private TextView txtSignUpName;
    private TextView txtSignUpEmail;
    private TextView txtSignUpPassword;
    private Button btnSignUpSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setTitle("Notes App Register");

        txtSignUpName = findViewById(R.id.txtSignUpName);
        txtSignUpEmail = findViewById(R.id.txtSignUpEmail);
        txtSignUpPassword = findViewById(R.id.txtSignUpPassword);
        btnSignUpSignUp = findViewById(R.id.btnSignUpSignUp);

        btnSignUpSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, NotesActivity.class);
                startActivity(intent);
            }
        });

    }
}