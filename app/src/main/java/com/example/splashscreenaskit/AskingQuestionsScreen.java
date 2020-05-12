package com.example.splashscreenaskit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

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
    private String question;

    // constructors
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    }

    // methods
    public void onClick( View v) {

        if( v.getId() == addButton.getId()) {
           allTags += tagSpace.getText() + "   ";
           tagsList.add( "" + tagSpace.getText());
           tvTags.setText(allTags);
        }

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
        else if( v.getId() == searchButton.getId()) {

            // GO TO SEARCH RESULTS SCREEN
            // question = "" + editText.getText();
            // Intent intent;
            // intent = new Intent(this, OverText.class);
            // intent.putStringArrayListExtra("tags", tagsList);
            // HOW DO I GET THE QUESTION???
            // intent.putExtra("question", question);
            // startActivity(intent);
        }
    }

    //NOTE: IGNORE THE SAME TAG

    /** public void compareTags( Questions ArrayList<Question>) {
     *    for( int i = 0; i < tagsList.size(); i++) {
     *        if( question.tagsList.get(i).equals( this.tagsList.get(i)))
     *    }
     */

    // public void compareTags( Question question) {
    //   this.
    // }
    // }
}
