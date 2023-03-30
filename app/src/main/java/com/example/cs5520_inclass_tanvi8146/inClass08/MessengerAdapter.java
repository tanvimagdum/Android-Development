package com.example.cs5520_inclass_tanvi8146.inClass08;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs5520_inclass_tanvi8146.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


/*
 * Tanvi Prashant Magdum
 * Assignment 08
 */

public class MessengerAdapter extends FirestoreRecyclerAdapter<Message, MessengerAdapter.MessageViewHolder> {

    private static final int MESSAGE_SENT = 1;
    private static final int MESSAGE_RECEIVED = 2;

    private String currentUserId;

    public MessengerAdapter(@NonNull FirestoreRecyclerOptions<Message> options, String currentUserId) {
        super(options);
        this.currentUserId = currentUserId;
    }

    @Override
    protected void onBindViewHolder(@NonNull MessageViewHolder holder, int position, @NonNull Message model) {
        if (model.getSenderId().equals(currentUserId)) {
            holder.bindSentMessage(model);
        } else {
            holder.bindReceivedMessage(model);
        }
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.messenger_list_sent, parent, false);
            return new SentMessageViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.messenger_list_received, parent, false);
            return new ReceivedMessageViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        Message message = getItem(position);
        if (message.getSenderId().equals(currentUserId)) {
            return MESSAGE_SENT;
        } else {
            return MESSAGE_RECEIVED;
        }
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bindSentMessage(Message message) {}
        public void bindReceivedMessage(Message message) {}
    }

    public static class SentMessageViewHolder extends MessageViewHolder {
        private TextView messageText;

        public SentMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.txtMessengerChatSent);
        }

        @Override
        public void bindSentMessage(Message message) {
            messageText.setText(message.getTextMessage());
        }
    }

    public static class ReceivedMessageViewHolder extends MessageViewHolder {
        private TextView messageText;
        private TextView senderName;

        public ReceivedMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.txtMessengerChatReceived);
            senderName = itemView.findViewById(R.id.txtMessengerChatFriend);
        }

        @Override
        public void bindReceivedMessage(Message message) {
            messageText.setText(message.getTextMessage());
            FirebaseFirestore.getInstance().collection("users").document(message.getSenderId()).get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                String sender = documentSnapshot.getString("firstName");
                                senderName.setText(sender);
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @SuppressLint("RestrictedApi")
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Failed to retrieve sender's user object", Toast.LENGTH_SHORT).show();

                        }
                    });

        }
    }
}