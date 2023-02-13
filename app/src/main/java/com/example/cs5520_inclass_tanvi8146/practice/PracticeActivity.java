package com.example.cs5520_inclass_tanvi8146.practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.cs5520_inclass_tanvi8146.R;

/*
 * Tanvi Prashant Magdum
 * Assignment 01
 */

public class PracticeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        setTitle("Practice Activity");

        Button button_log = findViewById(R.id.button_log);
        button_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("message", "Practice!Practice!!Practice!!!");
            }
        });

        Button button_toast = findViewById(R.id.button_toast);
        button_toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PracticeActivity.this, "Now push to GitHub and Submit!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}