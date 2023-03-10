package com.example.cs5520_inclass_tanvi8146.inClass01;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs5520_inclass_tanvi8146.R;

/*
 * Tanvi Prashant Magdum
 * Assignment 01
 */

public class InClass01Activity extends AppCompatActivity {

    private Button btn_bmi;
    private TextView txtWeight;
    private TextView txtHeightFt;
    private TextView txtHeightIn;
    private TextView txtMessage;
    private TextView txtStatus;
    private TextView msgBMI;

    @Override
    protected void onCreate(Bundle savedInstanceStat) {
        super.onCreate(savedInstanceStat);
        setContentView(R.layout.activity_inclass01);
        setTitle("BMI Calculator");

        btn_bmi = findViewById(R.id.btnBMI);
        btn_bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txtWeight = findViewById(R.id.txtWeight);
                txtHeightFt = findViewById(R.id.txtHeightFt);
                txtHeightIn = findViewById(R.id.txtHeightIn);
                txtMessage = findViewById(R.id.txtMessage);
                txtStatus = findViewById(R.id.txtStatus);
                msgBMI = findViewById(R.id.msgBMI);

                msgBMI.setText("Click on Calculate BMI to find your BMI");
                txtMessage.setText("");
                txtStatus.setText("");


                String strWeight = String.valueOf(txtWeight.getText());
                float weight = 0;
                try {
                    weight = Float.parseFloat(strWeight);
                } catch (Exception e) {
                    Toast.makeText(InClass01Activity.this, "Invalid Inputs", Toast.LENGTH_SHORT).show();
                    return;
                }
                String strHeightFt = String.valueOf(txtHeightFt.getText());
                float heightFt = 0;
                try {
                    heightFt = Float.parseFloat(strHeightFt);
                } catch (Exception e) {
                    Toast.makeText(InClass01Activity.this, "Invalid Inputs", Toast.LENGTH_SHORT).show();
                    return;
                }
                String strHeightIn = String.valueOf(txtHeightIn.getText());
                float heightIn = 0;
                try {
                    heightIn = Float.parseFloat(strHeightIn);
                } catch (Exception e) {
                    Toast.makeText(InClass01Activity.this, "Invalid Inputs", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (weight <= 0) {
                    Toast.makeText(InClass01Activity.this, "Invalid Inputs", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (heightFt == 0) {
                    if (heightIn <= 0) {
                        Toast.makeText(InClass01Activity.this, "Invalid Inputs", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                if (heightFt < 0) {
                    Toast.makeText(InClass01Activity.this, "Invalid Inputs", Toast.LENGTH_SHORT).show();
                    return;
                }

                float heightConverted = (heightFt * 12) + heightIn;
                float bmi = (weight/(heightConverted * heightConverted)) * 703;

                Toast.makeText(InClass01Activity.this, "BMI Calculated", Toast.LENGTH_SHORT).show();
                txtMessage.setText(String.format("Your BMI : %.2f", bmi));
                msgBMI.setText("");

                if (bmi < 18.5) {
                    txtStatus.setText("You are Underweight");
                }
                else if ((bmi >= 18.5) && (bmi <= 24.9)) {
                    txtStatus.setText("You are Normal Weight");
                }
                else if ((bmi >= 25) && (bmi <= 29.9)) {
                    txtStatus.setText("You are Overweight");
                }
                else if (bmi >= 30) {
                    txtStatus.setText("You are Obese");
                }
            }

        });
    }

}
