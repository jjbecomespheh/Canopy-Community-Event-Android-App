package com.example.project;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class CreatePostActivity extends AppCompatActivity {
    private static final String TAG = "CreatePostActivity";
    private static final String UploadTAG = "Uploading.....";

    private ImageView imageView;
    private ImageButton attachImage;
    private TextInputEditText title;
    private TextInputEditText hashtag;
    private TextInputEditText link;
    private Bitmap photo;
    private FloatingActionButton upload;
    private TextInputEditText description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        imageView = (ImageView) findViewById(R.id.attach_image);

        attachImage = (ImageButton) findViewById(R.id.attach_image);
        attachImage.setImageDrawable(getResources().getDrawable(R.drawable.attach_image));
        title = (TextInputEditText) findViewById(R.id.create_title);
        hashtag = (TextInputEditText) findViewById(R.id.create_tag);
        description = (TextInputEditText) findViewById(R.id.create_description);
        link = (TextInputEditText) findViewById(R.id.create_link);
        upload = (FloatingActionButton) findViewById(R.id.fab);

        Context context = getApplicationContext();
        CharSequence text = "Please enter the title and description!";
        int duration = Toast.LENGTH_SHORT;

        attachImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage(CreatePostActivity.this);
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((title.getText().toString().length() >0) && (hashtag.getText().toString().length() >0) && (description.getText().toString().length() >0)){
                    Log.i(UploadTAG, "Upload: Starting...");
                    UploadPost p = new UploadPost();
                    p.uploadPost(title.getText().toString(), hashtag.getText().toString(), description.getText().toString(), photo, link.getText().toString());
                    Toast toast = Toast.makeText(context, "Uploaded", duration);
                    toast.show();
                    Log.i(UploadTAG, "Upload: Completed...");
                    startActivity(new Intent(CreatePostActivity.this, MainActivity.class));
                } else{
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });

    }

    private void selectImage(Context context) {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your profile picture");
        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, "Entered onActivityResult ...");

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {

            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        //imageView.setImageBitmap(selectedImage);
                        //attachImage.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        attachImage.setImageBitmap(selectedImage);
                        Log.i(TAG, "Inside Camera");
                    }
                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getContentResolver().query(selectedImage,
                                    filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();
                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                //imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                                //attachImage.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                photo = BitmapFactory.decodeFile(picturePath);
                                attachImage.setImageBitmap(photo);
                                cursor.close();

                                Log.i(TAG, "Inside Gallery, showing photo");
                            }
                        }
                    }
                    break;
            }
        }
    }
}

