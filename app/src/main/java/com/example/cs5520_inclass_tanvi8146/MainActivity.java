package com.example.cs5520_inclass_tanvi8146;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cs5520_inclass_tanvi8146.inClass01.InClass01Activity;
import com.example.cs5520_inclass_tanvi8146.inClass02.InClass02Activity;
import com.example.cs5520_inclass_tanvi8146.practice.PracticeActivity;

/*
 * Tanvi Prashant Magdum
 * Assignment 01
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button practice = findViewById(R.id.practice);
        Button inClass_01 = findViewById(R.id.inclass01);
        Button inClass_02 = findViewById(R.id.inclass02);
        Button inClass_03 = findViewById(R.id.inclass03);

        practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PracticeActivity.class);
                startActivity(intent);
            }
        });

        inClass_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InClass01Activity.class);
                startActivity(intent);
            }
        });

        inClass_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InClass02Activity.class);
                startActivity(intent);
            }
        });

        inClass_03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InClass03HostActivity.class);
                startActivity(intent);
            }
        });
    }
}