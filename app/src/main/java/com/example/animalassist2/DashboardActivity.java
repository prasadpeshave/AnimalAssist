package com.example.animalassist2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity {
    ImageView user, org;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        user= findViewById(R.id.img1);
        org= findViewById(R.id.img2);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, SignupActivity.class);
                startActivity(intent);
                Toast.makeText(DashboardActivity.this,"Proceeding as a user",Toast.LENGTH_SHORT).show();
            }
        });


        org.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, SignuporgActivity.class);
                startActivity(intent);
                Toast.makeText(DashboardActivity.this,"Proceeding as an organization",Toast.LENGTH_SHORT).show();
            }
        });
    }
}