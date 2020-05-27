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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
/**
 * This screen allows users to ask new questions by typing the question and adding tags.
 * After that the similar questions will be searched by comparing its tags with other questions in the database
 */

public class AskingQuestionsScreen extends AppCompatActivity implements View.OnClickListener {

    // properties
    private EditText editText;
    private TextView addYourTags;
    private EditText tagSpace;
    private ImageButton addButton;
    private TextView tvTags;
    private Button searchButton;
    private String allTags;
    private ArrayList<String> tagsList;
    private ArrayList<String> similar;
    private String question;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private int tmp = 0;

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

    }

    // methods
    public void onClick(View v) {
        // if addButton is pressed, gets only non repeated tags into tagsList
        if (v.getId() == addButton.getId()) {
            for (int i = 0; i < tagsList.size(); i++) {
                if (tagsList.get(i).toLowerCase().equals(("#" + tagSpace.getText()).toLowerCase())
                        || tagsList.get(i).toLowerCase().equals(("" + tagSpace.getText()).toLowerCase())) {
                    tagSpace.setText("");
                    System.out.println(tagsList);
                    return;
                }
            }

            // if first character is #, don't add #
            if(("" + tagSpace.getText()).length() > 0 && ("" + tagSpace.getText()).substring(0,1).equals("#")) {
                allTags += ("" + tagSpace.getText()).toLowerCase() + "   ";
                tagsList.add(("" + tagSpace.getText()).toLowerCase());
            }

            // else, add #
            else {
                allTags += ("#" + tagSpace.getText() + "   ").toLowerCase();
                tagsList.add(("#" + tagSpace.getText()).toLowerCase());
            }

            tvTags.setText(allTags);
            tagSpace.setText("");

            System.out.println(similar);
        }

        else if (v.getId() == searchButton.getId()) {
            question = "" + editText.getText();
            System.out.println(question);
            Intent intent;
            intent = new Intent(this, SearchResultScreen.class);
            intent.putStringArrayListExtra("question_numbers", similar);
            intent.putExtra("user_question", question);
            intent.putStringArrayListExtra("tags", tagsList);
            startActivity(intent);
        }

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                similar = new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    // Retrieving data from realtime database and placing them in variables
                    ArrayList<String> tags = (ArrayList<String>) postSnapshot.child("Tags").getValue();
                    System.out.println(tags);

                    // Compare with tags with taglist
                    String questNum = (String) postSnapshot.getKey();
                    if (compareTags(tags) > 0) {
                        similar.add(questNum);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        }); //Add listener
    }

     public int compareTags(ArrayList<String> ar) {
         int count = 0;
         for (int i = 0; i < tagsList.size(); i++) {
             for (int j = 0; j < ar.size(); j++) {
                 if (this.tagsList.get(i).toLowerCase().equals(ar.get(j).toLowerCase())) {
                     count++;
                 }
             }
         }
         return count;
     }
}