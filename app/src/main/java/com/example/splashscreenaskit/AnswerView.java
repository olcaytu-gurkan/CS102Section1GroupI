package com.example.splashscreenaskit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AnswerView extends AppCompatActivity
{
    TextView username, answer;
    ImageButton profileButton;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
//created new

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_view);
        username = (TextView) findViewById(R.id.username);
        answer = (TextView) findViewById(R.id.answer);
        profileButton = (ImageButton) findViewById(R.id.profileButton);
        rootNode = FirebaseDatabase.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();

    }

}
