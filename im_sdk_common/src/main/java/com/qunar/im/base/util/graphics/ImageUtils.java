package com.qunar.im.base.util.graphics;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Region.Op;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.TextView;
import android.widget.Toast;
import com.qunar.im.base.util.LogUtil;
import com.qunar.im.common.CommonConfig;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.List;

public final class ImageUtils {
    public static final String TAG = "ImageUtils";

    public static class FastBlur {
        public static Bitmap doBlur(Bitmap sentBitmap, int radius, boolean canReuseInBitmap) {
            Bitmap bitmap;
            if (canReuseInBitmap) {
                bitmap = sentBitmap;
            } else {
                bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);
            }
            if (radius < 1) {
                return null;
            }
            int i;
            int y;
            int bsum;
            int gsum;
            int rsum;
            int boutsum;
            int goutsum;
            int routsum;
            int binsum;
            int ginsum;
            int rinsum;
            int p;
            int[] sir;
            int rbs;
            int stackpointer;
            int x;
            int w = bitmap.getWidth();
            int h = bitmap.getHeight();
            int[] pix = new int[(w * h)];
            bitmap.getPixels(pix, 0, w, 0, 0, w, h);
            int wm = w - 1;
            int hm = h - 1;
            int wh = w * h;
            int div = (radius + radius) + 1;
            int[] r = new int[wh];
            int[] g = new int[wh];
            int[] b = new int[wh];
            int[] vmin = new int[Math.max(w, h)];
            int divsum = (div + 1) >> 1;
            divsum *= divsum;
            int[] dv = new int[(divsum * 256)];
            for (i = 0; i < divsum * 256; i++) {
                dv[i] = i / divsum;
            }
            int yi = 0;
            int yw = 0;
            int[][] stack = (int[][]) Array.newInstance(Integer.TYPE, new int[]{div, 3});
            int r1 = radius + 1;
            for (y = 0; y < h; y++) {
                bsum = 0;
                gsum = 0;
                rsum = 0;
                boutsum = 0;
                goutsum = 0;
                routsum = 0;
                binsum = 0;
                ginsum = 0;
                rinsum = 0;
                for (i = -radius; i <= radius; i++) {
                    p = pix[Math.min(wm, Math.max(i, 0)) + yi];
                    sir = stack[i + radius];
                    sir[0] = (16711680 & p) >> 16;
                    sir[1] = (65280 & p) >> 8;
                    sir[2] = p & 255;
                    rbs = r1 - Math.abs(i);
                    rsum += sir[0] * rbs;
                    gsum += sir[1] * rbs;
                    bsum += sir[2] * rbs;
                    if (i > 0) {
                        rinsum += sir[0];
                        ginsum += sir[1];
                        binsum += sir[2];
                    } else {
                        routsum += sir[0];
                        goutsum += sir[1];
                        boutsum += sir[2];
                    }
                }
                stackpointer = radius;
                for (x = 0; x < w; x++) {
                    r[yi] = dv[rsum];
                    g[yi] = dv[gsum];
                    b[yi] = dv[bsum];
                    rsum -= routsum;
                    gsum -= goutsum;
                    bsum -= boutsum;
                    sir = stack[((stackpointer - radius) + div) % div];
                    routsum -= sir[0];
                    goutsum -= sir[1];
                    boutsum -= sir[2];
                    if (y == 0) {
                        vmin[x] = Math.min((x + radius) + 1, wm);
                    }
                    p = pix[vmin[x] + yw];
                    sir[0] = (16711680 & p) >> 16;
                    sir[1] = (65280 & p) >> 8;
                    sir[2] = p & 255;
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                    rsum += rinsum;
                    gsum += ginsum;
                    bsum += binsum;
                    stackpointer = (stackpointer + 1) % div;
                    sir = stack[stackpointer % div];
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                    rinsum -= sir[0];
                    ginsum -= sir[1];
                    binsum -= sir[2];
                    yi++;
                }
                yw += w;
            }
            for (x = 0; x < w; x++) {
                bsum = 0;
                gsum = 0;
                rsum = 0;
                boutsum = 0;
                goutsum = 0;
                routsum = 0;
                binsum = 0;
                ginsum = 0;
                rinsum = 0;
                int yp = (-radius) * w;
                for (i = -radius; i <= radius; i++) {
                    yi = Math.max(0, yp) + x;
                    sir = stack[i + radius];
                    sir[0] = r[yi];
                    sir[1] = g[yi];
                    sir[2] = b[yi];
                    rbs = r1 - Math.abs(i);
                    rsum += r[yi] * rbs;
                    gsum += g[yi] * rbs;
                    bsum += b[yi] * rbs;
                    if (i > 0) {
                        rinsum += sir[0];
                        ginsum += sir[1];
                        binsum += sir[2];
                    } else {
                        routsum += sir[0];
                        goutsum += sir[1];
                        boutsum += sir[2];
                    }
                    if (i < hm) {
                        yp += w;
                    }
                }
                yi = x;
                stackpointer = radius;
                for (y = 0; y < h; y++) {
                    pix[yi] = (((-16777216 & pix[yi]) | (dv[rsum] << 16)) | (dv[gsum] << 8)) | dv[bsum];
                    rsum -= routsum;
                    gsum -= goutsum;
                    bsum -= boutsum;
                    sir = stack[((stackpointer - radius) + div) % div];
                    routsum -= sir[0];
                    goutsum -= sir[1];
                    boutsum -= sir[2];
                    if (x == 0) {
                        vmin[y] = Math.min(y + r1, hm) * w;
                    }
                    p = x + vmin[y];
                    sir[0] = r[p];
                    sir[1] = g[p];
                    sir[2] = b[p];
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                    rsum += rinsum;
                    gsum += ginsum;
                    bsum += binsum;
                    stackpointer = (stackpointer + 1) % div;
                    sir = stack[stackpointer];
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                    rinsum -= sir[0];
                    ginsum -= sir[1];
                    binsum -= sir[2];
                    yi += w;
                }
            }
            bitmap.setPixels(pix, 0, w, 0, 0, w, h);
            return bitmap;
        }
    }

    public enum ImageType {
        BMP,
        JPEG,
        GIF,
        PNG,
        NONE
    }

    public static Bitmap compressBimap(String mPath, Activity activity) {
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        int screenWidth = metric.widthPixels;
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mPath, options);
        options.inPreferredConfig = Config.RGB_565;
        options.inSampleSize = calculateInSampleSize(options, screenWidth);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(mPath, options);
    }

    public static int calculateInSampleSize(Options options, int reqWidth, int reqHeight) {
        int height = options.outHeight;
        int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            int halfHeight = height / 2;
            int halfWidth = width / 2;
            while (halfHeight / inSampleSize > reqHeight && halfWidth / inSampleSize > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    public static int calculateInSampleSize(Options options, int reqWidth) {
        int inSampleSize = 1;
        while (options.outWidth / inSampleSize > reqWidth) {
            inSampleSize *= 2;
        }
        return inSampleSize;
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:23:0x0030=Splitter:B:23:0x0030, B:15:0x0021=Splitter:B:15:0x0021} */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0026 A:{SYNTHETIC, Splitter:B:18:0x0026} */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0035 A:{SYNTHETIC, Splitter:B:26:0x0035} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0041 A:{SYNTHETIC, Splitter:B:32:0x0041} */
    private static byte[] toByteArray(java.io.File r5, int r6) {
        /*
        r2 = 0;
        r0 = new byte[r6];
        r4 = r5.exists();
        if (r4 != 0) goto L_0x000a;
    L_0x0009:
        return r0;
    L_0x000a:
        r3 = new java.io.FileInputStream;	 Catch:{ FileNotFoundException -> 0x0020, IOException -> 0x002f }
        r3.<init>(r5);	 Catch:{ FileNotFoundException -> 0x0020, IOException -> 0x002f }
        r4 = 0;
        r3.read(r0, r4, r6);	 Catch:{ FileNotFoundException -> 0x0050, IOException -> 0x004d, all -> 0x004a }
        if (r3 == 0) goto L_0x0053;
    L_0x0015:
        r3.close();	 Catch:{ IOException -> 0x001a }
        r2 = r3;
        goto L_0x0009;
    L_0x001a:
        r1 = move-exception;
        r1.printStackTrace();
        r2 = r3;
        goto L_0x0009;
    L_0x0020:
        r1 = move-exception;
    L_0x0021:
        r1.printStackTrace();	 Catch:{ all -> 0x003e }
        if (r2 == 0) goto L_0x0009;
    L_0x0026:
        r2.close();	 Catch:{ IOException -> 0x002a }
        goto L_0x0009;
    L_0x002a:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0009;
    L_0x002f:
        r1 = move-exception;
    L_0x0030:
        r1.printStackTrace();	 Catch:{ all -> 0x003e }
        if (r2 == 0) goto L_0x0009;
    L_0x0035:
        r2.close();	 Catch:{ IOException -> 0x0039 }
        goto L_0x0009;
    L_0x0039:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0009;
    L_0x003e:
        r4 = move-exception;
    L_0x003f:
        if (r2 == 0) goto L_0x0044;
    L_0x0041:
        r2.close();	 Catch:{ IOException -> 0x0045 }
    L_0x0044:
        throw r4;
    L_0x0045:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0044;
    L_0x004a:
        r4 = move-exception;
        r2 = r3;
        goto L_0x003f;
    L_0x004d:
        r1 = move-exception;
        r2 = r3;
        goto L_0x0030;
    L_0x0050:
        r1 = move-exception;
        r2 = r3;
        goto L_0x0021;
    L_0x0053:
        r2 = r3;
        goto L_0x0009;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qunar.im.base.util.graphics.ImageUtils.toByteArray(java.io.File, int):byte[]");
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x003e A:{SYNTHETIC, Splitter:B:21:0x003e} */
    /* JADX WARNING: Removed duplicated region for block: B:45:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0043 A:{Catch:{ IOException -> 0x0047 }} */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x004c A:{SYNTHETIC, Splitter:B:28:0x004c} */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0051 A:{Catch:{ IOException -> 0x0055 }} */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x003e A:{SYNTHETIC, Splitter:B:21:0x003e} */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0043 A:{Catch:{ IOException -> 0x0047 }} */
    /* JADX WARNING: Removed duplicated region for block: B:45:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x004c A:{SYNTHETIC, Splitter:B:28:0x004c} */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0051 A:{Catch:{ IOException -> 0x0055 }} */
    private static boolean copy(java.io.File r9, java.io.File r10) {
        /*
        r6 = 0;
        r0 = 0;
        r2 = 0;
        r1 = new java.io.BufferedInputStream;	 Catch:{ IOException -> 0x003b, all -> 0x0049 }
        r7 = new java.io.FileInputStream;	 Catch:{ IOException -> 0x003b, all -> 0x0049 }
        r7.<init>(r9);	 Catch:{ IOException -> 0x003b, all -> 0x0049 }
        r1.<init>(r7);	 Catch:{ IOException -> 0x003b, all -> 0x0049 }
        r3 = new java.io.BufferedOutputStream;	 Catch:{ IOException -> 0x005e, all -> 0x0057 }
        r7 = new java.io.FileOutputStream;	 Catch:{ IOException -> 0x005e, all -> 0x0057 }
        r8 = 0;
        r7.<init>(r10, r8);	 Catch:{ IOException -> 0x005e, all -> 0x0057 }
        r3.<init>(r7);	 Catch:{ IOException -> 0x005e, all -> 0x0057 }
        r7 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r4 = new byte[r7];	 Catch:{ IOException -> 0x0061, all -> 0x005a }
        r1.read(r4);	 Catch:{ IOException -> 0x0061, all -> 0x005a }
    L_0x001f:
        r3.write(r4);	 Catch:{ IOException -> 0x0061, all -> 0x005a }
        r7 = r1.read(r4);	 Catch:{ IOException -> 0x0061, all -> 0x005a }
        r8 = -1;
        if (r7 != r8) goto L_0x001f;
    L_0x0029:
        if (r1 == 0) goto L_0x002e;
    L_0x002b:
        r1.close();	 Catch:{ IOException -> 0x0037 }
    L_0x002e:
        if (r3 == 0) goto L_0x0033;
    L_0x0030:
        r3.close();	 Catch:{ IOException -> 0x0037 }
    L_0x0033:
        r6 = 1;
        r2 = r3;
        r0 = r1;
    L_0x0036:
        return r6;
    L_0x0037:
        r5 = move-exception;
        r2 = r3;
        r0 = r1;
        goto L_0x0036;
    L_0x003b:
        r5 = move-exception;
    L_0x003c:
        if (r0 == 0) goto L_0x0041;
    L_0x003e:
        r0.close();	 Catch:{ IOException -> 0x0047 }
    L_0x0041:
        if (r2 == 0) goto L_0x0036;
    L_0x0043:
        r2.close();	 Catch:{ IOException -> 0x0047 }
        goto L_0x0036;
    L_0x0047:
        r5 = move-exception;
        goto L_0x0036;
    L_0x0049:
        r7 = move-exception;
    L_0x004a:
        if (r0 == 0) goto L_0x004f;
    L_0x004c:
        r0.close();	 Catch:{ IOException -> 0x0055 }
    L_0x004f:
        if (r2 == 0) goto L_0x0054;
    L_0x0051:
        r2.close();	 Catch:{ IOException -> 0x0055 }
    L_0x0054:
        throw r7;
    L_0x0055:
        r5 = move-exception;
        goto L_0x0036;
    L_0x0057:
        r7 = move-exception;
        r0 = r1;
        goto L_0x004a;
    L_0x005a:
        r7 = move-exception;
        r2 = r3;
        r0 = r1;
        goto L_0x004a;
    L_0x005e:
        r5 = move-exception;
        r0 = r1;
        goto L_0x003c;
    L_0x0061:
        r5 = move-exception;
        r2 = r3;
        r0 = r1;
        goto L_0x003c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qunar.im.base.util.graphics.ImageUtils.copy(java.io.File, java.io.File):boolean");
    }

    public static void compressFile(File src, long maxSize, int maxW, int maxH, File target) {
        Options newOpts = new Options();
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(src.getAbsolutePath(), newOpts);
        if (adjustImageType(toByteArray(src, 4)) == ImageType.GIF) {
            copy(src, target);
            return;
        }
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        int be = 1;
        if (w > h && w > maxW) {
            be = (int) ((((float) newOpts.outWidth) * 1.0f) / ((float) maxW));
        } else if (w < h && h > maxH) {
            be = (int) ((((float) newOpts.outHeight) * 1.0f) / ((float) maxH));
        }
        if (be <= 0) {
            be = 1;
        }
        newOpts.inSampleSize = be;
        Bitmap newBitmap = BitmapFactory.decodeFile(src.getAbsolutePath(), newOpts);
        compressImage(newBitmap, maxSize, target);
        if (bitmap != null) {
            bitmap.recycle();
        }
        if (newBitmap != null) {
            newBitmap.recycle();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0034 A:{SYNTHETIC, Splitter:B:15:0x0034} */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0039 A:{SYNTHETIC, Splitter:B:18:0x0039} */
    /* JADX WARNING: Removed duplicated region for block: B:81:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x003e A:{SYNTHETIC, Splitter:B:21:0x003e} */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00a3 A:{SYNTHETIC, Splitter:B:53:0x00a3} */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00a8 A:{SYNTHETIC, Splitter:B:56:0x00a8} */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00ad A:{SYNTHETIC, Splitter:B:59:0x00ad} */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0034 A:{SYNTHETIC, Splitter:B:15:0x0034} */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0039 A:{SYNTHETIC, Splitter:B:18:0x0039} */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x003e A:{SYNTHETIC, Splitter:B:21:0x003e} */
    /* JADX WARNING: Removed duplicated region for block: B:81:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00a3 A:{SYNTHETIC, Splitter:B:53:0x00a3} */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00a8 A:{SYNTHETIC, Splitter:B:56:0x00a8} */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00ad A:{SYNTHETIC, Splitter:B:59:0x00ad} */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0034 A:{SYNTHETIC, Splitter:B:15:0x0034} */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0039 A:{SYNTHETIC, Splitter:B:18:0x0039} */
    /* JADX WARNING: Removed duplicated region for block: B:81:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x003e A:{SYNTHETIC, Splitter:B:21:0x003e} */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00a3 A:{SYNTHETIC, Splitter:B:53:0x00a3} */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00a8 A:{SYNTHETIC, Splitter:B:56:0x00a8} */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00ad A:{SYNTHETIC, Splitter:B:59:0x00ad} */
    private static void compressImage(android.graphics.Bitmap r11, long r12, java.io.File r14) {
        /*
        if (r11 != 0) goto L_0x0003;
    L_0x0002:
        return;
    L_0x0003:
        r3 = 0;
        r0 = 0;
        r5 = 0;
        r1 = new java.io.ByteArrayOutputStream;	 Catch:{ IOException -> 0x00d8 }
        r1.<init>();	 Catch:{ IOException -> 0x00d8 }
        r8 = android.graphics.Bitmap.CompressFormat.JPEG;	 Catch:{ IOException -> 0x0029, all -> 0x00cc }
        r9 = 100;
        r11.compress(r8, r9, r1);	 Catch:{ IOException -> 0x0029, all -> 0x00cc }
        r7 = 100;
    L_0x0014:
        r8 = r1.toByteArray();	 Catch:{ IOException -> 0x0029, all -> 0x00cc }
        r8 = r8.length;	 Catch:{ IOException -> 0x0029, all -> 0x00cc }
        r8 = (long) r8;	 Catch:{ IOException -> 0x0029, all -> 0x00cc }
        r8 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1));
        if (r8 <= 0) goto L_0x004b;
    L_0x001e:
        r1.reset();	 Catch:{ IOException -> 0x0029, all -> 0x00cc }
        r7 = r7 + -10;
        r8 = android.graphics.Bitmap.CompressFormat.JPEG;	 Catch:{ IOException -> 0x0029, all -> 0x00cc }
        r11.compress(r8, r7, r1);	 Catch:{ IOException -> 0x0029, all -> 0x00cc }
        goto L_0x0014;
    L_0x0029:
        r2 = move-exception;
        r0 = r1;
    L_0x002b:
        r8 = "ImageUtils";
        r9 = "IO Exception";
        com.qunar.im.base.util.LogUtil.e(r8, r9, r2);	 Catch:{ all -> 0x00a0 }
        if (r3 == 0) goto L_0x0037;
    L_0x0034:
        r3.close();	 Catch:{ IOException -> 0x008e }
    L_0x0037:
        if (r0 == 0) goto L_0x003c;
    L_0x0039:
        r0.close();	 Catch:{ IOException -> 0x0097 }
    L_0x003c:
        if (r5 == 0) goto L_0x0002;
    L_0x003e:
        r5.close();	 Catch:{ IOException -> 0x0042 }
        goto L_0x0002;
    L_0x0042:
        r2 = move-exception;
        r8 = "ImageUtils";
        r9 = "IO Exception";
        com.qunar.im.base.util.LogUtil.e(r8, r9, r2);
        goto L_0x0002;
    L_0x004b:
        r6 = new java.io.ByteArrayInputStream;	 Catch:{ IOException -> 0x0029, all -> 0x00cc }
        r8 = r1.toByteArray();	 Catch:{ IOException -> 0x0029, all -> 0x00cc }
        r6.<init>(r8);	 Catch:{ IOException -> 0x0029, all -> 0x00cc }
        r4 = new java.io.FileOutputStream;	 Catch:{ IOException -> 0x00db, all -> 0x00cf }
        r4.<init>(r14);	 Catch:{ IOException -> 0x00db, all -> 0x00cf }
        r1.writeTo(r4);	 Catch:{ IOException -> 0x00e0, all -> 0x00d3 }
        if (r4 == 0) goto L_0x0061;
    L_0x005e:
        r4.close();	 Catch:{ IOException -> 0x006f }
    L_0x0061:
        if (r1 == 0) goto L_0x0066;
    L_0x0063:
        r1.close();	 Catch:{ IOException -> 0x0078 }
    L_0x0066:
        if (r6 == 0) goto L_0x00e6;
    L_0x0068:
        r6.close();	 Catch:{ IOException -> 0x0081 }
        r5 = r6;
        r0 = r1;
        r3 = r4;
        goto L_0x0002;
    L_0x006f:
        r2 = move-exception;
        r8 = "ImageUtils";
        r9 = "IO Exception";
        com.qunar.im.base.util.LogUtil.e(r8, r9, r2);
        goto L_0x0061;
    L_0x0078:
        r2 = move-exception;
        r8 = "ImageUtils";
        r9 = "IO Exception";
        com.qunar.im.base.util.LogUtil.e(r8, r9, r2);
        goto L_0x0066;
    L_0x0081:
        r2 = move-exception;
        r8 = "ImageUtils";
        r9 = "IO Exception";
        com.qunar.im.base.util.LogUtil.e(r8, r9, r2);
        r5 = r6;
        r0 = r1;
        r3 = r4;
        goto L_0x0002;
    L_0x008e:
        r2 = move-exception;
        r8 = "ImageUtils";
        r9 = "IO Exception";
        com.qunar.im.base.util.LogUtil.e(r8, r9, r2);
        goto L_0x0037;
    L_0x0097:
        r2 = move-exception;
        r8 = "ImageUtils";
        r9 = "IO Exception";
        com.qunar.im.base.util.LogUtil.e(r8, r9, r2);
        goto L_0x003c;
    L_0x00a0:
        r8 = move-exception;
    L_0x00a1:
        if (r3 == 0) goto L_0x00a6;
    L_0x00a3:
        r3.close();	 Catch:{ IOException -> 0x00b1 }
    L_0x00a6:
        if (r0 == 0) goto L_0x00ab;
    L_0x00a8:
        r0.close();	 Catch:{ IOException -> 0x00ba }
    L_0x00ab:
        if (r5 == 0) goto L_0x00b0;
    L_0x00ad:
        r5.close();	 Catch:{ IOException -> 0x00c3 }
    L_0x00b0:
        throw r8;
    L_0x00b1:
        r2 = move-exception;
        r9 = "ImageUtils";
        r10 = "IO Exception";
        com.qunar.im.base.util.LogUtil.e(r9, r10, r2);
        goto L_0x00a6;
    L_0x00ba:
        r2 = move-exception;
        r9 = "ImageUtils";
        r10 = "IO Exception";
        com.qunar.im.base.util.LogUtil.e(r9, r10, r2);
        goto L_0x00ab;
    L_0x00c3:
        r2 = move-exception;
        r9 = "ImageUtils";
        r10 = "IO Exception";
        com.qunar.im.base.util.LogUtil.e(r9, r10, r2);
        goto L_0x00b0;
    L_0x00cc:
        r8 = move-exception;
        r0 = r1;
        goto L_0x00a1;
    L_0x00cf:
        r8 = move-exception;
        r5 = r6;
        r0 = r1;
        goto L_0x00a1;
    L_0x00d3:
        r8 = move-exception;
        r5 = r6;
        r0 = r1;
        r3 = r4;
        goto L_0x00a1;
    L_0x00d8:
        r2 = move-exception;
        goto L_0x002b;
    L_0x00db:
        r2 = move-exception;
        r5 = r6;
        r0 = r1;
        goto L_0x002b;
    L_0x00e0:
        r2 = move-exception;
        r5 = r6;
        r0 = r1;
        r3 = r4;
        goto L_0x002b;
    L_0x00e6:
        r5 = r6;
        r0 = r1;
        r3 = r4;
        goto L_0x0002;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qunar.im.base.util.graphics.ImageUtils.compressImage(android.graphics.Bitmap, long, java.io.File):void");
    }

    public static File compressFile(Bitmap bitmap, File target) {
        try {
            target.createNewFile();
            bitmap.compress(CompressFormat.JPEG, 85, new FileOutputStream(target));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return target;
    }

    public static File compressFile(File file) {
        return compressFile(file, null);
    }

    public static File compressFile(File source, File target) {
        Bitmap bitmap = getThumbnail(source, 0);
        if (target == null) {
            target = new File(source.getParent(), "compress_" + source.getName());
        }
        if (target.exists()) {
            target.delete();
        }
        File file = compressFile(bitmap, target);
        if (bitmap != null) {
            bitmap.recycle();
        }
        return file;
    }

    public static boolean saveBitmap(Bitmap bitmap, File file) {
        if (bitmap != null) {
            if (file.exists()) {
                file.delete();
            }
            try {
                file.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                bitmap.compress(CompressFormat.JPEG, 85, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public static int computeSampleSize(int w, int h, int minSideLength, int maxNumOfPixels) {
        Options options = new Options();
        options.outHeight = h;
        options.outWidth = w;
        int initialSize = computeInitialSampleSize(options, minSideLength, maxNumOfPixels);
        if (initialSize > 8) {
            return ((initialSize + 7) / 8) * 8;
        }
        int roundedSize = 1;
        while (roundedSize < initialSize) {
            roundedSize <<= 1;
        }
        return roundedSize;
    }

    public static int computeSampleSize(Options options, int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength, maxNumOfPixels);
        if (initialSize > 8) {
            return ((initialSize + 7) / 8) * 8;
        }
        int roundedSize = 1;
        while (roundedSize < initialSize) {
            roundedSize <<= 1;
        }
        return roundedSize;
    }

    private static int computeInitialSampleSize(Options options, int minSideLength, int maxNumOfPixels) {
        double w = (double) options.outWidth;
        double h = (double) options.outHeight;
        int lowerBound = maxNumOfPixels == -1 ? 1 : (int) Math.ceil(Math.sqrt((w * h) / ((double) maxNumOfPixels)));
        int upperBound = minSideLength == -1 ? 128 : (int) Math.min(Math.floor(w / ((double) minSideLength)), Math.floor(h / ((double) minSideLength)));
        if (upperBound < lowerBound) {
            return lowerBound;
        }
        if (maxNumOfPixels == -1 && minSideLength == -1) {
            return 1;
        }
        if (minSideLength != -1) {
            return upperBound;
        }
        return lowerBound;
    }

    public static Bitmap getThumbnail(File imageFile, int sampleSize) {
        Bitmap bitmap = null;
        try {
            Options options = new Options();
            options.inJustDecodeBounds = true;
            bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath(), options);
            options.inSampleSize = computeSampleSize(options, -1, sampleSize * sampleSize);
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeFile(imageFile.getAbsolutePath(), options);
        } catch (OutOfMemoryError e) {
            System.gc();
            try {
                Method m = Runtime.class.getDeclaredMethod("runFinalization", new Class[]{Boolean.TYPE});
                m.setAccessible(true);
                m.invoke(Runtime.getRuntime(), new Object[]{Boolean.valueOf(true)});
                return bitmap;
            } catch (Exception e2) {
                return bitmap;
            }
        }
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != -1 ? Config.ARGB_8888 : Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static Display getScreenDisplay(Activity activity) {
        return activity.getWindowManager().getDefaultDisplay();
    }

    public static Drawable getEqualHeightDrawable(TextView textView, int resId) {
        Drawable drawable = null;
        if (resId <= 0) {
            return null;
        }
        try {
            drawable = textView.getContext().getResources().getDrawable(resId);
            drawable.setBounds(0, 0, (int) textView.getPaint().getTextSize(), (int) textView.getPaint().getTextSize());
            return drawable;
        } catch (Exception e) {
            return drawable;
        }
    }

    public static Options getImageSize(String path) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        return options;
    }

    public static Bitmap rotate(Bitmap b, int degrees) {
        if (degrees == 0 || b == null) {
            return b;
        }
        Matrix m = new Matrix();
        m.setRotate((float) degrees, (float) (b.getWidth() / 2), (float) (b.getHeight() / 2));
        try {
            Bitmap b2 = Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(), m, true);
            if (b == b2) {
                return b;
            }
            b.recycle();
            return b2;
        } catch (OutOfMemoryError e) {
            return b;
        }
    }

    public static ImageType adjustImageType(byte[] image) {
        if (image.length < 2) {
            return ImageType.NONE;
        }
        if (image[0] == (byte) 66 && image[1] == (byte) 77) {
            return ImageType.BMP;
        }
        if (image[0] == (byte) -1 && image[1] == (byte) -40) {
            return ImageType.JPEG;
        }
        if (image[0] == (byte) 71 && image[1] == (byte) 73 && image[2] == (byte) 70) {
            return ImageType.GIF;
        }
        if (image[0] == (byte) -119 && image[1] == (byte) 80 && image[2] == (byte) 78 && image[3] == (byte) 71) {
            return ImageType.PNG;
        }
        return ImageType.NONE;
    }

    public static StateListDrawable createBGSelector(int normal, int pressed) {
        StateListDrawable drawable = new StateListDrawable();
        ColorDrawable cdNormal = new ColorDrawable(normal);
        ColorDrawable cdPressed = new ColorDrawable(pressed);
        drawable.addState(new int[]{16842919, 16842910}, cdPressed);
        drawable.addState(new int[]{16842913, 16842910}, cdPressed);
        drawable.addState(new int[]{16842912, 16842910}, cdPressed);
        drawable.addState(new int[]{16842910}, cdNormal);
        return drawable;
    }

    public static void saveToGallery(final Context context, Bitmap bitmap) {
        Exception e;
        Throwable th;
        ContentValues values = new ContentValues();
        values.put("title", "title");
        values.put("description", "desc");
        values.put("datetaken", Long.valueOf(System.currentTimeMillis()));
        values.put("mime_type", "image/jpeg");
        OutputStream imageOut = null;
        File appDir = new File(Environment.getExternalStorageDirectory(), "Qtalk");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        final File outfile = new File(appDir, System.currentTimeMillis() + ".png");
        try {
            if (!outfile.exists()) {
                outfile.createNewFile();
            }
            OutputStream imageOut2 = new FileOutputStream(outfile);
            try {
                bitmap.compress(CompressFormat.JPEG, 100, imageOut2);
                values.put("_data", outfile.getAbsolutePath());
                context.getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, values);
                try {
                    imageOut2.close();
                    imageOut = imageOut2;
                } catch (Exception e2) {
                    LogUtil.e("ImageUtils", "error", e2);
                    imageOut = imageOut2;
                }
            } catch (Exception e3) {
                e2 = e3;
                imageOut = imageOut2;
                try {
                    LogUtil.e("ImageUtils", "error", e2);
                    try {
                        imageOut.close();
                    } catch (Exception e22) {
                        LogUtil.e("ImageUtils", "error", e22);
                    }
                    CommonConfig.mainhandler.post(new Runnable() {
                        public void run() {
                            context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(outfile)));
                            Toast.makeText(context, "保存到" + outfile.getAbsolutePath(), 1).show();
                        }
                    });
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        imageOut.close();
                    } catch (Exception e222) {
                        LogUtil.e("ImageUtils", "error", e222);
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                imageOut = imageOut2;
                imageOut.close();
                throw th;
            }
        } catch (Exception e4) {
            e222 = e4;
            LogUtil.e("ImageUtils", "error", e222);
            imageOut.close();
            CommonConfig.mainhandler.post(/* anonymous class already generated */);
        }
        CommonConfig.mainhandler.post(/* anonymous class already generated */);
    }

    public static void saveToGallery(Context context, File file, boolean isGif) {
        String suffix;
        Exception e;
        final Context context2;
        Throwable th;
        ContentValues values = new ContentValues();
        values.put("title", "title");
        values.put("description", "desc");
        values.put("datetaken", Long.valueOf(System.currentTimeMillis()));
        if (isGif) {
            values.put("mime_type", "image/gif");
            suffix = ".gif";
        } else {
            values.put("mime_type", "image/jpeg");
            suffix = ".jpg";
        }
        File appDir = new File(Environment.getExternalStorageDirectory(), CommonConfig.currentPlat);
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        final File outfile = new File(appDir, System.currentTimeMillis() + suffix);
        OutputStream imageOut = null;
        InputStream is = null;
        try {
            OutputStream imageOut2;
            if (!outfile.exists()) {
                outfile.createNewFile();
            }
            values.put("_data", outfile.getAbsolutePath());
            InputStream is2 = new BufferedInputStream(new FileInputStream(file));
            try {
                imageOut2 = new FileOutputStream(outfile);
            } catch (Exception e2) {
                e = e2;
                is = is2;
                try {
                    e.printStackTrace();
                    try {
                        is.close();
                        imageOut.close();
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                    context2 = context;
                    CommonConfig.mainhandler.post(new Runnable() {
                        public void run() {
                            if (context2 != null) {
                                context2.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(outfile)));
                                Toast.makeText(context2, "保存到" + outfile.getAbsolutePath(), 1).show();
                            }
                        }
                    });
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        is.close();
                        imageOut.close();
                    } catch (Exception e32) {
                        e32.printStackTrace();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                is = is2;
                is.close();
                imageOut.close();
                throw th;
            }
            try {
                byte[] buffer = new byte[1024];
                while (true) {
                    int n = is2.read(buffer);
                    if (n == -1) {
                        break;
                    }
                    imageOut2.write(buffer, 0, n);
                }
                context.getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, values);
                try {
                    is2.close();
                    imageOut2.close();
                    is = is2;
                    imageOut = imageOut2;
                } catch (Exception e322) {
                    e322.printStackTrace();
                    is = is2;
                    imageOut = imageOut2;
                }
            } catch (Exception e4) {
                e322 = e4;
                is = is2;
                imageOut = imageOut2;
                e322.printStackTrace();
                is.close();
                imageOut.close();
                context2 = context;
                CommonConfig.mainhandler.post(/* anonymous class already generated */);
            } catch (Throwable th4) {
                th = th4;
                is = is2;
                imageOut = imageOut2;
                is.close();
                imageOut.close();
                throw th;
            }
        } catch (Exception e5) {
            e322 = e5;
            e322.printStackTrace();
            is.close();
            imageOut.close();
            context2 = context;
            CommonConfig.mainhandler.post(/* anonymous class already generated */);
        }
        context2 = context;
        CommonConfig.mainhandler.post(/* anonymous class already generated */);
    }

    public static Bitmap transformRotation(String src) {
        if (src == null) {
            return null;
        }
        Bitmap bitmap = BitmapFactory.decodeFile(src);
        Bitmap target = null;
        ExifInterface exifInterface = null;
        if (bitmap != null) {
            try {
                exifInterface = new ExifInterface(src);
            } catch (IOException e) {
                LogUtil.e("IO Exception");
            }
            if (exifInterface != null) {
                Matrix matrix = new Matrix();
                switch (exifInterface.getAttributeInt("Orientation", 1)) {
                    case 3:
                        matrix.postRotate(180.0f);
                        target = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                        break;
                    case 6:
                        matrix.postRotate(90.0f);
                        target = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                        break;
                    case 8:
                        matrix.postRotate(270.0f);
                        target = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                        break;
                    default:
                        target = bitmap;
                        break;
                }
            }
        }
        if (target == bitmap) {
            return target;
        }
        bitmap.recycle();
        return target;
    }

    public static Bitmap compose(List<File> source, int width, int height) {
        if (source == null || source.size() == 0) {
            return null;
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        switch (source.size()) {
            case 1:
                compose1Bitmap(source, canvas, width / 2, height / 2);
                return bitmap;
            case 2:
                compose2Bitmap(source, canvas, width / 2, height / 2);
                return bitmap;
            case 3:
                compose3Bitmap(source, canvas, width / 2, height / 2);
                return bitmap;
            case 4:
                compose4Bitmap(source, canvas, width / 2, height / 2);
                return bitmap;
            case 5:
                compose5Bitmap(source, canvas, width / 3, height / 3);
                return bitmap;
            case 6:
                compose6Bitmap(source, canvas, width / 3, height / 3);
                return bitmap;
            case 7:
                compose7Bitmap(source, canvas, width / 3, height / 3);
                return bitmap;
            case 8:
                compose8Bitmap(source, canvas, width / 3, height / 3);
                return bitmap;
            case 9:
                compose9Bitmap(source, canvas, width / 3, height / 3);
                return bitmap;
            default:
                compose9PlusBitmap(source, canvas, width / 3, height / 3);
                return bitmap;
        }
    }

    private static void compose1Bitmap(List<File> source, Canvas canvas, int w, int h) {
        int left = (canvas.getWidth() / 2) - (w / 2);
        int top = (canvas.getHeight() / 2) - (h / 2);
        Bitmap bp = scaleBitmap(((File) source.get(0)).getAbsolutePath(), w, h);
        canvas.save();
        canvas.clipRect(((float) left) + 0.5f, ((float) top) + 0.5f, ((float) (left + w)) - 0.5f, ((float) (top + h)) - 0.5f, Op.INTERSECT);
        canvas.drawBitmap(bp, (float) left, (float) top, null);
        canvas.restore();
        bp.recycle();
    }

    private static void compose2Bitmap(List<File> source, Canvas canvas, int w, int h) {
        int left = 0;
        int top = 0;
        for (int i = 0; i < source.size(); i++) {
            Bitmap bp = scaleBitmap(((File) source.get(i)).getAbsolutePath(), w, h);
            switch (i) {
                case 0:
                    left = 0;
                    top = 0;
                    break;
                case 1:
                    left = w;
                    top = h;
                    break;
                default:
                    break;
            }
            canvas.save();
            canvas.clipRect(((float) left) + 0.5f, ((float) top) + 0.5f, ((float) (left + w)) - 0.5f, ((float) (top + h)) - 0.5f, Op.INTERSECT);
            canvas.drawBitmap(bp, (float) left, (float) top, null);
            canvas.restore();
            bp.recycle();
        }
    }

    private static void compose3Bitmap(List<File> source, Canvas canvas, int w, int h) {
        int left = 0;
        int top = 0;
        for (int i = 0; i < source.size(); i++) {
            Bitmap bp = scaleBitmap(((File) source.get(i)).getAbsolutePath(), w, h);
            switch (i) {
                case 0:
                    left = (canvas.getWidth() / 2) - (w / 2);
                    top = 0;
                    break;
                case 1:
                    left = 0;
                    top = h;
                    break;
                case 2:
                    left = w;
                    top = h;
                    break;
                default:
                    break;
            }
            canvas.save();
            canvas.clipRect(((float) left) + 0.5f, ((float) top) + 0.5f, ((float) (left + w)) - 0.5f, ((float) (top + h)) - 0.5f, Op.INTERSECT);
            canvas.drawBitmap(bp, (float) left, (float) top, null);
            canvas.restore();
            bp.recycle();
        }
    }

    private static void compose4Bitmap(List<File> source, Canvas canvas, int w, int h) {
        int left = 0;
        int top = 0;
        for (int i = 0; i < source.size(); i++) {
            Bitmap bp = scaleBitmap(((File) source.get(i)).getAbsolutePath(), w, h);
            switch (i) {
                case 0:
                    left = 0;
                    top = 0;
                    break;
                case 1:
                    left = w;
                    top = 0;
                    break;
                case 2:
                    left = 0;
                    top = h;
                    break;
                case 3:
                    left = w;
                    top = h;
                    break;
                default:
                    break;
            }
            canvas.save();
            canvas.clipRect(((float) left) + 0.5f, ((float) top) + 0.5f, ((float) (left + w)) - 0.5f, ((float) (top + h)) - 0.5f, Op.INTERSECT);
            canvas.drawBitmap(bp, (float) left, (float) top, null);
            canvas.restore();
            bp.recycle();
        }
    }

    private static void compose5Bitmap(List<File> source, Canvas canvas, int w, int h) {
        int left = 0;
        int top = 0;
        for (int i = 0; i < source.size(); i++) {
            Bitmap bp = scaleBitmap(((File) source.get(i)).getAbsolutePath(), w, h);
            switch (i) {
                case 0:
                    left = (canvas.getWidth() / 2) - w;
                    top = (canvas.getHeight() / 2) - h;
                    break;
                case 1:
                    left = canvas.getWidth() / 2;
                    top = (canvas.getHeight() / 2) - h;
                    break;
                case 2:
                    left = 0;
                    top = canvas.getHeight() / 2;
                    break;
                case 3:
                    left = w;
                    top = canvas.getHeight() / 2;
                    break;
                case 4:
                    left = w * 2;
                    top = canvas.getHeight() / 2;
                    break;
                default:
                    break;
            }
            canvas.save();
            canvas.clipRect(((float) left) + 0.5f, ((float) top) + 0.5f, ((float) (left + w)) - 0.5f, ((float) (top + h)) - 0.5f, Op.INTERSECT);
            canvas.drawBitmap(bp, (float) left, (float) top, null);
            canvas.restore();
            bp.recycle();
        }
    }

    private static void compose6Bitmap(List<File> source, Canvas canvas, int w, int h) {
        int left = 0;
        int top = 0;
        for (int i = 0; i < source.size(); i++) {
            Bitmap bp = scaleBitmap(((File) source.get(i)).getAbsolutePath(), w, h);
            switch (i) {
                case 0:
                    left = 0;
                    top = (canvas.getHeight() / 2) - h;
                    break;
                case 1:
                    left = w;
                    top = (canvas.getHeight() / 2) - h;
                    break;
                case 2:
                    left = w * 2;
                    top = (canvas.getHeight() / 2) - h;
                    break;
                case 3:
                    left = 0;
                    top = canvas.getHeight() / 2;
                    break;
                case 4:
                    left = w;
                    top = canvas.getHeight() / 2;
                    break;
                case 5:
                    left = w * 2;
                    top = canvas.getHeight() / 2;
                    break;
                default:
                    break;
            }
            canvas.save();
            canvas.clipRect(((float) left) + 0.5f, ((float) top) + 0.5f, ((float) (left + w)) - 0.5f, ((float) (top + h)) - 0.5f, Op.INTERSECT);
            canvas.drawBitmap(bp, (float) left, (float) top, null);
            canvas.restore();
            bp.recycle();
        }
    }

    private static void compose7Bitmap(List<File> source, Canvas canvas, int w, int h) {
        int left = 0;
        int top = 0;
        for (int i = 0; i < source.size(); i++) {
            Bitmap bp = scaleBitmap(((File) source.get(i)).getAbsolutePath(), w, h);
            switch (i) {
                case 0:
                    left = 0;
                    top = 0;
                    break;
                case 1:
                    left = w;
                    top = 0;
                    break;
                case 2:
                    left = w * 2;
                    top = 0;
                    break;
                case 3:
                    left = 0;
                    top = h;
                    break;
                case 4:
                    left = w;
                    top = h;
                    break;
                case 5:
                    left = w * 2;
                    top = h;
                    break;
                case 6:
                    left = (canvas.getWidth() / 2) - (w / 2);
                    top = h * 2;
                    break;
                default:
                    break;
            }
            canvas.save();
            canvas.clipRect(((float) left) + 0.5f, ((float) top) + 0.5f, ((float) (left + w)) - 0.5f, ((float) (top + h)) - 0.5f, Op.INTERSECT);
            canvas.drawBitmap(bp, (float) left, (float) top, null);
            canvas.restore();
            bp.recycle();
        }
    }

    private static void compose8Bitmap(List<File> source, Canvas canvas, int w, int h) {
        int left = 0;
        int top = 0;
        for (int i = 0; i < source.size(); i++) {
            Bitmap bp = scaleBitmap(((File) source.get(i)).getAbsolutePath(), w, h);
            switch (i) {
                case 0:
                    left = 0;
                    top = 0;
                    break;
                case 1:
                    left = w;
                    top = 0;
                    break;
                case 2:
                    left = w * 2;
                    top = 0;
                    break;
                case 3:
                    left = 0;
                    top = h;
                    break;
                case 4:
                    left = w;
                    top = h;
                    break;
                case 5:
                    left = w * 2;
                    top = h;
                    break;
                case 6:
                    left = (canvas.getWidth() / 2) - w;
                    top = h * 2;
                    break;
                case 7:
                    left = canvas.getWidth() / 2;
                    top = h * 2;
                    break;
                default:
                    break;
            }
            canvas.save();
            canvas.clipRect(((float) left) + 0.5f, ((float) top) + 0.5f, ((float) (left + w)) - 0.5f, ((float) (top + h)) - 0.5f, Op.INTERSECT);
            canvas.drawBitmap(bp, (float) left, (float) top, null);
            canvas.restore();
            bp.recycle();
        }
    }

    private static void compose9Bitmap(List<File> source, Canvas canvas, int w, int h) {
        for (int i = 0; i < source.size(); i++) {
            int left;
            int top;
            Bitmap bp = scaleBitmap(((File) source.get(i)).getAbsolutePath(), w, h);
            switch (i) {
                case 0:
                    left = 0;
                    top = 0;
                    break;
                case 1:
                    left = w;
                    top = 0;
                    break;
                case 2:
                    left = w * 2;
                    top = 0;
                    break;
                case 3:
                    left = 0;
                    top = h;
                    break;
                case 4:
                    left = w;
                    top = h;
                    break;
                case 5:
                    left = w * 2;
                    top = h;
                    break;
                case 6:
                    left = 0;
                    top = h * 2;
                    break;
                case 7:
                    left = w;
                    top = h * 2;
                    break;
                case 8:
                    left = w * 2;
                    top = h * 2;
                    break;
                default:
                    return;
            }
            canvas.save();
            canvas.clipRect(((float) left) + 0.5f, ((float) top) + 0.5f, ((float) (left + w)) - 0.5f, ((float) (top + h)) - 0.5f, Op.INTERSECT);
            canvas.drawBitmap(bp, (float) left, (float) top, null);
            canvas.restore();
            bp.recycle();
        }
    }

    private static void compose9PlusBitmap(List<File> source, Canvas canvas, int w, int h) {
        compose9Bitmap(source, canvas, w, h);
    }

    private static Bitmap scaleBitmap(String path, int w, int h) {
        Bitmap bitmap = BitmapHelper.decodeFile(path, w, h);
        if (bitmap == null) {
            return Bitmap.createBitmap(w, h, Config.ARGB_8888);
        }
        if (w == bitmap.getWidth() && h == bitmap.getHeight()) {
            return bitmap;
        }
        Bitmap result = Bitmap.createScaledBitmap(bitmap, w, h, false);
        bitmap.recycle();
        return result;
    }

    public static void blur(Bitmap bkg, View view) {
        if (bkg != null && view != null && !bkg.isRecycled()) {
            int viewHeight = view.getHeight() == 0 ? bkg.getHeight() : view.getHeight();
            int viewWidth = view.getWidth() == 0 ? bkg.getWidth() : view.getWidth();
            int width = bkg.getWidth();
            int finalWidth = (int) (((float) width) / 8.0f);
            int finalHeight = (int) (((float) ((width * viewHeight) / viewWidth)) / 8.0f);
            if (finalWidth <= 0) {
                finalWidth = 48;
            }
            if (finalHeight <= 0) {
                finalHeight = 48;
            }
            Bitmap overlay = Bitmap.createBitmap(finalWidth, finalHeight, Config.ARGB_8888);
            Canvas canvas = new Canvas(overlay);
            canvas.translate(((float) (-view.getLeft())) / 8.0f, ((float) (-view.getTop())) / 8.0f);
            canvas.scale(1.0f / 8.0f, 1.0f / 8.0f);
            Paint paint = new Paint();
            paint.setFlags(2);
            canvas.drawBitmap(bkg, 0.0f, 0.0f, paint);
            overlay = FastBlur.doBlur(overlay, (int) 2.0f, true);
            if (VERSION.SDK_INT >= 16) {
                view.setBackground(new BitmapDrawable(view.getResources(), overlay));
            } else {
                view.setBackgroundDrawable(new BitmapDrawable(view.getResources(), overlay));
            }
        }
    }

    public static Bitmap getViewScreenshot(View view) {
        Bitmap bm = null;
        if (view != null) {
            view.setDrawingCacheEnabled(true);
            view.buildDrawingCache();
            int measureSpec = MeasureSpec.makeMeasureSpec(0, 0);
            view.measure(measureSpec, measureSpec);
            if (view.getMeasuredWidth() > 0 && view.getMeasuredHeight() > 0) {
                try {
                    bm = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Config.ARGB_8888);
                } catch (OutOfMemoryError e) {
                    System.gc();
                    try {
                        bm = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Config.ARGB_8888);
                    } catch (OutOfMemoryError e2) {
                    }
                }
                Canvas bigCanvas = new Canvas(bm);
                bigCanvas.drawBitmap(bm, 0.0f, (float) bm.getHeight(), new Paint());
                view.draw(bigCanvas);
            }
        }
        return bm;
    }

    public static Bitmap getViewScreenshot(Activity context) {
        View view = context.getWindow().getDecorView();
        Bitmap bm = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Config.ARGB_8888);
        view.draw(new Canvas(bm));
        return bm;
    }
}
