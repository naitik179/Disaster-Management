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

public class Food_Activity extends AppCompatActivity {

    EditText rice,dal,wheat,tea,others,milk,otherName;
    Button submit;
    private static int riceV,dalV,teaV,wheatV,milkV;

    DatabaseReference mref,mreg;
    FirebaseAuth mauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_);
        rice=findViewById(R.id.rice);
        dal=findViewById(R.id.dal);
        wheat=findViewById(R.id.wheat);
        tea=findViewById(R.id.tea);
        others=findViewById(R.id.otherQuantity);
        milk=findViewById(R.id.milk);
        otherName=findViewById(R.id.otherName);
        submit=findViewById(R.id.sumitButton);

        mref= FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Food");
        mreg=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Food").child(mauth.getInstance().getCurrentUser().getUid());
        //riceV=Integer.parseInt(rice.getText().toString()) +  Integer.parseInt(String.valueOf(mref.child(mauth.getInstance().getCurrentUser().getUid()).child("Rice_Quantity").getValue()));

        mauth=FirebaseAuth.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (rice.getText().toString().isEmpty() && wheat.getText().toString().isEmpty() && dal.getText().toString().isEmpty() && milk.getText().toString().isEmpty() && tea.getText().toString().isEmpty()) {
                    rice.setError("Enter atleast one field");
                    rice.requestFocus();
                    wheat.setError("Enter atleast one field");
                    wheat.requestFocus();
                    tea.setError("Enter atleast one field");
                    tea.requestFocus();
                    milk.setError("Enter atleast one field");
                    milk.requestFocus();
                    dal.setError("Enter atleast one field");
                    dal.requestFocus();
                }
                else if(otherName.getText().toString().isEmpty()!=true && others.getText().toString().isEmpty()){
                    others.setError("Enter the quantity required of the above requested item");
                    others.requestFocus();
                }

                else {
                    mref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            if (dataSnapshot.child(mauth.getInstance().getCurrentUser().getUid()).child("Rice_Quantity").getValue() != null) {
                                riceV = Integer.parseInt(String.valueOf(dataSnapshot.child(mauth.getInstance().getCurrentUser().getUid()).child("Rice_Quantity").getValue())) + Integer.parseInt(String.valueOf(rice.getText()));
                                dalV = Integer.parseInt(String.valueOf(dataSnapshot.child(mauth.getInstance().getCurrentUser().getUid()).child("Dal_Quantity").getValue())) + Integer.parseInt(String.valueOf(rice.getText()));
                                teaV = Integer.parseInt(String.valueOf(dataSnapshot.child(mauth.getInstance().getCurrentUser().getUid()).child("Tea_Quantity").getValue())) + Integer.parseInt(String.valueOf(rice.getText()));
                                wheatV = Integer.parseInt(String.valueOf(dataSnapshot.child(mauth.getInstance().getCurrentUser().getUid()).child("Wheat_Quantity").getValue())) + Integer.parseInt(String.valueOf(rice.getText()));
                                milkV = Integer.parseInt(String.valueOf(dataSnapshot.child(mauth.getInstance().getCurrentUser().getUid()).child("Milk_Quantity").getValue())) + Integer.parseInt(String.valueOf(rice.getText()));
                            }

                            else {
                                riceV = Integer.parseInt(String.valueOf(rice.getText()));
                                wheatV = Integer.parseInt(String.valueOf(wheat.getText()));
                                teaV = Integer.parseInt(String.valueOf(tea.getText()));
                                dalV = Integer.parseInt(String.valueOf(dal.getText()));
                                milkV = Integer.parseInt(String.valueOf(milk.getText()));
                            }

                            mref.child(mauth.getInstance().getCurrentUser().getUid()).child("Rice_Quantity").setValue(String.valueOf(riceV));
                            mref.child(mauth.getInstance().getCurrentUser().getUid()).child("Wheat_Quantity").setValue(String.valueOf(wheatV));
                            mref.child(mauth.getInstance().getCurrentUser().getUid()).child("Tea_Quantity").setValue(String.valueOf(teaV));
                            mref.child(mauth.getInstance().getCurrentUser().getUid()).child("Dal_Quantity").setValue(String.valueOf(dalV));
                            mref.child(mauth.getInstance().getCurrentUser().getUid()).child("Milk_Quantity").setValue(String.valueOf(milkV));
                            if (others.getText() != null) {
                                mref.child(mauth.getInstance().getCurrentUser().getUid()).child("Other_Items").child(otherName.getText().toString()).setValue(others.getText().toString());
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    Intent i = new Intent(Food_Activity.this, MainActivity.class);
                    startActivity(i);
                }

            }

        });

    }
}
