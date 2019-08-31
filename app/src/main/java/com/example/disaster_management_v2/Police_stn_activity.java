package com.example.disaster_management_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Police_stn_activity extends AppCompatActivity {

    ListView policelist;

    int countimage;

    int[] image={R.drawable.contact,R.drawable.contact,R.drawable.contact,R.drawable.contact,R.drawable.contact,
            R.drawable.contact,R.drawable.contact,R.drawable.contact,R.drawable.contact,R.drawable.contact};

    String[] pol_names={"Goregaon","Vashi","Thane","Andheri","Dadar","Vidyavihar","Ghatkoper","Kandivali","CSMT","Borivali"};
    String[] pol_mob={"+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_stn_activity);


        policelist=findViewById(R.id.police_list_view);

        countimage=image.length;

        CustomAdapter customAdapter=new CustomAdapter();
        policelist.setAdapter(customAdapter);

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
            View view=getLayoutInflater().inflate(R.layout.custom_list_view_police_stn,null);

            TextView nametextview=(TextView) view.findViewById(R.id.custom_list_view_phoneno_pol);
            TextView namephoneno=(TextView) view.findViewById(R.id.custom_list_view_phoneno_pol);

            ImageView contactadmins=(ImageView) view.findViewById(R.id.contactlogo);

            nametextview.setText(pol_names[position]);

            namephoneno.setText(pol_mob[position]);

            contactadmins.setImageResource(image[position]);
            return view;
        }
    }
}
