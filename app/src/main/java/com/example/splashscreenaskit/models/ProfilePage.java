package com.example.splashscreenaskit.models;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This is a model for the profile of the users. Instances of this class will be created in the profile screen
 * also might be used in Overtext to display the username and the profile picture
 */
public class ProfilePage {

    // properties
    private ImageView profilePic;
    private String username;
    private String password;

    // constructors
    public ProfilePage(String username , String password) {
        this.username = username;
        this.password = password;
    }

    // methods
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ImageView getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(ImageView profilePic) {
        this.profilePic = profilePic;
    }
}