package com.example.cs5520_inclass_tanvi8146.inClass07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.cs5520_inclass_tanvi8146.R;

/*
 * Tanvi Prashant Magdum
 * Vivek Dantu
 * Assignment 07
 */

public class NoteDetailActivity extends AppCompatActivity {

    private TextView txtNotesDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        setTitle("Notes App");

        txtNotesDetail = findViewById(R.id.txtNotesDetail);

        Intent intent = getIntent();
        String note_key = intent.getStringExtra("note");

        txtNotesDetail.setText(note_key);

    }
}