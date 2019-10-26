package com.example.disaster_management_v2;

import android.app.LauncherActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Adapter.MyAdapter;
import Model.ListItem;

public class AdminInitialActivity extends AppCompatActivity {
    private RecyclerView  recyclerView;
    private RecyclerView.Adapter adapter,adapter1;
    private List<ListItem> listItems,listItems1;




    FirebaseAuth mFirebaseAuth;
    private DatabaseReference mReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initialadminactivity);

        mFirebaseAuth=FirebaseAuth.getInstance();

        mReg= FirebaseDatabase.getInstance().getReference().child("Affected_People").child(mFirebaseAuth.getInstance().getCurrentUser().getUid());

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewID1);
         recyclerView.setHasFixedSize(true);
        //every item has a fixed size
        recyclerView.setLayoutManager(new
                LinearLayoutManager(this));

        /*recyclerView1 = (RecyclerView) findViewById(R.id.recyclerViewID2);
        recyclerView1.setHasFixedSize(true);
        *///every item has a fixed size
        /*ecyclerView1.setLayoutManager(new
                LinearLayoutManager(this));
*/
        listItems = new ArrayList<>();
        listItems1 = new ArrayList<>();
        //RC_list=new ArrayList<>();


        mReg.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                TextView Total_Count=findViewById(R.id.title);
                boolean flag=false;
                int i=1;


                //EditText value=findViewById(R.id.RC_num);

                      //Map<Object, String> data = (Map<Object, String>) ds.getValue();
                    for (DataSnapshot Aadhars: dataSnapshot.getChildren()
                         ) {


                        ListItem listItem = new ListItem(
                                "Name : " + String.valueOf(Aadhars.child("Name").getValue()),
                                "Gender : "+String.valueOf(Aadhars.child("Gender").getValue())
                        );
                        listItems.add(listItem);

                        //RC_list.add(i,listItem);

                       for (DataSnapshot Familys: Aadhars.child("Other Family Members").getChildren()
                             ){
                           Log.i("Info", "onDataChange: Family :"+Familys.child("Name").getValue());
                            ListItem listItem1 = new ListItem(
                                    "Name : " + String.valueOf(Familys.child("Name").getValue()),
                                    "Gender : " + String.valueOf(Familys.child("Gender").getValue())
                            );
                            listItems.add(listItem1);
                                //RC_list.add(listItem1);
                           i++;

                          // RC_list.add(i,listItem);
                        }

                       i++;
                    }

                    recyclerView.setAdapter(adapter1);



                recyclerView .setAdapter(adapter);
                //recyclerView1.setAdapter(adapter2);
                Total_Count.setText(Total_Count.getText() + String.valueOf(i));
                Toast.makeText(AdminInitialActivity.this, "Total RC count :"+i, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        adapter = new MyAdapter(this, listItems);
        adapter1 = new MyAdapter(this, listItems1);
        //adapter2 = new MyAdapter(this, RC_list);





    }
}
