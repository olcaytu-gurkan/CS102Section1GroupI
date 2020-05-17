package com.example.splashscreenaskit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class QuestionScreen extends AppCompatActivity
{
   private QuestionRecyclerView questionRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_screen);
        course = getIntent().getStringExtra("course");
    }
    //TODO: Get answers with this specific questionRecyclerView
}
