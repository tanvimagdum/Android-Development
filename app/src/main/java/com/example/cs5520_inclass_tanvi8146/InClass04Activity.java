package com.example.cs5520_inclass_tanvi8146;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cs5520_inclass_tanvi8146.inClass02.InClass02Activity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InClass04Activity extends AppCompatActivity {

    private Button btnGenerateNumber;
    private SeekBar seekComplexity;
    private TextView txtComplexity;
    private TextView txtMinimum;
    private TextView txtMaximum;
    private TextView txtAverage;
    private ExecutorService threadPool;
    private Handler messageQueue;
    private ProgressBar progressGenerateNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inclass04);
        setTitle("Number Generator");

        txtComplexity = findViewById(R.id.txtComplexity);
        txtMinimum = findViewById(R.id.txtMinimum);
        txtMaximum = findViewById(R.id.txtMaximum);
        txtAverage = findViewById(R.id.txtAverage);
        seekComplexity = findViewById(R.id.seekComplexity);
        progressGenerateNumber = findViewById(R.id.progressGenerateNumber);
        btnGenerateNumber = findViewById(R.id.btnGenerateNumber);

        progressGenerateNumber.setVisibility(View.INVISIBLE);

        seekComplexity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                txtComplexity.setText(String.format("%d", i));
                txtMinimum.setText("");
                txtMaximum.setText("");
                txtAverage.setText("");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        threadPool = Executors.newFixedThreadPool(1);

        btnGenerateNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = Integer.parseInt(txtComplexity.getText().toString());
                if (number == 0) {
                    Toast.makeText(InClass04Activity.this, "Please select complexity more than 0", Toast.LENGTH_SHORT).show();
                    return;
                }
                threadPool.execute(new HeavyWork(number, messageQueue));
                progressGenerateNumber.setVisibility(View.VISIBLE);
                progressGenerateNumber.setMax(number);
            }
        });

        messageQueue = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {

                switch(message.what) {
                    case HeavyWork.STATUS_PROGRESS:
                        Bundle receivedProgress = message.getData();
                        int i = receivedProgress.getInt(HeavyWork.KEY_PROGRESS);
                        progressGenerateNumber.setProgress(i);
                        break;
                    case HeavyWork.STATUS_VALUES:
                        Bundle receivedBundle = message.getData();
                        double min = receivedBundle.getDouble(HeavyWork.KEY_MIN);
                        double max = receivedBundle.getDouble(HeavyWork.KEY_MAX);
                        double avg = receivedBundle.getDouble(HeavyWork.KEY_AVG);
                        txtMinimum.setText(String.format("%.2f", min));
                        txtMaximum.setText(String.format("%.2f", max));
                        txtAverage.setText(String.format("%.2f", avg));
                        progressGenerateNumber.setVisibility(View.INVISIBLE);
                        progressGenerateNumber.setProgress(0);
                        break;
                }



                return false;
            }
        });
    }

}