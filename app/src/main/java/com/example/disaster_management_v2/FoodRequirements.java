package com.example.disaster_management_v2;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class FoodRequirements extends AppCompatActivity {
    EditText rice,dal,wheat,tea,others,milk,otherName;
    Button submit;
    private static int riceV,dalV,teaV,wheatV,milkV;
    //TextView R,W,D,T,M;
    DatabaseReference mref,mreg;
    FirebaseAuth mauth;
    TextView r1,r2,r3,r4,r5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_requirements);

        rice=findViewById(R.id.rice1);
        dal=findViewById(R.id.dal1);
        wheat=findViewById(R.id.wheat1);
        tea=findViewById(R.id.tea1);
        others=findViewById(R.id.otherQuantity1);
        milk=findViewById(R.id.milk1);
        otherName=findViewById(R.id.otherName1);
        r1=findViewById(R.id.requiredRiceQuantity);
        r2=findViewById(R.id.requiredWheatQuantity);
        r3=findViewById(R.id.requiredDalQuantity);
        r4=findViewById(R.id.requiredTeaQuantity);
        r5=findViewById(R.id.requiredMilkQuantity);

        submit=findViewById(R.id.donateFood);

        mref= FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Food");
        mreg=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Food").child("JsgGmbvZV8NBdqxjstKs6dcHhKc2");
        //riceV=Integer.parseInt(rice.getText().toString()) +  Integer.parseInt(String.valueOf(mref.child(mauth.getInstance().getCurrentUser().getUid()).child("Rice_Quantity").getValue()));

        mauth= FirebaseAuth.getInstance();
        mreg.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String r=String.valueOf(dataSnapshot.child("Rice_Quantity").getValue());
                  r1.setText(r);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mreg.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String r=String.valueOf(dataSnapshot.child("Wheat_Quantity").getValue());
                r2.setText(r);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mreg.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String r=String.valueOf(dataSnapshot.child("Dal_Quantity").getValue());
                r3.setText(r);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mreg.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String r=String.valueOf(dataSnapshot.child("Tea_Quantity").getValue());
                r4.setText(r);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mreg.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String r=String.valueOf(dataSnapshot.child("Milk_Quantity").getValue());
                r5.setText(r);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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


                                try {
                                    mref.child("JsgGmbvZV8NBdqxjstKs6dcHhKc2").child("Rice_Quantity").setValue(Integer.parseInt(String.valueOf(dataSnapshot.child("JsgGmbvZV8NBdqxjstKs6dcHhKc2").child("Rice_Quantity").getValue())) - Integer.parseInt(String.valueOf(rice.getText())));
                                }
                                catch (Exception e){

                                }
                                try{
                                    mref.child("JsgGmbvZV8NBdqxjstKs6dcHhKc2").child("Dal_Quantity").setValue(Integer.parseInt(String.valueOf(dataSnapshot.child("JsgGmbvZV8NBdqxjstKs6dcHhKc2").child("Dal_Quantity").getValue())) - Integer.parseInt(String.valueOf(dal.getText())));

                                }
                                catch (Exception e){

                                }
                                try{
                                    mref.child("JsgGmbvZV8NBdqxjstKs6dcHhKc2").child("Tea_Quantity").setValue(Integer.parseInt(String.valueOf(dataSnapshot.child("JsgGmbvZV8NBdqxjstKs6dcHhKc2").child("Tea_Quantity").getValue())) - Integer.parseInt(String.valueOf(tea.getText())));

                                }
                                catch (Exception e){

                                }
                                try {
                                    mref.child("JsgGmbvZV8NBdqxjstKs6dcHhKc2").child("Wheat_Quantity").setValue(Integer.parseInt(String.valueOf(dataSnapshot.child("JsgGmbvZV8NBdqxjstKs6dcHhKc2").child("Wheat_Quantity").getValue())) - Integer.parseInt(String.valueOf(wheat.getText())));

                                }
                                catch (Exception e){

                                }
                                try{
                                    mref.child("JsgGmbvZV8NBdqxjstKs6dcHhKc2").child("Milk_Quantity").setValue(Integer.parseInt(String.valueOf(dataSnapshot.child("JsgGmbvZV8NBdqxjstKs6dcHhKc2").child("Milk_Quantity").getValue())) - Integer.parseInt(String.valueOf(milk.getText())));

                                }
                                catch(Exception e){

                                }
                                if (others.getText() != null) {
                                mref.child("JsgGmbvZV8NBdqxjstKs6dcHhKc2").child("Other_Items").child(otherName.getText().toString()).setValue(others.getText().toString());
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    Toast.makeText(FoodRequirements.this,"Food Items Donated to the Relief Center.Thank You for Your Contribution!!!",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(FoodRequirements.this, HelperDashboardActivity.class);
                    startActivity(i);
                }

            }

        });
    }
}
