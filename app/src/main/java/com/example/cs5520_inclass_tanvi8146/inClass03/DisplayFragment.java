package com.example.cs5520_inclass_tanvi8146.inClass03;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cs5520_inclass_tanvi8146.R;

/*
 * Tanvi Prashant Magdum
 * Assignment 03
 */

public class DisplayFragment extends Fragment {

    private static final String ARG_PARAM = "param1";

    private TextView txtFragDisplayName;
    private TextView txtFragDisplayEmail;
    private ImageView imgFragDisplayAvatar;
    private TextView txtFragDisplayIUse;
    private TextView txtFragDisplayMood;
    private ImageView imgFragDisplayMood;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("Display Activity");

        View rootView = inflater.inflate(R.layout.fragment_display, container, false);

        txtFragDisplayName = rootView.findViewById(R.id.txtFragDisplayName);
        txtFragDisplayEmail = rootView.findViewById(R.id.txtFragDisplayEmail);
        imgFragDisplayAvatar = rootView.findViewById(R.id.imgFragDisplayAvatar);
        txtFragDisplayIUse = rootView.findViewById(R.id.txtFragDisplayIUse);
        txtFragDisplayMood = rootView.findViewById(R.id.txtFragDisplayMood);
        imgFragDisplayMood = rootView.findViewById(R.id.imgFragDisplayMood);

        if (getArguments() != null) {
            ProfileFrag profile = (ProfileFrag) getArguments().getSerializable(ARG_PARAM);
            txtFragDisplayName.setText(profile.getName());
            txtFragDisplayEmail.setText(profile.getEmail());
            txtFragDisplayIUse.setText(profile.getOs());
            txtFragDisplayMood.setText(profile.getMood());
            imgFragDisplayAvatar.setImageResource(profile.getImgAvatar());
            imgFragDisplayMood.setImageResource(profile.getImgMood());
        }

        return rootView;
    }

    public static DisplayFragment newInstance(ProfileFrag profile) {
        DisplayFragment fragment = new DisplayFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM, profile);
        fragment.setArguments(args);
        return fragment;
    }

}