package com.example.disaster_management_v2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import androidx.fragment.app.Fragment;

public class Contact_Peer_Admins_Activity extends Fragment implements SearchView.OnQueryTextListener {

    ListView contactlist;
    private SearchView searchView;
    private ContactAdapter contactAdapter;
    private ArrayList<Contacts> contactsArrayList;


    //7-9-19


    FirebaseAuth mFirebaseAuth;
    private DatabaseReference mReg;
    Contacts contact;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_contact__peer__admins_,null);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchView=(SearchView)view.findViewById(R.id.contactsearch);


        contactlist=view.findViewById(R.id.contact_list_view);


        mFirebaseAuth=FirebaseAuth.getInstance();

        mReg= FirebaseDatabase.getInstance().getReference().child("Sub Admin Registration");
        //contact=new Contacts();

        contactsArrayList=new ArrayList<Contacts>();

        contactlist.setAdapter(contactAdapter);

        contactlist.setTextFilterEnabled(true);
        setupSearchView();



        //7-9-19
        contactAdapter=new ContactAdapter(getContext(),contactsArrayList);

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

                        contactsArrayList.add(new Contacts(String.valueOf(ds.child("Name of Relief Center").getValue()),String.valueOf(ds.child("Phone No").getValue())));


                    }

                }
            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        contactAdapter=new ContactAdapter(getContext(), contactsArrayList);
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


