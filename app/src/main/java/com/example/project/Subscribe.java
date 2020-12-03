package com.example.project;

import android.graphics.Bitmap;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Subscribe {
    private static final String TAG = "In UploadPost";
    private FirebaseAuth mAuth;
    public static String user_email;

    public static String getUser_email() {
        return user_email;
    }

    public static void setUser_email(String user_email) {
        UploadPost.user_email = user_email;
    }

    Subscribe(){
    }

    public void subscribeTo(String hashtag) {
        Log.i(TAG, "Inside UploadPost: Starting...");
        Map<String, String> hashtag_data = new HashMap<>();

        Log.i(TAG, "Inside UploadPost: Successful...");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        hashtag_data.put("hashtag", hashtag);
        hashtag_data.put("email", UploadPost.getUser_email());

        DatabaseReference myRef = ref.child("Subscriptions");
        DatabaseReference newPostRef = myRef.push();
        newPostRef.setValue(hashtag_data);
    }
}
