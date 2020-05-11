package com.example.searchresult;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> testbase;
    private Button submitButton;
    private ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testbase = new ArrayList<>();
        list = findViewById(R.id.listview); // Okay, we will assume that this is the similar question
        submitButton = findViewById(R.id.button);
        testbase.add("first"); // Don't have to this process for our similar question database, this is just for testing.
        testbase.add("second");
        testbase.add("third");
        testbase.add("third");
        testbase.add("third");
        testbase.add("third");
        testbase.add("third");
        testbase.add("third");
        testbase.add("third");
        testbase.add("third");
        testbase.add("third");
        testbase.add("third");
        testbase.add("third");
        testbase.add("third");
        testbase.add("third");
        testbase.add("third");
        testbase.add("third");
        testbase.add("third");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, testbase); // We will add our similar questions to this adapter
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Intent intent = new Intent( this, testbase.get( position).getClass()); // I want to call QuestionScreen class of our question here. Not sure how to do it.
                // It would be healthier to try after we merge the code.
                //startActivity( intent); // Going to open the question screen activity here.
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // We will add a new question to our question database, by taking the properties of question from AskScreen
            }
        });
    }
}
