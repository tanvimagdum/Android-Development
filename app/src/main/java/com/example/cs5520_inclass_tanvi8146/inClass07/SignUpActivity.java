package com.example.cs5520_inclass_tanvi8146.inClass07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cs5520_inclass_tanvi8146.R;

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

public class SignUpActivity extends AppCompatActivity {

    private TextView txtSignUpName;
    private TextView txtSignUpEmail;
    private TextView txtSignUpPassword;
    private Button btnSignUpSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setTitle("Notes App Register");

        txtSignUpName = findViewById(R.id.txtSignUpName);
        txtSignUpEmail = findViewById(R.id.txtSignUpEmail);
        txtSignUpPassword = findViewById(R.id.txtSignUpPassword);
        btnSignUpSignUp = findViewById(R.id.btnSignUpSignUp);

        btnSignUpSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtSignUpEmail.getText().toString().trim().equals("") ||
                        txtSignUpName.getText().toString().trim().equals("") ||
                        txtSignUpPassword.getText().toString().trim().equals("") ||
                        !(Patterns.EMAIL_ADDRESS.matcher(txtSignUpEmail.getText().toString()).matches())) {

                    Context context = getApplicationContext();
                    CharSequence text = "Cannot Sign up. Enter proper username or email or password!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    Toast.makeText(context, text, duration);
                    return;

                }

                OkHttpClient client = new OkHttpClient();
                RequestBody formBody = new FormBody.Builder()
                        .add("name", txtSignUpName.getText().toString())
                        .add("email", txtSignUpEmail.getText().toString())
                        .add("password", txtSignUpPassword.getText().toString())
                        .build();

                Request request = new Request.Builder()
                        .url("http://ec2-54-164-201-39.compute-1.amazonaws.com:3000/api/auth/register")
                        .post(formBody)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Context context = getApplicationContext();
                                CharSequence text = "Cannot Sign up. Maybe the username and email has already been taken!";
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
                                if(!jsonRes.getBoolean("auth")){
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Context context = getApplicationContext();
                                            CharSequence text = "Cannot Sign up. " +
                                                    "Maybe the username and email has already been taken!";
                                            int duration = Toast.LENGTH_SHORT;
                                            Toast toast = Toast.makeText(context, text, duration);
                                            toast.show();
                                            Toast.makeText(context, text, duration);
                                            return;
                                        }
                                    });
                                }else{
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Intent intent = new Intent(SignUpActivity.this, InClass07Activity.class);
                                            startActivity(intent);
                                        }
                                    });
                                }
                            } catch (JSONException e) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Context context = getApplicationContext();
                                        CharSequence text = "Cannot Sign up. " +
                                                "Maybe the username and email has already been taken!";
                                        int duration = Toast.LENGTH_SHORT;
                                        Toast toast = Toast.makeText(context, text, duration);
                                        toast.show();
                                        Toast.makeText(context, text, duration);
                                        return;
                                    }
                                });
                            }
                        }else{
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Context context = getApplicationContext();
                                    CharSequence text = "Cannot Sign up. " +
                                            "Maybe the username and email has already been taken!";
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