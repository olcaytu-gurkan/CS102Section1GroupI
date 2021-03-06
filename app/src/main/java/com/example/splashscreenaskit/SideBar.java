package com.example.splashscreenaskit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
/**
 * This is a side bar that allows the user to choose from the options: go to main menu, go to profie page
 * frequently asked questions, random question, check notifications.
 */

import com.example.splashscreenaskit.models.ProfilePage;

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

    public void openActivityProfilePage() {
        Intent intent = new Intent(this, ProfilePage.class);
        startActivity(intent); // go to profile page
    }

    public void openActivityHomeScreen() {}

    public void openActivityRandomQ() {}

    public void openActivityFAQ() {}

    public void openActivityNotifications() {}

}
