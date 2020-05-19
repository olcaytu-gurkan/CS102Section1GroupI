package com.example.splashscreenaskit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.splashscreenaskit.models.Answer;
import com.example.splashscreenaskit.models.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AskingQuestionsScreen extends AppCompatActivity implements View.OnClickListener {

    // properties
    EditText editText;
    TextView addYourTags;
    EditText tagSpace;
    ImageButton addButton;
    TextView tvTags;
    Button searchButton;
    String allTags;
    ArrayList<String> tagsList;
    ArrayList<String> similar;
    String question;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;



    // constructors
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asking_questions_screen);

        // initialize
        editText = findViewById(R.id.editText);
        addYourTags = findViewById(R.id.addYourTags);
        tagSpace = findViewById(R.id.tagSpace);
        addButton = findViewById(R.id.addTag);
        tvTags = findViewById(R.id.tv_tags);
        searchButton = findViewById(R.id.searchButton);
        allTags = "";
        tagsList = new ArrayList<String>();

        // listeners
        addButton.setOnClickListener(this);
        searchButton.setOnClickListener(this);

        // database
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Questions");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot ) {
                similar = new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //Retrieving data from realtime database and placing them in variables
                    ArrayList<String> tags = (ArrayList<String>) postSnapshot.child("Tags").getValue();
                    // String question = (String) postSnapshot.child("Question").getValue();
                    // ArrayList<Answer> answers = (ArrayList<Answer>) postSnapshot.child( "Answers").getValue();
                    String questNum = (String) postSnapshot.getKey();
                    // int numOfAns = answers.size();
                    // Question newQuestion= new Question( question, answers, tags, questNum, numOfAns );
                    //Compare with tags with taglist
                    if ( compareTags( tags ) > 0 ) {
                        similar.add( questNum);
                    }
                }
                for( int i = 0; i < similar.size(); i++) {
                    System.out.println(similar.get(i));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        }); //Add listener
    }

    // methods
    public void onClick( View v) {
        // if addButton is pressed, gets only nonrepeated tags into tagsList
        if( v.getId() == addButton.getId()) {
            for( int i = 0; i < tagsList.size(); i++) {
                if( tagsList.get(i).toLowerCase().equals(("#" + tagSpace.getText()).toLowerCase())
                        || tagsList.get(i).toLowerCase().equals( ("" + tagSpace.getText()).toLowerCase())) {
                    return;
                }
            }

            // if first character is #, don't add #
            if( ( "" + tagSpace.getText()).substring(0,1).equals("#")) {
                allTags += ("" + tagSpace.getText()).toLowerCase() + "   ";
                tagsList.add(("" + tagSpace.getText()).toLowerCase());
            }

            // else, add #
            else {
                allTags += ("#" + tagSpace.getText() + "   ").toLowerCase();
                tagsList.add(("#" + tagSpace.getText()).toLowerCase());
            }

            tvTags.setText(allTags);
        }

        else if( v.getId() == searchButton.getId()) {

            // GO TO SEARCH RESULTS SCREEN
            question = "" + editText.getText();
            Intent intent;
            intent = new Intent(this, SearchResultScreen.class);
            intent.putStringArrayListExtra("question_numbers", similar);
            intent.putExtra("user_question", question);
            intent.putStringArrayListExtra("tags", tagsList);
            startActivity(intent);
        }
    }

    // hashtag bug, until the first number (covid 19 --> covid 18)
    // upper lower case
    // TODO: fix the bugs in the line above
     public int compareTags(  ArrayList<String> ar ) {
         int count = 0;
         for (int i = 0; i < tagsList.size(); i++) {
             for (int j = 0; j < ar.size(); j++) {
                 if (this.tagsList.get(i).equals(ar.get(j))) {
                     count++;
                 }
             }
         }
         return count;
     }
}
