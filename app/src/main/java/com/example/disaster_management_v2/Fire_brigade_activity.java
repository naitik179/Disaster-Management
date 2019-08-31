package com.example.disaster_management_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Fire_brigade_activity extends AppCompatActivity {

    ListView firebrigadelist;

    int countimage;

    int[] image={R.drawable.contact,R.drawable.contact,R.drawable.contact,R.drawable.contact,R.drawable.contact,
            R.drawable.contact,R.drawable.contact,R.drawable.contact,R.drawable.contact,R.drawable.contact};

    String[] fire_stn_names={"Goregaon","Vashi","Thane","Andheri","Dadar","Vidyavihar","Ghatkoper","Kandivali","CSMT","Borivali"};
    String[] fire_stn_mob={"+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_brigade_activity);


        firebrigadelist=findViewById(R.id.fire_station_list_view);

        countimage=image.length;

        CustomAdapter customAdapter=new CustomAdapter();
        firebrigadelist.setAdapter(customAdapter);

    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return countimage;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=getLayoutInflater().inflate(R.layout.custom_list_view_contact_firestn_hospital_policestn,null);

            TextView nametextview=(TextView) view.findViewById(R.id.custom_name_list_view_fire);
            TextView namephoneno=(TextView) view.findViewById(R.id.custom_list_view_phoneno_fire);

            ImageView contactadmins=(ImageView) view.findViewById(R.id.contactlogo);

            nametextview.setText(fire_stn_names[position]);

            namephoneno.setText(fire_stn_mob[position]);

            contactadmins.setImageResource(image[position]);
            return view;
        }
    }

    }

