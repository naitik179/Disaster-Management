package com.example.disaster_management_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;


public class Analyze1 extends AppCompatActivity {
    FirebaseAuth mauth;
    DatabaseReference mref;
    float countm=0f,countf=0f,counto=0f;
    Object gen,gen1;
    String g,g1;
    long maxid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyze1);
        final PieChart pieChart = findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);
        mauth= FirebaseAuth.getInstance();
        mref= FirebaseDatabase.getInstance().getReference().child("Affected_People").child(mauth.getInstance().getCurrentUser().getUid());

        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<PieEntry> value = new ArrayList<>();
                if(dataSnapshot.hasChildren()){
                    for(DataSnapshot myDataSnapshot : dataSnapshot.getChildren()){
                        gen=myDataSnapshot.child("Gender").getValue();
                        maxid=myDataSnapshot.child("Other Family Members").getChildrenCount();
                        g=String.valueOf(gen);
                        if(g.compareTo("Male")==0)
                            countm=countm+1;
                        else if(g.compareTo("Female")==0)
                            countf++;
                        else
                            counto++;

                        for(long i=0;i<maxid;i++)
                        {
                            gen1=myDataSnapshot.child("Other Family Members").child(String.valueOf(i)).child("Gender").getValue();
                            g1=String.valueOf(gen1);
                            if(g1.compareTo("Male")==0)
                                countm=countm+1;
                            else if (g1.compareTo("Female")==0)
                                countf=countf+1;
                            else
                                counto=counto+1;
                        }
                    }
                }
                Toast.makeText(Analyze1.this,"Male:"+countm,Toast.LENGTH_SHORT).show();
                Toast.makeText(Analyze1.this,"Female:"+countf,Toast.LENGTH_SHORT).show();
                value.add(new PieEntry(countm,"Male"));
                value.add(new PieEntry(countf,"Female"));
                value.add(new PieEntry(counto,"Others"));


                PieDataSet pieDataSet = new PieDataSet(value,"Gender");
                PieData pieData = new PieData(pieDataSet);
                pieChart.setData(pieData);
                pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}