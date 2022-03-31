package com.example.project;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.project.Utils.BitmapEncoder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class UploadPost  {
    private static final String TAG = "In UploadPost";
    private FirebaseAuth mAuth;
    public static String user_email;

    public static String getUser_email() {
        return user_email;
    }

    public static void setUser_email(String user_email) {
        UploadPost.user_email = user_email;
    }

    UploadPost(){
    }

    public void uploadPost(String title, String hashtag, String description, Bitmap photo, String link) {
        Log.i(TAG, "Inside UploadPost: Starting...");
        Encode post = new Encode(title, hashtag, description, photo, link);
        Map<String, String> hashtag_data = new HashMap<>();

        Log.i(TAG, "Inside UploadPost: Successful...");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        hashtag_data.put("link", post.getLink());
        hashtag_data.put("hashtag", post.getHashtag());
        hashtag_data.put("title", post.getTitle());
        hashtag_data.put("description", post.getDescription());
        hashtag_data.put("encoded_photo", post.getEncoded_photo());
        hashtag_data.put("email", UploadPost.getUser_email());

        DatabaseReference myRef = ref.child("Uploaded_Posts");
        DatabaseReference newPostRef = myRef.push();
        newPostRef.setValue(hashtag_data);
    }

}
