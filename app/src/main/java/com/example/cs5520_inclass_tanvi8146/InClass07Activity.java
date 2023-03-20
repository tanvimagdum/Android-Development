package com.example.cs5520_inclass_tanvi8146;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/*
 * Tanvi Prashant Magdum
 * Assignment 07
 */


public class InClass07Activity extends AppCompatActivity {

    private Button btnLogin;
    private Button btnSignUp;
    private static boolean authenticated = false;
    private static String authToken = null;
    private static User user = null;
    private static Notes notes = null;
    private static boolean posted = false;
    private static boolean deleted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class07);
        setTitle("Notes App");

        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(InClass07Activity.this, LoginActivity.class);
                startActivity(intent1);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(InClass07Activity.this, SignUpActivity.class);
                startActivity(intent2);
            }
        });
    }


    protected void signUp(String name, String email, String password){
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder().add("name", name).add("email", email).add("password", password).build();
        Request request = new Request.Builder().url("http://ec2-54-164-201-39.compute-1.amazonaws.com:3000/api/auth").post(formBody).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                authenticated = false;
                authToken = null;
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()) {
                    try {
                        JSONObject jsonRes = new JSONObject(response.body().string());
                        authenticated = jsonRes.getBoolean("auth");
                        authToken = jsonRes.getString("token");
                    } catch (JSONException e) {
                        authenticated = false;
                        authToken = null;
                    }
                }else{
                    authenticated = false;
                    authToken = null;
                }

            }
        });
    }

    protected void login(String email, String password){
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder().add("email", email).add("password", password).build();
        Request request = new Request.Builder().url("http://ec2-54-164-201-39.compute-1.amazonaws.com:3000/api/auth/login").post(formBody).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                authenticated = false;
                authToken = null;
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()) {
                    try {
                        JSONObject jsonRes = new JSONObject(response.body().string());
                        authenticated = jsonRes.getBoolean("auth");
                        authToken = jsonRes.getString("token");
                    } catch (JSONException e) {
                        authenticated = false;
                        authToken = null;
                    }
                }else{
                    authenticated = false;
                    authToken = null;
                }

            }
        });
    }

    protected void getUser(String authToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://ec2-54-164-201-39.compute-1.amazonaws.com:3000/api/auth/me").addHeader("x-access-token", authToken).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                user = null;
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()) {
                    Gson gson = new Gson();
                    user = gson.fromJson(response.body().charStream(), User.class);
                }else{
                    user = null;
                }
            }
        });
    }

    protected void getAllNotes(String authToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://ec2-54-164-201-39.compute-1.amazonaws.com:3000/api/note/getall").addHeader("x-access-token", authToken).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                notes = null;
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()) {
                    Gson gson = new Gson();
                    notes = gson.fromJson(response.body().charStream(), Notes.class);
                }else{
                    notes = null;
                }
            }
        });
    }

    protected void addNote(String authToken, String note){
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder().add("email", note).build();
        Request request = new Request.Builder().url("http://ec2-54-164-201-39.compute-1.amazonaws.com:3000/api/auth/login").post(formBody).addHeader("x-access-token", authToken).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                posted = false;
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()) {
                    try {
                        JSONObject jsonRes = new JSONObject(response.body().string());
                        posted = jsonRes.getBoolean("posted");
                    } catch (JSONException e) {
                        posted = false;
                    }
                }else{
                    posted = false;
                }

            }
        });
    }

    protected void deleteNote(String authToken, String id){
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder().add("id", id).build();
        Request request = new Request.Builder().url("http://ec2-54-164-201-39.compute-1.amazonaws.com:3000/api/auth/login").post(formBody).addHeader("x-access-token", authToken).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                deleted = false;
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()) {
                    try {
                        JSONObject jsonRes = new JSONObject(response.body().string());
                        deleted = jsonRes.getBoolean("posted");
                    } catch (JSONException e) {
                        deleted = false;
                    }
                }else{
                    deleted = false;
                }
            }
        });
    }


}