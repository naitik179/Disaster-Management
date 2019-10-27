package com.example.disaster_management_v2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class water_status extends Fragment {
    TextView q1,dis;
    String n1s;
    int s1;
    Object n1;
    int sa;

    FirebaseAuth mFirebaseAuth;
    private DatabaseReference mReg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_water_status,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        q1 = view.findViewById(R.id.waterQuantity);
        dis = view.findViewById(R.id.display);

        mFirebaseAuth=FirebaseAuth.getInstance();
        mReg= FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Water").child(mFirebaseAuth.getInstance().getCurrentUser().getUid());


        mReg.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                n1=dataSnapshot.child("quantity").getValue();
                n1s=String.valueOf(n1);
                if (n1s.isEmpty())
                    sa = 0;
                else
                {
                    try {
                        sa=Integer.parseInt(n1s);
                    }
                    catch (NumberFormatException ex){

                    }
                }

                dis.setText("Quantity = " + sa);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}


