package com.example.disaster_management_v2;

import Adapter.ContactAdapter;
import Model.Contacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class Fire_brigade_activity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private SearchView searchView;
    private ListView firebrigadelist;
    private ContactAdapter contactAdapter;
    private ArrayList<Contacts> contactsArrayList;


    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;

    public static int countimage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_brigade_activity);
        searchView=(SearchView)findViewById(R.id.firestationsearch);

        mDatabase = FirebaseDatabase.getInstance();
        mRef=mDatabase.getReference("Emergency Contacts").child("Fire Station");

        firebrigadelist=findViewById(R.id.fire_station_list_view);

        contactsArrayList=new ArrayList<Contacts>();


        mRef.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshots: dataSnapshot.getChildren()) {
                    try {
                        Map<Object, String> data = (Map<Object, String>) snapshots.getValue();

                        contactsArrayList.add(new Contacts(data.get("Name"),data.get("Contact")));


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


        contactAdapter=new ContactAdapter(Fire_brigade_activity.this, contactsArrayList);
        firebrigadelist.setAdapter(contactAdapter);

        firebrigadelist.setTextFilterEnabled(true);
        setupSearchView();

    }

    private void setupSearchView()
    {
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
            firebrigadelist.clearTextFilter();
        } else {
            firebrigadelist.setFilterText(newText);
        }
        return true;
    }






}

