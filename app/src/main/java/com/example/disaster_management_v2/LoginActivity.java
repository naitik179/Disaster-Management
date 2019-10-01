package com.example.disaster_management_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {


    Button loginButton;
    TextView registerTransfer;
    private DatabaseReference helper,reliefCenter;
    EditText email,password,phone;
    FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        helper= FirebaseDatabase.getInstance().getReference("Helper Registration");
        reliefCenter=FirebaseDatabase.getInstance().getReference("Sub Admin Registration");
        loginButton=findViewById(R.id.loginButn);
        mAuth=FirebaseAuth.getInstance();
        email = findViewById(R.id.emailid);
        password = findViewById(R.id.password);
        phone=findViewById(R.id.loginphoneno);
        mAuthStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mUser=mAuth.getCurrentUser();
                if(mUser!=null)
                {
                    helper.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(mAuth.getInstance().getCurrentUser().getUid())) {
                                // run some code
                                Toast.makeText(LoginActivity.this, "You are Logged in", Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(LoginActivity.this,HelperDashboardActivity.class);
                                startActivity(i);
                                View inflatedView = getLayoutInflater().inflate(R.layout.nav_header_helper_dashboard, null);
                                TextView text =  inflatedView.findViewById(R.id.username);
                                TextView  text1=inflatedView.findViewById(R.id.displayEmail);
                                text.setText(email.getText().toString());
                                // text1.setText(landmark.getText().toString());
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    reliefCenter.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(mAuth.getInstance().getCurrentUser().getUid())) {
                                // run some code
                                Toast.makeText(LoginActivity.this, "You are Logged in", Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(i);
                                View inflatedView = getLayoutInflater().inflate(R.layout.nav_header_helper_dashboard, null);
                                TextView text =  inflatedView.findViewById(R.id.username);
                                TextView  text1=inflatedView.findViewById(R.id.displayEmail);
                                text.setText(email.getText().toString());
                                // text1.setText(landmark.getText().toString());
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                 /*  reliefCenter.addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       void onDataChange(DataSnapshot snapshot) {
                           if (snapshot.hasChild(mAuth.getInstance().getCurrentUser().getUid())) {
                               // run some code
                               Toast.makeText(LoginActivity.this, "You are Logged in", Toast.LENGTH_SHORT).show();
                               Intent i=new Intent(LoginActivity.this,MainActivity.class);
                               startActivity(i);
                           }
                       }
                   });*/

                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Please Login", Toast.LENGTH_SHORT).show();
                }
            }
        };
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String e=email.getText().toString();
                String pwd=password.getText().toString();
                String pne=phone.getText().toString();
                if(e.isEmpty())
                {
                    email.setError("Please Enter email id!!");
                    email.requestFocus();
                }
                else if(pwd.isEmpty())
                {
                    password.setError("Please enter Your Password!!");
                    password.requestFocus();
                }
                else if(pne.isEmpty())
                {
                    phone.setError("Please enter phone  no!!!");
                    phone.requestFocus();
                }
                else if(e.isEmpty()&&pwd.isEmpty()&&pne.isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "Fields are Empty!!", Toast.LENGTH_SHORT).show();
                }
                else if(!(e.isEmpty()&&pwd.isEmpty()&&pne.isEmpty()))
                {
                    mAuth.signInWithEmailAndPassword(e,pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(LoginActivity.this, "Login Error,Please Login again!!", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                helper.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.hasChild(mAuth.getInstance().getCurrentUser().getUid())) {
                                            // run some code
                                            Toast.makeText(LoginActivity.this, "You are Logged in", Toast.LENGTH_SHORT).show();
                                            Intent i=new Intent(LoginActivity.this,HelperDashboardActivity.class);
                                            startActivity(i);
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                                Intent i= new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(i);
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Error Occurred!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerTransfer=findViewById(R.id.toregister);
        registerTransfer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v1) {
                Intent launchActivity1= new Intent(LoginActivity.this,Registration_activity.class);
                startActivity(launchActivity1);

            }
        });

    }
    public void onStart()
    {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

}
