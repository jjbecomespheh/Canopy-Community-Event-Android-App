package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.project.Utils.BottomNavigationViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import static com.example.project.R.layout.activity_profile;
import static com.example.project.Utils.BottomNavigationViewHelper.setupBottomNavigationView;

public class Profile extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Context mContext = Profile.this;
    Button MyPost;
    Button SavedPost;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_profile);

        setupBottomNavigationView();

        MyPost = findViewById(R.id.myposts);
        SavedPost =findViewById(R.id.savedposts);

       MyPost.setOnClickListener((v) -> {
           System.out.println("hi");
               });

       SavedPost.setOnClickListener((v) -> {
                //show saved post
            System.out.println("hi");
        });





    }


    private void setupBottomNavigationView(){
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx);
    }
}
