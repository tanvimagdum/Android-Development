package com.example.cs5520_inclass_tanvi8146.inClass02;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cs5520_inclass_tanvi8146.R;

/*
 * Tanvi Prashant Magdum
 * Assignment 02
 */

public class InClass02Activity extends AppCompatActivity {

    final static String Profile_Key = "toProfile";

    private TextView txtName;
    private TextView txtEmail;
    private ImageView imgProfile;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private RadioButton radioAndroid;
    private RadioButton radioiOS;
    private TextView txtMood;
    private SeekBar seekMood;
    private ImageView imgMood;
    private Button btnSubmit;
    private int titleImg;

    ActivityResultLauncher<Intent> startActivityForResult
            = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        assert result.getData() != null;
                        titleImg = result.getData().getIntExtra("imgId",0);
                        imgProfile.setImageResource(titleImg);
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inclass02);
        setTitle("Edit Profile Activity");

        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        radioGroup = findViewById(R.id.radioGroup);
        radioAndroid = findViewById(R.id.radioAndroid);
        radioiOS = findViewById(R.id.radioiOS);

        //avatar selection
        imgProfile = findViewById(R.id.imgProfile);
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InClass02Activity.this, SelectAvatar.class);
                startActivityForResult.launch(intent);

            }
        });

        //mood seekbar
        seekMood = findViewById(R.id.seekMood);
        imgMood = findViewById(R.id.imgMood);
        imgMood.setTag(R.drawable.angry);
        txtMood = findViewById(R.id.txtMood);

        seekMood.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                switch(i) {
                    case 0:
                        txtMood.setText("Angry");
                        imgMood.setImageResource(R.drawable.angry);
                        imgMood.setTag(R.drawable.angry);
                        break;
                    case 1:
                        txtMood.setText("Sad");
                        imgMood.setImageResource(R.drawable.sad);
                        imgMood.setTag(R.drawable.sad);
                        break;
                    case 2:
                        txtMood.setText("Happy");
                        imgMood.setImageResource(R.drawable.happy);
                        imgMood.setTag(R.drawable.happy);
                        break;
                    case 3:
                        txtMood.setText("Awesome");
                        imgMood.setImageResource(R.drawable.awesome);
                        imgMood.setTag(R.drawable.awesome);
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //submit button
        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = txtName.getText().toString();
                String email = txtEmail.getText().toString();

                if (name.isEmpty()) {
                    Toast.makeText(InClass02Activity.this, "Please enter a valid name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (email.isEmpty() || !(Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
                    Toast.makeText(InClass02Activity.this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (titleImg == 0) {
                    Toast.makeText(InClass02Activity.this, "Please select an Avatar", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!radioAndroid.isChecked() && !radioiOS.isChecked()) {
                    Toast.makeText(InClass02Activity.this, "Please select Android or iOS", Toast.LENGTH_SHORT).show();
                    return;
                }

                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                String radio = radioButton.getText().toString();
                String mood = txtMood.getText().toString();
                int emoji = Integer.parseInt(imgMood.getTag().toString());

                Intent intent = new Intent(InClass02Activity.this, DisplayActivity.class);
                Profile profile = new Profile(name, email, radio, mood, titleImg, emoji);
                intent.putExtra(Profile_Key, profile);
                startActivity(intent);

            }
        });

    }
}