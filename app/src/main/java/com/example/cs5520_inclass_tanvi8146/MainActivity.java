package com.example.cs5520_inclass_tanvi8146;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cs5520_inclass_tanvi8146.inClass01.InClass01Activity;
import com.example.cs5520_inclass_tanvi8146.inClass02.InClass02Activity;
import com.example.cs5520_inclass_tanvi8146.inClass03.InClass03HostActivity;
import com.example.cs5520_inclass_tanvi8146.inClass04.InClass04Activity;
import com.example.cs5520_inclass_tanvi8146.inClass05.InClass05Activity;
import com.example.cs5520_inclass_tanvi8146.inClass06.InClass06Activity;
import com.example.cs5520_inclass_tanvi8146.inClass07.InClass07Activity;
import com.example.cs5520_inclass_tanvi8146.inClass07.NotesActivity;
import com.example.cs5520_inclass_tanvi8146.inClass08.InClass08Activity;
import com.example.cs5520_inclass_tanvi8146.practice.PracticeActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


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
        Button inClass_06 = findViewById(R.id.inclass06);
        Button inClass_07 = findViewById(R.id.inclass07);
        Button inClass_08 = findViewById(R.id.inclass08);

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

        inClass_06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InClass06Activity.class);
                startActivity(intent);
            }
        });

        inClass_07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sh = getSharedPreferences("sharedpref", MODE_PRIVATE);
                String authToken = sh.getString("authToken", "");
                if(!authToken.equals("")){
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url("http://ec2-54-164-201-39.compute-1.amazonaws.com:3000/api/auth/me").addHeader("x-access-token", authToken).build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(MainActivity.this, InClass07Activity.class);
                                startActivity(intent);
                            }
                        });
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject jobj = new JSONObject(response.body().string());
                                if(jobj.has("_id")){
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Intent intent = new Intent(MainActivity.this, NotesActivity.class);
                                            startActivity(intent);
                                        }
                                    });

                                }else{
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Intent intent = new Intent(MainActivity.this, InClass07Activity.class);
                                            startActivity(intent);
                                        }
                                    });

                                }
                            } catch (JSONException e) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(MainActivity.this, InClass07Activity.class);
                                        startActivity(intent);
                                    }
                                });

                            }
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(MainActivity.this, InClass07Activity.class);
                                    startActivity(intent);
                                }
                            });
                        }
                    }
                });
            }else{
                    Intent intent = new Intent(MainActivity.this, InClass07Activity.class);
                    startActivity(intent);
                }
                }

        });

        inClass_08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InClass08Activity.class);
                startActivity(intent);
            }
        });

    }
}