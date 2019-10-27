package com.example.disaster_management_v2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
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

public class toil_status extends Fragment
{
    FirebaseAuth mFirebaseAuth;
    TextView blanket_q,diapers_q,disinfectant_q,sanitary_q,soap_q;

    Object b_o,d_o,dis_o,sn_o,so_o;
    String b_s,d_s,dis_s,sn_s,so_s;
    int b_i,d_i,dis_i,sn_i,so_i;

    private DatabaseReference mat;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_toil_status,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        blanket_q = view.findViewById(R.id.bl_q);
        diapers_q = view.findViewById(R.id.di_q);
        disinfectant_q = view.findViewById(R.id.dis_q);
        sanitary_q = view.findViewById(R.id.sn_q);
        soap_q = view.findViewById(R.id.s_q);

        mFirebaseAuth=FirebaseAuth.getInstance();
        mat=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Toiletries").child(mFirebaseAuth.getInstance().getCurrentUser().getUid());

        mat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot ds)
            {
                b_o  = ds.child("Blanket").child("quantity").getValue();
                d_o = ds.child("Diapers").child("quantity").getValue();
                dis_o= ds.child("Disinfectant").child("quantity").getValue();
                sn_o = ds.child("Sanitary Napkin").child("quantity").getValue();
                so_o = ds.child("Soap").child("quantity").getValue();

                b_s = String.valueOf(b_o);
                d_s = String.valueOf(d_o);
                dis_s = String.valueOf(dis_o);
                sn_s = String.valueOf(sn_o);
                so_s  =String.valueOf(so_o);

                if (b_s.isEmpty())
                    b_i = 0;
                else
                {
                    try {
                        b_i = Integer.parseInt(b_s);
                    }
                    catch (NumberFormatException ex)
                    {
                        Log.i("Cricket","Disaster:"+ex);
                    }
                }
                if (d_s.isEmpty())
                    d_i = 0;
                else {
                    try {
                        d_i = Integer.parseInt(d_s);
                    }
                    catch(NumberFormatException ex){
                        Log.i("MEssage","Disaster:"+ex);
                    }
                }
                //

                if (dis_s.isEmpty())
                    dis_i = 0;
                else{
                    try {
                        dis_i = Integer.parseInt(dis_s);
                    }
                    catch (NumberFormatException ex)
                    {
                        Log.i("Message1","M1");
                    }
                }

                //

                if (sn_s.isEmpty())
                    sn_i = 0;
                else
                {
                    try {
                        sn_i = Integer.parseInt(sn_s);
                        //
                    }
                    catch (NumberFormatException ex){
                        Log.i("Message1","M1");
                    }
                }
                if (so_s.isEmpty())
                    so_i = 0;
                else
                {
                    try {
                        so_i = Integer.parseInt(so_s);
                    }
                    catch (NumberFormatException ex)
                    {
                        Log.i("Message1","M1");
                    }
                }
                //

                //set text
                blanket_q.setText("Quantity = " + b_i);
                diapers_q.setText("Quantity = " + d_i);
                disinfectant_q.setText("Quantity = " + dis_i);
                sanitary_q.setText("Quantity = " + sn_i);
                soap_q.setText("Quantity = " + so_i);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
