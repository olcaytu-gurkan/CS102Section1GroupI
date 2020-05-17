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

import com.example.splashscreenaskit.models.Question;
import com.google.firebase.database.DataSnapshot;
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
        searchButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openSearchResultsScreen();
            }
        });

        // database
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Questions");
    }

    // methods
    public void openSearchResultsScreen()
    {
        Intent intent;
        intent = new Intent(this, SearchResultScreen.class);
        startActivity( intent );
    }

    public void onClick( View v) {

        if( v.getId() == addButton.getId()) {
            for( int i = 0; i < tagsList.size(); i++) {
                if( tagsList.get(i).equals("" + tagSpace.getText())) {
                    return;
                }
            }
            allTags += tagSpace.getText() + "   ";
            tagsList.add("" + tagSpace.getText());
            tvTags.setText(allTags);
        }
        // else if( v.getId() == searchButton.getId()) {

            // GO TO SEARCH RESULTS SCREEN
            // question = "" + editText.getText();
            // Intent intent;
            // intent = new Intent(this, OverText.class);
            // intent.putStringArrayListExtra("tags", tagsList);
            // HOW DO I GET THE QUESTION???
            // intent.putExtra("question", question);
            // startActivity(intent);
        //}
    }

    //NOTE: IGNORE THE SAME TAG

     public int compareTags( Question q) {
         int count = 0;
         for (int i = 0; i < tagsList.size(); i++) {
             for (int j = 0; j < q.getTagsList().size(); j++) {
                 if (this.tagsList.get(i).equals(q.getTagsList().get(j))) {
                     count++;
                 }
             }
         }
         return count;
     }

     public void sendQuestions()
     {
        return;
     }
}
