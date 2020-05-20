package com.example.splashscreenaskit;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.example.splashscreenaskit.models.Answer;
import com.example.splashscreenaskit.models.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This is the main menu where the user can see the most frequently asked questions and choose to view them
 * with their answers or choose to ask a new question by clicking on the logo.
 */
public class MainScreen extends AppCompatActivity
{
    private ImageButton logo;
    private ArrayList<Question> questionsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private QuestionAdapter questionAdapter;
    private RecyclerView.Adapter mAdapter;
    private Query query;
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        //Setting the logo to open the asking questions screen
        logo = (ImageButton) findViewById(R.id.searchButton);
        logo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openAskScreen();
            }
        });

        //Activating toolbar ( incomplete)
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Working with recycle view
        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager( new LinearLayoutManager(this)); //set the layout of the contents, i.e. list of repeating views in the recycler view

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);


        //Setting up firebase
        //Getting the questions from firebase and sorting them according to how frequent they have been asked
        rootNode = FirebaseDatabase.getInstance();
        query = FirebaseDatabase.getInstance().getReference().child("Questions").orderByChild( "Number of times asked");
        query.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                questionsList = new ArrayList<>();
                //iterating through all questions ( later we will update this so it will iterate through most asked 15-20 questions)
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                {
                    //Retrieving data from realtime database and placing them in variables
                    String question = (String) postSnapshot.child("Question").getValue();
                    ArrayList<String> tags = (ArrayList<String>) postSnapshot.child( "Tags").getValue();
                    ArrayList<Answer> answers = new ArrayList<>();
                    Long timesAsked =(Long) postSnapshot.child( "Number of times asked").getValue();
                    int i = 0;
                    int numOfAns = 0;

                    //Getting the answers of the question
                    for (DataSnapshot postSnapshot1 : postSnapshot.child("Answers").getChildren())
                    {
                        i++;
                        String ans = (String) postSnapshot1.getValue();
                        Answer newAnswer = new Answer( ans, i );
                        answers.add( newAnswer );
                        numOfAns = i;
                    }
                    String questNum = (String) postSnapshot.getKey();
                    Question newQuestion= new Question( question, answers, tags, questNum, numOfAns, timesAsked );
                    questionsList.add(newQuestion );
                }
                mAdapter = new QuestionAdapter(MainScreen.this, questionsList, "no");
                recyclerView.setAdapter(mAdapter);

                //Reversing the arraylist of Question so we will get the questions in descending order based on how many times they have been asked
                Collections.reverse( questionsList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
                Toast.makeText(MainScreen.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        }); //Add listener
    }
    public void openAskScreen()
    {
        Intent intent;
        intent = new Intent(this, AskingQuestionsScreen.class);
        startActivity(intent);
    }
}







