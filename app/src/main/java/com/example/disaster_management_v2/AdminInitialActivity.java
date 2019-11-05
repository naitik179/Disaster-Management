package com.example.disaster_management_v2;

import android.app.LauncherActivity;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;


import androidx.recyclerview.widget.RecyclerView;

import com.example.disaster_management_v2.utils.PDFCreationUtils;
import com.example.disaster_management_v2.utils.PdfBitmapCache;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Adapter.MyAdapter;
import Model.ListItem;

public class AdminInitialActivity extends AppCompatActivity {
    private RecyclerView  recyclerView;
    private RecyclerView.Adapter adapter,adapter1;
    public static List<ListItem> listItems,listItems1;
    private EditText getDate;
    String date;
    Button getInmates;
    TextView t1;
    FirebaseAuth mFirebaseAuth;
    private DatabaseReference mReg;

    private TextView btnPdfPath;


    private Button btnSharePdfFile;


    private boolean IS_MANY_PDF_FILE;

    private int SECTOR = 100; // Default value for one pdf file.
    private int START;
    private int END = SECTOR;
    private int NO_OF_PDF_FILE = 1;
    private int NO_OF_FILE;
    private int LIST_SIZE;
    public PDFCreationUtils pdfCreationUtils;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfgeneration);

        mFirebaseAuth=FirebaseAuth.getInstance();
        getDate=findViewById(R.id.getDate);
        getInmates=findViewById(R.id.getInmates);

        t1=findViewById(R.id.t1);
        mReg= FirebaseDatabase.getInstance().getReference().child("Affected_People").child(mFirebaseAuth.getInstance().getCurrentUser().getUid());

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewID1_pdf);
        recyclerView.setHasFixedSize(true);
        //every item has a fixed size
        recyclerView.setLayoutManager(new
                LinearLayoutManager(this));

        findViewById(R.id.btn_create_pdf).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermission();
            }
        });
        /*recyclerView1 = (RecyclerView) findViewById(R.id.recyclerViewID2);
        recyclerView1.setHasFixedSize(true);
        *///every item has a fixed size
        /*ecyclerView1.setLayoutManager(new
                LinearLayoutManager(this));
*/
        listItems = new ArrayList<>();
        listItems1 = new ArrayList<>();
        btnSharePdfFile = (Button) findViewById(R.id.btn_share_pdf);
        btnPdfPath = (TextView) findViewById(R.id.btn_pdf_path);
        //RC_list=new ArrayList<>();

        getInmates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date=getDate.getText().toString();
                if(getDate.getText().length()==0){
                    getDate.setError("Date cannot be Empty");
                    getDate.requestFocus();
                }
                else if(date.indexOf("/")==2||date.indexOf("/")==5||date.indexOf("/")==4){
                    getDate.setError("Enter Date in DD-MM-YYYY format");
                    getDate.requestFocus();
                }
                else{
                    mReg.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            TextView Total_Count=findViewById(R.id.totalInmates);
                            boolean flag=false;
                            int i=1;

                            if(dataSnapshot.child(date).exists()) {
                                //EditText value=findViewById(R.id.RC_num);

                                //Map<Object, String> data = (Map<Object, String>) ds.getValue();
                                //for(DataSnapshot ds:dataSnapshot.get)
                                listItems.clear();
                                t1.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.VISIBLE);
                                for (DataSnapshot Aadhars : dataSnapshot.child(getDate.getText().toString()).getChildren()) {

                                    ListItem listItem = new ListItem(
                                            "Name : " + String.valueOf(Aadhars.child("Name").getValue()),
                                            "Gender : " + String.valueOf(Aadhars.child("Gender").getValue()),
                                            "Age : " + String.valueOf(Aadhars.child("Age").getValue()),
                                            "Address : " + String.valueOf(Aadhars.child("Address").getValue())

                                    );
                                    listItems.add(listItem);

                                    //RC_list.add(i,listItem);

                                    for (DataSnapshot Familys : Aadhars.child("Other Family Members").getChildren()) {
                                        Log.i("Info", "onDataChange: Family :" + Familys.child("Name").getValue());
                                        ListItem listItem1 = new ListItem(
                                                "Name : " + String.valueOf(Familys.child("Name").getValue()),
                                                "Gender : " + String.valueOf(Familys.child("Gender").getValue()),
                                                "Age : " + String.valueOf(Familys.child("Age").getValue()),
                                                "Address : " + String.valueOf(Aadhars.child("Address").getValue())

                                        );
                                        listItems.add(listItem1);
                                        //RC_list.add(listItem1);
                                        i++;

                                        // RC_list.add(i,listItem);
                                    }

                                    i++;
                                }
                            }
                            else{
                                Toast.makeText(AdminInitialActivity.this,"No Inmates Registered on this Date",Toast.LENGTH_LONG).show();
                                t1.setVisibility(View.INVISIBLE);
                                recyclerView.setVisibility(View.INVISIBLE);
                            }

                            recyclerView.setAdapter(adapter1);
                            recyclerView .setAdapter(adapter);
                            //recyclerView1.setAdapter(adapter2);
                            Total_Count.setText(String.valueOf(i-1));
                            Toast.makeText(AdminInitialActivity.this, "Total Inmates count :"+(i-1), Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }
            }
        });
        adapter = new MyAdapter(this, listItems);
        adapter1 = new MyAdapter(this, listItems1);
        //adapter2 = new MyAdapter(this, RC_list);



    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 111 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            generatePdfReport();
        }
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 111);
        } else {
            generatePdfReport();
        }
    }



    private void generatePdfReport() {

        LIST_SIZE = listItems.size();
        NO_OF_FILE = LIST_SIZE / SECTOR;
        if (LIST_SIZE % SECTOR != 0) {
            NO_OF_FILE++;
        }
        if (LIST_SIZE > SECTOR) {
            IS_MANY_PDF_FILE = true;
        } else {
            END = LIST_SIZE;
        }
        createPDFFile();
    }

    private void createProgressBarForPDFCreation(int maxProgress) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(String.format(getString(R.string.msg_progress_pdf), String.valueOf(maxProgress)));
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(maxProgress);
        progressDialog.show();
    }

    private void createProgressBarForMergePDF() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.msg_progress_merger_pdf));
        progressDialog.setCancelable(false);
        progressDialog.show();
    }




    private void createPDFFile() {
        List<ListItem> pdfDataList = listItems.subList(START, END);
        PdfBitmapCache.clearMemory();
        PdfBitmapCache.initBitmapCache(getApplicationContext());
        final PDFCreationUtils pdfCreationUtils = new PDFCreationUtils(AdminInitialActivity.this, pdfDataList, LIST_SIZE, NO_OF_PDF_FILE);
        if (NO_OF_PDF_FILE == 1) {
            createProgressBarForPDFCreation(PDFCreationUtils.TOTAL_PROGRESS_BAR);
        }
        pdfCreationUtils.createPDF(new PDFCreationUtils.PDFCallback() {

            @Override
            public void onProgress(final int i) {
                progressDialog.setProgress(i);
            }

            @Override
            public void onCreateEveryPdfFile() {
                // Execute may pdf files and this is depend on NO_OF_FILE
                if (IS_MANY_PDF_FILE) {
                    NO_OF_PDF_FILE++;
                    if (NO_OF_FILE == NO_OF_PDF_FILE - 1) {

                        progressDialog.dismiss();
                        createProgressBarForMergePDF();
                        pdfCreationUtils.downloadAndCombinePDFs();
                    } else {

                        // This is identify to manage sub list of current pdf model list data with START and END

                        START = END;
                        if (LIST_SIZE % SECTOR != 0) {
                            if (NO_OF_FILE == NO_OF_PDF_FILE) {
                                END = (START - SECTOR) + LIST_SIZE % SECTOR;
                            }
                        }
                        END = SECTOR + END;
                        createPDFFile();
                    }

                } else {
                    // Merge one pdf file when all file is downloaded
                    progressDialog.dismiss();

                    createProgressBarForMergePDF();
                    pdfCreationUtils.downloadAndCombinePDFs();
                }

            }

            @Override
            public void onComplete(final String filePath) {
                progressDialog.dismiss();

                if (filePath != null) {
                    btnPdfPath.setVisibility(View.VISIBLE);
                    btnPdfPath.setText("PDF path : " + filePath);
                    Toast.makeText(AdminInitialActivity.this, "pdf file " + filePath, Toast.LENGTH_LONG).show();
                    btnSharePdfFile.setVisibility(View.VISIBLE);
                    btnSharePdfFile.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            sharePdf(filePath);
                        }
                    });

                }
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(AdminInitialActivity.this, "Error  " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    private void sharePdf(String fileName) {
        final Intent emailIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
        emailIntent.setType("text/plain");
        emailIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        ArrayList<Uri> uris = new ArrayList<Uri>();
        File fileIn = new File(fileName);
        Uri u = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID, fileIn);

        uris.add(u);
        emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
        try {
            startActivity(Intent.createChooser(emailIntent, getString(R.string.send_to)));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, getString(R.string.error_file), Toast.LENGTH_SHORT).show();
        }
    }
}
