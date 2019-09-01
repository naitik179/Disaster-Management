package com.example.disaster_management_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ReliefCentreRegisterActivity extends AppCompatActivity {

    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relief_centre_register);

        registerButton= findViewById(R.id.RegisterButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toReliefCentreDashboard = new Intent(ReliefCentreRegisterActivity.this, MainActivity.class);
                startActivity(toReliefCentreDashboard);
            }
        });
    }
}
