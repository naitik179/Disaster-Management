package com.example.disaster_management_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class DataRetrieve extends AppCompatActivity {

    Button retrieveButton;
    public static final String TAG = "Info";
    TextView dataFrmDb;

    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_retrieve);

        dataFrmDb=findViewById(R.id.textViewDB);
        //initViews();
        mDatabase =FirebaseDatabase.getInstance();
        mRef=mDatabase.getReference("Sub Admin Registration");
        retrieveButton=findViewById(R.id.retrieve);
        //this.retrieveButton.setOnClickListener(this::runCode);
        this.retrieveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readData(v);
            }
        });

    }

    public void readData(View view){

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshots: dataSnapshot.getChildren()) {
                    try {
                        Map<Object, String> data = (Map<Object, String>) snapshots.getValue();

                        Log.d(TAG, "onDataChange: Landmark= " + data.get("Landmark"));
                        Log.d(TAG, "onDataChange: Email= "+data.get("Email id"));
                        Log.d(TAG, "onDataChange: Email= "+data.get("Latitude"));
                        Log.d(TAG, "onDataChange: Email= "+data.get("Phone no"));
                        Log.d(TAG, "onDataChange: Email= "+data.get("Longitude"));
                        Log.d(TAG, "onDataChange: Email= "+data.get("Aadhar UID"));
                        Log.d(TAG, "onDataChange: Email= "+data.get("Affected People"));
                        dataFrmDb.setText(data.get("Latitude"));
                        Log.d(TAG, "onDataChange: Key= " + snapshots.getKey());

                    }

                    catch (Exception e){
                        Log.i(TAG, "onDataChange: "+e.getMessage());
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
