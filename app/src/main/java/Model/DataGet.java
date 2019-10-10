package Model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class DataGet {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;

    public void DataGet(){
        mDatabase =FirebaseDatabase.getInstance();
        mRef=mDatabase.getReference("Sub Admin Registration");

    }

    public ArrayList<String> readData(final ArrayList <String> list, final String type){
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshots: dataSnapshot.getChildren()) {
                    try {
                        Map<Object, String> data = (Map<Object, String>) snapshots.getValue();

                        Log.i("Info", "onDataChange: Email= "+data.get("Email id"));
                        Log.i("info", "onDataChange: Email= "+data.get("Phone no"));
                        list.add(data.get("Phone No"));


                    }

                    catch (Exception e){
                        Log.i("Info", "onDataChange: "+e.getMessage());
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return list;
    }
}

