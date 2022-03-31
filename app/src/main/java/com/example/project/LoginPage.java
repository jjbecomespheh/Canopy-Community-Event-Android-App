package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.example.project.R.layout.activity_login;

public class LoginPage extends AppCompatActivity{
    MainApplication mApplication;

    EditText mEmail, mPassword;
    Button Btn_login;
    FirebaseAuth fAuth;
    ProgressBar mProgressBar;

    public static String user_email;
    private static final String TAG = "LoginPage";

    private FirebaseAuth mAuth;

    public static String getUser_email() {
        return user_email;
    }

    public static void setUser_email(String user_email) {
        LoginPage.user_email = user_email;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_login);

        mApplication = (MainApplication) getApplicationContext();
        mEmail = findViewById(R.id.input_email);
        mPassword = findViewById(R.id.input_password);
        Btn_login = findViewById(R.id.btn_login);
        mProgressBar = findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.GONE);
        Btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.setVisibility(View.VISIBLE);
                String Email = mEmail.getText().toString().trim();
                String Password = mPassword.getText().toString().trim();
                mAuth = FirebaseAuth.getInstance();
                if(mAuth.getCurrentUser() != null){
                    startActivity(new Intent(LoginPage.this,MainActivity.class));
                    finish();
                }

                mAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(LoginPage.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            mApplication.email = mEmail.getText().toString();

                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginPage.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });



        }
    }

