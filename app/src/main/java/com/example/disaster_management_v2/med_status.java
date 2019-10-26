package com.example.disaster_management_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class med_status extends AppCompatActivity
{
    FirebaseAuth mFirebaseAuth;
    TextView chol,fa,jau;

    Object c,f,j;
    String c_s,f_s,j_s;
    int c_i,f_i,j_i;

    private DatabaseReference mat;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_status);
        chol = findViewById(R.id.Ch_q);
        fa = findViewById(R.id.fa_q);
        jau = findViewById(R.id.j_q);

        mFirebaseAuth=FirebaseAuth.getInstance();

        mat=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Med").child(mFirebaseAuth.getInstance().getCurrentUser().getUid());

        mat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot ds)
            {
                c = ds.child("Cholera").child("quantity").getValue();
                f = ds.child("First Aid").child("quantity").getValue();
                j = ds.child("Jaundice").child("quantity").getValue();

                c_s = String.valueOf(c);
                f_s = String.valueOf(f);
                j_s = String.valueOf(j);

                if (c_s.isEmpty())
                    c_i = 0;
                else
                {
                    try {
                        c_i = Integer.parseInt(c_s);
                    }
                    catch (NumberFormatException ex){

                    }
                }
                if (f_s.isEmpty())
                    f_i = 0;
                else
                {
                    try {
                        f_i = Integer.parseInt(f_s);
                    }
                    catch (NumberFormatException ex){

                    }
                }
                if (j_s.isEmpty())
                    j_i = 0;
                else
                {
                    try {
                        j_i = Integer.parseInt(j_s);
                    }
                    catch (NumberFormatException ex){

                    }
                }
                chol.setText("Quantity = " + c_i);
                fa.setText("Quantity = " + f_i);
                jau.setText("Quantity = " + j_i);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
