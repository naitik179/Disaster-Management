//package com.example.disaster_management_v2;
//
//import Adapter.ContactAdapter;
//import Model.Contacts;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.SearchView;
//
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.ListView;
//import android.widget.TextView;
//
//
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//
//public class Police_stn_activity extends AppCompatActivity implements SearchView.OnQueryTextListener {
//
////    ListView policelist;
////    private SearchView searchView;
////    private ContactAdapter contactAdapter;
////    private ArrayList<Contacts> contactsArrayList;
////    /*int countimage;
////
////    int[] image={R.drawable.ic_contact_phone_black_24dp,R.drawable.ic_contact_phone_black_24dp,R.drawable.ic_contact_phone_black_24dp,R.drawable.ic_contact_phone_black_24dp,R.drawable.ic_contact_phone_black_24dp,
////            R.drawable.ic_contact_phone_black_24dp,R.drawable.ic_contact_phone_black_24dp,R.drawable.ic_contact_phone_black_24dp,R.drawable.ic_contact_phone_black_24dp,R.drawable.ic_contact_phone_black_24dp};
////
////    String[] pol_names={"Goregaon","Vashi","Thane","Andheri","Dadar","Vidyavihar","Ghatkoper","Kandivali","CSMT","Borivali"};
////    String[] pol_mob={"+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094"};
////*/
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_police_stn_activity);
////
////        searchView=(SearchView)findViewById(R.id.policestnsearch);
////        policelist=findViewById(R.id.police_list_view);
////
////        //countimage=image.length;
////
////        contactsArrayList=new ArrayList<Contacts>();
////        contactsArrayList.add(new Contacts("Goregaon","+91-7045750094"));
////        contactsArrayList.add(new Contacts("Vashi","+91-7045750094"));
////        contactsArrayList.add(new Contacts("Thane","+91-7045750094"));
////        contactsArrayList.add(new Contacts("Andheri","+91-7045750094"));
////        contactsArrayList.add(new Contacts("Dadar","+91-7045750094"));
////        contactsArrayList.add(new Contacts("Vidyavihar","+91-7045750094"));
////        contactsArrayList.add(new Contacts("Ghatkopar","+91-7045750094"));
////        contactsArrayList.add(new Contacts("Kandivali","+91-7045750094"));
////        contactsArrayList.add(new Contacts("CSMT","+91-7045750094"));
////        contactsArrayList.add(new Contacts("Borivali","+91-7045750094"));
////
////        contactAdapter=new ContactAdapter(Police_stn_activity.this, contactsArrayList);
////        policelist.setAdapter(contactAdapter);
////
////        policelist.setTextFilterEnabled(true);
////        setupSearchView();
////
////    }
////
////    private void setupSearchView() {
////        searchView.setIconifiedByDefault(false);
////        searchView.setOnQueryTextListener(this);
////        searchView.setSubmitButtonEnabled(true);
////        searchView.setQueryHint("Search Here");
////    }
////
////    @Override
////    public boolean onQueryTextSubmit(String query) {
////        return false;
////    }
////
////    @Override
////    public boolean onQueryTextChange(String newText) {
////        if (TextUtils.isEmpty(newText)) {
////            policelist.clearTextFilter();
////        } else {
////            policelist.setFilterText(newText);
////        }
////        return true;
////    }
//
//
//    ListView hospitallist;
//    private android.widget.SearchView searchView;
//    private ContactAdapter contactAdapter;
//    private ArrayList<Contacts> contactsArrayList;
//
//
//    //7-9-19
//
//
//    FirebaseAuth mFirebaseAuth;
//    private DatabaseReference mReg;
//    Contacts contact;
//
//
//
//    //7-9-19
//
//    //int countimage;
//
//    /* int[] image={R.drawable.ic_contact_phone_black_24dp,R.drawable.ic_contact_phone_black_24dp,R.drawable.ic_contact_phone_black_24dp,R.drawable.ic_contact_phone_black_24dp,R.drawable.ic_contact_phone_black_24dp,
//             R.drawable.ic_contact_phone_black_24dp,R.drawable.ic_contact_phone_black_24dp,R.drawable.ic_contact_phone_black_24dp,R.drawable.ic_contact_phone_black_24dp,R.drawable.ic_contact_phone_black_24dp};
// */
//   /* String[] hos_names={"Goregaon","Vashi","Thane","Andheri","Dadar","Vidyavihar","Ghatkoper","Kandivali","CSMT","Borivali"};
//    String[] hos_mob={"+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094"};
//*/
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_hospital_activity);
//        searchView=(android.widget.SearchView)findViewById(R.id.hospitalsearch);
//
//
//        hospitallist=findViewById(R.id.hospital_list_view);
//
//
//        mFirebaseAuth=FirebaseAuth.getInstance();
//
//        mReg= FirebaseDatabase.getInstance().getReference().child("Emergency Contacts").child("Hospital");
//        contact=new Contacts();
//
//
//        //countimage=image.length;
//        contactsArrayList=new ArrayList<Contacts>();
//        contactsArrayList.add(new Contacts("Goregaon","+91-7045750094"));
//        contactsArrayList.add(new Contacts("Vashi","+91-7045750094"));
//        contactsArrayList.add(new Contacts("Thane","+91-7045750094"));
//        contactsArrayList.add(new Contacts("Andheri","+91-7045750094"));
//        contactsArrayList.add(new Contacts("Dadar","+91-7045750094"));
//        contactsArrayList.add(new Contacts("Vidyavihar","+91-7045750094"));
//        contactsArrayList.add(new Contacts("Ghatkopar","+91-7045750094"));
//        contactsArrayList.add(new Contacts("Kandivali","+91-7045750094"));
//        contactsArrayList.add(new Contacts("CSMT","+91-7045750094"));
//        contactsArrayList.add(new Contacts("Borivali","+91-7045750094"));
//
//
//        hospitallist.setAdapter(contactAdapter);
//
//        hospitallist.setTextFilterEnabled(true);
//        setupSearchView();
//
//
//
//        //7-9-19
//        contactAdapter=new ContactAdapter(Police_stn_activity.this,contactsArrayList);
//
//        mReg.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot ds:dataSnapshot.getChildren())
//                {
//                    contact=ds.getValue(Contacts.class);
////                    contactsArrayList.add(contact.getName().toString());
////                    contactsArrayList.add(contact.getContact().toString());
//
//                }
//                hospitallist.setAdapter(contactAdapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//
//
//        //
//
//    }
//
//    private void setupSearchView() {
//        searchView.setIconifiedByDefault(false);
//        searchView.setOnQueryTextListener(this);
//        searchView.setSubmitButtonEnabled(true);
//        searchView.setQueryHint("Search Here");
//    }
//
//    @Override
//    public boolean onQueryTextSubmit(String query) {
//        return false;
//    }
//
//    @Override
//    public boolean onQueryTextChange(String newText) {
//        if (TextUtils.isEmpty(newText)) {
//            hospitallist.clearTextFilter();
//        } else {
//            hospitallist.setFilterText(newText);
//        }
//        return true;
//    }
//
//
//
//
//
//
//}


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

public class Police_stn_activity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    ListView hospitallist;
    private SearchView searchView;
    private ContactAdapter contactAdapter;
    private ArrayList<Contacts> contactsArrayList;


    //7-9-19


    FirebaseAuth mFirebaseAuth;
    private DatabaseReference mReg;
    Contacts contact;



    //7-9-19

    //int countimage;

    /* int[] image={R.drawable.ic_contact_phone_black_24dp,R.drawable.ic_contact_phone_black_24dp,R.drawable.ic_contact_phone_black_24dp,R.drawable.ic_contact_phone_black_24dp,R.drawable.ic_contact_phone_black_24dp,
             R.drawable.ic_contact_phone_black_24dp,R.drawable.ic_contact_phone_black_24dp,R.drawable.ic_contact_phone_black_24dp,R.drawable.ic_contact_phone_black_24dp,R.drawable.ic_contact_phone_black_24dp};
 */
   /* String[] hos_names={"Goregaon","Vashi","Thane","Andheri","Dadar","Vidyavihar","Ghatkoper","Kandivali","CSMT","Borivali"};
    String[] hos_mob={"+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094"};
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_stn_activity);
        searchView=(SearchView)findViewById(R.id.hospitalsearch);


        hospitallist=findViewById(R.id.hospital_list_view);


        mFirebaseAuth=FirebaseAuth.getInstance();

        mReg= FirebaseDatabase.getInstance().getReference().child("Emergency Contacts").child("Hospital");
        contact=new Contacts();


        //countimage=image.length;
        contactsArrayList=new ArrayList<Contacts>();
        contactsArrayList.add(new Contacts("Goregaon","+91-7045750094"));
        contactsArrayList.add(new Contacts("Vashi","+91-7045750094"));
        contactsArrayList.add(new Contacts("Thane","+91-7045750094"));
        contactsArrayList.add(new Contacts("Andheri","+91-7045750094"));
        contactsArrayList.add(new Contacts("Dadar","+91-7045750094"));
        contactsArrayList.add(new Contacts("Vidyavihar","+91-7045750094"));
        contactsArrayList.add(new Contacts("Ghatkopar","+91-7045750094"));
        contactsArrayList.add(new Contacts("Kandivali","+91-7045750094"));
        contactsArrayList.add(new Contacts("CSMT","+91-7045750094"));
        contactsArrayList.add(new Contacts("Borivali","+91-7045750094"));


        hospitallist.setAdapter(contactAdapter);

        hospitallist.setTextFilterEnabled(true);
        setupSearchView();



        //7-9-19
        contactAdapter=new ContactAdapter(Police_stn_activity.this,contactsArrayList);

        mReg.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    contact=ds.getValue(Contacts.class);
//                    contactsArrayList.add(contact.getName().toString());
//                    contactsArrayList.add(contact.getContact().toString());

                }
                hospitallist.setAdapter(contactAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



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



