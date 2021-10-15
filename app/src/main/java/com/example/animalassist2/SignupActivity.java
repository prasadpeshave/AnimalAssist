package com.example.animalassist2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

public class SignupActivity extends AppCompatActivity {
    EditText uname, upass, uem;
    CountryCodePicker uph;
    Button ureg, ulog;
    FirebaseDatabase rootnode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ureg=(Button)findViewById(R.id.butsign);
        ulog=(Button)findViewById(R.id.butexis);
        uname=findViewById(R.id.name);
        upass=findViewById(R.id.pass);

        uem= findViewById(R.id.useem);


        ulog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        ureg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootnode=FirebaseDatabase.getInstance();
                reference=rootnode.getReference().child("users");

                //get all the values
                String ph = uph.getSelectedCountryCode();
                String em=uem.getEditableText().toString();
                String nm=uname.getEditableText().toString();
                String ps=upass.getEditableText().toString();



                UserHelper helperclass =new UserHelper(ph, em, nm, ps);
                reference.child(ph).setValue(helperclass);

                Toast.makeText(SignupActivity.this,"Registered Successfully,Go to Login Page",Toast.LENGTH_SHORT).show();
            }
        });
    }
}