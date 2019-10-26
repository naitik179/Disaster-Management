package com.example.disaster_management_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class water extends AppCompatActivity {

    EditText q1;
    Button tt;
    String n1s;
    int s1;
    Object n1;
    int sa;

    FirebaseAuth mFirebaseAuth;
    private DatabaseReference mReg,md1,need1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);
        q1 = findViewById(R.id.waterQuantity);
        tt=findViewById(R.id.applyWater);


        mFirebaseAuth=FirebaseAuth.getInstance();
        mReg= FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Water").child(mFirebaseAuth.getInstance().getCurrentUser().getUid());

        md1=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Water").child(mFirebaseAuth.getInstance().getCurrentUser().getUid());

        String mdd1=md1.toString();
        tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    s1 = q1.getText().toString();
                //   mReg.child("quantity").setValue(s1);

                if(q1.getText().toString().isEmpty())
                {
                    q1.setError("Enter Quantity in Litres required for Water");
                    q1.requestFocus();

                }
                else {


                    s1 = Integer.parseInt(q1.getText().toString());

                    mReg.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            n1 = dataSnapshot.child("quantity").getValue();
                            n1s = String.valueOf(n1);
                            sa = Integer.parseInt(n1s);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                    int s1Total = s1 + sa;
                    mReg.child("quantity").setValue(s1Total);
                    Intent i=new Intent(water.this,MainActivity.class);
                    startActivity(i);

                }


            }
        });
    }
}