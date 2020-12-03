package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
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
    Button subscribe;
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
        postimage.setImageBitmap(BitmapEncoder.decodeImage(info.getString("pic")));
        signuplink.setOnClickListener((v) -> {
           Intent intent2 = new Intent(Intent.ACTION_VIEW);
           intent2.setData(Uri.parse("https://"+info.getString("lnk")));
           startActivity(intent2);
        });
        subscribe = findViewById(R.id.subscribe);
        subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Subscribe s = new Subscribe();
                s.subscribeTo(info.getString("hash"));
            }
        });

    }

}