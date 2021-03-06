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
 * Login page
 */
public class Login extends AppCompatActivity {
    private EditText mEmail, mPassword;
    private Button mLoginButton;
    private TextView mSignUpButton;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = findViewById(R.id.email2);
        mPassword = findViewById(R.id.password2);
        mLoginButton = findViewById(R.id.login2);
        mSignUpButton = findViewById(R.id.logToUp);
        mAuth = FirebaseAuth.getInstance();

        // If the user already has an account
        mLoginButton.setOnClickListener(new View.OnClickListener() {
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

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Login.this, "Logged in successfully!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), com.example.splashscreenaskit.MainScreen.class));
                        }
                        else {
                            Toast.makeText(Login.this, "Error!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        // If the user does not have an account then take to register page
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });
    }
}
