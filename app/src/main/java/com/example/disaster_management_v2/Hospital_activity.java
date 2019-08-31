package com.example.disaster_management_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Hospital_activity extends AppCompatActivity {

    ListView hospitallist;

    int countimage;

    int[] image={R.drawable.contact,R.drawable.contact,R.drawable.contact,R.drawable.contact,R.drawable.contact,
            R.drawable.contact,R.drawable.contact,R.drawable.contact,R.drawable.contact,R.drawable.contact};

    String[] hos_names={"Goregaon","Vashi","Thane","Andheri","Dadar","Vidyavihar","Ghatkoper","Kandivali","CSMT","Borivali"};
    String[] hos_mob={"+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_activity);


        hospitallist=findViewById(R.id.hospital_list_view);

        countimage=image.length;

        CustomAdapter customAdapter=new CustomAdapter();
        hospitallist.setAdapter(customAdapter);

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
            View view=getLayoutInflater().inflate(R.layout.custom_list_view_hospital,null);

            TextView nametextview=(TextView) view.findViewById(R.id.custom_list_view_phoneno_hos);
            TextView namephoneno=(TextView) view.findViewById(R.id.custom_list_view_phoneno_hos);

            ImageView contactadmins=(ImageView) view.findViewById(R.id.contactlogo);

            nametextview.setText(hos_names[position]);

            namephoneno.setText(hos_mob[position]);

            contactadmins.setImageResource(image[position]);
            return view;
        }
    }

}

