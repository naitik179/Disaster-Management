package com.example.disaster_management_v2;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;

public class Registration_activity extends AppCompatActivity  {

    Button button1,button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_option);


        button1=findViewById(R.id.registerHelper);
        button2=findViewById(R.id.registerReliefCentre);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transferToHelperRegister = new Intent(Registration_activity.this, HelperRegistrationActivity.class);
                startActivity(transferToHelperRegister);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transferToReliefCentreRegister = new Intent(Registration_activity.this, ReliefCentreRegisterActivity.class);
                startActivity(transferToReliefCentreRegister);
            }
        });



    }


}
