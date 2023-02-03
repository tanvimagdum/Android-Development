package com.example.cs5520_inclass_tanvi8146;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DisplayActivity extends AppCompatActivity {

    private TextView txtDisplayName;
    private TextView txtDisplayEmail;
    private ImageView imgDisplayAvatar;
    private TextView txtDisplayIUse;
    private TextView txtDisplayMood;
    private ImageView imgDisplayMood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        setTitle("Display Activity");

        txtDisplayName = findViewById(R.id.txtDisplayName);
        txtDisplayEmail = findViewById(R.id.txtDisplayEmail);
        imgDisplayAvatar = findViewById(R.id.imgDisplayAvatar);
        txtDisplayIUse = findViewById(R.id.txtDisplayIUse);
        txtDisplayMood = findViewById(R.id.txtDisplayMood);
        imgDisplayMood = findViewById(R.id.imgDisplayMood);

        if(getIntent() != null && getIntent().getExtras() != null) {
            Profile profile = (Profile) getIntent().getSerializableExtra(InClass02Activity.Profile_Key);
            txtDisplayName.setText(profile.name);
            txtDisplayEmail.setText(profile.email);
            txtDisplayIUse.setText(profile.os);
            txtDisplayMood.setText(profile.mood);
            imgDisplayAvatar.setImageResource(profile.imgAvatar);
            imgDisplayMood.setImageResource(profile.imgMood);
        }

    }
}