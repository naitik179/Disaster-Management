package com.example.disaster_management_v2;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    Button retrieveButton;
    public static final String TAG = "Info";
    TextView dataFrmDb;

    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private GoogleMap mMap;

    public double latitude[]=new double[100];
    public double longitude[]=new double[100];
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        dataFrmDb=findViewById(R.id.textViewDB);
        //initViews();
        mDatabase =FirebaseDatabase.getInstance();
        mRef=mDatabase.getReference("Sub Admin Registration");


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
    public void onMapReady(final GoogleMap googleMap) {

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshots: dataSnapshot.getChildren()) {
                    try {
                        Map<Object, Double> data = (Map<Object, Double>) snapshots.getValue();

                        Log.d(TAG, "onDataChange: latitude= " + data.get("Latitude"));
                        Log.d(TAG, "onDataChange: Longitude= " + data.get("Longitude"));
                        Log.d(TAG, "onDataChange: Key= " + snapshots.getKey());


                        latitude[i] = data.get("Latitude");
                        longitude[i] =data.get("Longitude");



                        Log.i(TAG, "onDataChange: latitude in array="+latitude[i]);
                        Log.i(TAG, "onDataChange: longitude= in array"+longitude[i]);
                        mMap = googleMap;

                        Log.i(TAG, "latitude: " + latitude[0] + " Longitude: " + longitude[0]);
                        // Add a marker in Sydney and move the camera
                        LatLng place = new LatLng(latitude[i], longitude[i]);
                        mMap.addMarker(new MarkerOptions().position(place).title("Marker"+i));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(place));

                        i++;

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




    }
}
