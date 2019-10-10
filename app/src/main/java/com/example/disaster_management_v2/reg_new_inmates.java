package com.example.disaster_management_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class reg_new_inmates extends AppCompatActivity {


    public EditText name,afname,aadhar,mobile,gender;

    EditText totcount;

    EditText namea[]=new EditText[10];
    EditText agea[]=new EditText[10];
    EditText gendera[]=new EditText[10];
    int count;
    public LinearLayout myLayout;
    public Button add,savebtn;
    DatabaseReference mref,mreg;
    FirebaseAuth mauth;
    long maxid;

    String n,a,ph,g;

    private static final String TAG = "info" ;
    final int c=3;
    List<EditText> allEds = new ArrayList<EditText>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_new_inmates);

        add= findViewById(R.id.addbtn);
        savebtn=findViewById(R.id.save);
        mref= FirebaseDatabase.getInstance().getReference().child("Affected_People");

        afname=findViewById(R.id.affectname);
        aadhar=findViewById(R.id.aadhar);
        mobile=findViewById(R.id.mobile);
        gender=findViewById(R.id.gender_new_inmate);
        mreg=FirebaseDatabase.getInstance().getReference().child("Affected_People").child(mauth.getInstance().getCurrentUser().getUid()).child(aadhar.getText().toString()).child("OtherFamilyMembers");
        mauth=FirebaseAuth.getInstance();
        n=afname.getText().toString();
        a=aadhar.getText().toString();
        ph=mobile.getText().toString();
        g=gender.getText().toString();
        totcount=findViewById(R.id.totaleditreq);

        myLayout = (LinearLayout)findViewById(R.id.myLayout);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                mreg.addValueEventListener(new ValueEventListener() {


                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists())
                        {
                            maxid=dataSnapshot.getChildrenCount();
                            Log.i(TAG,"on save btn clicked "+maxid);

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


                count=Integer.valueOf(totcount.getText().toString());



                for (int i = 0; i < count; i++) {
                    namea[i] = new EditText(reg_new_inmates.this);
                    gendera[i] = new EditText(reg_new_inmates.this);
                    agea[i] = new EditText(reg_new_inmates.this);
                    myLayout=(LinearLayout) findViewById(R.id.myLayout);
                    namea[i].setHint("Enter the name of the family member");

                    gendera[i].setHint("Enter Gender of the Members");
                    agea[i].setHint("Enter the age");
                    namea[i].setId(i);
                    gendera[i].setId(i);
                    agea[i].setId(i);




                    LinearLayout.LayoutParams abc = new LinearLayout.LayoutParams(

                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );

                    myLayout.addView(namea[i], abc);
                    myLayout.addView(gendera[i], abc);
                    myLayout.addView(agea[i], abc);

                    //mref.child(mauth.getInstance().getCurrentUser().getUid()).child(aadhar.getText().toString()).child("Name").setValue(namea[i].getText().toString());
                   // mref.child(mauth.getInstance().getCurrentUser().getUid()).child(aadhar.getText().toString()).child("PhoneNo").setValue(gendera[i].getText().toString());
                   // mref.child(mauth.getInstance().getCurrentUser().getUid()).child(aadhar.getText().toString()).child("Age").setValue(agea[i].getText().toString());





                }


                mref.child(mauth.getInstance().getCurrentUser().getUid()).child(aadhar.getText().toString()).child("Name").setValue(afname.getText().toString());
                mref.child(mauth.getInstance().getCurrentUser().getUid()).child(aadhar.getText().toString()).child("Gender").setValue(gender.getText().toString());
                mref.child(mauth.getInstance().getCurrentUser().getUid()).child(aadhar.getText().toString()).child("PhoneNo").setValue(mobile.getText().toString());





            }
        });
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





                for (int i = 0; i < count; i++) {

                    mref.child(mauth.getInstance().getCurrentUser().getUid()).child(aadhar.getText().toString()).child("Other Family Members").child(String.valueOf(i)).child("Name").setValue(namea[i].getText().toString());
                    mref.child(mauth.getInstance().getCurrentUser().getUid()).child(aadhar.getText().toString()).child("Other Family Members").child(String.valueOf(i)).child("Gender").setValue(gendera[i].getText().toString());
                    mref.child(mauth.getInstance().getCurrentUser().getUid()).child(aadhar.getText().toString()).child("Other Family Members").child(String.valueOf(i)).child("Age").setValue(agea[i].getText().toString());

                }


                Intent i=new Intent(reg_new_inmates.this,MainActivity.class);
                startActivity(i);



            }
        });
    }
}
