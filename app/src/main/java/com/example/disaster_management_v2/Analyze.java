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
public class Analyze extends AppCompatActivity {
    DatabaseReference mref;
    FirebaseAuth mauth;
    //int countm=0f,countf=0f,counto=0f;
    Object gen;
    String g;
    Object age;
    String a;
    int ag;
    long maxid;
    float countchild=0f,countteen=0f,countman=0f,countsenior=0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyze);
        final PieChart pieChart = findViewById(R.id.piechart1);
        pieChart.setUsePercentValues(true);

        mauth= FirebaseAuth.getInstance();
        mref= FirebaseDatabase.getInstance().getReference().child("Affected_People").child(mauth.getInstance().getCurrentUser().getUid());

        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<PieEntry> value = new ArrayList<>();
                if(dataSnapshot.hasChildren()){
                    for(DataSnapshot myDataSnapshot : dataSnapshot.getChildren()){
                        // gen=myDataSnapshot.child("Gender").getValue
                        maxid = myDataSnapshot.child("Other Family Members").getChildrenCount();
                        for(long i=0;i<maxid;i++) {
                            age = myDataSnapshot.child("Other Family Members").child(String.valueOf(i)).child("Age").getValue();

                            a = String.valueOf(age);
                            ag = Integer.parseInt(a);
                            if (ag >= 0 && ag <= 12)
                                countchild = countchild + 1;
                            else if (ag > 12 && ag <= 21)
                                countteen++;
                            else if (ag > 21 && ag < 60)
                                countman++;
                            else
                                countsenior++;
                        }

                    }
                }
                Toast.makeText(Analyze.this,"Male:"+countman,Toast.LENGTH_SHORT).show();
                // Toast.makeText(Analyze.this,"Female:"+countf,Toast.LENGTH_SHORT).show();
                value.add(new PieEntry(countchild,"Children"));
                value.add(new PieEntry(countman,"Man"));
                value.add(new PieEntry(countteen,"Teenager"));
                value.add(new PieEntry(countsenior,"Senior"));
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