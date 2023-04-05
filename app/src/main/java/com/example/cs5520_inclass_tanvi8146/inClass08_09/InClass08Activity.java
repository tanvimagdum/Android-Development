package com.example.cs5520_inclass_tanvi8146.inClass08_09;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cs5520_inclass_tanvi8146.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/*
 * Tanvi Prashant Magdum
 * Assignment 08
 */

public class InClass08Activity extends AppCompatActivity {

    private TextView txtFirebaseLoginUsername;
    private TextView txtFirebaseLoginPwd;
    private Button btnFirebaseLogin;
    private Button btnFirebaseLoginRegister;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class08);
        setTitle("Messenger");

        txtFirebaseLoginUsername = findViewById(R.id.txtFirebaseLoginUsername);
        txtFirebaseLoginPwd = findViewById(R.id.txtFirebaseLoginPwd);
        btnFirebaseLogin = findViewById(R.id.btnFirebaseLogin);
        btnFirebaseLoginRegister = findViewById(R.id.btnFirebaseLoginRegister);

        mAuth = FirebaseAuth.getInstance();

        btnFirebaseLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = txtFirebaseLoginUsername.getText().toString().trim();
                String password = txtFirebaseLoginPwd.getText().toString().trim();

                if (username.isEmpty()) {
                    txtFirebaseLoginUsername.setError("Email is required");
                    txtFirebaseLoginUsername.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    txtFirebaseLoginPwd.setError("Password is required");
                    txtFirebaseLoginPwd.requestFocus();
                    return;
                }

                mAuth.signInWithEmailAndPassword(username, password)
                        .addOnCompleteListener(InClass08Activity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(InClass08Activity.this, ChatActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "Login failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        btnFirebaseLoginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InClass08Activity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

}