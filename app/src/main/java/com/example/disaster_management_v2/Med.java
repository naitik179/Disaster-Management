package com.example.disaster_management_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Med extends AppCompatActivity {

    CheckBox cb11, cb2, cb3, cb4, cb5;
    LinearLayout myLayout;
    EditText q1, q2, q3, q4, q5;
    String n1s,n2s,n3s,n4s,n5s;
    Button tt;
    int flag1=0,flag2=0,flag3=0,flag4=0,flag5=0;
    int s1,s2,s3,s4,s5,sa,sb,sc,sd,se;
    Object n1,n2,n3,n4,n5;

    FirebaseAuth mFirebaseAuth;
    private DatabaseReference mReg,mreg2,mreg3,mreg4,mreg5,md1,md2,md3,md4,md5,need1,need2,need3,need4,need5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med);
        cb11 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        cb3 = findViewById(R.id.cb3);
        cb4 = findViewById(R.id.cb4);
        cb5 = findViewById(R.id.cb5);
        q1 = findViewById(R.id.t1);
        q2 = findViewById(R.id.t2);
        q3 = findViewById(R.id.t3);
        q4 = findViewById(R.id.t4);
        q5 = findViewById(R.id.t5);
        tt=findViewById(R.id.toiletriesbtn);





        mFirebaseAuth=FirebaseAuth.getInstance();
        mReg=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Med").child(mFirebaseAuth.getInstance().getCurrentUser().getUid()).child("First Aid");
        mreg2=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Med").child(mFirebaseAuth.getInstance().getCurrentUser().getUid()).child("Viral Fever");
        mreg3=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Med").child(mFirebaseAuth.getInstance().getCurrentUser().getUid()).child("Jaundice");
        mreg4=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Med").child(mFirebaseAuth.getInstance().getCurrentUser().getUid()).child("Cholera");
        mreg5=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Med").child(mFirebaseAuth.getInstance().getCurrentUser().getUid()).child("Cold");

        md1=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Med").child(mFirebaseAuth.getInstance().getCurrentUser().getUid());

        String mdd1=md1.toString();

//        Log.i("value ","")
        cb11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cb11.isChecked()) {
                    q1.setVisibility(View.VISIBLE);
                    flag1=1;
                    need1=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Toiletries").child(mFirebaseAuth.getInstance().getCurrentUser().getUid()).child("First Aid");

//                    if(md1.child("quantity")

                } else {
                    q1.setVisibility(View.INVISIBLE);
                }
            }
        });

        cb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cb2.isChecked()) {
                    q2.setVisibility(View.VISIBLE);
                    flag2=1;
                    need2=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Med").child(mFirebaseAuth.getInstance().getCurrentUser().getUid()).child("Viral Fever");
                } else {
                    q2.setVisibility(View.INVISIBLE);

                }
            }
        });

        cb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cb3.isChecked()) {
                    q3.setVisibility(View.VISIBLE);
                    flag3=1;
                    need3=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Med").child(mFirebaseAuth.getInstance().getCurrentUser().getUid()).child("Jaundice");

                } else {
                    q3.setVisibility(View.INVISIBLE);

                }
            }
        });

        cb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cb4.isChecked()) {
                    q4.setVisibility(View.VISIBLE);
                    flag4=1;
                    need4=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Med").child(mFirebaseAuth.getInstance().getCurrentUser().getUid()).child("Cholera");

                } else {
                    q4.setVisibility(View.INVISIBLE);

                }
            }
        });

        cb5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cb5.isChecked()) {
                    q5.setVisibility(View.VISIBLE);
                    flag5=1;
                    need5=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Med").child(mFirebaseAuth.getInstance().getCurrentUser().getUid()).child("Cold");

                } else {
                    q5.setVisibility(View.INVISIBLE);

                }
            }
        });

        tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Cricket","S!:"+s1);
                if(flag1==1) {
                    s1 = Integer.parseInt(q1.getText().toString());

                    mReg.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            n1=dataSnapshot.child("quantity").getValue();
                            n1s=String.valueOf(n1);
                            sa=Integer.parseInt(n1s);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    Log.i("Pranav","value:"+sa);
                    int s1Total=s1+sa;
                    mReg.child("quantity").setValue(s1Total);
                }

                if(flag2==1) {
                    s2 = Integer.parseInt(q2.getText().toString());
                    mreg2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            n2=dataSnapshot.child("quantity").getValue();
                            n2s=String.valueOf(n2);
                            sb=Integer.parseInt(n2s);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    // Log.i("Pranav","value:"+sa);
                    int s2Total=s2+sb;
                    need2.child("quantity").setValue(s2Total);
                }
                if(flag3==1) {
                    s3 = Integer.parseInt(q3.getText().toString());

                    mreg3.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            n3=dataSnapshot.child("quantity").getValue();
                            n3s=String.valueOf(n3);
                            sc=Integer.parseInt(n3s);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    // Log.i("Pranav","value:"+sa);
                    int s3Total=s3+sc;
                    need3.child("quantity").setValue(s3Total);

                }

                if(flag4==1) {
                    s4 = Integer.parseInt(q4.getText().toString());

                    mreg4.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            n4=dataSnapshot.child("quantity").getValue();
                            n4s=String.valueOf(n4);
                            sd=Integer.parseInt(n4s);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    // Log.i("Pranav","value:"+sa);
                    int s4Total=s4+sd;
                    need4.child("quantity").setValue(s4Total);
                }

                if (flag5 == 1) {
                    s5 = Integer.parseInt(q5.getText().toString());
                    mreg5.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            n5=dataSnapshot.child("quantity").getValue();
                            n5s=String.valueOf(n5);
                            se=Integer.parseInt(n5s);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    // Log.i("Pranav","value:"+sa);
                    int s5Total=s5+se;
                    need5.child("quantity").setValue(s5Total);
                }



            }
        });
    }

}
