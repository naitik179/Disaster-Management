package com.example.disaster_management_v2;

import Adapter.ContactAdapter;
import Model.Contacts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Relief_Centre_List_Activity extends AppCompatActivity {

    ListView contactlist;
    private ContactAdapter contactAdapter;
    private ArrayList<Contacts> contactsArrayList;

    FirebaseAuth mFirebaseAuth;
    private DatabaseReference mReg;
    Contacts contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__peer__admins_);
        contactlist=findViewById(R.id.list_view_RC);


        mFirebaseAuth=FirebaseAuth.getInstance();

        mReg= FirebaseDatabase.getInstance().getReference().child("Sub Admin Registration");

        contactsArrayList=new ArrayList<Contacts>();

        contactlist.setAdapter(contactAdapter);


        contactAdapter=new ContactAdapter(this,contactsArrayList);

        mReg.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot date : dataSnapshot.getChildren()) {


                    for (DataSnapshot ds : date.getChildren()) {
                        //contact=ds.getValue(Contacts.class);
//                    contactsArrayList.add(contact.getName().toString());
//                    contactsArrayList.add(contact.getContact().toString());

                        //Map<Object, String> data = (Map<Object, String>) ds.getValue();

                        //contactsArrayList.add(new Contacts(data.get("Email id"), data.get("Pincode"), data.get("Name of Relief Center"), data.get("Phone No")));

                       // contactsArrayList.add(new Contacts(String.valueOf(ds.child("Email id").getValue()),String.valueOf(ds.child("Pincode").getValue()),String.valueOf(ds.child("Name of Relief Center").getValue()),String.valueOf(ds.child("Phone No").getValue())));


                    }

                }
            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        contactAdapter=new ContactAdapter(this, contactsArrayList);
        contactlist.setAdapter(contactAdapter);
    }
}
