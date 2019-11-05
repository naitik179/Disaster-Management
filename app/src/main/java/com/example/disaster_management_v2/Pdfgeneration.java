package com.example.disaster_management_v2;

import Adapter.MyAdapter;
import Model.Contacts;
import Model.ListItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.disaster_management_v2.utils.PDFCreationUtils;
import com.example.disaster_management_v2.utils.PdfBitmapCache;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.example.disaster_management_v2.AdminInitialActivity.listItems;

public class Pdfgeneration extends AppCompatActivity {

    private boolean IS_MANY_PDF_FILE;

    private int SECTOR = 100; // Default value for one pdf file.
    private int START;
    private int END = SECTOR;
    private int NO_OF_PDF_FILE = 1;
    private int NO_OF_FILE;
    private int LIST_SIZE;
    public PDFCreationUtils pdfCreationUtils;
    private ProgressDialog progressDialog;




    private List<ListItem> pdfModels;
    private TextView btnPdfPath;


    private Button btnSharePdfFile;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfgeneration);


        btnSharePdfFile = (Button) findViewById(R.id.btn_share_pdf);
        btnPdfPath = (TextView) findViewById(R.id.btn_pdf_path);


        findViewById(R.id.btn_create_pdf).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermission();
            }
        });
        pdfModels = listItems;


        RecyclerView rvShowDemo = (RecyclerView) findViewById(R.id.recyclerViewID1);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvShowDemo.setLayoutManager(mLayoutManager);
        MyAdapter myadap = new MyAdapter(this, pdfModels);
        myadap.setListData1(pdfModels);
        rvShowDemo.setAdapter(myadap);


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

        LIST_SIZE = pdfModels.size();
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
        List<ListItem> pdfDataList = pdfModels.subList(START, END);
        PdfBitmapCache.clearMemory();
        PdfBitmapCache.initBitmapCache(getApplicationContext());
       // final PDFCreationUtils pdfCreationUtils = new PDFCreationUtils(AdminInitialActivity.this, pdfDataList, LIST_SIZE, NO_OF_PDF_FILE);
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
                    Toast.makeText(Pdfgeneration.this, "pdf file " + filePath, Toast.LENGTH_LONG).show();
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
                Toast.makeText(Pdfgeneration.this, "Error  " + e.getMessage(), Toast.LENGTH_LONG).show();
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
