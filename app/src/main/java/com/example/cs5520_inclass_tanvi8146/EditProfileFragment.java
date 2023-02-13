package com.example.cs5520_inclass_tanvi8146;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;



public class EditProfileFragment extends Fragment {

    private static final String ARG_PARAM = null;
    private int avatar;
    private int emoji = R.drawable.angry;

    private TextView txtFragName;
    private TextView txtFragEmail;
    private ImageView imgFragAvatar;
    private RadioGroup radioGroupFrag;
    private RadioButton radioButtonFrag;
    private RadioButton radioFragAndroid;
    private RadioButton radioFragiOS;
    private TextView txtFragMood;
    private SeekBar seekFragMood;
    private ImageView imgFragMood;
    private Button btnFragSubmit;
    private ProfileFromEditProfileToHostActivity sendProfile;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("Edit Profile Activity");

        View rootView = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        txtFragName = rootView.findViewById(R.id.txtFragName);
        txtFragEmail = rootView.findViewById(R.id.txtFragEmail);
        imgFragAvatar = rootView.findViewById(R.id.imgFragAvatar);
        radioGroupFrag = rootView.findViewById(R.id.radioGroupFrag);
        radioFragAndroid = rootView.findViewById(R.id.radioFragAndroid);
        radioFragiOS = rootView.findViewById(R.id.radioFragiOS);
        txtFragMood = rootView.findViewById(R.id.txtFragMood);
        seekFragMood = rootView.findViewById(R.id.seekFragMood);
        imgFragMood = rootView.findViewById(R.id.imgFragMood);
        btnFragSubmit = rootView.findViewById(R.id.btnFragSubmit);

        if (getArguments() != null) {

            ProfileFrag newProfile = (ProfileFrag) getArguments().getSerializable(ARG_PARAM);
            txtFragName.setText(newProfile.getName());
            txtFragEmail.setText(newProfile.getEmail());
            txtFragMood.setText(newProfile.getMood());
            imgFragMood.setImageResource(newProfile.getImgMood());
            imgFragAvatar.setImageResource(newProfile.getImgAvatar());
            avatar = newProfile.getImgAvatar();
            emoji = newProfile.getImgMood();

            if (newProfile.getOs().equals("Android")) {
                radioFragAndroid.setChecked(true);
            }
            else if (newProfile.getOs().equals("iOS")) {
                radioFragiOS.setChecked(true);
            }

            switch(newProfile.getMood()) {
                case "Angry" :
                    seekFragMood.setProgress(0);
                    break;
                case "Sad" :
                    seekFragMood.setProgress(1);
                    break;
                case "Happy":
                    seekFragMood.setProgress(2);
                    break;
                case "Awesome" :
                    seekFragMood.setProgress(3);
                    break;
            }

        }

        imgFragAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txtFragName.getText().toString();
                String email = txtFragEmail.getText().toString();
                int selectedId = radioGroupFrag.getCheckedRadioButtonId();
                radioButtonFrag = rootView.findViewById(selectedId);
                String radio;
                try {
                    radio = radioButtonFrag.getText().toString();
                } catch(Exception e) {
                    radio = "";
                }
                String mood = txtFragMood.getText().toString();

                ProfileFrag profile = new ProfileFrag(name, email, radio, mood, avatar, emoji);
                sendProfile.profileFromEditProfileFragment(profile);

            }
        });

        imgFragMood.setTag(R.drawable.angry);
        seekFragMood.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                switch(i) {
                    case 0:
                        txtFragMood.setText("Angry");
                        imgFragMood.setImageResource(R.drawable.angry);
                        imgFragMood.setTag(R.drawable.angry);
                        break;
                    case 1:
                        txtFragMood.setText("Sad");
                        imgFragMood.setImageResource(R.drawable.sad);
                        imgFragMood.setTag(R.drawable.sad);
                        break;
                    case 2:
                        txtFragMood.setText("Happy");
                        imgFragMood.setImageResource(R.drawable.happy);
                        imgFragMood.setTag(R.drawable.happy);
                        break;
                    case 3:
                        txtFragMood.setText("Awesome");
                        imgFragMood.setImageResource(R.drawable.awesome);
                        imgFragMood.setTag(R.drawable.awesome);
                        break;
                }
                emoji = Integer.parseInt(imgFragMood.getTag().toString());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnFragSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = txtFragName.getText().toString();
                String email = txtFragEmail.getText().toString();

                if (name.isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter a valid name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (email.isEmpty() || !(Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
                    Toast.makeText(getActivity(), "Please enter a valid email", Toast.LENGTH_SHORT).show();
                    return;
                }

                /*if (titleImg == 0) {
                    Toast.makeText(InClass02Activity.this, "Please select an Avatar", Toast.LENGTH_SHORT).show();
                    return;
                }*/

                if (!radioFragAndroid.isChecked() && !radioFragiOS.isChecked()) {
                    Toast.makeText(getActivity(), "Please select Android or iOS", Toast.LENGTH_SHORT).show();
                    return;
                }

                int selectedId = radioGroupFrag.getCheckedRadioButtonId();
                radioButtonFrag = rootView.findViewById(selectedId);
                String radio = radioButtonFrag.getText().toString();
                String mood = txtFragMood.getText().toString();
                emoji = Integer.parseInt(imgFragMood.getTag().toString());

                ProfileFrag profile = new ProfileFrag(name, email, radio, mood, avatar, emoji);

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentLayout, DisplayFragment.newInstance(profile))
                        .addToBackStack(null)
                        .commit();



            }
        });

        return rootView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof ProfileFromEditProfileToHostActivity) {
            sendProfile = (ProfileFromEditProfileToHostActivity) context;
        }
        else {
            throw new RuntimeException(context + " must implement interface");
        }
    }

    public interface ProfileFromEditProfileToHostActivity {
        void profileFromEditProfileFragment(ProfileFrag profile);
    }


    public static EditProfileFragment newInstance(ProfileFrag profile) {
        EditProfileFragment fragment = new EditProfileFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM, profile);
        fragment.setArguments(args);
        return fragment;
    }


}