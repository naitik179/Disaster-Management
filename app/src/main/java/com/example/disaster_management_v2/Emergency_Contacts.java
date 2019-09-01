package com.example.disaster_management_v2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Emergency_Contacts extends Fragment {
    Button firebrigadebtnclicked,hospitalbtnclicked,policestnbtnclicked,armybtnclicked;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.emergency_contacts_layout,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firebrigadebtnclicked=view.findViewById(R.id.firebrigadeservicesbtn);

        firebrigadebtnclicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transfertofirebrigadeservices = new Intent(getContext(), Fire_brigade_activity.class);
                startActivity(transfertofirebrigadeservices);
            }
        });


        hospitalbtnclicked=view.findViewById(R.id.hospitalbtn);

        hospitalbtnclicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transfertohospitalcontacts = new Intent(getContext(), Hospital_activity.class);
                startActivity(transfertohospitalcontacts);
            }
        });


        policestnbtnclicked=view.findViewById(R.id.policestnbtn);

        policestnbtnclicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transfertopolicestncontacts = new Intent(getContext(), Police_stn_activity.class);
                startActivity(transfertopolicestncontacts);
            }
        });

        armybtnclicked=view.findViewById(R.id.armycontactbtn);

        armybtnclicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transfertoarmy = new Intent(getContext(), Armed_forces_activity.class);
                startActivity(transfertoarmy);
            }
        });
    }

}
