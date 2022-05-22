package com.example.multiapiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedBack extends AppCompatActivity {

    EditText inp_name,inp_email,inp_subject,inp_message;
    Button btn_submit;
    private FirebaseAuth mAuth;
    DatabaseReference myRef;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);


        inp_name=findViewById(R.id.fi);
        inp_email=findViewById(R.id.emi);
        inp_subject=findViewById(R.id.si);
        inp_message=findViewById(R.id.mi);
        btn_submit=findViewById(R.id.submit);



        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("User's Feedback");
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                insert_data();

            }
        });
    }

    private void insert_data() {
        String fullname=inp_name.getText().toString();
        String email=inp_email.getText().toString();
        String subject=inp_subject.getText().toString();
        String message=inp_message.getText().toString();


        if (fullname.isEmpty()){
            inp_name.setError("full name is required");
            inp_name.requestFocus();
            return;
        }
        if (email.isEmpty()){
            inp_email.setError("email is required");
            inp_email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            inp_email.setError("please provide valid email");
            inp_email.requestFocus();
            return;
        }
        if (subject.isEmpty()){
            inp_subject.setError("subject is required");
            inp_subject.requestFocus();
            return;
        }

        if (message.isEmpty()){
            inp_message.setError("message is required");
            inp_message.requestFocus();
            return;
        }
       else{
            Model_Class model_class=new Model_Class(fullname,email,subject,message);
            myRef.push().setValue(model_class);
            Toast.makeText(FeedBack.this, "Submitted", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(FeedBack.this,MainActivity.class);
            startActivity(intent);
        }


    }

}