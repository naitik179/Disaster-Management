package com.example.disaster_management_v2;

import Adapter.ContactAdapter;
import Model.Contacts;
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

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class Fire_brigade_activity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private SearchView searchView;
    private ListView firebrigadelist;
    private ContactAdapter contactAdapter;
    private ArrayList<Contacts> contactsArrayList;


    public static int countimage;

   /* public int[] image={R.drawable.contact,R.drawable.contact,R.drawable.contact,R.drawable.contact,R.drawable.contact,
            R.drawable.contact,R.drawable.contact,R.drawable.contact,R.drawable.contact,R.drawable.contact};*//*

    String[] fire_stn_names={"Goregaon","Vashi","Thane","Andheri","Dadar","Vidyavihar","Ghatkoper","Kandivali","CSMT","Borivali"};
    String[] fire_stn_mob={"+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094"};

*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_brigade_activity);
        searchView=(SearchView)findViewById(R.id.firestationsearch);
        firebrigadelist=findViewById(R.id.fire_station_list_view);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        toolbar.setTitle("Disaster Management App");
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_fire);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
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


//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_fire);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//
//
//        } else {
//            super.onBackPressed();
//
//        }
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//
//
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        searchView.setClickable(false);
//        firebrigadelist.setItemsCanFocus(false);
//
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        searchView.setVisibility(View.INVISIBLE);
//        firebrigadelist.setVisibility(View.INVISIBLE);
//        int id = item.getItemId();
//
//        Fragment fragment=null;
//
//        if (id == R.id.registerinmates) {
//            fragment=new RegisterNewInmates();
//        } else if (id == R.id.applyreliefmaterial) {
//            fragment=new requirements();
//        } else if (id == R.id.reqstatus) {
//            fragment=new RequirementStatus();
//        } else if (id == R.id.viewreliefcampstatus) {
//            fragment=new ReliefCampStatus();
//        } else if (id == R.id.contactpeeradmins) {
//            fragment=new contact_peer_admins();
//        } else if (id == R.id.Emergencycontacts) {
//            fragment=new Emergency_Contacts();
//        }
//        else if (id==R.id.logout){
//            Intent toLogin = new Intent(Fire_brigade_activity.this,LoginActivity.class);
//            startActivity(toLogin);
//        }
//
//        if(fragment!=null)
//        {
//            FragmentManager fragmentManager=getSupportFragmentManager();
//            FragmentTransaction ft=fragmentManager.beginTransaction();
//
//            ft.replace(R.id.screen_area,fragment);
//            ft.commit();
//
//        }

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_fire);
//        drawer.closeDrawer(GravityCompat.START);



}

