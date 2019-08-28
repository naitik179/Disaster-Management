package com.example.disaster_management_v2;

import android.app.LauncherActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;


import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import Adapter.MyAdapter;
import Model.ListItem;

public class AdminInitialActivity extends AppCompatActivity {
    private RecyclerView  recyclerView ;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initialadminactivity);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewID);
         recyclerView.setHasFixedSize(true);
        //every item has a fixed size
        recyclerView.setLayoutManager(new
                LinearLayoutManager(this));

        listItems = new ArrayList<>();

        for (int i = 0; i<10; i++) {
            ListItem listItem = new ListItem(
                    "Relief centre " + (i+1),
                    "City "
            );
            listItems.add(listItem);
        }

        adapter = new MyAdapter(this, listItems);

        recyclerView .setAdapter(adapter);

    }
}
