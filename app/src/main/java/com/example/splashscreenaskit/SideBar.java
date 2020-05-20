package com.example.splashscreenaskit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.splashscreenaskit.R;

public class SideBar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.profile:
                Toast.makeText(this,"Profile Selected",Toast.LENGTH_SHORT).show();
                openActivityProfilePage();
                return true;
            case R.id.homescreen:
                Toast.makeText(this,"Home Screen Selected",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.faq:
                Toast.makeText(this,"F.A.Q Selected",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.randomquestions:
                Toast.makeText(this,"Random Questions Selected",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.notification:
                Toast.makeText(this,"Notifications Selected",Toast.LENGTH_SHORT);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void openActivityProfilePage(){
        Intent intent = new Intent(this, ProfilePage.class);
        startActivity(intent);
    }

    public void openActivityHomeScreen(){}

    public void openActivityRandomQ(){}

    public void openActivityFAQ(){}

    public void openActivityNotifications(){}

}
