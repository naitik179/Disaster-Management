package com.example.disaster_management_v2.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.LruCache;

public class PdfBitmapCache {


    private static LruCache<Integer, Bitmap> memoryCache = null;

    public static void initBitmapCache(Context context) {

        if (memoryCache == null) {
            final int memClass = ((ActivityManager) context.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
            final int cacheSize = 1024 * 1024 * memClass;
            memoryCache = new LruCache<Integer, Bitmap>(cacheSize) {

                @Override
                protected int sizeOf(Integer key, Bitmap bitmap) {
                    return bitmap.getByteCount() / 1024;
                }
            };
        }
    }

    public static void clearMemory() {
        if (memoryCache != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
                memoryCache.evictAll();
            }
        }
    }

    public static void addBitmapToMemoryCache(Integer key, Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            memoryCache.put(key, bitmap);
        }
    }

    public static Bitmap getBitmapFromMemCache(Integer key) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            return memoryCache.get(key);
        }
        else{
            return null;
        }
    }
}


