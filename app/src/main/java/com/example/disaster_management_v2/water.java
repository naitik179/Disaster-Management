package com.example.disaster_management_v2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class water extends Fragment {

    EditText q1;
    Button tt;
    String n1s;
    int s1;
    Object n1;
    int sa;

    FirebaseAuth mFirebaseAuth;
    private DatabaseReference mReg,md1,need1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_water,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        q1 = view.findViewById(R.id.waterQuantity);
        tt=view.findViewById(R.id.applyWater);


        mFirebaseAuth=FirebaseAuth.getInstance();
        mReg= FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Water").child(mFirebaseAuth.getInstance().getCurrentUser().getUid());

        md1=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Water").child(mFirebaseAuth.getInstance().getCurrentUser().getUid());

        String mdd1=md1.toString();
        tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    s1 = q1.getText().toString();
                //   mReg.child("quantity").setValue(s1);

                if(q1.getText().toString().isEmpty())
                {
                    q1.setError("Enter Quantity in Litres required for Water");
                    q1.requestFocus();

                }
                else {


                    s1 = Integer.parseInt(q1.getText().toString());

                    mReg.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            try {
                                n1 = dataSnapshot.child("quantity").getValue();
                                n1s = String.valueOf(n1);
                                sa = Integer.parseInt(n1s);

                                int s1Total = s1 + sa;
                                Log.i("Info", "onDataChange: Water="+s1Total);
                                mReg.child("quantity").setValue(s1Total);
                            }catch(Exception e){}
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                    Toast.makeText(getContext(),"Applied Successfully",Toast.LENGTH_SHORT).show();
                    Fragment fragment = new RequirementLayout();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(getId(), fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                }


            }
        });
    }
    }

