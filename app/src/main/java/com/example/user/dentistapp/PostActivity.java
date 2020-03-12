package com.example.user.dentistapp;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.UUID;


public class PostActivity extends AppCompatActivity {
    private ImageButton nSelectImage;
    private EditText mPostTitle;
    private EditText mPostDesc;
    private Button nSubmitBtn;
    private Uri nImageUri = null;
    private static final int GALLERY_REQUEST = 1;
    private final int PICK_IMAGE_REQUEST = 71;
    private StorageReference mStorage;

    private DatabaseReference mDatabase;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        mStorage = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference("DentalBlog/" ).child("Blog");

        nSelectImage = (ImageButton) findViewById(R.id.imageSelect);

        mPostTitle = (EditText) findViewById(R.id.titleField);
        mPostDesc = (EditText) findViewById(R.id.descField);
        nSubmitBtn = (Button) findViewById(R.id.submitbtn);

        mProgress = new ProgressDialog(this);

        nSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST);
            }
        });
        nSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPosting();
            }
        });
    }


    public String getRealPathFromURI(Context context , Uri contentUri) {
       Cursor cursor = null;
       try{
           String[] proj= {MediaStore.Images.Media.DATA};
           cursor = context.getContentResolver().query(contentUri, proj ,null,null,null);
           int column_index= ((Cursor) cursor).getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
           cursor.moveToFirst();
           return cursor.getString(column_index);



           } finally {
           if (cursor != null)
           {
               cursor.close();

           }
    }}
    private void startPosting() {

        mProgress.setMessage("Posting to Blog ...");
        final String title_val = mPostTitle.getText().toString().trim();
        final String desc_val = mPostDesc.getText().toString().trim();
      final StorageReference profileImageRef= FirebaseStorage.getInstance().getReference("profilepics/" + ".jpg");
        if ((!TextUtils.isEmpty(title_val)) && (!TextUtils.isEmpty(desc_val)) && (nImageUri != null)) {

            mProgress.show();

                final StorageReference filepath = mStorage.child("Blog_Images").child(nImageUri.getLastPathSegment());

                filepath.putFile(nImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                      @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                          String downloadUrl =taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();

                        DatabaseReference newPost = mDatabase.push();
                        newPost.child("title").setValue(title_val);
                        newPost.child("desc").setValue(desc_val);
                        newPost.child("image").setValue(filepath.toString());

                          mProgress.dismiss();                    }


            });
        }
    }

    @Override
    protected void onActivityResult(int requestcode, int resultCode, Intent data) {
        super.onActivityResult(requestcode, resultCode, data);
        if (requestcode == GALLERY_REQUEST && resultCode == RESULT_OK) ;

       nImageUri = data.getData();
        nSelectImage.setImageURI(nImageUri);

       Picasso.with(this).load(nImageUri).into(nSelectImage);

    }

}