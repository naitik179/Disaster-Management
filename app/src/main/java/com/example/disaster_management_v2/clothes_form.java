package com.example.disaster_management_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class clothes_form extends AppCompatActivity {

    FirebaseAuth mFirebaseAuth;
    private DatabaseReference mReg,mat;

    Button btn;
    EditText male_s,male_l,male_xl;
    EditText female_s,female_l,female_xl;
    EditText child_s,child_l,child_xl;
    EditText infant;

    //database stuff
    Object m_s,m_l,m_xl,f_s,f_l,f_xl,c_s,c_l,c_xl,i_c;
    String m_s_db,m_l_db,m_xl_db,f_s_db,f_l_db,f_xl_db,c_s_db,c_l_db,c_xl_db,i_c_db;
    int m_s_db_int,m_l_db_int,m_xl_db_int,f_s_db_int,f_l_db_int,f_xl_db_int,c_s_db_int,c_l_db_int,c_xl_db_int,i_c_db_int;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes_form);

        male_s = findViewById(R.id.male_s);
        male_l = findViewById(R.id.male_l);
        male_xl= findViewById(R.id.male_xl);

        female_s= findViewById(R.id.female_s);
        female_l= findViewById(R.id.female_l);
        female_xl= findViewById(R.id.female_xl);

        child_s= findViewById(R.id.child_s);
        child_l= findViewById(R.id.child_l);
        child_xl= findViewById(R.id.child_xl);

        infant= findViewById(R.id.infant);

        mFirebaseAuth=FirebaseAuth.getInstance();

        mReg= FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Clothes");

        //get previous values from Database
        mat=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Clothes").child(mFirebaseAuth.getInstance().getCurrentUser().getUid());

        mat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot ds) {

                m_s = ds.child("Male").child("S").getValue();
                m_l = ds.child("Male").child("L").getValue();
                m_xl = ds.child("Male").child("XL").getValue();

                m_s_db  = String.valueOf(m_s);
                m_l_db  = String.valueOf(m_l);
                m_xl_db  = String.valueOf(m_xl);

                if (m_s_db==null || m_s_db.equals(""))
                    m_s_db_int = 0;
                else {
                    m_s_db_int = Integer.parseInt(m_s_db);
                }

                if (m_l_db==null || m_l_db.equals(""))
                    m_l_db_int = 0;
                else
                    m_l_db_int = Integer.parseInt(m_l_db);

                if (m_xl_db==null || m_xl_db.equals(""))
                    m_xl_db_int = 0;
                else
                    m_xl_db_int = Integer.parseInt(m_xl_db);

                //Female

                f_s = ds.child("Female").child("S").getValue();
                f_l = ds.child("Female").child("L").getValue();
                f_xl = ds.child("Female").child("XL").getValue();

                f_s_db  = String.valueOf(f_s);
                f_l_db  = String.valueOf(f_l);
                f_xl_db  = String.valueOf(f_xl);

                if (f_s_db==null || f_s_db.equals(""))
                    f_s_db_int = 0;
                else
                    f_s_db_int = Integer.parseInt(f_s_db);

                if (f_l_db==null || f_l_db.equals(""))
                    f_l_db_int = 0;
                else
                    f_l_db_int = Integer.parseInt(f_l_db);

                if (f_xl_db==null || f_xl_db.equals(""))
                    f_xl_db_int = 0;
                else
                    f_xl_db_int = Integer.parseInt(f_xl_db);

                //children
                c_s = ds.child("Children").child("S").getValue();
                c_l = ds.child("Children").child("L").getValue();
                c_xl = ds.child("Children").child("XL").getValue();

                c_s_db  = String.valueOf(c_s);
                c_l_db  = String.valueOf(c_l);
                c_xl_db  = String.valueOf(c_xl);

                if (c_s_db==null || c_s_db.equals(""))
                    c_s_db_int = 0;
                else
                    c_s_db_int = Integer.parseInt(c_s_db);

                if (c_l_db==null || c_l_db.equals(""))
                    c_l_db_int = 0;
                else
                    c_l_db_int = Integer.parseInt(c_l_db);

                if (c_xl_db==null || c_xl_db.equals(""))
                    c_xl_db_int = 0;
                else
                    c_xl_db_int = Integer.parseInt(c_xl_db);

                //infant
                i_c = ds.child("Infant").child("count").getValue();
                i_c_db  = String.valueOf(i_c);
                if (i_c_db==null || i_c_db.equals(""))
                    i_c_db_int = 0;
                else
                    i_c_db_int = Integer.parseInt(i_c_db);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast toast=Toast.makeText(getApplicationContext(),"db error" + databaseError,Toast.LENGTH_SHORT);
                toast.show();

            }
        });

        btn = (Button) findViewById(R.id.submit);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mat.child("Male").child("S").setValue(Integer.parseInt(male_s.getText().toString()) + m_s_db_int );
                mat.child("Male").child("L").setValue(Integer.parseInt(male_l.getText().toString()) + m_l_db_int );
                mat.child("Male").child("XL").setValue(Integer.parseInt(male_xl.getText().toString()) + m_xl_db_int );

                mat.child("Female").child("S").setValue(Integer.parseInt(female_s.getText().toString()) + f_s_db_int );
                mat.child("Female").child("L").setValue(Integer.parseInt(female_l.getText().toString()) + f_l_db_int );
                mat.child("Female").child("XL").setValue(Integer.parseInt(female_xl.getText().toString()) + f_xl_db_int );

                mat.child("Children").child("S").setValue(Integer.parseInt(child_s.getText().toString()) + c_s_db_int );
                mat.child("Children").child("L").setValue(Integer.parseInt(child_l.getText().toString()) + c_l_db_int );
                mat.child("Children").child("XL").setValue(Integer.parseInt(child_xl.getText().toString()) + c_xl_db_int );

                mat.child("Infant").child("count").setValue(Integer.parseInt(infant.getText().toString()) + i_c_db_int );
            }
        });
    }

}
