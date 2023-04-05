package com.example.cs5520_inclass_tanvi8146.inClass08_09;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cs5520_inclass_tanvi8146.R;

/*
 * Tanvi Prashant Magdum
 * Assignment 08
 */


public class sendMessageFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Message message;
    private ISendMessageAction mListener;
    private Uri imageUri;
    private ImageView imgPreview;

    public sendMessageFragment() {

    }

    public static sendMessageFragment newInstance() {
        sendMessageFragment fragment = new sendMessageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    ActivityResultLauncher<Intent> startActivityForResult
            = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        assert result.getData() != null;
                        imageUri = result.getData().getData();
                        if (imageUri == null) {
                            Bitmap photo = (Bitmap) result.getData().getExtras().get("data");
                            imgPreview.setImageBitmap(photo);
                            imageUri = saveImageToStorage(photo);
                        }
                        if (imgPreview != null) {
                            imgPreview.setVisibility(View.VISIBLE);
                            Glide.with(getActivity()).load(imageUri).into(imgPreview);
                        }
                    }
                }
            });

    private Uri saveImageToStorage(Bitmap bitmap) {
        String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, "ProfilePhoto", null);
        return Uri.parse(path);
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
        ImageView imgSendCameraPhoto = view.findViewById(R.id.imgSendCameraPhoto);
        imgPreview = view.findViewById(R.id.imgPreview);
        imgPreview.setVisibility(View.INVISIBLE);

        imgbtnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messageText = txtSendMessage.getText().toString().trim();
                if (!messageText.isEmpty() || imgPreview != null) {
                    Message message = new Message();
                    message.setTextMessage(messageText);
                    message.setImageUrl(imageUri != null ? imageUri.toString() : null);
                    mListener.sendMessage(message);
                    txtSendMessage.setText("");
                    if (imgPreview != null) {
                        imgPreview.setVisibility(View.INVISIBLE);
                        imgPreview.setImageResource(0);
                        imageUri = null;
                    }
                } else {
                    Toast.makeText(getContext(), "Message cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imgSendCameraPhoto.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgSendCameraPhoto:
                selectPhoto();
                break;
        }
    }

    private void selectPhoto() {
        Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        Intent chooserIntent = Intent.createChooser(galleryIntent, "Select Source");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {cameraIntent});

        startActivityForResult.launch(chooserIntent);
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