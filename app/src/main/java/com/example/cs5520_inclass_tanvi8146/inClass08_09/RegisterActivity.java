package com.example.cs5520_inclass_tanvi8146.inClass08_09;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cs5520_inclass_tanvi8146.R;
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

/*
 * Tanvi Prashant Magdum
 * Assignment 08
 */

public class RegisterActivity extends AppCompatActivity {

    private TextView txtFirebaseRegisterFName;
    private TextView txtFirebaseRegisterLName;
    private TextView txtFirebaseRegisterEmail;
    private TextView txtFirebaseRegisterDisplayName;
    private TextView txtFirebaseRegisterPwd;
    private TextView txtFirebaseRegisterRePwd;
    private Button btnFirebaseRegister;
    private ImageView imgFirebaseRegisterAvatar;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private Uri avatarUri;

    ActivityResultLauncher<Intent> startActivityForResult
            = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        assert result.getData() != null;
                        Bitmap photo = (Bitmap) result.getData().getExtras().get("data");
                        imgFirebaseRegisterAvatar.setImageBitmap(photo);
                        avatarUri = saveImageToStorage(photo);
                    }
                }
            });

    private Uri saveImageToStorage(Bitmap bitmap) {
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "ProfilePhoto", null);
        return Uri.parse(path);
    }

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
        imgFirebaseRegisterAvatar = findViewById(R.id.imgFirebaseRegisterAvatar);

        imgFirebaseRegisterAvatar.setImageResource(R.drawable.select_avatar);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        imgFirebaseRegisterAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(RegisterActivity.this,
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(RegisterActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(RegisterActivity.this,
                            new String[]{Manifest.permission.CAMERA,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            1);
                } else {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult.launch(cameraIntent);
                }
            }
        });

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
        else if(avatarUri == null) {
            Toast.makeText(getApplicationContext(), "Please select a profile photo", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("TAG", "till update");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateProfile(user, fName, lName, avatarUri);
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

    private void updateProfile(FirebaseUser user, String fName, String lName, Uri avatarUri) {

        UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                .setDisplayName(fName)
                .setPhotoUri(avatarUri)
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
                            userMap.put("photo", avatarUri);

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