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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.project.Utils.BottomNavViewHelper;
import com.example.project.Utils.BottomNavigationViewHelper;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Button buttonCategories;
    private RecyclerView PostList;
    private DatabaseReference Postsref;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter adapter;

    private Context mContext = MainActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Starting...");
        //setupBottomNavView();
        setupBottomNavigationView();

        PostList = (RecyclerView) findViewById(R.id.mypostslist);
        PostList.setHasFixedSize(true);
        Context context;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        PostList.setLayoutManager(linearLayoutManager);
        PostList.setHasFixedSize(true);
        FloatingActionButton fab = findViewById(R.id.create_post_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreatePostActivity.class));
            }
        });





    }
    @Override
    protected void onStart() {
        super.onStart();
        DisplayAllMyPosts();
    }



    //example menu remove if needed
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }


    private void setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx);
    }

    private void DisplayAllMyPosts() {
        Query query = FirebaseDatabase.getInstance()
                .getReference("Uploaded_Posts");

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

        FirebaseRecyclerAdapter<Posts, Profile.PostsViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Posts, Profile.PostsViewHolder>(options) {
                    @NonNull
                    @Override
                    public Profile.PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(MainActivity.this)
                                .inflate(R.layout.my_posts_layout, parent, false);
                        return new Profile.PostsViewHolder(view);
                    }

                    @Override
                    protected void onBindViewHolder(@NonNull Profile.PostsViewHolder holder, int position, @NonNull Posts model) {
                        TextView username = holder.itemView.findViewById(R.id.Post_username);

                        username.setText(model.getPoster());
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

    //Pseudocode
    //ArrayList<String> tags = new ArrayList<String>();
    //private void showPosts() {
    //  FirebaseDatabase database = FirebaseDatabase.getInstance();
    //  DatabaseReference myRef = database.getReference("Subscriptions");
    //  Query newQuery = database.getReference("Uploaded_Posts");
    //        newQuery.addValueEventListener(new ValueEventListener() {
    //            @Override
    //            public void onDataChange(DataSnapshot dataSnapshot) {
    //                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
    //                    if (dataSnapshot1.child("email").getValue().toString().equals(get_email) {
    //                        tags.add((String) dataSnapshot1.child("hashtag").getValue());
        //                    Set<String> set = new LinkedHashSet<String>();
        //                    set.addAll(tags);
        //                    tags.clear();
        //                    tags.addAll(set);
    //                    }
    //
    //                }
    //                for (String tag : tags) {
    //                    Print out posts if tags match
    //            }


}