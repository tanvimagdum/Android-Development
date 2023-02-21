package com.example.cs5520_inclass_tanvi8146.inClass03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cs5520_inclass_tanvi8146.R;

public class InClass03HostActivity extends AppCompatActivity
                                implements SelectAvatarFragment.AvatarFromSelectAvatarToHostActivity,
                                            EditProfileFragment.ProfileFromEditProfileToHostActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inclass03);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentLayout, new EditProfileFragment(), "EditProfile")
                .commit();

    }

    @Override
    public void avatarFromSelectAvatar(ProfileFrag profile) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentLayout, EditProfileFragment.newInstance(profile))
                .commit();
    }

    @Override
    public void profileFromEditProfileFragment(ProfileFrag profile) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentLayout, SelectAvatarFragment.newInstance(profile))
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getSupportFragmentManager().popBackStack();
    }
}
