package com.roger.makecoffee.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ResizeBitmap {

    public static Bitmap getBitmapFormUri(Activity ac, Uri uri) throws IOException {
        InputStream input = ac.getContentResolver().openInputStream(uri);
        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;
        onlyBoundsOptions.inDither = true;//optional
        onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
        input.close();
        int originalWidth = onlyBoundsOptions.outWidth;
        int originalHeight = onlyBoundsOptions.outHeight;
        if ((originalWidth == -1) || (originalHeight == -1)) {
            return null;
        }
        //圖片解析度以480x800為標準
        float hh = 800f;//這裡設定高度為800f
        float ww = 480f;//這裡設定寬度為480f
        //縮放比。由於是固定比例縮放，只用高或者寬其中一個數據進行計算即可
        int be = 1;//be=1表示不縮放
        if (originalWidth > originalHeight && originalWidth > ww) { //如果寬度大的話根據寬度固定大小縮放
            be = (int) (originalWidth / ww);
        } else if (originalWidth < originalHeight && originalHeight > hh) { //如果高度高的話根據寬度固定大小縮放
            be = (int) (originalHeight / hh);
        }
        if (be <= 0)
            be = 1;
        //比例壓縮
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = be;//設定縮放比例
        bitmapOptions.inDither = true;//optional
        bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//optional
        input = ac.getContentResolver().openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
        input.close();

        return compressImage(bitmap);//再進行質量壓縮
    }

    public static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//質量壓縮方法，這裡100表示不壓縮，把壓縮後的資料存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {  //迴圈判斷如果壓縮後圖片是否大於100kb,大於繼續壓縮
            baos.reset();//重置baos即清空baos
            //第一個引數 ：圖片格式 ，第二個引數： 圖片質量，100為最高，0為最差  ，第三個引數：儲存壓縮後的資料的流
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//這裡壓縮options%，把壓縮後的資料存放到baos中
            options -= 10;//每次都減少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把壓縮後的資料baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream資料生成圖片
        return bitmap;
    }
}
