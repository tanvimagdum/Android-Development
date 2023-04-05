package com.example.cs5520_inclass_tanvi8146.inClass08;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cs5520_inclass_tanvi8146.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class EditProfileActivity extends AppCompatActivity {

    private TextView txtFirebaseProfileFName;
    private TextView txtFirebaseProfileEmail;
    private ImageView imgFirebaseProfileAvatar;
    private Button btnFirebaseSaveProfile;
    private Uri avatarUri;

    ActivityResultLauncher<Intent> startActivityForResult
            = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        assert result.getData() != null;
                        avatarUri = result.getData().getData();
                        Glide.with(EditProfileActivity.this).load(avatarUri).into(imgFirebaseProfileAvatar);
                    }
                }
            });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        setTitle("Edit Profile");

        txtFirebaseProfileFName = findViewById(R.id.txtFirebaseProfileFName);
        txtFirebaseProfileEmail = findViewById(R.id.txtFirebaseProfileEmail);
        imgFirebaseProfileAvatar = findViewById(R.id.imgFirebaseProfileAvatar);
        btnFirebaseSaveProfile = findViewById(R.id.btnFirebaseSaveProfile);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String userId = currentUser.getUid();

        if (currentUser != null) {
            txtFirebaseProfileFName.setText(currentUser.getDisplayName());
            txtFirebaseProfileEmail.setText(currentUser.getEmail());
            Glide.with(this).load(currentUser.getPhotoUrl()).into(imgFirebaseProfileAvatar);
        }

        imgFirebaseProfileAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagePicker();
            }
        });

        btnFirebaseSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveChanges();
            }
        });

    }

    private void openImagePicker() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult.launch(intent);
    }

    private void saveChanges() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String newName = txtFirebaseProfileFName.getText().toString().trim();
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(newName)
                    .build();
            currentUser.updateProfile(profileUpdates)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "User profile updated.", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

            if (avatarUri != null) {

                UserProfileChangeRequest profileUpdatesPhoto = new UserProfileChangeRequest.Builder()
                        .setPhotoUri(avatarUri)
                        .build();
                currentUser.updateProfile(profileUpdatesPhoto)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "User profile updated.", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        }
        finish();
    }
}