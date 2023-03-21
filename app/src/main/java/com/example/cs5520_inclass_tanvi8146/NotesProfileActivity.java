package com.example.cs5520_inclass_tanvi8146;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NotesProfileActivity extends AppCompatActivity {

    private TextView txtNotesProfileName;
    private TextView txtNotesProfileEmail;
    private TextView txtNotesProfilePassword;
    private static String authToken = "";
    private static User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_profile);
        setTitle("Profile");

        txtNotesProfileName = findViewById(R.id.txtNotesProfileName);
        txtNotesProfileEmail = findViewById(R.id.txtNotesProfileEmail);
        txtNotesProfilePassword = findViewById(R.id.txtNotesProfilePassword);

        SharedPreferences sh = getSharedPreferences("sharedpref", MODE_PRIVATE);
        authToken = sh.getString("authToken","");

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://ec2-54-164-201-39.compute-1.amazonaws.com:3000/api/auth/me")
                .addHeader("x-access-token", authToken)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Context context = getApplicationContext();
                        CharSequence text = "Cannot get user details. Please try again!";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        Toast.makeText(context, text, duration);
                        return;
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()) {
                    Gson gson = new Gson();
                    user = gson.fromJson(response.body().charStream(), User.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            txtNotesProfileEmail.setText(user.getEmail());
                            txtNotesProfileName.setText(user.getName());
                            txtNotesProfilePassword.setText(user.get_id());
                        }
                    });
                }
                else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Context context = getApplicationContext();
                            CharSequence text = "Cannot get user details. Please try again!";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                            Toast.makeText(context, text, duration);
                            return;
                        }
                    });
                }
            }
        });
        


    }
}