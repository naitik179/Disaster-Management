package com.example.disaster_management_v2;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class contact_peer_admins extends Fragment {


    ListView mylistview;
    int countimage;

    int[] image={R.drawable.contact,R.drawable.contact,R.drawable.contact,R.drawable.contact,R.drawable.contact,
            R.drawable.contact,R.drawable.contact,R.drawable.contact,R.drawable.contact,R.drawable.contact};

    String[] peer_admin_names={"Naitik","Ankita","Pranav","Riya","Priya","Akshita","Vallabhi","Dishant","Khusang","Mohit"};
    String[] contact_peer_admin_mob={"+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094"};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.contact_peer_admin,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        mylistview=view.findViewById(R.id.contact_peer_admin_listview);

//        int[] image={R.drawable.contact,R.drawable.contact,R.drawable.contact,R.drawable.contact,R.drawable.contact,
//                     R.drawable.contact,R.drawable.contact,R.drawable.contact,R.drawable.contact,R.drawable.contact};

//        String[] peer_admin_names={"Naitik","Ankita","Pranav","Riya","Priya","Akshita","Vallabhi","Dishant","Khusang","Mohit"};

        countimage=image.length;



//        ArrayAdapter<String> arrayAdapter =new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,peeradmins);
//
//        mylistview.setAdapter(arrayAdapter);
//        mylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View adapterView, int position, long id) {
//
//                Toast.makeText(getContext(),"Hello "+ peeradmins.get(position),Toast.LENGTH_LONG).show();
//            }
//        });
//
        CustomAdapter customAdapter=new CustomAdapter();
        mylistview.setAdapter(customAdapter);

    }

    class CustomAdapter extends BaseAdapter{

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
            View view=getLayoutInflater().inflate(R.layout.custom_list_view,null);

            TextView nametextview=(TextView) view.findViewById(R.id.custom_name_list_view);
            TextView namephoneno=(TextView) view.findViewById(R.id.custom_list_view_phoneno);

            ImageView contactadmins=(ImageView) view.findViewById(R.id.contactlogo);

            nametextview.setText(peer_admin_names[position]);

            namephoneno.setText(contact_peer_admin_mob[position]);

            contactadmins.setImageResource(image[position]);
            return view;
        }
    }
}




