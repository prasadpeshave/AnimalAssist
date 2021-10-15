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

public class LoginActivity extends AppCompatActivity {
EditText userph, usps;
Button uslog, usreg;
    private DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userph= findViewById(R.id.userphno);
        usps= findViewById(R.id.passs);
        uslog= findViewById(R.id.butlog);
        usreg= findViewById(R.id.butdirlog);

        usreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
        uslog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin(v);
            }
        });

        reference= FirebaseDatabase.getInstance().getReference().child("users");
    }
    String pass;
    public void openLogin(View v){
        final String phoneno = userph.getText().toString();
        pass = usps.getText().toString();
        if (reference.child(phoneno) != null) {
            reference.child(phoneno).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    UserHelper userHelp = dataSnapshot.getValue(UserHelper.class);
                    if (pass.equals(userHelp.getPs())) {
                        Toast.makeText(LoginActivity.this, "Login  Successfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "Wrong details", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        } else {
            Toast.makeText(LoginActivity.this, "User doesn't exist", Toast.LENGTH_SHORT).show();
        }
    }
    }


