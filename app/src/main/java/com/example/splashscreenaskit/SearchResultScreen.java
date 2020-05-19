package com.example.splashscreenaskit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.splashscreenaskit.models.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchResultScreen extends AppCompatActivity {
    private ArrayList<String> testbase;
    private Button submitButton;
    private ListView listView;
    private ArrayList<String> sendedQuestions;
    private ArrayList<String> similarQuestions;
    private ArrayList<Question> refQuestions;
    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchresult);
        testbase = new ArrayList<>();
        refQuestions = new ArrayList<>();
        sendedQuestions = getIntent().getStringArrayListExtra("question_numbers");
        listView = findViewById(R.id.list1); // Okay, we will assume that this is the similar question
        similarQuestions = new ArrayList<>();
        submitButton = findViewById(R.id.submit_for_answering);


        reference = FirebaseDatabase.getInstance().getReference().child("Questions");
        reference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                for (int i = 0; i < sendedQuestions.size(); i++) {
                    similarQuestions.add( dataSnapshot.child(sendedQuestions.get(i)).child("Question").getValue().toString());
                }
            }

            @Override

            public void onCancelled(@NonNull DatabaseError databaseError)
            {
            }
        });


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, similarQuestions); // We will add our similar questions to this adapter
        listView.setAdapter(adapter);


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
