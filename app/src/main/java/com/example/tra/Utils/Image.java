package com.example.tra.Utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

import java.io.File;

/**
 * Created by ua on 9/18/2018.
 */



public class Image {

    public String location;
    private Bitmap myBitmap;
    private Bitmap myRoundBitmap;

    Image(String path){
        location = path;
    }

    public Bitmap getBitmap(){
        if(exists()){
            if(myBitmap == null){
                myBitmap = BitmapFactory.decodeFile(location);
                //Log.e("Homeactivity","bitmap new:");
            }else {
                //Log.e("Homeactivity","bitmap reused:");
            }
            return myBitmap;
        }
        return null;
    }

    public Bitmap getBitmap(String path){
        location = path;
        return getBitmap();
    }

    public Bitmap getRoundBitmap(String path){
        location = path;
        return getRoundBitmap();
    }

    public Bitmap getRoundBitmap(){
        if(exists()){
            if(myRoundBitmap == null){
                myBitmap = BitmapFactory.decodeFile(location);
                getRoundedCornerBitmap(myBitmap.getHeight()/2);
                // Log.e("Homeactivity","bitmap new:");
            }else {
                // Log.e("Homeactivity","bitmap reused:");
            }
            return myRoundBitmap;
        }
        return null;
    }



    public Bitmap getRoundedCornerBitmap(int pixels) {

        Bitmap bitmap =  getBitmap();

        Bitmap myRoundBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(myRoundBitmap);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return myRoundBitmap;
    }

    public boolean exists(){
        if(location != null) {
            File imgFile = new File(location);
            if (imgFile.exists()) {
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }




}
