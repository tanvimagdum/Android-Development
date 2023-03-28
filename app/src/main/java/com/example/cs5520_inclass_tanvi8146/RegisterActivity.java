package com.example.cs5520_inclass_tanvi8146;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private TextView txtFirebaseRegisterFName;
    private TextView txtFirebaseRegisterLName;
    private TextView txtFirebaseRegisterEmail;
    private TextView txtFirebaseRegisterDisplayName;
    private TextView txtFirebaseRegisterPwd;
    private TextView txtFirebaseRegisterRePwd;
    private Button btnFirebaseRegister;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Register");

        txtFirebaseRegisterFName = findViewById(R.id.txtFirebaseRegisterFName);
        txtFirebaseRegisterLName = findViewById(R.id.txtFirebaseRegisterLName);
        txtFirebaseRegisterEmail = findViewById(R.id.txtFirebaseRegisterEmail);
        txtFirebaseRegisterDisplayName = findViewById(R.id.txtFirebaseRegisterUsername);
        txtFirebaseRegisterPwd = findViewById(R.id.txtFirebaseRegisterPwd);
        txtFirebaseRegisterRePwd = findViewById(R.id.txtFirebaseRegisterRePwd);
        btnFirebaseRegister = findViewById(R.id.btnFirebaseRegister);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        btnFirebaseRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    private void registerUser() {

        String fName = txtFirebaseRegisterFName.getText().toString().trim();
        String lName = txtFirebaseRegisterLName.getText().toString().trim();
        String email = txtFirebaseRegisterEmail.getText().toString().trim();
        String password = txtFirebaseRegisterPwd.getText().toString().trim();
        String rePassword = txtFirebaseRegisterRePwd.getText().toString().trim();

        if (fName.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter your first name", Toast.LENGTH_LONG).show();
            return;
        }
        else if (lName.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter your last name", Toast.LENGTH_LONG).show();
            return;
        }
        else if (email.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter your email", Toast.LENGTH_LONG).show();
            return;
        }
        else if (password.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter your password", Toast.LENGTH_LONG).show();
            return;
        }
        else if (rePassword.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please re-enter your password", Toast.LENGTH_LONG).show();
            return;
        }
        else if (!(Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
            Toast.makeText(getApplicationContext(), "Please enter a valid email", Toast.LENGTH_LONG).show();
            return;
        }
        else if (!password.equals(rePassword)) {
            Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateProfile(user, fName, lName);
                            Intent intent = new Intent(RegisterActivity.this, InClass08Activity.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "There was a problem registering.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void updateProfile(FirebaseUser user, String fName, String lName) {

        UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                .setDisplayName(fName)
                .build();

        user.updateProfile(profileUpdate)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Map<String,Object> userMap = new HashMap<>();
                            userMap.put("firstName", fName);
                            userMap.put("lastName", lName);
                            userMap.put("email", user.getEmail());
                            userMap.put("uid", user.getUid());

                            db.collection("users")
                                    .document(user.getUid())
                                    .set(userMap)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(getApplicationContext(), "Registration successful", Toast.LENGTH_LONG).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(getApplicationContext(), "Registration failed", Toast.LENGTH_LONG).show();
                                        }
                                    });
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Registration unsuccessful", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}