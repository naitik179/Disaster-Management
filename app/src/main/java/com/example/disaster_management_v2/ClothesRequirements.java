package com.example.disaster_management_v2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ClothesRequirements extends AppCompatActivity {
    FirebaseAuth mFirebaseAuth;
    private DatabaseReference mReg,mat;

    Button btn;
    EditText male_s,male_l,male_xl;
    EditText female_s,female_l,female_xl;
    EditText child_s,child_l,child_xl;
    EditText infant;

    TextView m1,m2,m3;
    TextView f1,f2,f3;
    TextView c1,c2,c3;
    TextView baby;

    //database stuff
    Object m_s,m_l,m_xl,f_s,f_l,f_xl,c_s,c_l,c_xl,i_c;
    String m_s_db,m_l_db,m_xl_db,f_s_db,f_l_db,f_xl_db,c_s_db,c_l_db,c_xl_db,i_c_db;
    int m_s_db_int,m_l_db_int,m_xl_db_int,f_s_db_int,f_l_db_int,f_xl_db_int,c_s_db_int,c_l_db_int,c_xl_db_int,i_c_db_int;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes_requirements);
        male_s = findViewById(R.id.male_s2);
        male_l = findViewById(R.id.male_l2);
        male_xl= findViewById(R.id.male_xl2);

        female_s= findViewById(R.id.female_s2);
        female_l= findViewById(R.id.female_l2);
        female_xl= findViewById(R.id.female_xl2);

        child_s= findViewById(R.id.child_s2);
        child_l= findViewById(R.id.child_l2);
        child_xl= findViewById(R.id.child_xl2);

        infant= findViewById(R.id.infant2);

        m1=findViewById(R.id.male_s1);
        m2=findViewById(R.id.male_l1);
        m3=findViewById(R.id.male_xl1);

        f1=findViewById(R.id.female_s1);
        f2=findViewById(R.id.female_l1);
        f3=findViewById(R.id.female_xl1);

        c1=findViewById(R.id.child_s1);
        c2=findViewById(R.id.child_l1);
        c3=findViewById(R.id.child_xl1);

        baby=findViewById(R.id.infant1);


        mFirebaseAuth=FirebaseAuth.getInstance();

        mReg= FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Clothes");

        //get previous values from Database
        mat=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Clothes").child("JsgGmbvZV8NBdqxjstKs6dcHhKc2");

        mat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot ds) {

                m_s = ds.child("Male").child("S").getValue();
                m_l = ds.child("Male").child("L").getValue();
                m_xl = ds.child("Male").child("XL").getValue();

                m_s_db  = String.valueOf(m_s);
                m_l_db  = String.valueOf(m_l);
                m_xl_db  = String.valueOf(m_xl);
                m1.setText(m_s_db);
                m2.setText(m_l_db);
                m3.setText(m_xl_db);

                if (m_s_db==null || m_s_db.equals(""))
                    m_s_db_int = 0;
                else {
                    try {
                        m_s_db_int = Integer.parseInt(m_s_db);


                    }
                    catch (Exception e){

                    }
                }

                if (m_l_db==null || m_l_db.equals(""))
                    m_l_db_int = 0;
                else
                {
                    try {
                        m_l_db_int = Integer.parseInt(m_l_db);
                    }
                    catch (Exception e){

                    }
                }

                if (m_xl_db==null || m_xl_db.equals(""))
                    m_xl_db_int = 0;
                else{
                    try {
                        m_xl_db_int = Integer.parseInt(m_xl_db);
                    }
                    catch(Exception e){

                    }
                }

                //Female

                f_s = ds.child("Female").child("S").getValue();
                f_l = ds.child("Female").child("L").getValue();
                f_xl = ds.child("Female").child("XL").getValue();

                f_s_db  = String.valueOf(f_s);
                f_l_db  = String.valueOf(f_l);
                f_xl_db  = String.valueOf(f_xl);

                f1.setText(f_s_db);
                f2.setText(f_l_db);
                f3.setText(f_xl_db);

                if (f_s_db==null || f_s_db.equals(""))
                    f_s_db_int = 0;
                else{
                    try {
                        f_s_db_int = Integer.parseInt(f_s_db);
                    }
                    catch (Exception e){

                    }
                }

                if (f_l_db==null || f_l_db.equals(""))
                    f_l_db_int = 0;
                else{
                    try {
                        f_l_db_int = Integer.parseInt(f_l_db);
                    }
                    catch (Exception e)
                    {

                    }
                }

                if (f_xl_db==null || f_xl_db.equals(""))
                    f_xl_db_int = 0;
                else
                {
                    try {
                        f_xl_db_int = Integer.parseInt(f_xl_db);
                    }
                    catch (Exception e){

                    }
                }
                //children
                c_s = ds.child("Children").child("S").getValue();
                c_l = ds.child("Children").child("L").getValue();
                c_xl = ds.child("Children").child("XL").getValue();

                c_s_db  = String.valueOf(c_s);
                c_l_db  = String.valueOf(c_l);
                c_xl_db  = String.valueOf(c_xl);

                c1.setText(c_s_db);
                c2.setText(c_l_db);
                c3.setText(c_xl_db);

                if (c_s_db==null || c_s_db.equals(""))
                    c_s_db_int = 0;
                else
                {
                    try {
                        c_s_db_int = Integer.parseInt(c_s_db);
                    }
                    catch (Exception e){

                    }
                }
                if (c_l_db==null || c_l_db.equals(""))
                    c_l_db_int = 0;
                else
                {
                    try {
                        c_l_db_int = Integer.parseInt(c_l_db);
                    }
                    catch (Exception e){

                    }
                }
                if (c_xl_db==null || c_xl_db.equals(""))
                    c_xl_db_int = 0;
                else
                {
                    try {
                        c_xl_db_int = Integer.parseInt(c_xl_db);
                    }
                    catch (Exception e){

                    }
                }
                //infant
                i_c = ds.child("Infant").child("count").getValue();
                i_c_db  = String.valueOf(i_c);

                baby.setText(i_c_db);
                if (i_c_db==null || i_c_db.equals(""))
                    i_c_db_int = 0;
                else
                {
                    try {
                        i_c_db_int = Integer.parseInt(i_c_db);
                    }
                    catch (Exception e){

                    }
                }
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
            public void onClick(View view)
            {
                try {
                    mat.child("Male").child("S").setValue(m_s_db_int - Integer.parseInt(male_s.getText().toString()));
                }
                catch (Exception e) {
                }
                try {
                    mat.child("Male").child("L").setValue(m_l_db_int - Integer.parseInt(male_l.getText().toString()));
                }
                catch (Exception e) {
                }
                try {
                    mat.child("Male").child("XL").setValue(m_xl_db_int - Integer.parseInt(male_xl.getText().toString()));
                }
                catch (Exception e){

                }
                try{
                    mat.child("Female").child("S").setValue(f_s_db_int - Integer.parseInt(female_s.getText().toString()));
                }
                catch (Exception e){

                }
                try {
                    mat.child("Female").child("L").setValue(f_l_db_int - Integer.parseInt(female_l.getText().toString()));
                }
                catch(Exception e){

                }
                try{
                    mat.child("Female").child("XL").setValue(f_xl_db_int - Integer.parseInt(female_xl.getText().toString()));
                }
                catch (Exception e){

                }
                try{
                    mat.child("Children").child("S").setValue(c_s_db_int - Integer.parseInt(child_s.getText().toString()));
                }
                catch (Exception e){

                }
                try{
                    mat.child("Children").child("L").setValue(c_l_db_int - Integer.parseInt(child_l.getText().toString()));
                }
                catch(Exception e){

                }
                try{
                    mat.child("Children").child("XL").setValue(c_xl_db_int - Integer.parseInt(child_xl.getText().toString()));
                }
                catch(Exception e){

                }
                try {
                    mat.child("Infant").child("count").setValue(i_c_db_int - Integer.parseInt(infant.getText().toString()));
                }
                catch (Exception e){

                }
                Toast.makeText(ClothesRequirements.this,"Donated Clothes to the Relief Center.Thank You For Your Contribution!!!",Toast.LENGTH_LONG);
                Intent i=new Intent(ClothesRequirements.this,RequiredItems.class);
                startActivity(i);
            }
        });
    }
}
