package com.example.animalassist2;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
Button chat,dist;;
Button vets;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        chat= findViewById(R.id.chatbot);
        dist= findViewById(R.id.chatbot2);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ChatbotActivity.class);
                startActivity(intent);
            }
        });
        dist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, UploadinfoActivity.class);
                startActivity(intent);
            }
        });

        vets=findViewById(R.id.chatbot3);
        vets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MapsActivity.class);
                startActivity(intent);
                Toast.makeText(HomeActivity.this,"Vets Nearby",Toast.LENGTH_SHORT).show();
            }
        });
    }
}