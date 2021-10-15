package com.example.animalassist2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginorgActivity extends AppCompatActivity {
    EditText orph, orps;
    Button orlog, orreg;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginorg);

        orph= findViewById(R.id.orgphone);
        orps= findViewById(R.id.orgpasss);
        orlog= findViewById(R.id.butlogg);
        orreg= findViewById(R.id.butdirlogorg);


        orreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginorgActivity.this, SignuporgActivity.class);
                startActivity(intent);
            }
        });

        reference= FirebaseDatabase.getInstance().getReference().child("orgs");
        orlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginorg(v);
            }
        });
    }
    String passs;
    public void openLoginorg(View v){
        final String phonenoo = orph.getText().toString();
        passs = orps.getText().toString();
        if (reference.child(phonenoo) != null) {
            reference.child(phonenoo).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    UserHelper userHelp = dataSnapshot.getValue(UserHelper.class);
                    if (passs.equals(userHelp.getPs())) {
                        Toast.makeText(LoginorgActivity.this, "Login  Successfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginorgActivity.this, ShowActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(LoginorgActivity.this, "Wrong details", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        } else {
            Toast.makeText(LoginorgActivity.this, "User doesn't exist", Toast.LENGTH_SHORT).show();
        }
    }
}