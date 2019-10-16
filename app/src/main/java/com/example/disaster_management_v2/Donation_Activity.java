package com.example.disaster_management_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Donation_Activity extends AppCompatActivity {
    EditText clothes,food,medicine,water,others;
    Button proceed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_);

        clothes=findViewById(R.id.clothes_donate);
        food=findViewById(R.id.food_donate);
        medicine=findViewById(R.id.medicine_donate);
        water=findViewById(R.id.medicine_donate);
        others=findViewById(R.id.clothes_donate);
        proceed=findViewById(R.id.proceed);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Donation_Activity.this,Barcode_Generation_Activity.class);
                String sendData = "Clothes : "+clothes.getText()+" Food : "+food.getText()+" Medicine : "+medicine.getText()+" Water : "+water.getText()+" Others : "+ others.getText();
                i.putExtra("Donation",sendData);
                startActivity(i);
            }
        });
    }
}
