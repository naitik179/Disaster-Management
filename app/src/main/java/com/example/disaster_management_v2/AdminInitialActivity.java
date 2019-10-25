package com.example.disaster_management_v2;

import android.app.LauncherActivity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;


import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Adapter.MyAdapter;
import Model.Contacts;
import Model.ListItem;

public class AdminInitialActivity extends AppCompatActivity {
    private RecyclerView  recyclerView ;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;
    FirebaseAuth mFirebaseAuth;
    private DatabaseReference mReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initialadminactivity);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewID);

         recyclerView.setHasFixedSize(true);

        mFirebaseAuth=FirebaseAuth.getInstance();

        mReg= FirebaseDatabase.getInstance().getReference().child("Affected_People");
         //every item has a fixed size
        recyclerView.setLayoutManager(new
                LinearLayoutManager(this));


        listItems = new ArrayList<>();

        for (int i = 0; i<10; i++) {
            /*ListItem listItem = new ListItem(
                    "Relief centre " + (i+1),
                    "City "
            );
            listItems.add(listItem);*/
        }
        mReg.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //listItems.clear();
                for(DataSnapshot RCs:dataSnapshot.getChildren())
                {
                  Log.i("Naitik","Pranav");
                    for (DataSnapshot Aadhars:RCs.getChildren()
                         ) {
                             String name=String.valueOf(Aadhars.child("Name").getValue());
                             String gen=String.valueOf(Aadhars.child("Gender").getValue());
                             Log.i("name","namee:"+name);

                        ListItem listItem = new ListItem(
                                "Name: " + name,
                                "Gender : "+gen
                        );
                        listItems.add(listItem);
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        adapter = new MyAdapter(this, listItems);

        recyclerView .setAdapter(adapter);

    }
}
