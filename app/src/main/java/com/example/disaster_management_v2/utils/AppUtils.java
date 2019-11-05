package com.example.disaster_management_v2.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Environment;
import android.view.View;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import Adapter.MyAdapter;
import Model.ListItem;
import androidx.recyclerview.widget.RecyclerView;

public class AppUtils {

    public static Bitmap findViewBitmap(final List<ListItem> currentPDFModels, int deviceWidth, int deviceHeight, MyAdapter pdfRootAdapter, RecyclerView mPDFCreationRV, View mPDFCreationView) {
        pdfRootAdapter.setListData1(currentPDFModels);
        mPDFCreationRV.setAdapter(pdfRootAdapter);
        return getViewBitmap(mPDFCreationView, deviceWidth, deviceHeight);
    }

    private static Bitmap getViewBitmap(View view, int deviceWidth, int deviceHeight) {
        int measuredWidth = View.MeasureSpec.makeMeasureSpec(deviceWidth, View.MeasureSpec.EXACTLY);
        int measuredHeight = View.MeasureSpec.makeMeasureSpec(deviceHeight, View.MeasureSpec.EXACTLY);
        view.measure(measuredWidth, measuredHeight);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        Bitmap b = Bitmap.createBitmap(deviceWidth, deviceHeight, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        view.draw(c);
        return getResizedBitmap(b, (measuredWidth * 80) / 100, (measuredHeight * 80) / 100);
    }

    private static Bitmap getResizedBitmap(Bitmap image, int width, int height) {

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            height = (int) (width / bitmapRatio);
        } else {
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public static String createPDFPath() {
        String foldername = "pdf_creation_by_xml";
        File folder = new File(Environment.getExternalStorageDirectory() + "/" + foldername);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

        String date = simpleDateFormat.format(Calendar.getInstance().getTime());

        return folder + File.separator + "pdf_" + date + ".pdf";
    }


}
