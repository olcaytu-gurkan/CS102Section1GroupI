package com.example.splashscreenaskit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

/**
 * This screen displays similar questions that the user has asked and allows them to click on these questions
 * or if they have not found an  answer to their question then choose to submit his question
 */

public class SearchResultScreen extends AppCompatActivity {
    private Button submitButton;
    private TextView textView;
    private RecyclerView recyclerView;
    private ArrayList<String> sendedQuestions;
    private ArrayList<Question> similarQuestions;
    private ArrayList<Question> refQuestions;
    private RecyclerView.Adapter mAdapter;
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;
    private String newQuestion;
    private ArrayList<String> newTags;
    private String lastQuestionNum;
    private int newQuestionNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchresult);
        refQuestions = new ArrayList<>();

        // Getting data from asking questions screen
        sendedQuestions = getIntent().getStringArrayListExtra("question_numbers");
        newQuestion = getIntent().getStringExtra( "user_question" );
        newTags = getIntent().getStringArrayListExtra( "tags");
        textView = findViewById(R.id.textView39);

        recyclerView = findViewById(R.id.recycler2);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
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
                    // Retrieving data from realtime database and placing them in variables
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
                    lastQuestionNum = postSnapshot.getKey().toString();
                }
                mAdapter = new QuestionAdapter(SearchResultScreen.this, similarQuestions, "yes");
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
                Toast.makeText(SearchResultScreen.this, "Oops.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });

        //If the user chooses to submit his question
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // We will add a new question to our question database, by taking the properties of question from AskScreen
                newQuestionNum = Integer.parseInt( lastQuestionNum ) + 1;
                reference.child(Integer.toString(newQuestionNum)).child("Question").setValue(newQuestion);
                reference.child(Integer.toString(newQuestionNum)).child("Number of times asked").setValue(1);
                reference.child(Integer.toString(newQuestionNum)).child("Tags").setValue(newTags);
                openMainMenu(); //Go to main menu after submitting the question
            }
        });
    }

    //Go to main menu
    private void openMainMenu()
    {
        Intent intent;
        intent = new Intent(this, MainScreen.class);
        startActivity( intent );
    }
}
