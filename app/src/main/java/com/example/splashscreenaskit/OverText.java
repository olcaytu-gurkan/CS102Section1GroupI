package com.example.splashscreenaskit;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OverText extends AppCompatActivity implements View.OnClickListener {
    // properties
    private ImageButton pp;
    private ProfilePage profile;
    private int upvote;
    private int downvote;
    private int vote;
    private boolean isUpvoted;
    private boolean isDownvoted;

    private ImageButton upvoteButton;
    private ImageButton downvoteButton;
    private TextView username;
    private TextView karma;

    // constructors
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overtext);
        profile = new ProfilePage("ahmet", "123456");
        upvote = 0; // question.getUpvoteCount();
        downvote = 0;
        vote = upvote - downvote;
        isUpvoted = false;
        isDownvoted = false;

        pp = (ImageButton) findViewById(R.id.profileXML);
        // profile picture
        if ( profile.getProfilePic() != null )
            pp = (ImageButton) profile.getProfilePic();

        // username
       username = (TextView) findViewById(R.id.username);
       if( profile.getUsername() != null)
           username.setText( profile.getUsername());
       else
           username.setText("anonymous");

        // upvote, downvote buttons
        upvoteButton = (ImageButton) findViewById(R.id.upvoteButton);
        downvoteButton = (ImageButton) findViewById(R.id.downvoteButton);
        karma = (TextView) findViewById( R.id.karma);
        karma.setText("0");

        // listeners
        upvoteButton.setOnClickListener(this);
        downvoteButton.setOnClickListener(this);
        username.setOnClickListener(this);
        pp.setOnClickListener(this);
        karma.setOnClickListener(this);

    }

    // methods
    @Override
    public void onClick( View v) {
        // upvoted
        if( isUpvoted) {
            // clicked on upvote button
            if (v.getId() == upvoteButton.getId()) {
                upvote--;
                karma.setText("" + getVote());
                isUpvoted = !isUpvoted;
            }

            // clicked on downvote button
            else if (v.getId() == downvoteButton.getId()) {
                upvote--;
                downvote++;
                karma.setText("" + getVote());
                isUpvoted = !isUpvoted;
                isDownvoted = !isDownvoted;
            }
        }

        // downvoted
        else if( isDownvoted){
            // clicked on upvote button
            if (v.getId() == upvoteButton.getId()) {
                downvote--;
                upvote++;
                karma.setText("" + getVote());
                isUpvoted = !isUpvoted;
                isDownvoted = !isDownvoted;
            }

            // clicked on downvote button
            else if (v.getId() == downvoteButton.getId()) {
                downvote--;
                karma.setText("" + getVote());
                isDownvoted = !isDownvoted;
            }
        }

        // no actions done yet
        else if( !isUpvoted && !isDownvoted) {
            // clicked on upvote button
            if (v.getId() == upvoteButton.getId()) {
                upvote++;
                karma.setText("" + getVote());
                isUpvoted = !isUpvoted;
            }

            // clicked on downvote button
            else if (v.getId() == downvoteButton.getId()) {
                downvote++;
                karma.setText("" + getVote());
                isDownvoted = !isDownvoted;
            }
        }
        // clicked on username or profile picture
        if( v.getId() == username.getId() || v.getId() == pp.getId()) {
            // GO TO THE PROFILE SCREEN
            // Intent intent;
            // intent = new Intent(this, ProfileScreen.class);
            // startActivity(intent);
        }

        // UPVOTE- DOWNVOTE ONCE
        //  isPressed

    }

    public int getUpvote() { return upvote; }

    public int getDownvote() {
        return downvote;
    }

    public int getVote() {
        return upvote - downvote;
    }

    public void setVote(int n) {
        vote = n;
    }
}
