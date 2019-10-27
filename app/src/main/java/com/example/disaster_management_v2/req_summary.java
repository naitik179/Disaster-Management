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

public class req_summary extends AppCompatActivity
{
    FirebaseAuth mFirebaseAuth1;
    private DatabaseReference mat1,mat2,mat3,mat4,mat5,mat6;

    TextView c_display,m_display,t_display,f_display,w_display,f_o_items;

    Object m_s,m_l,m_xl,f_s,f_l,f_xl,c_s,c_l,c_xl,i_c;
    String m_s_db,m_l_db,m_xl_db,f_s_db,f_l_db,f_xl_db,c_s_db,c_l_db,c_xl_db,i_c_db;
    int m_s_db_int,m_l_db_int,m_xl_db_int,f_s_db_int,f_l_db_int,f_xl_db_int,c_s_db_int,c_l_db_int,c_xl_db_int,i_c_db_int;

    Object c,f,j;
    String ch_s,fa_s,j_s;
    int c_i,f_i,j_i;

    Object b_o,d_o,dis_o,sn_o,so_o;
    String b_s,d_s,dis_s,sn_s,so_s;
    int b_i,d_i,dis_i,sn_i,so_i;

    String n1s,n2s,n3s,n4s,n5s,n6s;
    Object n1,n2,n3,n4,n5,n6;
    int sa1,sa2,sa3,sa4,sa5,sa6;

    Object water_q;
    String water_s;
    int water_i;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_summary);

        c_display = findViewById(R.id.clothes_display);
        m_display =findViewById(R.id.med_display);
        t_display = findViewById(R.id.toil_display);
        f_display = findViewById(R.id.food_display);
        w_display = findViewById(R.id.water_display);
        f_o_items = findViewById(R.id.food_other_items_display);

        mFirebaseAuth1=FirebaseAuth.getInstance();

        mat1=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Clothes").child(mFirebaseAuth1.getInstance().getCurrentUser().getUid());


        mat1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot ds)
            {
                m_s = ds.child("Male").child("S").getValue();
                m_l = ds.child("Male").child("L").getValue();
                m_xl = ds.child("Male").child("XL").getValue();

                m_s_db  = String.valueOf(m_s);
                m_l_db  = String.valueOf(m_l);
                m_xl_db  = String.valueOf(m_xl);

                if (m_s_db==null || m_s_db.equals(""))
                    m_s_db_int = 0;
                else
                    m_s_db_int = Integer.parseInt(m_s_db);

                if (m_l_db==null || m_l_db.equals(""))
                    m_l_db_int = 0;
                else
                    m_l_db_int = Integer.parseInt(m_l_db);

                if (m_xl_db==null || m_xl_db.equals(""))
                    m_xl_db_int = 0;
                else
                    m_xl_db_int = Integer.parseInt(m_xl_db);

                //set text in the text view for Male
                String male_data = "MALE" + "\nS = " + m_s_db_int + "\nL = " + m_l_db_int + "\nXL = " +  m_xl_db_int + "\nTotal = " + (m_s_db_int + m_l_db_int +m_xl_db_int) + "\n";

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

                String female_data = "FEMALE" + "\nS = " + f_s_db_int + "\nL = " + f_l_db_int + "\nXL = " +  f_xl_db_int + "\nTotal = " + (f_s_db_int + f_l_db_int +f_xl_db_int) + "\n";

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

                String child_data = "CHILDREN" + "\nS = " + c_s_db_int + "\nL = " + c_l_db_int + "\nXL = " +  c_xl_db_int + "\nTotal = " + (c_s_db_int + c_l_db_int +c_xl_db_int) + "\n";
                //infant
                i_c = ds.child("Infant").child("count").getValue();
                i_c_db  = String.valueOf(i_c);
                if (i_c_db==null || i_c_db.equals(""))
                    i_c_db_int = 0;
                else
                    i_c_db_int = Integer.parseInt(i_c_db);
                String infant_data = "INFANT" + "\nCount = " + i_c_db_int;

                c_display.setText(male_data +"\n" + female_data + "\n" + child_data +  "\n" +infant_data);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mat2=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Med").child(mFirebaseAuth1.getInstance().getCurrentUser().getUid());

        mat2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot ds) {
                c = ds.child("Cholera").child("quantity").getValue();
                f = ds.child("First Aid").child("quantity").getValue();
                j = ds.child("Jaundice").child("quantity").getValue();

                c_s = String.valueOf(c);
                f_s = String.valueOf(f);
                j_s = String.valueOf(j);

                if (ch_s==null || ch_s.equals(""))
                    c_i = 0;
                else
                    c_i = Integer.parseInt(ch_s);

                if (fa_s==null || fa_s.equals(""))
                    f_i = 0;
                else
                    f_i = Integer.parseInt(fa_s);

                if (j_s==null || j_s.equals(""))
                    j_i = 0;
                else
                    j_i = Integer.parseInt(j_s);

                m_display.setText("CHOLERA\nQuantity = "  + c_i + "\n\nFIRST AID\nQuantity = " + f_i + "\n\nJAUNDICE\nQuantity = " + j_i);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mat3=FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Toiletries").child(mFirebaseAuth1.getInstance().getCurrentUser().getUid());
        mat3.addValueEventListener(new ValueEventListener() {
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

                if (b_s==null || b_s.equals(""))
                    b_i = 0;
                else
                    b_i = Integer.parseInt(b_s);
                //
                if (d_s==null || d_s.equals(""))
                    d_i = 0;
                else
                    d_i = Integer.parseInt(d_s);
                //

                if (dis_s==null || dis_s.equals(""))
                    dis_i = 0;
                else
                    dis_i = Integer.parseInt(dis_s);
                //

                if (sn_s==null || sn_s.equals(""))
                    sn_i = 0;
                else
                    sn_i = Integer.parseInt(sn_s);
                //
                if (so_s==null || so_s.equals(""))
                    so_i = 0;
                else
                    so_i = Integer.parseInt(so_s);
                //

                //set text

                t_display.setText("Blanket\nQuantity = " + b_i + "\n\nDiapers\nQuantity = " + d_i + "\n\nDisinfectant\nQuantity = " + dis_i + "\n\nSanitary Napkins\nQuantity = " + sn_i + "\n\nSoap\nQuantity = " + so_i);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mat4= FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Food").child(mFirebaseAuth1.getInstance().getCurrentUser().getUid());
        mat4.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                n1=dataSnapshot.child("Dal_Quantity").getValue();
                n2=dataSnapshot.child("Milk_Quantity").getValue();
                n3=dataSnapshot.child("Rice_Quantity").getValue();
                n4=dataSnapshot.child("Tea_Quantity").getValue();
                n5=dataSnapshot.child("Wheat_Quantity").getValue();
                n6=dataSnapshot.child("Other_Items").getChildren();

                //String item_other = String.valueOf(n6.Key());

                n1s=String.valueOf(n1);
                if (n1s==null || n1s.equals(""))
                    sa1 = 0;
                else
                    sa1=Integer.parseInt(n1s);

                n2s=String.valueOf(n2);
                if (n2s==null || n2s.equals(""))
                    sa2 = 0;
                else
                    sa2=Integer.parseInt(n2s);


                n3s=String.valueOf(n3);
                if (n3s==null || n3s.equals(""))
                    sa3 = 0;
                else
                    sa3=Integer.parseInt(n3s);

                n4s=String.valueOf(n4);
                if (n4s==null || n4s.equals(""))
                    sa4 = 0;
                else
                    sa4=Integer.parseInt(n4s);


                n5s=String.valueOf(n5);
                if (n5s==null || n5s.equals(""))
                    sa5 = 0;
                else
                    sa5=Integer.parseInt(n5s);

                f_display.setText("Dal\nQuantity = " + sa1 + "\n\nRice\nQuantity = " + sa3 + "\n\nWheat\nQuantity = " + sa5 + "\n\nMilk\nQauntity = " + sa2 + "\n\nTea\nQuantity = " + sa4);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mat5 = FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Water").child(mFirebaseAuth1.getInstance().getCurrentUser().getUid());
        mat5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                water_q=dataSnapshot.child("quantity").getValue();
                water_s=String.valueOf(water_q);

                if (water_s==null || water_s.equals(""))
                    water_i = 0;
                else
                    water_i=Integer.parseInt(water_s);


                w_display.setText("Quantity = " + water_i);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mat6 = FirebaseDatabase.getInstance().getReference().child("Material_Application").child("Food").child(mFirebaseAuth1.getInstance().getCurrentUser().getUid());
        mat6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot ds)
            {
                DataSnapshot other_items = ds.child("Other_Items");
                Iterable<DataSnapshot> other_itemsChildren = other_items.getChildren();
                String entry = "Others\n";
                for (DataSnapshot items : other_itemsChildren)
                {
                    entry = entry + "\n\t" + items.getKey() + "\n\t\tQuantity = " + items.getValue() ;
                }
                f_o_items.setText(entry);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
