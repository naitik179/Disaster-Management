package com.example.disaster_management_v2;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

import Adapter.MyAdapter;
import Model.DataGet;


public class contact_peer_admins extends Fragment {



    ListView mylistview;
    int countimage;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    final String TAG =" Info";

    int[] image={R.drawable.contact,R.drawable.contact,R.drawable.contact,R.drawable.contact,R.drawable.contact,
            R.drawable.contact,R.drawable.contact,R.drawable.contact,R.drawable.contact,R.drawable.contact};

    //String[] peer_admin_names={"Naitik","Ankita","Pranav","Riya","Priya","Akshita","Vallabhi","Dishant","Khusang","Mohit"};
    //String[] contact_peer_admin_mob={"+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094","+91-7045750094"};

    ArrayList <String> peer_admin_names = new ArrayList<String>();
    ArrayList <String> contact_peer_admin_mob = new ArrayList<String>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mDatabase =FirebaseDatabase.getInstance();
        mRef=mDatabase.getReference("Sub Admin Registration");
        return inflater.inflate(R.layout.contact_peer_admin,null);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshots: dataSnapshot.getChildren()) {
                    try {
                        Map<Object, String> data = (Map<Object, String>) snapshots.getValue();

                        Log.d(TAG, "onDataChange: latitude= " + data.get("Latitude"));
                        Log.d(TAG, "onDataChange: Longitude= " + data.get("Longitude"));
                        Log.d(TAG, "onDataChange: Key= " + snapshots.getKey());

                        contact_peer_admin_mob.add(data.get("Phone No"));
                        peer_admin_names.add(data.get("Email id"));




                    }

                    catch (Exception e){
                        Log.i(TAG, "onDataChange: "+e.getMessage());
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        countimage=image.length;

        CustomAdapter customAdapter=new CustomAdapter();
        mylistview.setAdapter(customAdapter);

    }


    class CustomAdapter extends BaseAdapter {

        TextView nametextview;
        TextView namephoneno;
        int globalPosition;
        @Override
        public int getCount() {
            return countimage;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        public int getPosition(int position){
            return position;
        }
        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

                final View contact_view = getLayoutInflater().inflate(R.layout.custom_list_view, null);

                nametextview = (TextView) contact_view.findViewById(R.id.custom_name_list_view);
                namephoneno = (TextView) contact_view.findViewById(R.id.custom_list_view_phoneno);

                //ImageView contactadmins = (ImageView) contact_view.findViewById(R.id.contactlogo);


                nametextview.setText(peer_admin_names.get(position));

                namephoneno.setText(contact_peer_admin_mob.get(position));

                //contactadmins.setImageResource(image[position]);

                contact_view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent =new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:"+contact_peer_admin_mob.get(position)));
                        contact_view.getContext().startActivity(intent);

                    }
                });
                return contact_view;





        }



    }
}





