package com.example.disaster_management_v2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Request_Status_Activity extends AppCompatActivity {

    TextView clothes,food;
    private final static int REQUEST_CODE_1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request__status_);

        clothes=findViewById(R.id.clothesText);
        food=findViewById(R.id.foodText);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String value = extras.getString("RC Id");
            clothes.setText(value);
        }
    }


}
