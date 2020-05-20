package com.example.splashscreenaskit;

import android.widget.ImageView;

public class Profile {
    // properties
    private ImageView profilePic;
    private String username;
    private String password;

    // constructors
    public Profile() {
        username = "ahmet";
        password = "123456";
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
