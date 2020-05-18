package com.example.splashscreenaskit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.splashscreenaskit.models.Answer;
import com.example.splashscreenaskit.models.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class QuestionScreen extends AppCompatActivity
{
   private String questNum;
   private FirebaseDatabase rootNode;
   private  DatabaseReference reference;
   private String question;
   private ArrayList<Answer> answers;
   private ArrayList<String> tags;
   private int numOfAns;
   private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_screen);

        //Getting the key of the clicked question
        questNum = getIntent().getStringExtra("Questions");

        //Setting up firebase to retrieve the question
        reference = FirebaseDatabase.getInstance().getReference().child("1");

        reference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                question = dataSnapshot.child( "Question").getValue().toString();
                answers = (ArrayList<Answer>) dataSnapshot.child( "Answers").getValue();
                tags = (ArrayList<String>) dataSnapshot.child( "Tags").getValue();
                numOfAns = answers.size();
                Question newQuestion = new Question( question, answers, tags, questNum, numOfAns);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
                Toast.makeText(QuestionScreen.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
    //TODO: Get answers with this specific questionRecyclerView
}
