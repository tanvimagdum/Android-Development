package com.example.cs5520_inclass_tanvi8146;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class SelectAvatarFragment extends Fragment {

    private ImageView imgFragAvatarf1;
    private ImageView imgFragAvatarf2;
    private ImageView imgFragAvatarf3;
    private ImageView imgFragAvatarm1;
    private ImageView imgFragAvatarm2;
    private ImageView imgFragAvatarm3;

    private static final String ARG_PARAM = "param1";
    private AvatarFromSelectAvatarToHostActivity sendAvatar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("Select Avatar");

        View rootView = inflater.inflate(R.layout.fragment_select_avatar, container, false);

        imgFragAvatarf1 = rootView.findViewById(R.id.imgFragAvatarf1);
        imgFragAvatarf2 = rootView.findViewById(R.id.imgFragAvatarf2);
        imgFragAvatarf3 = rootView.findViewById(R.id.imgFragAvatarf3);
        imgFragAvatarm1 = rootView.findViewById(R.id.imgFragAvatarm1);
        imgFragAvatarm2 = rootView.findViewById(R.id.imgFragAvatarm2);
        imgFragAvatarm3 = rootView.findViewById(R.id.imgFragAvatarm3);

        if (getArguments() != null) {
            ProfileFrag profile = (ProfileFrag) getArguments().getSerializable(ARG_PARAM);

            imgFragAvatarf1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    profile.setImgAvatar(R.drawable.avatar_f_1);
                    sendAvatar.avatarFromSelectAvatar(profile);
                }
            });

            imgFragAvatarf2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    profile.setImgAvatar(R.drawable.avatar_f_2);
                    sendAvatar.avatarFromSelectAvatar(profile);
                }
            });

            imgFragAvatarf3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    profile.setImgAvatar(R.drawable.avatar_f_3);
                    sendAvatar.avatarFromSelectAvatar(profile);
                }
            });

            imgFragAvatarm1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    profile.setImgAvatar(R.drawable.avatar_m_1);
                    sendAvatar.avatarFromSelectAvatar(profile);
                }
            });

            imgFragAvatarm2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    profile.setImgAvatar(R.drawable.avatar_m_2);
                    sendAvatar.avatarFromSelectAvatar(profile);
                }
            });

            imgFragAvatarm3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    profile.setImgAvatar(R.drawable.avatar_m_3);
                    sendAvatar.avatarFromSelectAvatar(profile);
                }
            });

        }

        return rootView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof AvatarFromSelectAvatarToHostActivity) {
            sendAvatar = (AvatarFromSelectAvatarToHostActivity) context;
        }
        else {
            throw new RuntimeException(context + " must implement interface");
        }
    }

    public interface AvatarFromSelectAvatarToHostActivity {
        void avatarFromSelectAvatar(ProfileFrag profile);
    }

    public static SelectAvatarFragment newInstance(ProfileFrag profile) {
        SelectAvatarFragment fragment = new SelectAvatarFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM, profile);
        fragment.setArguments(args);
        return fragment;
    }

}