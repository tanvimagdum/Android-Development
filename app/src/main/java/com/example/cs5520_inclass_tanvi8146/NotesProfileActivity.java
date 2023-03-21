package com.example.cs5520_inclass_tanvi8146;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class NotesProfileActivity extends AppCompatActivity {

    private TextView txtNotesProfileName;
    private TextView txtNotesProfileEmail;
    private TextView txtNotesProfilePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_profile);
        setTitle("Profile");

        txtNotesProfileName = findViewById(R.id.txtNotesProfileName);
        txtNotesProfileEmail = findViewById(R.id.txtNotesProfileEmail);
        txtNotesProfilePassword = findViewById(R.id.txtNotesProfilePassword);
        


    }
}