package com.example.disaster_management_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class tpactivity extends AppCompatActivity {
    TextView lat,lon;
    private DatabaseReference helper,reliefCenter;
    EditText email,password,phone;
    FirebaseAuth mAuth;
    FirebaseUser muser;
    DatabaseReference ref;
    String latitude,longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tpactivity);

        lat=findViewById(R.id.lati);
        lon=findViewById(R.id.longitu);
        mAuth=FirebaseAuth.getInstance();
        muser=mAuth.getCurrentUser();
        helper= FirebaseDatabase.getInstance().getReference("Sub Admin Registration").child(muser.getUid());
    }


    public void Buttonclick(View v)
    {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                latitude=dataSnapshot.child("Latitude").getValue().toString();
                longitude=dataSnapshot.child("Longitude").getValue().toString();

                lat.setText(latitude);
                lon.setText(longitude);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
