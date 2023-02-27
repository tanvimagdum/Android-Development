package com.example.cs5520_inclass_tanvi8146;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cs5520_inclass_tanvi8146.inClass01.InClass01Activity;
import com.example.cs5520_inclass_tanvi8146.inClass02.InClass02Activity;
import com.example.cs5520_inclass_tanvi8146.inClass03.InClass03HostActivity;
import com.example.cs5520_inclass_tanvi8146.inClass04.InClass04Activity;
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
        Button inClass_04 = findViewById(R.id.inclass04);
        Button inClass_05 = findViewById(R.id.inclass05);

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

        inClass_04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InClass04Activity.class);
                startActivity(intent);
            }
        });

        inClass_05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InClass05Activity.class);
                startActivity(intent);
            }
        });


    }
}