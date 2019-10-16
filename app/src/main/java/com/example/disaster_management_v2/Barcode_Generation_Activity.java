package com.example.disaster_management_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.Hashtable;

public class Barcode_Generation_Activity extends AppCompatActivity {

    private ImageView imageViewResult;
    String value,email,email1;
    Button home;
    Object email2;
    FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
    String email3=user.getEmail();

    private FirebaseDatabase mDatabase;
    FirebaseAuth mFirebaseAuth;
    private DatabaseReference mRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode__generation_);


        mDatabase =FirebaseDatabase.getInstance();
        mFirebaseAuth=FirebaseAuth.getInstance();
        mRef=mDatabase.getReference("Helper Registration").child(mFirebaseAuth.getCurrentUser().getUid());
        email2=mRef.child("Email id");
        email1=String.valueOf(email2);

        initView();
        home=findViewById(R.id.home);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Barcode_Generation_Activity.this,HelperDashboardActivity.class);
                startActivity(i);

            }
        });
    }

    private void initView() {

        imageViewResult = findViewById(R.id.imageViewResult);

        buttonGenerate_onClick();




    }

    private void buttonGenerate_onClick() {
        try {
            Bundle extras = getIntent().getExtras();
            if(extras != null){
                value = extras.getString("Donation");

            }
            Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix byteMatrix = writer.encode("Email : "+email3+"Donations : "+value, BarcodeFormat.QR_CODE,400, 200, hintMap);
            int width = byteMatrix.getWidth();
            int height = byteMatrix.getHeight();
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    bitmap.setPixel(i, j, byteMatrix.get(i, j) ? Color.BLACK : Color.WHITE);
                }
            }
            imageViewResult.setImageBitmap(bitmap);


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            String productId = intentResult.getContents().toString();
            Toast.makeText(getApplicationContext(), productId, Toast.LENGTH_LONG).show();
        }
    }



}