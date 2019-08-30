package com.example.disaster_management_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    Button loginButton;
    TextView registerTransfer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton=findViewById(R.id.loginButn);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transfertoDashboard = new Intent(LoginActivity.this, HelperDashboardActivity.class);
                startActivity(transfertoDashboard);
            }
        });

        registerTransfer=findViewById(R.id.toregister);
        registerTransfer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v1) {
                Intent launchActivity1= new Intent(LoginActivity.this,Registration_activity.class);
                startActivity(launchActivity1);

            }
        });

    }
}
