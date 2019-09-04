package com.example.disaster_management_v2;

import Adapter.ContactAdapter;
import Model.Contacts;
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

import java.util.ArrayList;

public class Hospital_activity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    ListView hospitallist;
    private SearchView searchView;
    private ContactAdapter contactAdapter;
    private ArrayList<Contacts> contactsArrayList;

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
        setContentView(R.layout.activity_hospital_activity);
        searchView=(SearchView)findViewById(R.id.hospitalsearch);


        hospitallist=findViewById(R.id.hospital_list_view);

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

        contactAdapter=new ContactAdapter(Hospital_activity.this, contactsArrayList);
        hospitallist.setAdapter(contactAdapter);

        hospitallist.setTextFilterEnabled(true);
        setupSearchView();

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


