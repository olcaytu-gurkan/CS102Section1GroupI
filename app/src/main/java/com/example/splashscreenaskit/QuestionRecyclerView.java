package com.example.splashscreenaskit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class QuestionRecyclerView extends AppCompatActivity
{
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    TextView qnum, quest, tg, ansnum;


    public QuestionRecyclerView( String qnum, String question, String tags, String numOfAns)
    {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        //TODO: Retrieve data from firebase

        //created new
        setContentView(R.layout.activity_question);
        qnum = (TextView) findViewById(R.id.QuestNum);
        quest = (TextView) findViewById(R.id.Question);
        tg = (TextView) findViewById(R.id.tags);
        ansnum = (TextView) findViewById(R.id.NumOfAns);
        reference = rootNode.getReference("Questions");

    }

}
