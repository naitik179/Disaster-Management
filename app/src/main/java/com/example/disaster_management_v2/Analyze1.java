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


public class Analyze1 extends Fragment {
    FirebaseAuth mauth;
    DatabaseReference mref;
    float countm=0f,countf=0f,counto=0f;
    Object gen,gen1;
    String g,g1;
    long maxid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_analyze1,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final PieChart pieChart = view.findViewById(R.id.piechart);
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
                Toast.makeText(getContext(),"Male:"+countm,Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(),"Female:"+countf,Toast.LENGTH_SHORT).show();
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