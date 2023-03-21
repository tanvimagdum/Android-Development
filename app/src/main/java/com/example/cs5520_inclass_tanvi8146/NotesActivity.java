package com.example.cs5520_inclass_tanvi8146;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NotesActivity extends AppCompatActivity implements AddEditNoteFragment.IAddButtonActions {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mRecyclerViewLayoutManager;
    private NotesAdapter mAdapter;

    private static Notes notes = new Notes();
    private static String authToken = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        setTitle("Notes App");
        notes = new Notes();
        SharedPreferences sh = getSharedPreferences("sharedpref", MODE_PRIVATE);
        authToken = sh.getString("authToken","");
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://ec2-54-164-201-39.compute-1.amazonaws.com:3000/api/note/getall").addHeader("x-access-token", authToken).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Context context = getApplicationContext();
                        CharSequence text = "Cannot get all the Notes. Please try again!";
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
                    notes = gson.fromJson(response.body().charStream(), Notes.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mRecyclerView = findViewById(R.id.notesRecyclerView);
                            mRecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());
                            mRecyclerView.setLayoutManager(mRecyclerViewLayoutManager);
                            mAdapter = new NotesAdapter(notes.getNotes());
                            mRecyclerView.setAdapter(mAdapter);

                            getSupportFragmentManager().beginTransaction()
                                    .add(R.id.addEditNotePanel, AddEditNoteFragment.newInstance(), "add fragment")
                                    .commit();
                        }
                    });

                }else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Context context = getApplicationContext();
                            CharSequence text = "Cannot get all the Notes. Please try again!";
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

    @Override
    public void addButtonClicked(Note note) {
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder().add("text", note.getText()).build();
        Request request = new Request.Builder().url("http://ec2-54-164-201-39.compute-1.amazonaws.com:3000/api/note/post").post(formBody).addHeader("x-access-token", authToken).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Context context = getApplicationContext();
                        CharSequence text = "Cannot add Note. Please try again!";
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
                        if(jsonRes.getBoolean("posted")){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    notes.addNotes(note);
                                    mAdapter.notifyDataSetChanged();
                                }
                            });

                        }
                    } catch (JSONException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Context context = getApplicationContext();
                                CharSequence text = "Cannot add Note. Please try again!";
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
                            CharSequence text = "Cannot add Note. Please try again!";
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