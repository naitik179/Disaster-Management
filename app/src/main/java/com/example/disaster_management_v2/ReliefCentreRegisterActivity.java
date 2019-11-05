package com.example.disaster_management_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReliefCentreRegisterActivity extends AppCompatActivity {

    //Button registerButton;


    EditText email,password,Name,Date;
    EditText landmark,phone,aadhar,policeThana,confirmPassword,confirmName,pincode;
    FirebaseAuth mFirebaseAuth;
    private DatabaseReference mReg,mRef,reliefContact;
    Button registerButton;
    Double latitude;
    Double longitude;
    int count=0;


    //Button registerBtn;

    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    Context context = this;
    TextView relief_centre_loc;
    private FusedLocationProviderClient mFusedLocationClient;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relief_centre_register);

        //  progressDialog = new ProgressDialog(this);
        mFirebaseAuth=FirebaseAuth.getInstance();
        Date=findViewById(R.id.Date);
        email=findViewById(R.id.emailid);
        phone=findViewById(R.id.registerPhoneno);
        confirmName=findViewById(R.id.Name);
        pincode=findViewById(R.id.pincode);
        password=findViewById(R.id.registerPassword);
        confirmPassword=findViewById(R.id.repeatPassword);
        Name=findViewById(R.id.registerPeople);
        landmark=findViewById(R.id.registerLandmark);
        aadhar=findViewById(R.id.registerAadhar);
        policeThana=findViewById(R.id.registerPolice);

        registerButton= findViewById(R.id.RegisterButton);
        mReg= FirebaseDatabase.getInstance().getReference().child("Sub Admin Registration");
        mRef=FirebaseDatabase.getInstance().getReference().child("Police Thana Details");
        reliefContact=FirebaseDatabase.getInstance().getReference().child("Relief Center Contacts");

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fetchLocation();

//
            }

        });

        registerButton = findViewById(R.id.RegisterButton);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
    }

    private void fetchLocation() {

        if (ContextCompat.checkSelfPermission(ReliefCentreRegisterActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(ReliefCentreRegisterActivity.this,
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
                                ActivityCompat.requestPermissions(ReliefCentreRegisterActivity.this,
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
                ActivityCompat.requestPermissions(ReliefCentreRegisterActivity.this,
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
                            }
                        }
                    });
            pranavfunction();

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            }else{

            }
        }
    }

    private void pranavfunction(){
        String date=Date.getText().toString();
        String e=email.getText().toString();
        String pwd=password.getText().toString();
        String cpwd=confirmPassword.getText().toString();
        String pne=phone.getText().toString();
        String name=Name.getText().toString();
        String cname=confirmName.getText().toString();
        String lmark=landmark.getText().toString();
        String pin=pincode.getText().toString();
        String aid=aadhar.getText().toString();
        String pt=policeThana.getText().toString();
        if(date.isEmpty()&&e.isEmpty() && pwd.isEmpty() && pne.isEmpty() && aid.isEmpty() && name.isEmpty() && lmark.isEmpty()&&pin.isEmpty() && pt.isEmpty() && cpwd.isEmpty())
        {
            Toast.makeText(ReliefCentreRegisterActivity.this, "Fields are Empty!!", Toast.LENGTH_SHORT).show();
        }
        if(date.isEmpty())
        {
            Date.setError("Enter Date of Setting Up Relief Center!!!");
            Date.requestFocus();
        }
        else if(date.indexOf("/")==2||date.indexOf("/")==5||date.indexOf("/")==4){
            Date.setError("Enter Date in DD-MM-YYYY Format!!!");
            Date.requestFocus();
        }
        else if(date.length()!=10){
            Date.setError("Date cannot Excced more than 10 Characters in Length");
            Date.requestFocus();
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
        else if(pne.length()!=10)
        {
            phone.setError("Phone number should be 10 digits!!!");
            phone.requestFocus();
        }
        else if(aid.isEmpty())
        {
            aadhar.setError("Please Enter Aadhar UID");
            aadhar.requestFocus();
            count++;
        }
        else if(aid.length()!=12)
        {
            aadhar.setError("Aadhar number should be 12 digits!!!");
            aadhar.requestFocus();
        }
        else if(name.isEmpty())
        {
            Name.setError("Enter First Name for Relief Center!!");
            Name.requestFocus();
            count++;
        }
        else if(cname.isEmpty())
        {
            confirmName.setError("Confirm Relief Center Name!!!!");
            confirmName.requestFocus();

        }
        else if(!(name.equals(cname))){
            confirmName.setError("Name of Relief Center Does Not Match!!!");
            confirmName.requestFocus();
        }
        else if(pt.isEmpty())
        {
            policeThana.setError("Enter nearest Police Thana details");
            policeThana.requestFocus();
        }
        else if(lmark.isEmpty())
        {

            landmark.setError("Enter nearest Landmark");
            landmark.requestFocus();
        }
        else if(pin.isEmpty()){
            pincode.setError("Pincode Cannot be Empty!!");
            pincode.requestFocus();
        }
        else if (pin.length()!=6){
            pincode.setError("Pincode is 6 Digit Long!!");
            pincode.requestFocus();
        }
        else if (pin.charAt(0)=='0'){
            pincode.setError("Pincode Cannot Start with 0");
            pincode.requestFocus();
        }
        else if(pwd.isEmpty())
        {

            password.setError("Please enter Your Password!!");
            password.requestFocus();
        }
        else if(pwd.length()<8)
        {
            password.setError("Password should be atleast 8 characters long!!!");
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
        else if(!(date.isEmpty()&&e.isEmpty()&&pwd.isEmpty()&&pne.isEmpty()&&aid.isEmpty()&&name.isEmpty()&&cname.isEmpty()&&lmark.isEmpty()&&pin.isEmpty()&&pt.isEmpty()&&cpwd.isEmpty()))
        {
            mFirebaseAuth.createUserWithEmailAndPassword(e,pwd).addOnCompleteListener(ReliefCentreRegisterActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(ReliefCentreRegisterActivity.this, "Signup Unsuccessful, Please Try Again!!", Toast.LENGTH_SHORT).show();
                    }
                    else {

                        mReg.child(Date.getText().toString()).child(mFirebaseAuth.getInstance().getCurrentUser().getUid()+"/Email id").setValue(email.getText().toString());
                        mReg.child(Date.getText().toString()).child(mFirebaseAuth.getInstance().getCurrentUser().getUid()+"/Phone No").setValue(phone.getText().toString());
                        reliefContact.child(mFirebaseAuth.getInstance().getCurrentUser().getUid()+"/Phone No").setValue(phone.getText().toString());
                        mReg.child(Date.getText().toString()).child(mFirebaseAuth.getInstance().getCurrentUser().getUid()+"/Aadhar UID").setValue(aadhar.getText().toString());
                        mReg.child(Date.getText().toString()).child(mFirebaseAuth.getInstance().getCurrentUser().getUid()+"/Name of Relief Center").setValue(Name.getText().toString()+"_"+pincode.getText().toString()+"_"+landmark.getText().toString());
                        mReg.child(Date.getText().toString()).child(mFirebaseAuth.getInstance().getCurrentUser().getUid()+"/Landmark").setValue(landmark.getText().toString());
                        mReg.child(Date.getText().toString()).child(mFirebaseAuth.getInstance().getCurrentUser().getUid()+"/Pincode").setValue(pincode.getText().toString());
                        mRef.child(Date.getText().toString()).child(mFirebaseAuth.getInstance().getCurrentUser().getUid()+"/Aadhar UID").setValue(aadhar.getText().toString());
                        mRef.child(Date.getText().toString()).child(mFirebaseAuth.getInstance().getCurrentUser().getUid()+"/Nearest Police Thana").setValue(policeThana.getText().toString());
                        mReg.child(Date.getText().toString()).child(mFirebaseAuth.getInstance().getCurrentUser().getUid()+"/Latitude").setValue(latitude);
                        mReg.child(Date.getText().toString()).child(mFirebaseAuth.getInstance().getCurrentUser().getUid()+"/Longitude").setValue(longitude);
                        Toast.makeText(ReliefCentreRegisterActivity.this,"Name of Relief Center is:"+Name.getText().toString()+"_"+pincode.getText().toString()+"_"+landmark.getText().toString(),Toast.LENGTH_LONG).show();
                        startActivity(new Intent(ReliefCentreRegisterActivity.this,MainActivity.class));

                    }

                }
            });
        }
        else
        {
            Toast.makeText(ReliefCentreRegisterActivity.this, "Error Occurred!!", Toast.LENGTH_SHORT).show();
        }
    }
}