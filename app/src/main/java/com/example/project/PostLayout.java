package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project.Utils.BitmapEncoder;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostLayout extends AppCompatActivity {
    TextView title;
    TextView description;
    ImageView postimage;
    Button signuplink;
    Bitmap a = null;
    private StorageReference mStorageRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_posts_layout);
        title = findViewById(R.id.Post_title);
        description = findViewById(R.id.Post_description);
        postimage = findViewById(R.id.post_image);
        signuplink = findViewById(R.id.Post_signup_link);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        Intent intent = getIntent();
        Bundle info = intent.getExtras();
        title.setText(info.getString("til"));
        description.setText(info.getString("desc"));
        //postimage.setImageBitmap(BitmapEncoder.decodeImage(info.getStringArray("Data"));
        signuplink.setOnClickListener((v) -> {
           Uri link = Uri.parse(info.getString("lnk"));
           Intent intent2 = new Intent(Intent.ACTION_VIEW, link);
           startActivity(intent2);
        });

    }

//    public void downloadViaUrl() {
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