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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Med extends Fragment {

    CheckBox cb11, cb2, cb3, cb4, cb5;
    LinearLayout myLayout;
    EditText q1, q2, q3, q4, q5;
    String n1s,n2s,n3s,n4s,n5s;
    Button tt;
    int flag1=0,flag2=0,flag3=0,flag4=0,flag5=0;
    int s1,s2,s3,s4,s5,sa,sb,sc,sd,se;
    Object n1,n2,n3,n4,n5;
    int txt1,txt2,txt3,txt4,txt5;
    int s1Total,s2Total,s3Total,s4Total,s5Total;
    int toreq=0;


    FirebaseAuth mFirebaseAuth;
    private DatabaseReference mReg,mreg2,mreg3,mreg4,mreg5,md1,md2,md3,md4,md5,need1,need2,need3,need4,need5;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_med, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cb11 = view.findViewById(R.id.cb1);
        cb2 = view.findViewById(R.id.cb2);
        cb3 = view.findViewById(R.id.cb3);
        cb4 = view.findViewById(R.id.cb4);
        cb5 = view.findViewById(R.id.cb5);
        q1 = view.findViewById(R.id.t1);
        q2 = view.findViewById(R.id.t2);
        q3 = view.findViewById(R.id.t3);
        q4 = view.findViewById(R.id.t4);
        q5 = view.findViewById(R.id.t5);
        tt=view.findViewById(R.id.toiletriesbtn);

        mFirebaseAuth=FirebaseAuth.getInstance();
        mReg=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Med").child(mFirebaseAuth.getInstance().getCurrentUser().getUid()).child("First Aid");
        mreg2=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Med").child(mFirebaseAuth.getInstance().getCurrentUser().getUid()).child("Viral Fever");
        mreg3=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Med").child(mFirebaseAuth.getInstance().getCurrentUser().getUid()).child("Jaundice");
        mreg4=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Med").child(mFirebaseAuth.getInstance().getCurrentUser().getUid()).child("Glucose");
        mreg5=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Med").child(mFirebaseAuth.getInstance().getCurrentUser().getUid()).child("Cold");

        md1=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Med").child(mFirebaseAuth.getInstance().getCurrentUser().getUid());

        String mdd1=md1.toString();

//        Log.i("value ","")
        cb11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag1=(flag1+1)%2;
                if (cb11.isChecked()) {
                    q1.setVisibility(View.VISIBLE);


                    //  need1=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Med").child(mFirebaseAuth.getInstance().getCurrentUser().getUid()).child("First Aid");

//                    if(md1.child("quantity")

                } else {
                    q1.setVisibility(View.INVISIBLE);
                    q1.getText().clear();

                }
            }
        });

        cb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag2=(flag2+1)%2;

                if (cb2.isChecked()) {
                    q2.setVisibility(View.VISIBLE);

                    //need2=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Med").child(mFirebaseAuth.getInstance().getCurrentUser().getUid()).child("Viral Fever");
                } else {
                    q2.setVisibility(View.INVISIBLE);
                    q2.getText().clear();

                }
            }
        });

        cb3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                flag3=(flag3+1)%2;
                if (cb3.isChecked()) {
                    q3.setVisibility(View.VISIBLE);
                    //need3=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Med").child(mFirebaseAuth.getInstance().getCurrentUser().getUid()).child("Jaundice");

                } else {
                    q3.setVisibility(View.INVISIBLE);
                    q3.getText().clear();


                }
            }
        });

        cb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag4=(flag4+1)%2;

                if (cb4.isChecked()) {
                    q4.setVisibility(View.VISIBLE);

                    //need4=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Med").child(mFirebaseAuth.getInstance().getCurrentUser().getUid()).child("Glucose");

                } else {
                    q4.setVisibility(View.INVISIBLE);
                    q4.getText().clear();


                }
            }
        });

        cb5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag5=(flag5+1)%2;

                if (cb5.isChecked()) {
                    q5.setVisibility(View.VISIBLE);
                    // need5=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Med").child(mFirebaseAuth.getInstance().getCurrentUser().getUid()).child("Cold");

                } else {
                    q5.setVisibility(View.INVISIBLE);
                    q5.getText().clear();


                }
            }
        });

        tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Cricket","S!:"+s1);
                if(flag1==0 && flag2==0 && flag3==0 && flag4==0 && flag5==0)
                {
                    Toast.makeText(getContext(),"Select atleast 1 type of medicine to apply for Medicines!!",Toast.LENGTH_SHORT).show();
                }


                if(flag1==1) {

                    if(q1.getText().toString().isEmpty())
                    {
                        Toast.makeText(getContext(), "Quantity for medicine of First Aid is Empty!!", Toast.LENGTH_SHORT).show();
                        txt1=0;
                    }

                    else {
                        //flag1=1;
                        txt1=1;
                        s1 = Integer.parseInt(q1.getText().toString());

                        mReg.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                n1 = dataSnapshot.child("quantity").getValue();
                                n1s = String.valueOf(n1);

                                sa = Integer.parseInt(n1s);
                                s1Total = s1 + sa;
                                mReg.child("quantity").setValue(s1Total);

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        Log.i("Pranav", "value:" + n1s);

                        //mReg.child("quantity").setValue(s1Total);
                        Log.i("s1", "value:" + s1Total);


                    }
                }

                if(flag2==1) {
                    if(q2.getText().toString().isEmpty())
                    {
                        Toast.makeText(getContext(), "Quantity for medicine of Viral Fever is Empty!!", Toast.LENGTH_SHORT).show();
                        txt2=0;
                    }
                    else {
                        //flag2=1;
                        txt2=1;
                        s2 = Integer.parseInt(q2.getText().toString());

                        mreg2.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                n2 = dataSnapshot.child("quantity").getValue();
                                n2s = String.valueOf(n2);
                                sb = Integer.parseInt(n2s);
                                s2Total = s2 + sb;
                                mreg2.child("quantity").setValue(s2Total);

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                        // Log.i("Pranav","value:"+sa);

                        //mreg2.child("quantity").setValue(s2Total);
                    }
                }
                if(flag3==1) {
                    if(q3.getText().toString().isEmpty())
                    {
                        Toast.makeText(getContext(), "Quantity for medicine of Jaundice is Empty!!", Toast.LENGTH_SHORT).show();
                        txt3=0;
                    }
                    else {
                        //flag3=1;
                        txt3=1;
                        s3 = Integer.parseInt(q3.getText().toString());

                        mreg3.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                n3 = dataSnapshot.child("quantity").getValue();
                                n3s = String.valueOf(n3);
                                sc = Integer.parseInt(n3s);
                                s3Total = s3 + sc;
                                mreg3.child("quantity").setValue(s3Total);

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                        Log.i("Pranav","value:"+sc);
                        Log.i("s3total value is ","S3 total "+s3Total);
                        //mreg3.child("quantity").setValue(s3Total);
                    }

                }

                if(flag4==1) {

                    if(q4.getText().toString().isEmpty())
                    {
                        Toast.makeText(getContext(), "Quantity of medicine for Glucose is Empty!!", Toast.LENGTH_SHORT).show();
                        txt4=0;
                    }
                    else {
                        //flag4=1;
                        txt4=1;
                        s4 = Integer.parseInt(q4.getText().toString());

                        mreg4.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                n4 = dataSnapshot.child("quantity").getValue();
                                n4s = String.valueOf(n4);
                                sd = Integer.parseInt(n4s);
                                s4Total = s4 + sd;
                                mreg4.child("quantity").setValue(s4Total);

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                        // Log.i("Pranav","value:"+sa);
                        //  mreg4.child("quantity").setValue(s4Total);
                    }
                }

                if (flag5 == 1) {
                    if(q5.getText().toString().isEmpty())
                    {
                        Toast.makeText(getContext(), "Quantity for Cold and Cough Medicine is Empty!!", Toast.LENGTH_SHORT).show();
                        txt5=0;
                    }
                    else {
                        txt5=1;

                        s5 = Integer.parseInt(q5.getText().toString());

                        mreg5.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                n5 = dataSnapshot.child("quantity").getValue();
                                n5s = String.valueOf(n5);
                                se = Integer.parseInt(n5s);
                                s5Total = s5 + se;
                                mreg5.child("quantity").setValue(s5Total);

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                        // Log.i("Pranav","value:"+sa);
                        //    mreg5.child("quantity").setValue(s5Total);
                    }
                }



                if(flag1==1 && txt1==1 && txt2!=0 && txt3!=0 && txt4!=0 && txt5!=0)
                {
                    // mReg.child("quantity").setValue(s1Total);
                    toreq++;
                }
                if(flag2==1 && txt2==1 && txt1!=0 && txt3!=0 && txt4!=0 && txt5!=0)
                {
                    //mreg2.child("quantity").setValue(s2Total);
                    toreq++;
                }
                if(flag3==1 && txt3==1 && txt1!=0 && txt2!=0 && txt4!=0 && txt5!=0)
                {
                    //mreg3.child("quantity").setValue(s3Total);
                    toreq++;
                }
                if(flag4==1 && txt4==1 && txt1!=0 && txt2!=0 && txt3!=0 && txt5!=0)
                {
                    //mreg4.child("quantity").setValue(s4Total);
                    toreq++;
                }
                if(flag5==1 && txt5==1 && txt1!=0 && txt2!=0 && txt3!=0 && txt4!=0)
                {
                    // mreg5.child("quantity").setValue(s5Total);
                    toreq++;
                }


                if(toreq!=0) {

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