package com.example.disaster_management_v2;

import Adapter.ContactAdapter;
import Model.Contacts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.firebase.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.Map;

public class Hospital_activity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    ListView hospitallist;
    private SearchView searchView;
    private ContactAdapter contactAdapter;
    private ArrayList<Contacts> contactsArrayList;


    //7-9-19


    FirebaseAuth mFirebaseAuth;
    private DatabaseReference mReg;
    Contacts contact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_activity);
        searchView=(SearchView)findViewById(R.id.hospitalsearch);


        hospitallist=findViewById(R.id.hospital_list_view);


        mFirebaseAuth=FirebaseAuth.getInstance();

        mReg= FirebaseDatabase.getInstance().getReference().child("Emergency Contacts").child("Hospital");
        //contact=new Contacts();

        contactsArrayList=new ArrayList<Contacts>();

        hospitallist.setAdapter(contactAdapter);

        hospitallist.setTextFilterEnabled(true);
        setupSearchView();



        //7-9-19
        contactAdapter=new ContactAdapter(Hospital_activity.this,contactsArrayList);

        mReg.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    //contact=ds.getValue(Contacts.class);
//                    contactsArrayList.add(contact.getName().toString());
//                    contactsArrayList.add(contact.getContact().toString());
                      Map<Object, String> data = (Map<Object, String>) ds.getValue();


                    contactsArrayList.add(new Contacts(data.get("Name"),data.get("Contact")));

                }
                hospitallist.setAdapter(contactAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        contactAdapter=new ContactAdapter(Hospital_activity.this, contactsArrayList);
        hospitallist.setAdapter(contactAdapter);

        hospitallist.setTextFilterEnabled(true);
        setupSearchView();

        //

    }

    private void setupSearchView() {
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("Search Here");
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            hospitallist.clearTextFilter();
        } else {
            hospitallist.setFilterText(newText);
        }
        return true;
    }

}


