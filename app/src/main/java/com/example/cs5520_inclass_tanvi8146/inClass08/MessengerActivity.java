package com.example.cs5520_inclass_tanvi8146.inClass08;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.cs5520_inclass_tanvi8146.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Arrays;
import java.util.Date;

/*
 * Tanvi Prashant Magdum
 * Assignment 08
 */

public class MessengerActivity extends AppCompatActivity implements sendMessageFragment.ISendMessageAction {

    private String mFriendId;
    private FirebaseFirestore db;
    private FirebaseUser currentUser;
    private MessengerAdapter adapter;
    private RecyclerView messageRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        setTitle("Messenger");

        getSupportFragmentManager().beginTransaction()
                .add(R.id.sendMessagePanel, sendMessageFragment.newInstance(), "sendMessage")
                .commit();

        Intent intent = getIntent();
        mFriendId = intent.getStringExtra("friendId");
        db = FirebaseFirestore.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        messageRecyclerView = findViewById(R.id.messengerRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        messageRecyclerView.setLayoutManager(layoutManager);


        Query query = db.collection("messages")
                .whereIn("senderId", Arrays.asList(currentUser.getUid(), mFriendId))
                .whereIn("receiverId", Arrays.asList(currentUser.getUid(), mFriendId))
                .orderBy("timestamp", Query.Direction.ASCENDING);

        FirestoreRecyclerOptions<Message> options = new FirestoreRecyclerOptions.Builder<Message>()
                .setQuery(query, Message.class)
                .build();

        adapter = new MessengerAdapter(options, currentUser.getUid());
        messageRecyclerView.setAdapter(adapter);

    }

    @Override
    public void sendMessage(Message message) {
        message.setSenderId(currentUser.getUid());
        message.setReceiverId(mFriendId);
        message.setTimestamp(String.valueOf(new Date()));

        db.collection("messages").add(message)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        adapter.notifyDataSetChanged();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error adding message to Firestore", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        messageRecyclerView.setAdapter(null);
    }

    @Override
    public void onResume() {
        super.onResume();
        messageRecyclerView.getAdapter().notifyDataSetChanged();
    }

}