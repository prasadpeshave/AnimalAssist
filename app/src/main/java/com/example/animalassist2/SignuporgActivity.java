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

public class SignuporgActivity extends AppCompatActivity {
    EditText oname, opass, oem, oph;
    Button oreg, olog;
    FirebaseDatabase rootnode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signuporg);

        oreg=(Button)findViewById(R.id.butsignorg);
        olog=(Button)findViewById(R.id.butal);
        oname=findViewById(R.id.orgname);
        opass=findViewById(R.id.orgpass);
        oph=findViewById(R.id.orgcon);
        oem= findViewById(R.id.offmail);

        olog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignuporgActivity.this, LoginorgActivity.class);
                startActivity(intent);
            }
        });

        oreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootnode= FirebaseDatabase.getInstance();
                reference=rootnode.getReference().child("orgs");

                //get all the values
                String phorg=oph.getEditableText().toString();
                String emorg=oem.getEditableText().toString();
                String nmorg=oname.getEditableText().toString();
                String psorg=opass.getEditableText().toString();



                UserHelper helperclass =new UserHelper(nmorg, phorg, emorg, psorg);
                reference.child(phorg).setValue(helperclass);

                Toast.makeText(SignuporgActivity.this,"Registered Successfully,Go to Login Page",Toast.LENGTH_SHORT).show();
            }
        });
    }
}