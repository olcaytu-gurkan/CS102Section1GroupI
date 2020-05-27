package com.example.splashscreenaskit;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * This screen allows users to create a new account if they are new to ASkIt
 */

public class Register extends AppCompatActivity {
    private EditText mFullName, mEmail, mPassword;
    private Button mRegisterButton;
    private TextView mLoginButton;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFullName = findViewById(R.id.fullName);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mRegisterButton = findViewById(R.id.login);
        mLoginButton = findViewById(R.id.upToLog);
        mAuth = FirebaseAuth.getInstance();
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Please type your e-mail!");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    mPassword.setError(("Please type your password!"));
                    return;
                }

                if (password.length() < 6) {
                    mPassword.setError("Minimum length of a password is six.");
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "Account created!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), com.example.splashscreenaskit.MainScreen.class));
                        }
                        else {
                            Toast.makeText(Register.this, "Error!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

    }
}
