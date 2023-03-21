package com.example.cs5520_inclass_tanvi8146;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    private TextView txtLoginUsername;
    private TextView txtLoginPassword;
    private Button btnLoginLogin;
    private static boolean authenticated = false;
    private static String authToken = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Notes App Login");

        txtLoginUsername = findViewById(R.id.txtLoginUsername);
        txtLoginPassword = findViewById(R.id.txtLoginPassword);
        btnLoginLogin = findViewById(R.id.btnLoginLogin);

        btnLoginLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(txtLoginUsername.getText().toString().trim().equals("") ||
                        txtLoginPassword.getText().toString().trim().equals("")){

                    Context context = getApplicationContext();
                    CharSequence text = "Cannot Login. Enter proper email or password!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    Toast.makeText(context, text, duration);
                    return;

                }

                OkHttpClient client = new OkHttpClient();

                RequestBody formBody = new FormBody.Builder()
                        .add("email", txtLoginUsername.getText().toString())
                        .add("password", txtLoginPassword.getText().toString())
                        .build();

                Request request = new Request.Builder()
                        .url("http://ec2-54-164-201-39.compute-1.amazonaws.com:3000/api/auth/login")
                        .post(formBody)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                Context context = getApplicationContext();
                                CharSequence text = "Cannot Login. Please check your email and password!";
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
                            try {
                                JSONObject jsonRes = new JSONObject(response.body().string());
                                authenticated = jsonRes.getBoolean("auth");
                                authToken = jsonRes.getString("token");

                                if(authenticated){
                                    SharedPreferences sharedPref = getSharedPreferences("sharedpref", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPref.edit();
                                    editor.putString("authToken", authToken);
                                    editor.apply();
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Intent intent = new Intent(LoginActivity.this, NotesActivity.class);
                                            startActivity(intent);
                                        }
                                    });
                                }
                                else{
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Context context = getApplicationContext();
                                            CharSequence text = "Cannot Login Please check your email and password!";
                                            int duration = Toast.LENGTH_SHORT;
                                            Toast toast = Toast.makeText(context, text, duration);
                                            toast.show();
                                            Toast.makeText(context, text, duration);
                                            return;
                                        }
                                    });
                                }
                            } catch (JSONException e) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Context context = getApplicationContext();
                                        CharSequence text = "Cannot Login Please check your email and password!";
                                        int duration = Toast.LENGTH_SHORT;
                                        Toast toast = Toast.makeText(context, text, duration);
                                        toast.show();
                                        Toast.makeText(context, text, duration);
                                        return;
                                    }
                                });

                            }
                        }
                        else{
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Context context = getApplicationContext();
                                    CharSequence text = "Cannot Login Please check your email and password!";
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
        });
    }
}