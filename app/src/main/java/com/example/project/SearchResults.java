package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.project.Utils.BitmapEncoder;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class SearchResults extends AppCompatActivity {
    Button newbtn;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");
    StorageReference mStorageRef;
    Bitmap a = null;
    LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        Intent intent = getIntent();
        String search = intent.getStringExtra("Search");
        System.out.println(search);

        Query newQuery = database.getReference("Uploaded_Posts");
        newQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    System.out.println(dataSnapshot1.child("title").getValue().toString());
                    System.out.println(search);
                    if (dataSnapshot1.child("hashtag").getValue().toString().equals(search)){
                        System.out.println(dataSnapshot1.child("title").getValue());
                        addButton(dataSnapshot1);
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void addButton(DataSnapshot datasnapshot) {
        System.out.println("yay");
        layout = (LinearLayout) findViewById(R.id.base);
        newbtn = new Button(this);
        newbtn.setText("button");
        layout.addView(newbtn);
        System.out.println("button added");
        newbtn.setOnClickListener((v) -> {
            Intent intent = new Intent(SearchResults.this, PostLayout.class);
            intent.putExtra("desc", datasnapshot.child("description").getValue().toString());
            intent.putExtra("pic", datasnapshot.child("encoded_photo").getValue().toString());
            intent.putExtra("hash", datasnapshot.child("hashtag").getValue().toString());
            intent.putExtra("lnk", datasnapshot.child("link").getValue().toString());
            intent.putExtra("til", datasnapshot.child("title").getValue().toString());
            startActivity(intent);
        });
    }

//    public void downloadViaUrl() {
//
//        StorageReference strref = mStorageRef.child("images/Smiley.png");
//
//        strref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
//                try {
//                    URL url = new URL(uri.toString());
//                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                    connection.setDoInput(true);
//                    connection.connect();
//                    InputStream input = connection.getInputStream();
//                    Bitmap myBitmap = BitmapFactory.decodeStream(input);
//                    a = myBitmap;
//                } catch (IOException e) {
//                    // Log exception
//                }
//            }
//        });
//    }
}