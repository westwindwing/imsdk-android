package com.qunar.im.base.util.graphics;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

public class BitmapHelper {
    public static Bitmap decodeFile(String pathName, int thumbWidth, int thumbHeight) {
        Options newOpts = new Options();
        newOpts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathName, newOpts);
        newOpts.inSampleSize = ImageUtils.computeSampleSize(newOpts, -1, thumbHeight * thumbWidth);
        newOpts.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(pathName, newOpts);
    }

    public static Bitmap decodeFile(String pathName) {
        return BitmapFactory.decodeFile(pathName);
    }

    public static Bitmap decodeResource(Resources res, int id) {
        Bitmap bitmap = null;
        try {
            Bitmap original = BitmapFactory.decodeResource(res, id);
            bitmap = original.copy(Config.ARGB_8888, true);
            original.recycle();
            return bitmap;
        } catch (OutOfMemoryError e) {
            return bitmap;
        }
    }
}
