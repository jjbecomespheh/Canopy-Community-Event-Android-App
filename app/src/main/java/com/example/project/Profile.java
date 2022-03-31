package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project.Utils.BitmapEncoder;
import com.example.project.Utils.BottomNavigationViewHelper;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.LinkedHashMap;

import static com.example.project.R.layout.activity_profile;
import static com.example.project.Utils.BottomNavigationViewHelper.setupBottomNavigationView;

public class Profile extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Context mContext = Profile.this;
    Button MyPost;
    Button SavedPost;
    private RecyclerView PostList;
    private DatabaseReference Postsref;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_profile);

        setupBottomNavigationView();

        MyPost = findViewById(R.id.myposts);
        SavedPost = findViewById(R.id.savedposts);
        Postsref = FirebaseDatabase.getInstance().getReference().child("Posts");

        PostList = (RecyclerView) findViewById(R.id.mypostslist);
        PostList.setHasFixedSize(true);
        Context context;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        PostList.setLayoutManager(linearLayoutManager);
        PostList.setHasFixedSize(true);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settingsIntent = new Intent(getApplicationContext(), Settings.class);
                startActivity(settingsIntent);
            }

        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        DisplayAllMyPosts();
    }



    private void setupBottomNavigationView(){
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx);
    }


    private void DisplayAllMyPosts() {
        Query query = FirebaseDatabase.getInstance()
                .getReference("Uploaded_Posts")
                .orderByChild("poster")
                .equalTo("clement_ravindran@mymail.sutd.edu.sg");

        FirebaseRecyclerOptions<Posts> options =
            new FirebaseRecyclerOptions.Builder<Posts>()
            .setQuery(query, new SnapshotParser<Posts>() {
                @NonNull
                @Override
                public Posts parseSnapshot(@NonNull DataSnapshot snapshot) {
                    return new Posts((String) snapshot.child("poster").getValue(),
                            (String) snapshot.child("description").getValue(),
                            (String) snapshot.child("encoded_photo").getValue(),
                            (String) snapshot.child("hashtag").getValue(),
                            (String) snapshot.child("link").getValue(),
                            (String) snapshot.child("title").getValue());

                }
            })
            .build();

        FirebaseRecyclerAdapter<Posts, PostsViewHolder> firebaseRecyclerAdapter =
            new FirebaseRecyclerAdapter<Posts, PostsViewHolder>(options) {
                @NonNull
                @Override
                public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(Profile.this)
                            .inflate(R.layout.my_posts_layout, parent, false);
                    return new PostsViewHolder(view);
                }

                @Override
                protected void onBindViewHolder(@NonNull PostsViewHolder holder, int position, @NonNull Posts model) {
                    TextView username = holder.itemView.findViewById(R.id.Post_username);
                    TextView title = holder.itemView.findViewById(R.id.Post_title);
                    TextView description = holder.itemView.findViewById(R.id.Post_description);
                    ImageView image = holder.itemView.findViewById(R.id.post_image);

                    username.setText(model.getPoster());
                    title.setText(model.getTitle());
                    description.setText(model.getDescription());
                    image.setImageBitmap(BitmapEncoder.decodeImage(model.getEncoded_photo()));
                }
            };
        firebaseRecyclerAdapter.startListening();
        PostList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class PostsViewHolder extends RecyclerView.ViewHolder {

        public PostsViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
