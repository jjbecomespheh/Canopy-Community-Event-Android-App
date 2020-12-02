package com.example.project.Utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.project.Categories;
import com.example.project.MainActivity;
import com.example.project.Profile;
import com.example.project.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class BottomNavViewHelper {

    private static final String TAG = "BottomNavViewHelper";

    public static void setupBottomNavView(BottomNavigationViewEx bottomNavigationViewEx){
        Log.d(TAG, "setupBottomNavView: Setting up Nav View...");
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.setTextVisibility(false);

    }

    public static void enableNavigation(final Context context, BottomNavigationViewEx view){
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.house:
                        Intent intent1 = new Intent(context, MainActivity.class);
                        context.startActivity(intent1);
                        break;
                    case R.id.category:
                        Intent intent2 = new Intent(context, Categories.class);
                        context.startActivity(intent2);
                        break;
                    case R.id.profile:
                        Intent intent3 = new Intent(context, Profile.class);
                        context.startActivity(intent3);
                        break;
                }

                return false;
            }
        });
    }
}
