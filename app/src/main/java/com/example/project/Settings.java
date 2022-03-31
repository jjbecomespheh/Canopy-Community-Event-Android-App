package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
        setContentView(R.layout.activity_settings);

        Button btnClick = (Button)findViewById(R.id.btnShowAbout);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.this,About.class));
            }
        });
    }}

