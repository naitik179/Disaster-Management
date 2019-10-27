package com.example.disaster_management_v2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
public class Analyze extends Fragment{
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


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_food_,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final PieChart pieChart = view.findViewById(R.id.piechart1);
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
                Toast.makeText(getContext(),"Male:"+countman,Toast.LENGTH_SHORT).show();
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