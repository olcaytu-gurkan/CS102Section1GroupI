package com.example.splashscreenaskit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splashscreenaskit.models.Answer;
import com.example.splashscreenaskit.models.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchResultScreen extends AppCompatActivity {
    private Button submitButton;
    private RecyclerView recyclerView;
    private ArrayList<String> sendedQuestions;
    private ArrayList<Question> similarQuestions;
    private ArrayList<Question> refQuestions;
    private RecyclerView.Adapter mAdapter;
    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchresult);
        refQuestions = new ArrayList<>();
        sendedQuestions = getIntent().getStringArrayListExtra("question_numbers");
        recyclerView = findViewById(R.id.recycler2); // Okay, we will assume that this is the similar question
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        //similarQuestions = new ArrayList<>();
        submitButton = findViewById(R.id.submit_for_answering);


        reference = FirebaseDatabase.getInstance().getReference().child("Questions");
        reference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                similarQuestions = new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                {
                    //Retrieving data from realtime database and placing them in variables
                    if (sendedQuestions.contains(postSnapshot.getKey())) {
                        String question = (String) postSnapshot.child("Question").getValue();
                        ArrayList<String> tags = (ArrayList<String>) postSnapshot.child("Tags").getValue();
                        ArrayList<Answer> answers = new ArrayList<>();
                        Long timesAsked = (Long) postSnapshot.child("Number of times asked").getValue();
                        int i = 0;
                        int numOfAns = 0;
                        for (DataSnapshot postSnapshot1 : postSnapshot.child("Answers").getChildren()) {
                            i++;
                            String ans = (String) postSnapshot1.getValue();
                            System.out.println(ans);
                            Answer newAnswer = new Answer(ans, i);
                            answers.add(newAnswer);
                            numOfAns = i;
                        }
                        String questNum = (String) postSnapshot.getKey();
                        Question newQuestion = new Question(question, answers, tags, questNum, numOfAns, timesAsked);
                        similarQuestions.add(newQuestion);
                    }
                }
                mAdapter = new QuestionAdapter(SearchResultScreen.this, similarQuestions);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
                Toast.makeText(SearchResultScreen.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });


        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, similarQuestions); // We will add our similar questions to this adapter
        //listView.setAdapter(adapter);


        /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Intent intent = new Intent( this, testbase.get( position).getClass()); // I want to call QuestionScreen class of our question here. Not sure how to do it.
                // It would be healthier to try after we merge the code.
                //startActivity( intent); // Going to open the question screen activity here.

                Intent intent = new Intent( context, QuestionScreen.class);
                intent.putExtra( "Questions", QuestNum.getText().toString());
                context.startActivity( intent);
            }
        });
        */




        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // We will add a new question to our question database, by taking the properties of question from AskScreen
                rootNode =  FirebaseDatabase.getInstance();
                reference = rootNode.getReference( "question");
                reference.setValue( "Who created this app?");
                openMainMenu();
            }
        });
    }


    private void openMainMenu()
    {
        Intent intent;
        intent = new Intent(this, MainScreen.class);
        startActivity( intent );
    }
}
