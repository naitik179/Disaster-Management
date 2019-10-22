package com.example.disaster_management_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HelperRegistrationActivity extends AppCompatActivity {

    EditText email,password,name;
    EditText phone,confirmPassword;
    FirebaseAuth mFirebaseAuth;
    private DatabaseReference mReg;
    Button registerButton;

    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    Context context = this;
    TextView user_location;
    private FusedLocationProviderClient mFusedLocationClient;


    String e;
    String pwd;
    Double latitude;
    Double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_registration);

        mFirebaseAuth=FirebaseAuth.getInstance();
        email=findViewById(R.id.emailid);
        phone=findViewById(R.id.registerPhoneno);
        password=findViewById(R.id.registerPassword);
        confirmPassword=findViewById(R.id.repeatPassword);
        name=findViewById(R.id.registerName);
        registerButton= findViewById(R.id.RegisterButton);

        mReg= FirebaseDatabase.getInstance().getReference().child("Helper Registration");


        //registerBtn = findViewById(R.id.RegisterButton);
        //user_location = findViewById(R.id.helper_location);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fetchLocation();

                e=email.getText().toString();
                pwd=password.getText().toString();
                String cpwd=confirmPassword.getText().toString();
                String pne=phone.getText().toString();
                String n=name.getText().toString();
                if(n.isEmpty())
                {
                    name.setError("Please enter your name!!!");
                    name.requestFocus();
                }
                else if(e.isEmpty())
                {
                    email.setError("Please Enter email id!!");
                    email.requestFocus();
                }
                else if(pne.isEmpty())
                {
                    phone.setError("Please Enter your phone number!!!");
                    phone.requestFocus();
                }

                else if(pwd.isEmpty())
                {
                    password.setError("Please enter Your Password!!");
                    password.requestFocus();
                }
                else if(cpwd.isEmpty())
                {
                    confirmPassword.setError("Confirm your Password");
                    confirmPassword.requestFocus();
                }
                else if(!(pwd.equals(cpwd)))
                {
                    confirmPassword.setError("Password does not Match");
                    confirmPassword.requestFocus();
                }
                else if(e.isEmpty() && pwd.isEmpty() && pne.isEmpty() && cpwd.isEmpty() && n.isEmpty())
                {
                    Toast.makeText(HelperRegistrationActivity.this, "Fields are Empty!!", Toast.LENGTH_SHORT).show();
                }
                else if(!(e.isEmpty() && pwd.isEmpty() && pne.isEmpty() && cpwd.isEmpty() && n.isEmpty()))
                {



                    mFirebaseAuth.createUserWithEmailAndPassword(e,pwd).addOnCompleteListener(HelperRegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(HelperRegistrationActivity.this, "Signup Unsuccessful, Please Try Again!!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                //startActivity(new Intent(HelperRegistrationActivity.this,HelperDashboardActivity.class));
                                mReg.child(mFirebaseAuth.getInstance().getCurrentUser().getUid()+"/Name").setValue(name.getText().toString());
                                mReg.child(mFirebaseAuth.getInstance().getCurrentUser().getUid()+"/Email id").setValue(email.getText().toString());
                                mReg.child(mFirebaseAuth.getInstance().getCurrentUser().getUid()+"/Phone No").setValue(phone.getText().toString());
                                mReg.child(mFirebaseAuth.getInstance().getCurrentUser().getUid()+"/Latitude").setValue(latitude);
                                mReg.child(mFirebaseAuth.getInstance().getCurrentUser().getUid()+"/Longitude").setValue(longitude);

                            }

                        }
                    });


                }
                else
                {
                    Toast.makeText(HelperRegistrationActivity.this, "Error Occurred!!", Toast.LENGTH_SHORT).show();
                }
                //Intent toReliefCentreDashboard = new Intent(ReliefCentreRegisterActivity.this, MainActivity.class);
                //startActivity(toReliefCentreDashboard);

//                fetchLocation();
            }

        });


//        registerBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                fetchLocation();
//
//
//            }
//        });

//        registerBtn = findViewById(R.id.RegisterButton);
//        registerBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent toHelperDashboard = new Intent(HelperRegistrationActivity.this, HelperDashboardActivity.class);
//                startActivity(toHelperDashboard);
//            }
//        });
    }


    private void fetchLocation() {


        if (ContextCompat.checkSelfPermission(HelperRegistrationActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(HelperRegistrationActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                new AlertDialog.Builder(this)
                        .setTitle("Required Location Permission")
                        .setMessage("You have to give this permission To Register and Use this Application")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(HelperRegistrationActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(HelperRegistrationActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {

            // Permission has already been granted
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                // Logic to handle location object
                                 latitude = location.getLatitude();
                                 longitude = location.getLongitude();

                                 //user_location.setText("Latitude = "+latitude + "\nLongitude = " + longitude);

                            }
                        }
                    });




        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //abc

//                registerButton = findViewById(R.id.RegisterButton);
//                registerButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                Intent toHelperDashboard = new Intent(HelperRegistrationActivity.this, HelperDashboardActivity.class);
//                startActivity(toHelperDashboard);
//                }
//        });
            }else{

            }
        }
    }



}
