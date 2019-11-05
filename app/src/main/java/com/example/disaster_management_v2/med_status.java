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

public class med_status extends Fragment {
    FirebaseAuth mFirebaseAuth;
    TextView chol, fa, jau,cold,glu,vf;

    Object c, f, j,co,g,viral;
    String c_s, f_s, j_s,co_s,g_s,vf_s;
    int c_i, f_i, j_i,co_i,g_i,vf_i;

    private DatabaseReference mat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_med_status, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        chol = view.findViewById(R.id.Ch_q);
        fa = view.findViewById(R.id.fa_q);
        jau = view.findViewById(R.id.j_q);
        cold = view.findViewById(R.id.cold_q);
        glu = view.findViewById(R.id.glucose_q);
        vf = view.findViewById(R.id.viral_fever_q);

        mFirebaseAuth = FirebaseAuth.getInstance();

        mat = FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Med").child(mFirebaseAuth.getInstance().getCurrentUser().getUid());

        mat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot ds) {
                c = ds.child("Cholera").child("quantity").getValue();
                f = ds.child("First Aid").child("quantity").getValue();
                j = ds.child("Jaundice").child("quantity").getValue();
                co = ds.child("Cold").child("quantity").getValue();
                g = ds.child("Glucose").child("quantity").getValue();
                viral  =ds.child("Viral Fever").child("quantity").getValue();

                c_s = String.valueOf(c);
                f_s = String.valueOf(f);
                j_s = String.valueOf(j);
                co_s  = String.valueOf(co);
                g_s  = String.valueOf(g);
                vf_s = String.valueOf(viral);

                if (c_s.isEmpty())
                    c_i = 0;
                else {
                    try {
                        c_i = Integer.parseInt(c_s);
                    } catch (NumberFormatException ex) {

                    }
                }
                if (f_s.isEmpty())
                    f_i = 0;
                else {
                    try {
                        f_i = Integer.parseInt(f_s);
                    } catch (NumberFormatException ex) {

                    }
                }
                if (j_s.isEmpty())
                    j_i = 0;
                else {
                    try {
                        j_i = Integer.parseInt(j_s);
                    } catch (NumberFormatException ex) {

                    }
                }
                if (co_s.isEmpty())
                    co_i = 0;
                else {
                    try {
                        co_i = Integer.parseInt(co_s);
                    } catch (NumberFormatException ex) {

                    }
                }
                if (g_s.isEmpty())
                    g_i = 0;
                else {
                    try {
                        g_i = Integer.parseInt(g_s);
                    } catch (NumberFormatException ex) {

                    }
                }
                if (vf_s.isEmpty())
                    vf_i = 0;
                else {
                    try {
                        vf_i = Integer.parseInt(vf_s);
                    } catch (NumberFormatException ex) {

                    }
                }


                chol.setText("Quantity = " + c_i);
                fa.setText("Quantity = " + f_i);
                jau.setText("Quantity = " + j_i);

                cold.setText("Quantity = " + co_i);
                glu.setText("Quantity = " + g_i);
                vf.setText("Quantity = " + vf_i);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}