package com.example.cs5520_inclass_tanvi8146;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


public class sendMessageFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Message message;
    private ISendMessageAction mListener;

    public sendMessageFragment() {

    }

    public static sendMessageFragment newInstance() {
        sendMessageFragment fragment = new sendMessageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_send_message, container, false);
        EditText txtSendMessage = view.findViewById(R.id.txtSendMessage);
        ImageButton imgbtnSendMessage = view.findViewById(R.id.imgbtnSendMessage);

        imgbtnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message message = new Message();
                message.setTextMessage(txtSendMessage.getText().toString());
                mListener.sendMessage(message);
                txtSendMessage.setText("");
            }
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof sendMessageFragment.ISendMessageAction) {
            mListener = (ISendMessageAction) context;
        }
        else {
            throw new RuntimeException(context.toString() + " must implement ISendMessageAction interface.");
        }
    }

    public interface ISendMessageAction {
        void sendMessage(Message message);
    }
}