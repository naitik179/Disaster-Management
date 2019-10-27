package com.example.disaster_management_v2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class food_status extends Fragment {
    TextView q1,q2,q3,q4,q5,dis1,dis2,dis3,dis4,dis5;
    String n1s,n2s,n3s,n4s,n5s;
    int s1,s2,s3,s4,s5;
    Object n1,n2,n3,n4,n5;
    int sa1,sa2,sa3,sa4,sa5;

    FirebaseAuth mFirebaseAuth;
    private DatabaseReference mReg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_food_status,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        q1 = view.findViewById(R.id.dal);
        dis1 = view.findViewById(R.id.dalQ);

        q2 = view.findViewById(R.id.milk);
        dis2 = view.findViewById(R.id.milkQ);

        q3 = view.findViewById(R.id.rice);
        dis3 = view.findViewById(R.id.riceQ);

        q4 = view.findViewById(R.id.tea);
        dis4 = view.findViewById(R.id.teaQ);

        q5 = view.findViewById(R.id.wheat);
        dis5 = view.findViewById(R.id.wheatQ);

        mFirebaseAuth=FirebaseAuth.getInstance();
        mReg= FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Food").child(mFirebaseAuth.getInstance().getCurrentUser().getUid());


        mReg.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // c = ds.child("Cholera").child("quantity").getValue();

                n1=dataSnapshot.child("Dal_Quantity").getValue();
                n2=dataSnapshot.child("Milk_Quantity").getValue();
                n3=dataSnapshot.child("Rice_Quantity").getValue();
                n4=dataSnapshot.child("Tea_Quantity").getValue();
                n5=dataSnapshot.child("Wheat_Quantity").getValue();

                n1s=String.valueOf(n1);
                if (n1s.isEmpty())
                    sa1 = 0;
                else
                {
                    try {
                        sa1=Integer.parseInt(n1s);
                    }
                    catch (NumberFormatException ex)
                    {

                    }
                }


                dis1.setText("Quantity = " + sa1);

                n2s=String.valueOf(n2);
                if (n2s.isEmpty())
                    sa2 = 0;
                else
                {
                    try {
                        sa2=Integer.parseInt(n2s);
                    }
                    catch (NumberFormatException ex)
                    {

                    }
                }


                dis2.setText("Quantity = " + sa2);

                n3s=String.valueOf(n3);
                if (n3s.isEmpty())
                    sa3 = 0;
                else
                {
                    try {
                        sa3=Integer.parseInt(n3s);

                    }
                    catch(NumberFormatException ex)
                    {

                    }
                }

                dis3.setText("Quantity = " + sa3);

                n4s=String.valueOf(n4);
                if (n4s.isEmpty())
                    sa4 = 0;
                else
                {
                    try {
                        sa4=Integer.parseInt(n4s);

                    }
                    catch (NumberFormatException ex){

                    }
                }

                dis4.setText("Quantity = " + sa4);

                n5s=String.valueOf(n5);
                if (n5s.isEmpty())
                    sa5 = 0;
                else
                {
                    try {
                        sa5=Integer.parseInt(n5s);
                    }
                    catch (NumberFormatException ex){

                    }
                }

                dis5.setText("Quantity = " + sa5);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}

