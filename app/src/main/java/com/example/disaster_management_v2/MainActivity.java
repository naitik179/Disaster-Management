package com.example.disaster_management_v2;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
//        tx.replace(R.id.nav_host_fragment, new RC_dashboard());
//        tx.commit();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Disaster Management App");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment=null;

        if (id == R.id.registerinmates) {
            Intent i = new Intent(MainActivity.this,reg_new_inmates.class);
            startActivity(i);

        }
        else if(id == R.id.home){
//            fragment = new RC_dashboard();
        }


        else if (id == R.id.applyreliefmaterial) {
            fragment=new RequirementLayout();
        } else if (id == R.id.reqstatus) {
            fragment=new RequirementStatus();
        } else if (id == R.id.viewreliefcampstatus) {
           // fragment=new ReliefCampStatus();
            Intent i1= new Intent(MainActivity.this,PieChart.class);
            startActivity(i1);
        } else if (id == R.id.contactpeeradmins) {
            //fragment=new contact_peer_admins();
            Intent i=new Intent(MainActivity.this,Contact_Peer_Admins_Activity.class);
            startActivity(i);
        } else if (id == R.id.Emergencycontacts) {
            fragment=new Emergency_Contacts();
        }
        else if (id==R.id.logout){
               FirebaseAuth.getInstance().signOut();
               Intent itomain=new Intent(MainActivity.this, LoginActivity.class);
               startActivity(itomain);
        }
        else{
//            fragment = new RC_dashboard();
        }

        if(fragment!=null)
        {
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction ft=fragmentManager.beginTransaction();

            ft.replace(R.id.nav_host_fragment,fragment);
            ft.commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
