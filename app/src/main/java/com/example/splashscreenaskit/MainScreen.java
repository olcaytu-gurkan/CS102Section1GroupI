package com.example.splashscreenaskit;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class MainScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    private ImageButton logo;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        logo = (ImageButton) findViewById(R.id.searchButton);
        logo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openAskScreen();
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spinner = (Spinner) findViewById(R.id.sort_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sorts, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        //specify the interface implementation
        spinner.setOnItemSelectedListener(this);


    }
    public void openAskScreen()
    {
        Intent intent;
        intent = new Intent(this, AskScreen.class);
        startActivity( intent );
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        String text = parent.getItemAtPosition( position ).toString();
        Toast.makeText(parent.getContext(),text, Toast.LENGTH_LONG);

        //Checks what the user has chosen for sorting the questions
        if ( text.equals( "FAQ") ) //If the user chooses to sort by frequently asked questions
        {
            sortByFAQ();
        }
        else if ( text.equals( "Most Voted") ) //If the user chooses to sort by most vote questions
        {
            sortByMostVoted();
        }
    }

    private void sortByMostVoted()
    {
       // System.out.println( "most voted");
    }

    private void sortByFAQ()
    {
        //System.out.println( "FAQ");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }
}
