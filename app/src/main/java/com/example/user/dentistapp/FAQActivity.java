package com.example.user.dentistapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.dentistapp.Adapter.ImageAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FAQActivity extends AppCompatActivity {
    private RecyclerView mBlogList;
   // private ImageAdapter mAdapter;
    //private ProgressBar mProgressCircle;
    private ImageButton add_post;
    //private List<Blog> mUploads;
    private DatabaseReference nDatabase;

    // Create a reference with an initial file path and name
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        add_post = (ImageButton) findViewById(R.id.btn_add);
        mBlogList = (RecyclerView) findViewById(R.id.blog_List);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));
        nDatabase = FirebaseDatabase.getInstance().getReference("DentalBlog/").child("Blog");
        add_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FAQActivity.this, PostActivity.class));
            }
        });
       /* //mDatabase = FirebaseDatabase.getInstance().getReference("DentalBlog/" ).child("Blog");

        final DatabaseReference dentalImage = nDatabase.child("image");

/*
                 mProgressCircle = findViewById(R.id.progress_circle);
                 mUploads = new ArrayList<>();


                 dentalImage.addValueEventListener(new ValueEventListener() {
                     @Override
                     public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                         for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                         {
                             Blog upload = postSnapshot.getValue(Blog.class);
                             mUploads.add(upload);
                         }
                         mAdapter = new ImageAdapter(FAQActivity.this, mUploads);
                         mBlogList.setAdapter(mAdapter);
                         mProgressCircle.setVisibility(View.INVISIBLE);
                     }

                     @Override
                     public void onCancelled(DatabaseError databaseError) {
                         Toast.makeText(FAQActivity.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
                         mProgressCircle.setVisibility(View.INVISIBLE);
                     }
                 });

    }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_add) {
            startActivity(new Intent(FAQActivity.this, PostActivity.class));


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Blog, BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, BlogViewHolder>(
                Blog.class,
                R.layout.blog_row,
                BlogViewHolder.class,
                nDatabase

        ) {

            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int position) {

                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getDesc());
                viewHolder.setImage(getApplicationContext(), model.getImage());

            }
        };
        mBlogList.setAdapter(firebaseRecyclerAdapter);
    }


    public static class BlogViewHolder extends RecyclerView.ViewHolder {
        View nView;

        public BlogViewHolder(View itemView) {

            super(itemView);
            nView = itemView;
        }

        public void setTitle(String title) {
            TextView post_title = (TextView) nView.findViewById(R.id.post_title);
            post_title.setText(title);
        }

        public void setDesc(String desc) {
            TextView post_desc = (TextView) nView.findViewById(R.id.post_desc);
            post_desc.setText(desc);
        }

        public void setImage(Context context, String image) {
            ImageView post_image = (ImageView) nView.findViewById(R.id.post_image);

            Picasso.with(context).load(image).into(post_image);

        }
    }
}