package com.example.disaster_management_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
    DatabaseReference mreg,mat;
    FirebaseAuth mauth;
    Object quantity;
    String q;
    EditText qua;
    Button apply;
    int qr,q1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);
        mreg= FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Drinking Water").child(mauth.getInstance().getCurrentUser().getUid());
        mat=FirebaseDatabase.getInstance().getReference();
        apply=findViewById(R.id.applyWater);
        qua=findViewById(R.id.waterQuantity);
        mreg.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                quantity=dataSnapshot.child("quantity").getValue();
                q=String.valueOf(quantity);
                Log.i("Water","Quantity:"+q);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qr=Integer.parseInt(qua.getText().toString());
                if(q=="")
                {
                    mat.child("Material_Application").child("Drinking Water").child(mauth.getInstance().getCurrentUser().getUid()).child("quantity").setValue(qr);
                }
                else
                {
                    q1=qr+Integer.parseInt(q);
                    mat.child("Material_Application").child("Drinking Water").child(mauth.getInstance().getCurrentUser().getUid()).child("quantity").setValue(q1);
                }
            }
        });

    }
}