package com.example.cs5520_inclass_tanvi8146;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MessengerActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        setTitle("Messenger");

//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.sendMessagePanel, sendMessageFragment.newInstance("yes","no"), "sendMessage");

    }
}