package com.example.splashscreenaskit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Question extends AppCompatActivity
{
    OverText userText;
    String question;
    int QuestionNum;
    int NumOfAns;
    ArrayList<String> tags;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    //Constructor
    public Question( String question, int numOfAns, int QuestionNum, ArrayList<String> tag )
    {
        this.question = question;
        this.QuestionNum = QuestionNum;
        this.NumOfAns = numOfAns;
        for ( String s: tag )
        {
            tags.add(s);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        //TODO: Retrieve data from firebase

    }

    //Some get and set methods

    public void setQuestion( String newQuestion )
    {
        question = newQuestion;
    }

    public String getQuestion()
    {
        return question;
    }

    public void setNumOfAns(int numOfAns)
    {
        NumOfAns = numOfAns;
    }

    public int getNumOfAns()
    {
        return NumOfAns;
    }

    public String getTags()
    {
        //Local variable
        String tagString;

        tagString = "";
        for ( String s: tags )
            tagString = tagString + " " + s;

        return tagString;
    }

    public int getQuestionNum()
    {
        return QuestionNum;
    }

    public void setQuestionNum(int questionNum)
    {
        QuestionNum = questionNum;
    }

    public void setTags(ArrayList<String> tags)
    {
        this.tags = tags;
    }

}
