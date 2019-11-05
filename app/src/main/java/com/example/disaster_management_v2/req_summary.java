package com.example.disaster_management_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;


public class req_summary extends AppCompatActivity
{
    FirebaseAuth mFirebaseAuth1;
    private DatabaseReference mat1,mat2,mat3,mat4,mat5,mat6;

    TextView c_display,m_display,t_display,f_display,w_display,f_o_items;
    Button summary_pdf;
    String entry ;
    Object m_s,m_l,m_xl,f_s,f_l,f_xl,c_s,c_l,c_xl,i_c;
    String m_s_db,m_l_db,m_xl_db,f_s_db,f_l_db,f_xl_db,c_s_db,c_l_db,c_xl_db,i_c_db;
    int m_s_db_int,m_l_db_int,m_xl_db_int,f_s_db_int,f_l_db_int,f_xl_db_int,c_s_db_int,c_l_db_int,c_xl_db_int,i_c_db_int;

    Object c, f, j,co,g,viral;
    String ch_s, fa_s, j_s,co_s,g_s,vf_s;
    int c_i, f_i, j_i,co_i,g_i,vf_i;

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
        summary_pdf = findViewById(R.id.summary);

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
                else {
                    try{m_s_db_int = Integer.parseInt(m_s_db);}
                    catch (Exception e){}

                }
                if (m_l_db==null || m_l_db.equals(""))
                    m_l_db_int = 0;
                else {
                    try{m_l_db_int = Integer.parseInt(m_l_db);}
                    catch (Exception e){}

                }
                if (m_xl_db==null || m_xl_db.equals(""))
                    m_xl_db_int = 0;
                else {
                    try{m_xl_db_int = Integer.parseInt(m_xl_db);}
                    catch (Exception e){}

                }
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
                else {
                    try{f_s_db_int = Integer.parseInt(f_s_db);}
                    catch(Exception e){}

                }
                if (f_l_db==null || f_l_db.equals(""))
                    f_l_db_int = 0;
                else {
                    try{f_l_db_int = Integer.parseInt(f_l_db);}
                    catch (Exception e){}

                }
                if (f_xl_db==null || f_xl_db.equals(""))
                    f_xl_db_int = 0;
                else {
                    try{f_xl_db_int = Integer.parseInt(f_xl_db);}
                    catch (Exception e){}

                }
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
                else {
                    try{c_s_db_int = Integer.parseInt(c_s_db);}
                    catch(Exception e){}

                }
                if (c_l_db==null || c_l_db.equals(""))
                    c_l_db_int = 0;
                else {
                    try{c_l_db_int = Integer.parseInt(c_l_db);}
                    catch (Exception e){}

                }
                if (c_xl_db==null || c_xl_db.equals(""))
                    c_xl_db_int = 0;
                else {
                    try{c_xl_db_int = Integer.parseInt(c_xl_db);}
                    catch (Exception e){}

                }
                String child_data = "CHILDREN" + "\nS = " + c_s_db_int + "\nL = " + c_l_db_int + "\nXL = " +  c_xl_db_int + "\nTotal = " + (c_s_db_int + c_l_db_int +c_xl_db_int) + "\n";
                //infant
                i_c = ds.child("Infant").child("count").getValue();
                i_c_db  = String.valueOf(i_c);
                if (i_c_db==null || i_c_db.equals(""))
                    i_c_db_int = 0;
                else{
                    try{i_c_db_int = Integer.parseInt(i_c_db);}
                    catch (Exception e){}
                    }
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

                co = ds.child("Cold").child("quantity").getValue();
                g = ds.child("Glucose").child("quantity").getValue();
                viral  =ds.child("Viral Fever").child("quantity").getValue();


                ch_s = String.valueOf(c);
                fa_s = String.valueOf(f);
                j_s = String.valueOf(j);
                co_s  = String.valueOf(co);
                g_s  = String.valueOf(g);
                vf_s = String.valueOf(viral);

                if (ch_s==null || ch_s.equals(""))
                    c_i = 0;
                else {
                    try{c_i = Integer.parseInt(ch_s);}
                    catch (Exception e){}

                }
                if (fa_s==null || fa_s.equals(""))
                    f_i = 0;
                else {
                    try{f_i = Integer.parseInt(fa_s);}
                    catch (Exception e){}

                }
                if (j_s==null || j_s.equals(""))
                    j_i = 0;
                else {
                    try{j_i = Integer.parseInt(j_s);}
                    catch (Exception e){}

                }
                if (co_s==null || co_s.equals(""))
                    co_i = 0;
                else {
                    try{co_i = Integer.parseInt(co_s);}
                    catch(Exception e){}

                }
                if (g_s==null || g_s.equals(""))
                    g_i = 0;
                else {
                    try{g_i = Integer.parseInt(g_s);}
                    catch (Exception e){}

                }
                if (vf_s==null || vf_s.equals(""))
                    vf_i = 0;
                else {
                    try{vf_i = Integer.parseInt(vf_s);}
                    catch (Exception e){}

                }


                m_display.setText("CHOLERA\nQuantity = "  + c_i + "\n\nFIRST AID\nQuantity = " + f_i + "\n\nJAUNDICE\nQuantity = " + j_i + "\n\nCOLD\nQuantity = " + co_i + "\n\nGLUCOSE\nQuantity = " + g_i + "\n\nVIRAL FEVER\nQuantity = " + vf_i);


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
                else {
                    try{b_i = Integer.parseInt(b_s);}
                    catch (Exception e){}

                } //
                if (d_s==null || d_s.equals(""))
                    d_i = 0;
                else {
                    try{d_i = Integer.parseInt(d_s);}
                    catch (Exception e){}

                    //
                }
                if (dis_s==null || dis_s.equals(""))
                    dis_i = 0;
                else {
                    try{dis_i = Integer.parseInt(dis_s);}
                    catch (Exception e){}

                    //
                }
                if (sn_s==null || sn_s.equals(""))
                    sn_i = 0;
                else {
                    try{sn_i = Integer.parseInt(sn_s);}
                    catch (Exception e){}

                }//
                if (so_s==null || so_s.equals(""))
                    so_i = 0;
                else {
                    try{so_i = Integer.parseInt(so_s);}
                    catch (Exception e){}

                }//

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
                else {
                    try{sa1 = Integer.parseInt(n1s);}
                    catch (Exception e){}

                }
                n2s=String.valueOf(n2);
                if (n2s==null || n2s.equals(""))
                    sa2 = 0;
                else {
                    try{sa2 = Integer.parseInt(n2s);}
                    catch (Exception e){}

                }

                n3s=String.valueOf(n3);
                if (n3s==null || n3s.equals(""))
                    sa3 = 0;
                else {
                    try{sa3 = Integer.parseInt(n3s);}
                    catch (Exception e){}

                }
                n4s=String.valueOf(n4);
                if (n4s==null || n4s.equals(""))
                    sa4 = 0;
                else {
                    try{sa4 = Integer.parseInt(n4s);}
                    catch (Exception e){}

                }

                n5s=String.valueOf(n5);
                if (n5s==null || n5s.equals(""))
                    sa5 = 0;
                else {
                    try{sa5 = Integer.parseInt(n5s);}
                    catch (Exception e){}

                }

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
                else {
                    try{water_i = Integer.parseInt(water_s);}
                    catch (Exception e){}

                }

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
                entry = "Others\n";
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
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        summary_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int inc=400;
                PdfDocument myPdfDocument= new PdfDocument();
                PdfDocument.PageInfo myPageInfo=new PdfDocument.PageInfo.Builder(300,5*inc,4).create();
                PdfDocument.Page myPage=myPdfDocument.startPage(myPageInfo);


                Paint myPaint=new Paint();
                int x=10,y=25;

                myPage.getCanvas().drawText("CLOTHES",x,y,myPaint);
                y += 10;
                myPage.getCanvas().drawText("MALE",x,y+20,myPaint);
                myPage.getCanvas().drawText("S = " + m_s_db_int ,x,y+40,myPaint);
                myPage.getCanvas().drawText("L = " + m_l_db_int,x,y+60,myPaint);
                myPage.getCanvas().drawText("XL = " + m_xl_db_int,x,y+80,myPaint);
                myPage.getCanvas().drawText("TOTAL = " + (m_s_db_int + m_l_db_int +m_xl_db_int) ,x,y+100,myPaint);
                myPage.getCanvas().drawText("FEMALE",x,y+120,myPaint);
                myPage.getCanvas().drawText("S = " + f_s_db_int,x,y +140,myPaint);
                myPage.getCanvas().drawText("L = " + f_l_db_int,x,y+160,myPaint);
                myPage.getCanvas().drawText("XL = " + f_xl_db_int,x,y+180,myPaint);
                myPage.getCanvas().drawText("TOTAL = " + (f_s_db_int + f_l_db_int +f_xl_db_int) ,x,y+200,myPaint);
                myPage.getCanvas().drawText("CHILD",x,y+220,myPaint);
                myPage.getCanvas().drawText("S = " + c_s_db_int,x,y+240,myPaint);
                myPage.getCanvas().drawText("L = " + c_l_db_int,x,y+260,myPaint);
                myPage.getCanvas().drawText("XL = " + c_xl_db_int,x,y+280,myPaint);
                myPage.getCanvas().drawText("TOTAL = " + (c_s_db_int + c_l_db_int +c_xl_db_int) ,x,y+300,myPaint);
                myPage.getCanvas().drawText("INFANT",x,y+320,myPaint);
                myPage.getCanvas().drawText("Count = " + i_c_db_int,x,y+340,myPaint);


                y += inc;
                myPage.getCanvas().drawText("MEDICINES",x,y,myPaint);
                y += 10;
                myPage.getCanvas().drawText("Cholera : Quantity = " + c_i,x,y+20,myPaint);
                myPage.getCanvas().drawText("Cold : Quantity = " + co_i ,x,y+40,myPaint);
                myPage.getCanvas().drawText("First Aid : Quantity =  " + f_i,x,y+60,myPaint);
                myPage.getCanvas().drawText("Glucose : Quantity = " + g_i,x,y+80,myPaint);
                myPage.getCanvas().drawText("Jaundice : Quantity = " + j_i,x,y+100,myPaint);
                myPage.getCanvas().drawText("Viral Fever : Quantity =  " + vf_i,x,y+120,myPaint);

                y += inc;
                myPage.getCanvas().drawText("FOOD",x,y,myPaint);
                y += 10;
                myPage.getCanvas().drawText("Daal : Quantity = " + sa1,x,y+20,myPaint);
                myPage.getCanvas().drawText("Milk : Quantity = " +  sa2,x,y+40,myPaint);
                myPage.getCanvas().drawText("Rice : Quantity =  " + sa3,x,y+60,myPaint);
                myPage.getCanvas().drawText("Tea : Quantity = " + sa4,x,y+80,myPaint);
                myPage.getCanvas().drawText("Wheat : Quantity = " + sa5,x,y+100,myPaint);


                y += inc;
                myPage.getCanvas().drawText("TOILETRIES",x,y,myPaint);
                y += 10;
                myPage.getCanvas().drawText("Blanket : Quantity = " + b_i,x,y+20,myPaint);
                myPage.getCanvas().drawText("Diapers : Quantity = " + d_i ,x,y+40,myPaint);
                myPage.getCanvas().drawText("Disinfectant : Quantity =  " + dis_i,x,y+60,myPaint);
                myPage.getCanvas().drawText("Sanitary Napkin : Quantity = " + sn_i,x,y+80,myPaint);
                myPage.getCanvas().drawText("Soap : Quantity = " + so_i,x,y+100,myPaint);

                y += inc;
                myPage.getCanvas().drawText("WATER",x,y,myPaint);
                myPage.getCanvas().drawText("Quantity = " + water_i,x,y+20,myPaint);

                y += 10;

                myPdfDocument.finishPage(myPage);

                String  path = Environment.getExternalStorageDirectory().getPath() + "/requirements.pdf";
                File myFile = new File(path);

                try
                {
                    myPdfDocument.writeTo(new FileOutputStream(myFile));
                    Toast.makeText(getApplicationContext(),"Your PDF is ready, go to documents for viewing the PDF",Toast.LENGTH_LONG).show();

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                myPdfDocument.close();

            }
        });
    }
}
