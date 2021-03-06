package com.example.splashscreenaskit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
 * This screen shows the user a clicked question with its previous answers and allows him to add new questions
 */
public class QuestionScreen extends AppCompatActivity
{
    private String questNum;
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;
    private DatabaseReference ansReference;
    private String question;
    private ArrayList<Answer> answers;
    private ArrayList<String> tags;
    private int numOfAns;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private Button submitButton;
    private EditText newAnswer;
    private TextView textQuestion;
    private TextView textNumOfAns;
    private TextView textTags;
    private String screen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_screen);

        // Getting the key of the clicked question
        questNum = getIntent().getStringExtra("Questions");
        screen = getIntent().getStringExtra( "IncrementOrNot");

        // Getting recyclerview
        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // Setting up firebase to retrieve the question
        reference = FirebaseDatabase.getInstance().getReference().child("Questions").child(questNum);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i = 0;
                answers = new ArrayList<>();
                question = (String) dataSnapshot.child("Question").getValue();
                Long timesAsked = (Long) dataSnapshot.child("Number of times asked").getValue();

                // Updating the times the question has been asked if the question was viewed by the search results screen
                if (screen.equals("yes")) {
                    reference.child("Number of times asked").setValue( timesAsked + 1);
                }

                // Getting the previous questions
                for (DataSnapshot postSnapshot : dataSnapshot.child("Answers").getChildren()) {
                    i++;
                    String ans = (String) postSnapshot.getValue();
                    Answer newAnswer = new Answer(ans, i);
                    answers.add(newAnswer);
                }

                tags = (ArrayList<String>) dataSnapshot.child("Tags").getValue();
                numOfAns = answers.size();
                Question newQuestion = new Question(question, answers, tags, questNum, numOfAns, timesAsked);
                mAdapter = new AnswerAdapter(QuestionScreen.this, answers);
                recyclerView.setAdapter(mAdapter);

                // Getting the tags as one string
                String tagString =  "";
                for (String s: tags) {
                    tagString = tagString + " " + s;
                }

                numOfAns = answers.size();
                textQuestion = (TextView) findViewById(R.id.Question);
                textNumOfAns = (TextView) findViewById(R.id.NumOfAns);
                textTags = (TextView) findViewById(R.id.tags);
                textQuestion.setText(question);
                textNumOfAns.setText(Integer.toString(numOfAns));
                textTags.setText( tagString);
            }

            @Override

            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(QuestionScreen.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });

        // Make the submit button add another answer to the question
        submitButton = (Button) findViewById(R.id.submitButton);
        newAnswer = (EditText) findViewById(R.id.answer);
        submitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String addingAns = newAnswer.getText().toString();
                if (!addingAns.equals("")) {
                    ansReference = reference.child("Answers");
                    ansReference.push().setValue(addingAns);
                    newAnswer.setText("");
                }

                else
                    Toast.makeText(QuestionScreen.this, "Please write your answer first", Toast.LENGTH_SHORT).show();
            }
        });
    }
}