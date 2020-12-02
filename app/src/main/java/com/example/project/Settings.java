package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.project.R;
import com.example.project.Utils.BottomNavigationViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import static com.example.project.Utils.BottomNavigationViewHelper.setupBottomNavigationView;

public class Settings extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Context mContext = com.example.project.Settings.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
    }}

