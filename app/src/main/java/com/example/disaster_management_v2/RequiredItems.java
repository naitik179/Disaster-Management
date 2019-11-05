package com.example.disaster_management_v2;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class RequiredItems extends AppCompatActivity {
    CardView water,toiletries,medicines,food,clothes;
    Fragment fragment=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_required_items);
       water=findViewById(R.id.item5);
       toiletries=findViewById(R.id.item4);
       medicines=findViewById(R.id.item3);
       food=findViewById(R.id.item1);
       clothes=findViewById(R.id.item2);
       toiletries.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i=new Intent(RequiredItems.this,ToiletriesRequirements.class);
               startActivity(i);
           }
       });
       water.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i=new Intent(RequiredItems.this,WaterRequirement.class);
               startActivity(i);
           }
       });
       medicines.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i=new Intent(RequiredItems.this,MedicineRequirements.class);
               startActivity(i);
           }
       });
       food.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i=new Intent(RequiredItems.this,FoodRequirements.class);
               startActivity(i);
           }
       });
       clothes.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i=new Intent(RequiredItems.this,ClothesRequirements.class);
               startActivity(i);
           }
       });

    }
}
