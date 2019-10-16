package com.example.disaster_management_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

import Adapter.ContactAdapter;
import Model.Contacts;

public class Contact_Peer_Admins_Activity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    ListView contactlist;
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
        setContentView(R.layout.activity_contact__peer__admins_);
        searchView=(SearchView)findViewById(R.id.contactsearch);


        contactlist=findViewById(R.id.contact_list_view);


        mFirebaseAuth=FirebaseAuth.getInstance();

        mReg= FirebaseDatabase.getInstance().getReference().child("Sub Admin Registration");
        //contact=new Contacts();

        contactsArrayList=new ArrayList<Contacts>();

        contactlist.setAdapter(contactAdapter);

        contactlist.setTextFilterEnabled(true);
        setupSearchView();



        //7-9-19
        contactAdapter=new ContactAdapter(Contact_Peer_Admins_Activity.this,contactsArrayList);

        mReg.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    //contact=ds.getValue(Contacts.class);
//                    contactsArrayList.add(contact.getName().toString());
//                    contactsArrayList.add(contact.getContact().toString());
                    Map<Object, String> data = (Map<Object, String>) ds.getValue();


                    contactsArrayList.add(new Contacts(data.get("Email id"),data.get("Phone No")));

                }
                contactlist.setAdapter(contactAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        contactAdapter=new ContactAdapter(Contact_Peer_Admins_Activity
                .this, contactsArrayList);
        contactlist.setAdapter(contactAdapter);

        contactlist.setTextFilterEnabled(true);
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
            contactlist.clearTextFilter();
        } else {
            contactlist.setFilterText(newText);
        }
        return true;
    }

}
