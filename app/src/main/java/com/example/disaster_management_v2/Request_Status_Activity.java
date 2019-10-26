package com.example.disaster_management_v2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class Request_Status_Activity extends AppCompatActivity {

    TextView clothes, food, medicine, water;
    Button donate, map;
    private final static int REQUEST_CODE_1 = 1;

    public static final String TAG = "Info";
    // TextView dataFrmDb;

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReg, mReg1, mReg2, mReg3,mReg4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request__status_);

        clothes = findViewById(R.id.textView);
        food = findViewById(R.id.foodText);
        medicine = findViewById(R.id.medicineText);
        water = findViewById(R.id.waterText);
        donate = findViewById(R.id.donate);
        map = findViewById(R.id.dashboard);

        mDatabase =FirebaseDatabase.getInstance();
        mReg = mDatabase.getReference("Material_Application").child("Clothes");
        mReg1 = mDatabase.getReference("Material_Application").child("Drinking Water");
        mReg2 = mDatabase.getReference("Material_Application").child("Food");
        mReg3 = mDatabase.getReference("Material_Application").child("Other Requirements");
        mReg4 = mDatabase.getReference("Material_Application").child("Medicines");
        final String value;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("RC Id").toString();

            Log.i(TAG,"value of valie is "+ value);

            //String abcd=mReg.child(value).child("quantity").getValue().toString();
           final String kk=value;


           //clothes
            mReg.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshots : dataSnapshot.getChildren()) {

                       // String kk=snapshots.getKey();
                        Log.i(TAG,"KK type : "+kk.getClass()+" Value type : "+value.getClass());
                        try {
                        if (kk.equals(value)) {

                               Map<Object, Long> data = (Map<Object, Long>) snapshots.getValue();

                                   // Log.i(TAG, "clothes quantity: " + data.get("quantity"));
                                    clothes.setText(snapshots.child("quantity").getValue().toString());
                                    //break;



                        } else {
                            Log.i(TAG, "nahi horha hai retrieve");
                        }
                        } catch (Exception e) {
                            Log.i(TAG, "onDataChange: " + e.getMessage());
                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

//water
            mReg1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshots : dataSnapshot.getChildren()) {

                        // String kk=snapshots.getKey();
                        Log.i(TAG,"KK type : "+kk.getClass()+" Value type : "+value.getClass());
                        try {
                            if (kk.equals(value)) {

                                Map<Object, Long> data = (Map<Object, Long>) snapshots.getValue();

                                // Log.i(TAG, "clothes quantity: " + data.get("quantity"));
                                water.setText(snapshots.child("quantity").getValue().toString());
                                //break;



                            } else {
                                Log.i(TAG, "nahi horha hai retrieve");
                            }
                        } catch (Exception e) {
                            Log.i(TAG, "onDataChange: " + e.getMessage());
                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

//food

            mReg2.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshots : dataSnapshot.getChildren()) {

                        // String kk=snapshots.getKey();
                        Log.i(TAG,"KK type : "+kk.getClass()+" Value type : "+value.getClass());
                        try {
                            if (kk.equals(value)) {

                                Map<Object, Long> data = (Map<Object, Long>) snapshots.getValue();

                                // Log.i(TAG, "clothes quantity: " + data.get("quantity"));
                                food.setText(snapshots.child("quantity").getValue().toString());
                                //break;



                            } else {
                                Log.i(TAG, "nahi horha hai retrieve");
                            }
                        } catch (Exception e) {
                            Log.i(TAG, "onDataChange: " + e.getMessage());
                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

//other Requirements
            mReg3.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshots : dataSnapshot.getChildren()) {

                        // String kk=snapshots.getKey();
                        Log.i(TAG,"KK type : "+kk.getClass()+" Value type : "+value.getClass());
                        try {
                            if (kk.equals(value)) {

                                Map<Object, Long> data = (Map<Object, Long>) snapshots.getValue();

                                // Log.i(TAG, "clothes quantity: " + data.get("quantity"));
                                clothes.setText(snapshots.child("quantity").getValue().toString());
                                //break;



                            } else {
                                Log.i(TAG, "nahi horha hai retrieve");
                            }
                        } catch (Exception e) {
                            Log.i(TAG, "onDataChange: " + e.getMessage());
                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



            mReg4.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshots : dataSnapshot.getChildren()) {

                        // String kk=snapshots.getKey();
                        Log.i(TAG,"KK type : "+kk.getClass()+" Value type : "+value.getClass());
                        try {
                            if (kk.equals(value)) {

                                Map<Object, Long> data = (Map<Object, Long>) snapshots.getValue();

                                // Log.i(TAG, "clothes quantity: " + data.get("quantity"));
                                medicine.setText(snapshots.child("quantity").getValue().toString());
                                //break;



                            } else {
                                Log.i(TAG, "nahi horha hai retrieve");
                            }
                        } catch (Exception e) {
                            Log.i(TAG, "onDataChange: " + e.getMessage());
                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



            donate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Request_Status_Activity.this, Donation_Activity.class);
                    startActivity(i);
                }
            });

            map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Request_Status_Activity.this, MapActivity.class);
                    startActivity(i);
                }
            });


        }
    }

    public static int stringCompare(String str1, String str2)
    {

        int l1 = str1.length();
        int l2 = str2.length();
        int lmin = Math.min(l1, l2);

        for (int i = 0; i < lmin; i++) {
            int str1_ch = (int)str1.charAt(i);
            int str2_ch = (int)str2.charAt(i);

            if (str1_ch != str2_ch) {
                return 0;
            }
        }

        return 1;
    }
}


