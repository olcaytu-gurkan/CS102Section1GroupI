package com.example.splashscreenaskit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
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
   private RecyclerView.Adapter mAdapter;
   private AnswerAdapter answerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_screen);

        //Getting the key of the clicked question
        questNum = getIntent().getStringExtra("Questions");
        System.out.println( questNum); //Working
        //Getting recyclerview
        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        //Setting up firebase to retrieve the question
        reference = FirebaseDatabase.getInstance().getReference().child("Questions").child( questNum );
        reference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                int i = 0;
                answers = new ArrayList<>();
                question = ( String) dataSnapshot.child( "Question").getValue();
                System.out.println( question); //Working
                for (DataSnapshot postSnapshot : dataSnapshot.child("Answers").getChildren())
                {
                    i++;
                    String ans = (String) postSnapshot.getValue();
                    System.out.println( ans); //Working
                    Answer newAnswer = new Answer( ans, i );
                    answers.add( newAnswer );
                }
                tags = (ArrayList<String>) dataSnapshot.child( "Tags").getValue();
                System.out.println( tags);
                numOfAns = answers.size();
                Question newQuestion = new Question( question, answers, tags, questNum, numOfAns);
                mAdapter = new AnswerAdapter( QuestionScreen.this, answers );
                recyclerView.setAdapter(mAdapter);
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
