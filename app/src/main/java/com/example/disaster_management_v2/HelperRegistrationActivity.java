package com.example.disaster_management_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HelperRegistrationActivity extends AppCompatActivity {

    Button registerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_registration);

        registerBtn = findViewById(R.id.RegisterButton);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toHelperDashboard = new Intent(HelperRegistrationActivity.this, HelperDashboardActivity.class);
                startActivity(toHelperDashboard);
            }
        });
    }
}
