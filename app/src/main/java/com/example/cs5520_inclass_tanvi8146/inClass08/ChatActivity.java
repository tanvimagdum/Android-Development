package com.example.cs5520_inclass_tanvi8146.inClass08;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cs5520_inclass_tanvi8146.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

/*
 * Tanvi Prashant Magdum
 * Assignment 08
 */

public class ChatActivity extends AppCompatActivity implements ChatListAdapter.OnUserClickListener {

    private RecyclerView recyclerView;
    private ChatListAdapter adapter;
    private FirebaseAuth mAuth;
    private Button btnFirebaseEditProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        btnFirebaseEditProfile = findViewById(R.id.btnFirebaseEditProfile);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String currentUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        mAuth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.chatRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Query query = db.collection("users")
                .whereNotEqualTo("uid", currentUserID)
                .orderBy("uid")
                .orderBy("firstName", Query.Direction.ASCENDING);

        FirestoreRecyclerOptions<User> options = new FirestoreRecyclerOptions.Builder<User>()
                .setQuery(query, User.class)
                .build();

        adapter = new ChatListAdapter(options, this);
        recyclerView.setAdapter(adapter);

        btnFirebaseEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChatActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            Intent intent = new Intent(ChatActivity.this, InClass08Activity.class);
            startActivity(intent);
            finish();
        }

        adapter.startListening();

    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onUserClick(String friendId) {
        Intent intent = new Intent(this, MessengerActivity.class);
        intent.putExtra("friendId", friendId);
        startActivity(intent);
    }

}