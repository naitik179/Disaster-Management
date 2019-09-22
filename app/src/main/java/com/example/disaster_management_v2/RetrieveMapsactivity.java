package com.example.disaster_management_v2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.println;

public class RetrieveMapsactivity extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap;


//
    private DatabaseReference helper;
    FirebaseAuth mFirebaseAuth;
    private DatabaseReference mReg;
    Double latitude,longitude;
    private MarkerOptions options = new MarkerOptions();
    private ArrayList<Double> coordinates = new ArrayList<>();


    FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_mapsactivity);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        //mFirebaseAuth=FirebaseAuth.getInstance();

        helper= FirebaseDatabase.getInstance().getReference("Helper Registration");

        mReg= FirebaseDatabase.getInstance().getReference("Helper Registration");

        mAuth=FirebaseAuth.getInstance();


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        FirebaseUser mUser=mAuth.getCurrentUser();

        helper.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ch: dataSnapshot.getChildren())
                {
                    latitude=ch.child(mAuth.getInstance().getCurrentUser().getUid()).child("Latitude").getValue(Double.class);
                    longitude=ch.child(mAuth.getInstance().getCurrentUser().getUid()).child("Longitude").getValue(Double.class);

                    LatLng location=new LatLng(latitude,longitude);

                   mMap.addMarker(new MarkerOptions().position(location));
                   mMap.moveCamera(CameraUpdateFactory.newLatLng(location));

                   Log.i("latitude",latitude.toString(latitude));
                   Log.i("longitude",longitude.toString(longitude));


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




//        mReg.addValueEventListener(new ValueEventListener() {
//            @Override
//           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                int i=0;
//
//                for(DataSnapshot uqs: dataSnapshot.getChildren())
//                {
//                    if(uqs!=null)
//                    {
//                        String un_id = uqs.getKey();
//                        //System.out,println(un_id);
//
//
//                        latitude=(uqs.child("Latitude").getValue(Double.class));
//                        longitude=(uqs.child("Longitude").getValue(Double.class));
//
//                        coordinates.add(latitude);
//                        coordinates.add(longitude);
//
//                        LatLng location=new LatLng(coordinates.get(i),coordinates.get(i++));
//                        i++;
//
//                        mMap.addMarker(new MarkerOptions().position(location));
//                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,7F));
//                    }
//                }


//                    latitude=dataSnapshot.child("Latitude").getValue(Double.class);
//                    longitude=dataSnapshot.child("Longitude").getValue(Double.class);
//                    LatLng location=new LatLng(latitude,longitude);
//
//                   mMap.addMarker(new MarkerOptions().position(location));
//                   mMap.moveCamera(CameraUpdateFactory.newLatLng(location));


//                for(DataSnapshot data : dataSnapshot.getChildren()){
//                    if(data.getKey().equals("Latitude")){
//                        latitude=data.getValue(Double.class);
//                        Log.i("Latitude",latitude.toString());
//                    }
//                    if(data.getKey().equals(("Longitude"))){
//                        longitude=data.getValue(Double.class);
//                        Log.i("longitude",longitude.toString());
//                    }
//
//                    latitude=17.89;
//                    longitude=88.99;
//                    LatLng location=new LatLng(latitude,longitude);
//                    mMap.addMarker(new MarkerOptions().position(location));
//                    mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
//                }




//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


    };
}

