package com.example.animalassist2;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class UploadinfoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    Button up, shw, checkk;
    EditText des, lo, an, ur;
      ImageView img;
    String item;
    DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("images");
    StorageReference storageref = FirebaseStorage.getInstance().getReference();
    Uri imageUri;
    String[] injury = {
            "Bleeding",
            "Burn wounds",
            "Broken bones",
            "Choking",
            "Eye injuries",
            "Fractures",
            "Seizure",
            "Shock",
            "Skin infection" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadinfo);

        up = findViewById(R.id.upload);
        shw = findViewById(R.id.ret);
        checkk= findViewById(R.id.check);
        des = findViewById(R.id.issue);
        lo = findViewById(R.id.loc);
        img = findViewById(R.id.select);
        up = findViewById(R.id.upload);
        an= findViewById(R.id.animal);
        ur= findViewById(R.id.urg);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice, injury);
        //Find TextView control
        AutoCompleteTextView acTextView = (AutoCompleteTextView) findViewById(R.id.injuries);
        //Set the number of characters the user must type before the drop down list is shown
        acTextView.setThreshold(1);
        //Set the adapter
        acTextView.setAdapter(adapter);
        acTextView.setOnItemClickListener(this);

        shw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UploadinfoActivity.this, ShowActivity.class));
            }
        });

        checkk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemm= item;
                Intent intent = new Intent(UploadinfoActivity.this, ShowcheckedActivity.class);
                intent.putExtra("injury",itemm);
                startActivity(intent);

            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, 2);
            }
        });

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageUri != null) {
                    uploadToFirebase(imageUri);
                } else {
                    Toast.makeText(UploadinfoActivity.this, "Please Select Image", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {

            imageUri = data.getData();
            img.setImageURI(imageUri);

        }
    }

    private void uploadToFirebase(Uri uri){

        final StorageReference fileRef = storageref.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String descp= des.getEditableText().toString();
                        String location= lo.getEditableText().toString();
                        String urgency= ur.getEditableText().toString();
                        String anim= an.getEditableText().toString();
                        String itemm = item;
                       Model model = new Model(uri.toString(), anim, location, itemm, descp, urgency);
                        String modelId = root.push().getKey();
                        root.child(modelId).setValue(model);
                        Toast.makeText(UploadinfoActivity.this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UploadinfoActivity.this, "Uploading Failed !!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getFileExtension(Uri mUri){

        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
         item = parent.getItemAtPosition(position).toString();
    }
}