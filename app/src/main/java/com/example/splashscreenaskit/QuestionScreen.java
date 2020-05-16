package com.example.splashscreenaskit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class QuestionScreen extends AppCompatActivity
{
   private Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_screen);
    }
    //TODO: Get answers with this specific question
}
