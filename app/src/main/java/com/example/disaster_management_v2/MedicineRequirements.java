package com.example.disaster_management_v2;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.widget.Toast.LENGTH_SHORT;

public class MedicineRequirements extends AppCompatActivity {
    CheckBox cb11, cb2, cb3, cb4, cb5;
    LinearLayout myLayout;
    EditText q1, q2, q3, q4, q5;
    TextView r1,r2,r3,r4,r5;
    String n1s,n2s,n3s,n4s,n5s;
    Button tt;
    int flag1=0,flag2=0,flag3=0,flag4=0,flag5=0;
    int s1,s2,s3,s4,s5,sa,sb,sc,sd,se;
    int cc=0;
    Object n1,n2,n3,n4,n5;
    FirebaseAuth mFirebaseAuth;
    private DatabaseReference mReg,mreg2,mreg3,mreg4,mreg5,md1,md2,md3,md4,md5,need1,need2,need3,need4,need5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_requirements);
        cb11 = findViewById(R.id.mcb1);
        cb2 = findViewById(R.id.mcb2);
        cb3 = findViewById(R.id.mcb3);
        cb4 = findViewById(R.id.mcb4);
        cb5 = findViewById(R.id.mcb5);
        q1 = findViewById(R.id.mt1);
        q2 = findViewById(R.id.mt2);
        q3 = findViewById(R.id.mt3);
        q4 = findViewById(R.id.mt4);
        q5 = findViewById(R.id.mt5);
        r1=findViewById(R.id.requiredFirstAidQuantity);
        r2=findViewById(R.id.requiredViralQuantity);
        r3=findViewById(R.id.requiredJaundiceQuantity);
        r4=findViewById(R.id.requiredGlucoseQuantity);
        r5=findViewById(R.id.requiredColdQuantity);
        tt=findViewById(R.id.medicineDonate);

        mFirebaseAuth= FirebaseAuth.getInstance();
        mReg= FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Med").child("JsgGmbvZV8NBdqxjstKs6dcHhKc2").child("First Aid");
        mreg2=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Med").child("JsgGmbvZV8NBdqxjstKs6dcHhKc2").child("Viral Fever");
        mreg3=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Med").child("JsgGmbvZV8NBdqxjstKs6dcHhKc2").child("Jaundice");
        mreg4=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Med").child("JsgGmbvZV8NBdqxjstKs6dcHhKc2").child("Glucose");
        mreg5=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Med").child("JsgGmbvZV8NBdqxjstKs6dcHhKc2").child("Cold");

        //md1=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Toiletries").child(mFirebaseAuth.getInstance().getCurrentUser().getUid());

        // String mdd1=md1.toString();

//        Log.i("value ","")
        cb11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cb11.isChecked()) {
                    q1.setVisibility(View.VISIBLE);
                    flag1=1;
                    need1=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Med").child("JsgGmbvZV8NBdqxjstKs6dcHhKc2").child("First Aid");

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
                    need2=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Med").child("JsgGmbvZV8NBdqxjstKs6dcHhKc2").child("Viral Fever");
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
                    need3=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Med").child("JsgGmbvZV8NBdqxjstKs6dcHhKc2").child("Jaundice");

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
                    need4=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Med").child("JsgGmbvZV8NBdqxjstKs6dcHhKc2").child("Glucose");

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
                    need5=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Med").child("JsgGmbvZV8NBdqxjstKs6dcHhKc2").child("Cold");

                } else {
                    q5.setVisibility(View.INVISIBLE);

                }
            }
        });
        mReg.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                n1s=String.valueOf(dataSnapshot.child("quantity").getValue());
                Log.i("Pranav","value:"+n1s);
                sa=Integer.parseInt(n1s);
                r1.setText(String.valueOf(sa));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mreg2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                n2 = dataSnapshot.child("quantity").getValue();
                n2s = String.valueOf(n2);
                sb = Integer.parseInt(n2s);
                r2.setText(n2s);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mreg3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                n3 = dataSnapshot.child("quantity").getValue();
                n3s = String.valueOf(n3);
                sc = Integer.parseInt(n3s);
                r3.setText(n3s);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mreg4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                n4 = dataSnapshot.child("quantity").getValue();
                n4s = String.valueOf(n4);
                sd = Integer.parseInt(n4s);
                r4.setText(n4s);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mreg5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                n5 = dataSnapshot.child("quantity").getValue();
                n5s = String.valueOf(n5);
                se = Integer.parseInt(n5s);
                r5.setText(n5s);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Cricket","S!:"+s1);

                if(flag1==0 && flag2==0 && flag3==0 && flag4==0 && flag5==0)
                {
                    Toast.makeText(MedicineRequirements.this,"Apply in atleast One Field to apply for Toiletries", LENGTH_SHORT).show();
                }
                if(flag1==1) {
                    if(q1.getText().toString().isEmpty())
                    {
                        Toast.makeText(MedicineRequirements.this,"Quantity for First Aid  is Required", LENGTH_SHORT).show();
                    }
                    else {
                        s1 = Integer.parseInt(q1.getText().toString());
                        cc++;
                        mReg.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                n1s=String.valueOf(dataSnapshot.child("quantity").getValue());
                                Log.i("Pranav","value:"+n1s);
                                sa=Integer.parseInt(n1s);

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        int s1Total=sa-s1;
                        mReg.child("quantity").setValue(s1Total);
                    }

                    Log.i("Pranav","value:"+sa);

                }

                if(flag2==1) {

                    if(q2.getText().toString().isEmpty())
                    {
                        Toast.makeText(MedicineRequirements.this,"Quantity for Viral Fever Medicines is Required", LENGTH_SHORT).show();
                    }
                    else {
                        s2 = Integer.parseInt(q2.getText().toString());
                        cc++;
                        mreg2.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                n2 = dataSnapshot.child("quantity").getValue();
                                n2s = String.valueOf(n2);
                                sb = Integer.parseInt(n2s);


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }

                    // Log.i("Pranav","value:"+sa);
                    int s2Total=sb-s2;
                    need2.child("quantity").setValue(s2Total);
                }
                if(flag3==1) {
                    if(q3.getText().toString().isEmpty())
                    {
                        Toast.makeText(MedicineRequirements.this,"Quantity for Medicines of Jaundice is Required", LENGTH_SHORT).show();
                    }
                    else {
                        s3 = Integer.parseInt(q3.getText().toString());
                        cc++;
                        mreg3.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                n3 = dataSnapshot.child("quantity").getValue();
                                n3s = String.valueOf(n3);
                                sc = Integer.parseInt(n3s);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }

                    // Log.i("Pranav","value:"+sa);
                    int s3Total=sc-s3;
                    need3.child("quantity").setValue(s3Total);

                }

                if(flag4==1) {
                    if(q4.getText().toString().isEmpty())
                    {
                        Toast.makeText(MedicineRequirements.this,"Quantity for Glucose is Required", LENGTH_SHORT).show();
                    }
                    else {
                        s4 = Integer.parseInt(q4.getText().toString());
                        cc++;

                        mreg4.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                n4 = dataSnapshot.child("quantity").getValue();
                                n4s = String.valueOf(n4);
                                sd = Integer.parseInt(n4s);


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }

                    // Log.i("Pranav","value:"+sa);
                    int s4Total=sd-s4;
                    need4.child("quantity").setValue(s4Total);
                }
                if (flag5 == 1) {
                    if(q5.getText().toString().isEmpty())
                    {
                        Toast.makeText(MedicineRequirements.this,"Quantity for Cold and Cough Medicines is Required", LENGTH_SHORT).show();
                    }
                    else {
                        s5 = Integer.parseInt(q5.getText().toString());
                        cc++;
                        mreg5.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                n5 = dataSnapshot.child("quantity").getValue();
                                n5s = String.valueOf(n5);
                                se = Integer.parseInt(n5s);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                    int s5Total=se-s5;
                    need5.child("quantity").setValue(s5Total);
                }
                if(cc!=0)
                {
                    Toast.makeText(MedicineRequirements.this, "Medicines Donated Successfully to the Relief Center.Thank You for Your Contribution! ", Toast.LENGTH_LONG).show();
                    Intent i=new Intent(MedicineRequirements.this,HelperDashboardActivity.class);
                    startActivity(i);
                }
            }
        });
    }
}
