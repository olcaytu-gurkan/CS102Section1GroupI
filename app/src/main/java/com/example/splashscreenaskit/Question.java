package com.example.splashscreenaskit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Question extends AppCompatActivity
{
    OverText userText;
    TextView question;
    TextView QuestionNum;
    TextView NumOfAns;
    TextView tags;
    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        question = (TextView) findViewById(R.id.Question);
        //QuestionNum = ( TextView) findViewById(R.id.QuestionNum);
        NumOfAns = (TextView) findViewById(R.id.NumAns);
        tags = ( TextView) findViewById(R.id.tags);
        //TODO: Retrieve data from firebase

    }
}
