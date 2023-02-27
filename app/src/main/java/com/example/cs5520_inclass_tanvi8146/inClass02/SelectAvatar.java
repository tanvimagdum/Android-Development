package com.example.cs5520_inclass_tanvi8146.inClass02;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.cs5520_inclass_tanvi8146.R;

/*
 * Tanvi Prashant Magdum
 * Assignment 02
 */

public class SelectAvatar extends AppCompatActivity {

    private ImageView imgAvatarf1;
    private ImageView imgAvatarf2;
    private ImageView imgAvatarf3;
    private ImageView imgAvatarm1;
    private ImageView imgAvatarm2;
    private ImageView imgAvatarm3;
    private int titleImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_avatar);
        setTitle("Select Avatar");

        imgAvatarf1 = findViewById(R.id.imgAvatarf1);
        imgAvatarf2 = findViewById(R.id.imgAvatarf2);
        imgAvatarf3 = findViewById(R.id.imgAvatarf3);
        imgAvatarm1 = findViewById(R.id.imgAvatarm1);
        imgAvatarm2 = findViewById(R.id.imgAvatarm2);
        imgAvatarm3 = findViewById(R.id.imgAvatarm3);

        imgAvatarf1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("imgId", R.drawable.avatar_f_1);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        imgAvatarf2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("imgId", R.drawable.avatar_f_2);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        imgAvatarf3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("imgId", R.drawable.avatar_f_3);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        imgAvatarm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("imgId", R.drawable.avatar_m_1);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        imgAvatarm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("imgId", R.drawable.avatar_m_2);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        imgAvatarm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("imgId", R.drawable.avatar_m_3);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}