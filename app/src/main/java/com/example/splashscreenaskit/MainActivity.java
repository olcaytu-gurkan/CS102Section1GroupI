package com.example.splashscreenaskit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.example.splashscreenaskit.ui.login.LoginActivity;

public class THIRD extends AppCompatActivity
{

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed( new Runnable()
        {
            @Override
            public void run()
            {
                Intent logIntent = new Intent( MainActivity. this, LoginActivity.class );
                startActivity( logIntent);
                finish();
            }

        }, SPLASH_TIME_OUT);
    }
}
