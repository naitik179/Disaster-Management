package com.example.disaster_management_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot extends AppCompatActivity {
    EditText resetEmail;
    Button reset;
    FirebaseAuth auth;
    //String e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        resetEmail=findViewById(R.id.resetEmail);
        reset=findViewById(R.id.sendReset);
        auth=FirebaseAuth.getInstance();
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rm=resetEmail.getText().toString();
                if(rm.isEmpty()) {
                            resetEmail.setError("Enter Email Id to get link for password link");
                            resetEmail.requestFocus();
                }else{
                    auth.sendPasswordResetEmail(resetEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Forgot.this, "Password Reset Email is successfully sent.", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Forgot.this, LoginActivity.class);
                                startActivity(i);
                            }
                        }
                    });
                }
            }
        });
    }
}

