package com.example.disaster_management_v2;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WaterRequirement extends AppCompatActivity {
    TextView water;
    EditText waterDonate;
    Button donate;
    DatabaseReference mreg;
    FirebaseAuth mauth;
    int c=0;
    //Object w1;
    String w;
    int waterPrevious,total,wd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_requirement);
        water=findViewById(R.id.waterRequired);
        waterDonate=findViewById(R.id.waterDonateQuantity);
        donate=findViewById(R.id.DonateWater);
        mreg= FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Water").child("XLZjJXBLacfdr6qgWOfMRN8L7WX2");
        mreg.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String w2=String.valueOf(dataSnapshot.child("quantity").getValue());
                waterPrevious=Integer.parseInt(w2);
                water.setText(String.valueOf(waterPrevious));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mreg.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String w2=String.valueOf(dataSnapshot.child("quantity").getValue());
                        waterPrevious=Integer.parseInt(w2);
                        water.setText(String.valueOf(waterPrevious));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                w=waterDonate.getText().toString();
                try {
                     wd = Integer.parseInt(w);
                }
                catch(Exception e){

                }
                total=waterPrevious-wd;
                mreg.child("quantity").setValue(total);
                Toast.makeText(WaterRequirement.this, ""+wd+" Litres of Water Donated to the Relief Center", Toast.LENGTH_LONG).show();
            }
        });


    }


}
