package com.example.disaster_management_v2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class requirements extends Fragment {

    EditText quantity,otherReq;
    FirebaseAuth mFirebaseAuth;
    private DatabaseReference mReg,mref,mat,clothes,medicine,water,food;
    Button updateReq;
    Object no;
    FirebaseUser user;
    String s,n;
    long maxid;
    int qua,q,flag=0;
    String Q;
    private Button clothes_button;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.requirements,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.requirementsspinner);
        quantity=view.findViewById(R.id.quantity);
        otherReq=view.findViewById(R.id.otherreq);
        mFirebaseAuth=FirebaseAuth.getInstance();
        mReg= FirebaseDatabase.getInstance().getReference();
        mref=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Other Requirements").child(mFirebaseAuth.getInstance().getCurrentUser().getUid()).child("Requirements");

        updateReq=view.findViewById(R.id.requirementssend);
        Spinner spinner = (Spinner) view.findViewById(R.id.requirementsspinner);

        //added Clothes form (Priya) (if there are any issues
        clothes_button = (Button)view.findViewById(R.id.apply_clothes);

        clothes_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(requirements.this.getActivity(), clothes_form.class);
                startActivity(intent);
            }
        });
        //


// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.Requirementsspinnervalues, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    maxid=(dataSnapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Choose From Below")) {
                    //doing nothing
                } else {
                    s = parent.getItemAtPosition(position).toString();
                    if (s.compareTo("Clothes")==0)
                        mat=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Clothes").child(mFirebaseAuth.getInstance().getCurrentUser().getUid());
                    else if(s.compareTo("Medicines")==0)
                        mat=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Medicines").child(mFirebaseAuth.getInstance().getCurrentUser().getUid());
                    else if (s.compareTo("Drinking Water")==0)
                        mat=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Drinking Water").child(mFirebaseAuth.getInstance().getCurrentUser().getUid());
                    else if (s.compareTo("Food")==0)
                        mat=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Food").child(mFirebaseAuth.getInstance().getCurrentUser().getUid());
                    Toast.makeText(parent.getContext(), "You selected " + s, Toast.LENGTH_LONG).show();

                    mat.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot ds) {

                            no = ds.child("quantity").getValue();
                            n = String.valueOf(no);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //TODO AUTOGENERATED METHOD STUB
            }
        });

        updateReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q=Integer.parseInt(quantity.getText().toString());
                if(n=="null") {
                    mReg.child("Material_Application").child(s).child(mFirebaseAuth.getInstance().getCurrentUser().getUid()).child("quantity").setValue(Integer.parseInt(quantity.getText().toString()));
                    if(otherReq.getText().toString()==""){

                    }
                    else
                        mReg.child("Material_Application").child("Other Requirements").child(mFirebaseAuth.getInstance().getCurrentUser().getUid()).child("Requirements").child(String.valueOf(maxid + 2)).setValue(otherReq.getText().toString());
                }
                else {
                    qua = Integer.parseInt(n) + q;
                    mReg.child("Material_Application").child(s).child(mFirebaseAuth.getInstance().getCurrentUser().getUid()).child("quantity").setValue(qua);
                    if(otherReq.getText().toString()==""){}
                    else
                        mReg.child("Material_Application").child("Other Requirements").child(mFirebaseAuth.getInstance().getCurrentUser().getUid()).child("Requirements").child(String.valueOf(maxid + 2)).setValue(otherReq.getText().toString());
                }

                //Toast.makeText(,"Succesfully applied for Materials. Contact Admin for further details.",Toast.LENGTH_SHORT).show();
            }
        });
    }


}