package com.example.disaster_management_v2;

import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;
import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.google.zxing.integration.android.IntentIntegrator.REQUEST_CODE;

public class RC_dashboard extends Fragment {

    private RcDashboardViewModel mViewModel;
    DatabaseReference mref;
    FirebaseAuth mauth;
    ImageButton user;
    //private ImageView imageView;

    private Uri filePath;
    Context context;
    TextView email, name;
    String n, e;
    Object n1, e1;

    private final int PICK_IMAGE_REQUEST = 71;

    public static RC_dashboard newInstance() {
        return new RC_dashboard();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.rc_dashboard_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RcDashboardViewModel.class);
        user = getView().findViewById(R.id.user_profile_photo);
        email = getView().findViewById(R.id.relief_email);
        name = getView().findViewById(R.id.user_profile_short_bio);
        mref = FirebaseDatabase.getInstance().getReference().child("Sub Admin Registration");
        mauth = FirebaseAuth.getInstance();
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.child(mauth.getInstance().getCurrentUser().getUid()).exists()) {
                        n1 = ds.child(mauth.getInstance().getCurrentUser().getUid()).child("Name of Relief Center").getValue();
                        e1 = ds.child(mauth.getInstance().getCurrentUser().getUid()).child("Email id").getValue();
                        n = String.valueOf(n1);
                        e = String.valueOf(e1);
                        name.setText(n);
                        email.setText(e);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });
        // TODO: Use the ViewModel
        TextView detailed_list = getView().findViewById(R.id.tag1);
        detailed_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), AdminInitialActivity.class);
                startActivity(i);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                user.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}