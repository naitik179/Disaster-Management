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

public class clothes_status extends Fragment {

    FirebaseAuth mFirebaseAuth;
    TextView male_s,male_l,male_xl,male_total,female_s,female_l,female_xl,female_total,children_s,children_l,children_xl,children_total,infant;

    Object m_s,m_l,m_xl,f_s,f_l,f_xl,c_s,c_l,c_xl,i_c;
    String m_s_db,m_l_db,m_xl_db,f_s_db,f_l_db,f_xl_db,c_s_db,c_l_db,c_xl_db,i_c_db;
    int m_s_db_int,m_l_db_int,m_xl_db_int,f_s_db_int,f_l_db_int,f_xl_db_int,c_s_db_int,c_l_db_int,c_xl_db_int,i_c_db_int;

    private DatabaseReference mReg,mat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_clothes_status,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        male_s = view.findViewById(R.id.male_s);
        male_l = view.findViewById(R.id.male_l);
        male_xl = view.findViewById(R.id.male_xl);
        male_total = view.findViewById(R.id.male_total);

        female_s = view.findViewById(R.id.female_s);
        female_l = view.findViewById(R.id.female_l);
        female_xl = view.findViewById(R.id.female_xl);
        female_total = view.findViewById(R.id.female_total);

        children_s = view.findViewById(R.id.children_s);
        children_l = view.findViewById(R.id.children_l);
        children_xl = view.findViewById(R.id.children_xl);
        children_total = view.findViewById(R.id.children_total);

        infant = view.findViewById(R.id.infant_total);


        mFirebaseAuth=FirebaseAuth.getInstance();

        mat=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Clothes").child(mFirebaseAuth.getInstance().getCurrentUser().getUid());

        mat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot ds)
            {
                m_s = ds.child("Male").child("S").getValue();
                m_l = ds.child("Male").child("L").getValue();
                m_xl = ds.child("Male").child("XL").getValue();

                m_s_db  = String.valueOf(m_s);
                m_l_db  = String.valueOf(m_l);
                m_xl_db  = String.valueOf(m_xl);

                if (m_s_db.isEmpty())
                    m_s_db_int = 0;
                else
                {
                    try {
                        m_s_db_int = Integer.parseInt(m_s_db);
                    }
                    catch (NumberFormatException ex){

                    }
                }
                if (m_l_db.isEmpty())
                    m_l_db_int = 0;
                else
                {
                    try {
                        m_l_db_int = Integer.parseInt(m_l_db);
                    }
                    catch (NumberFormatException ex){

                    }
                }
                if (m_xl_db.isEmpty())
                    m_xl_db_int = 0;
                else
                {
                    try {
                        m_xl_db_int = Integer.parseInt(m_xl_db);
                    }
                    catch (NumberFormatException ex){

                    }
                }
                //set text in the text view for Male
                male_s.setText("S = " + m_s_db_int);
                male_l.setText("L = " + m_l_db_int);
                male_xl.setText("XL = " + m_xl_db_int);
                male_total.setText("Total = " + (m_s_db_int + m_l_db_int +m_xl_db_int));



                //Female

                f_s = ds.child("Female").child("S").getValue();
                f_l = ds.child("Female").child("L").getValue();
                f_xl = ds.child("Female").child("XL").getValue();

                f_s_db  = String.valueOf(f_s);
                f_l_db  = String.valueOf(f_l);
                f_xl_db  = String.valueOf(f_xl);

                if (f_s_db.isEmpty())
                    f_s_db_int = 0;
                else
                {
                    try {
                        f_s_db_int = Integer.parseInt(f_s_db);
                    }
                    catch (NumberFormatException ex){

                    }
                }
                if (f_l_db.isEmpty())
                    f_l_db_int = 0;
                else
                {
                    try {
                        f_l_db_int = Integer.parseInt(f_l_db);
                    }
                    catch (NumberFormatException ex){

                    }
                }
                if (f_xl_db.isEmpty())
                    f_xl_db_int = 0;
                else
                {
                    try {
                        f_xl_db_int = Integer.parseInt(f_xl_db);
                    }
                    catch (NumberFormatException ex){

                    }
                }

                //set text in the text view for Female
                female_s.setText("S = " + f_s_db_int);
                female_l.setText("L = " + f_l_db_int);
                female_xl.setText("XL = " + f_xl_db_int);
                female_total.setText("Total = " + (f_s_db_int + f_l_db_int +f_xl_db_int));

                //children
                c_s = ds.child("Children").child("S").getValue();
                c_l = ds.child("Children").child("L").getValue();
                c_xl = ds.child("Children").child("XL").getValue();

                c_s_db  = String.valueOf(c_s);
                c_l_db  = String.valueOf(c_l);
                c_xl_db  = String.valueOf(c_xl);

                if (c_s_db.isEmpty())
                    c_s_db_int = 0;
                else
                {
                    try {
                        c_s_db_int = Integer.parseInt(c_s_db);
                    }
                    catch (NumberFormatException ex){

                    }
                }
                if (c_l_db.isEmpty())
                    c_l_db_int = 0;
                else
                {
                    try {
                        c_l_db_int = Integer.parseInt(c_l_db);
                    }
                    catch (NumberFormatException ex){

                    }
                }
                if (c_xl_db.isEmpty())
                    c_xl_db_int = 0;
                else
                {
                    try {
                        c_xl_db_int = Integer.parseInt(c_xl_db);
                    }
                    catch (NumberFormatException ex){

                    }
                }
                //set text in the text view for children
                children_s.setText("S = " + c_s_db_int);
                children_l.setText("L = " + c_l_db_int);
                children_xl.setText("XL = " + c_xl_db_int);
                children_total.setText("Total = " + (c_s_db_int + c_l_db_int +c_xl_db_int));

                //infant
                i_c = ds.child("Infant").child("count").getValue();
                i_c_db  = String.valueOf(i_c);
                if (i_c_db.isEmpty())
                    i_c_db_int = 0;
                else
                {
                    try {
                        i_c_db_int = Integer.parseInt(i_c_db);
                    }
                    catch (NumberFormatException ex){

                    }
                }
                infant.setText("Total = " + i_c_db_int);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}

