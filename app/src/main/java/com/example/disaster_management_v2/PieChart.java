package com.example.disaster_management_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PieChart extends AppCompatActivity {
    Button a,g,f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        a=findViewById(R.id.age);
        g=findViewById(R.id.gender);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PieChart.this, Analyze.class);
                startActivity(i);
            }
        });
        g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(PieChart.this, Analyze1.class);
                startActivity(i1);
            }
        });

    }
}