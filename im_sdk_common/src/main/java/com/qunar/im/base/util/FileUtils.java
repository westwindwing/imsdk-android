package com.qunar.im.base.util;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.provider.DocumentsContract;
import android.provider.MediaStore.Audio;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Video;
import com.qunar.im.common.CommonConfig;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtils {
    public static final String savePath = (Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_DOWNLOADS + "/");

    public enum FileSizeUnit {
        B,
        K,
        M,
        G
    }

    public static void saveFileToExtensionStorage(File sourceFile) {
        File myDir = new File(Environment.getExternalStorageDirectory().toString() + "/qtalk");
        myDir.mkdirs();
        File file = new File(myDir, sourceFile.getName());
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            byte[] bytes = new byte[((int) sourceFile.length())];
            new FileInputStream(sourceFile).read(bytes);
            out.write(bytes);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Bitmap ResizeBitmap(Bitmap bitmap, int scale) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale((float) (1 / scale), (float) (1 / scale));
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        bitmap.recycle();
        return resizedBitmap;
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:23:0x0030=Splitter:B:23:0x0030, B:15:0x0021=Splitter:B:15:0x0021} */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0026 A:{SYNTHETIC, Splitter:B:18:0x0026} */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0035 A:{SYNTHETIC, Splitter:B:26:0x0035} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0041 A:{SYNTHETIC, Splitter:B:32:0x0041} */
    public static byte[] toByteArray(java.io.File r5, int r6) {
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
        throw new UnsupportedOperationException("Method not decompiled: com.qunar.im.base.util.FileUtils.toByteArray(java.io.File, int):byte[]");
    }

    public static File getFilesDir(Context context) {
        try {
            return "mounted".equals(Environment.getExternalStorageState()) ? getExternalFilesDir(context) : context.getFilesDir();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static File getExternalFilesDir(Context context) {
        if (VERSION.SDK_INT >= 8) {
            File f = context.getExternalFilesDir(null);
            if (f != null) {
                return f;
            }
        }
        return new File(Environment.getExternalStorageDirectory().getPath() + ("/Android/data/" + context.getPackageName() + "/files/glide"));
    }

    public static String getPath(Uri uri, Context cxt) {
        if (uri == null) {
            return null;
        }
        Cursor cursor = cxt.getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();
        cursor = cxt.getContentResolver().query(Media.EXTERNAL_CONTENT_URI, null, "_id = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex("_data"));
        cursor.close();
        return path;
    }

    public static String getRealPath(Uri fileUrl) {
        return new File(fileUrl.getPath()).getPath();
    }

    public static void removeDir(File dir) {
        if (dir.isFile()) {
            dir.delete();
            return;
        }
        File[] childFiles = dir.listFiles();
        if (childFiles != null && childFiles.length > 0) {
            for (File file : childFiles) {
                if (file.isFile()) {
                    file.delete();
                } else {
                    removeDir(file);
                }
            }
        }
        dir.delete();
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:22:0x0037=Splitter:B:22:0x0037, B:14:0x0028=Splitter:B:14:0x0028} */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0048 A:{SYNTHETIC, Splitter:B:31:0x0048} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x003c A:{SYNTHETIC, Splitter:B:25:0x003c} */
    public static java.lang.String getFormatFileSize(java.lang.String r8) {
        /*
        r1 = new java.io.File;
        r1.<init>(r8);
        r4 = "0MB";
        r7 = r1.exists();
        if (r7 == 0) goto L_0x0021;
    L_0x000d:
        r5 = 0;
        r6 = new java.io.FileInputStream;	 Catch:{ FileNotFoundException -> 0x0027, IOException -> 0x0036 }
        r6.<init>(r1);	 Catch:{ FileNotFoundException -> 0x0027, IOException -> 0x0036 }
        r7 = r6.available();	 Catch:{ FileNotFoundException -> 0x0057, IOException -> 0x0054, all -> 0x0051 }
        r2 = (long) r7;	 Catch:{ FileNotFoundException -> 0x0057, IOException -> 0x0054, all -> 0x0051 }
        r4 = getFormatSizeStr(r2);	 Catch:{ FileNotFoundException -> 0x0057, IOException -> 0x0054, all -> 0x0051 }
        if (r6 == 0) goto L_0x0021;
    L_0x001e:
        r6.close();	 Catch:{ IOException -> 0x0022 }
    L_0x0021:
        return r4;
    L_0x0022:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0021;
    L_0x0027:
        r0 = move-exception;
    L_0x0028:
        r0.printStackTrace();	 Catch:{ all -> 0x0045 }
        if (r5 == 0) goto L_0x0021;
    L_0x002d:
        r5.close();	 Catch:{ IOException -> 0x0031 }
        goto L_0x0021;
    L_0x0031:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0021;
    L_0x0036:
        r0 = move-exception;
    L_0x0037:
        r0.printStackTrace();	 Catch:{ all -> 0x0045 }
        if (r5 == 0) goto L_0x0021;
    L_0x003c:
        r5.close();	 Catch:{ IOException -> 0x0040 }
        goto L_0x0021;
    L_0x0040:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0021;
    L_0x0045:
        r7 = move-exception;
    L_0x0046:
        if (r5 == 0) goto L_0x004b;
    L_0x0048:
        r5.close();	 Catch:{ IOException -> 0x004c }
    L_0x004b:
        throw r7;
    L_0x004c:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x004b;
    L_0x0051:
        r7 = move-exception;
        r5 = r6;
        goto L_0x0046;
    L_0x0054:
        r0 = move-exception;
        r5 = r6;
        goto L_0x0037;
    L_0x0057:
        r0 = move-exception;
        r5 = r6;
        goto L_0x0028;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qunar.im.base.util.FileUtils.getFormatFileSize(java.lang.String):java.lang.String");
    }

    public static String getFormatSizeStr(long size) {
        DecimalFormat df = new DecimalFormat("#.00");
        if (size < 1024) {
            return df.format((double) size) + "B";
        }
        if (size < 1048576) {
            return df.format(((double) size) / 1024.0d) + "K";
        }
        if (size < 1073741824) {
            return df.format(((double) size) / 1048576.0d) + "M";
        }
        return df.format(((double) size) / 1.073741824E9d) + "G";
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:35:0x0065=Splitter:B:35:0x0065, B:27:0x0056=Splitter:B:27:0x0056} */
    /* JADX WARNING: Removed duplicated region for block: B:56:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0028  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0028  */
    /* JADX WARNING: Removed duplicated region for block: B:56:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x005b A:{SYNTHETIC, Splitter:B:30:0x005b} */
    /* JADX WARNING: Removed duplicated region for block: B:56:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0028  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x006a A:{SYNTHETIC, Splitter:B:38:0x006a} */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0028  */
    /* JADX WARNING: Removed duplicated region for block: B:56:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0076 A:{SYNTHETIC, Splitter:B:44:0x0076} */
    public static int getFileSize(java.lang.String r12, com.qunar.im.base.util.FileUtils.FileSizeUnit r13) {
        /*
        r1 = new java.io.File;
        r1.<init>(r12);
        r6 = 0;
        r7 = r1.exists();
        if (r7 == 0) goto L_0x0026;
    L_0x000c:
        r4 = 0;
        r5 = new java.io.FileInputStream;	 Catch:{ FileNotFoundException -> 0x0055, IOException -> 0x0064 }
        r5.<init>(r1);	 Catch:{ FileNotFoundException -> 0x0055, IOException -> 0x0064 }
        r7 = r5.available();	 Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x0082, all -> 0x007f }
        r2 = (long) r7;	 Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x0082, all -> 0x007f }
        r7 = com.qunar.im.base.util.FileUtils.FileSizeUnit.B;	 Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x0082, all -> 0x007f }
        if (r13 != r7) goto L_0x002a;
    L_0x001b:
        r8 = (double) r2;	 Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x0082, all -> 0x007f }
        r8 = java.lang.Math.ceil(r8);	 Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x0082, all -> 0x007f }
        r6 = (int) r8;
    L_0x0021:
        if (r5 == 0) goto L_0x0026;
    L_0x0023:
        r5.close();	 Catch:{ IOException -> 0x0050 }
    L_0x0026:
        if (r6 != 0) goto L_0x0029;
    L_0x0028:
        r6 = 1;
    L_0x0029:
        return r6;
    L_0x002a:
        r7 = com.qunar.im.base.util.FileUtils.FileSizeUnit.K;	 Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x0082, all -> 0x007f }
        if (r13 != r7) goto L_0x0038;
    L_0x002e:
        r8 = (double) r2;	 Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x0082, all -> 0x007f }
        r10 = 4652218415073722368; // 0x4090000000000000 float:0.0 double:1024.0;
        r8 = r8 / r10;
        r8 = java.lang.Math.ceil(r8);	 Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x0082, all -> 0x007f }
        r6 = (int) r8;	 Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x0082, all -> 0x007f }
        goto L_0x0021;
    L_0x0038:
        r7 = com.qunar.im.base.util.FileUtils.FileSizeUnit.G;	 Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x0082, all -> 0x007f }
        if (r13 != r7) goto L_0x0046;
    L_0x003c:
        r8 = (double) r2;	 Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x0082, all -> 0x007f }
        r10 = 4742290407621132288; // 0x41d0000000000000 float:0.0 double:1.073741824E9;
        r8 = r8 / r10;
        r8 = java.lang.Math.ceil(r8);	 Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x0082, all -> 0x007f }
        r6 = (int) r8;	 Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x0082, all -> 0x007f }
        goto L_0x0021;
    L_0x0046:
        r8 = (double) r2;	 Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x0082, all -> 0x007f }
        r10 = 4697254411347427328; // 0x4130000000000000 float:0.0 double:1048576.0;
        r8 = r8 / r10;
        r8 = java.lang.Math.ceil(r8);	 Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x0082, all -> 0x007f }
        r6 = (int) r8;
        goto L_0x0021;
    L_0x0050:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0026;
    L_0x0055:
        r0 = move-exception;
    L_0x0056:
        r0.printStackTrace();	 Catch:{ all -> 0x0073 }
        if (r4 == 0) goto L_0x0026;
    L_0x005b:
        r4.close();	 Catch:{ IOException -> 0x005f }
        goto L_0x0026;
    L_0x005f:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0026;
    L_0x0064:
        r0 = move-exception;
    L_0x0065:
        r0.printStackTrace();	 Catch:{ all -> 0x0073 }
        if (r4 == 0) goto L_0x0026;
    L_0x006a:
        r4.close();	 Catch:{ IOException -> 0x006e }
        goto L_0x0026;
    L_0x006e:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0026;
    L_0x0073:
        r7 = move-exception;
    L_0x0074:
        if (r4 == 0) goto L_0x0079;
    L_0x0076:
        r4.close();	 Catch:{ IOException -> 0x007a }
    L_0x0079:
        throw r7;
    L_0x007a:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0079;
    L_0x007f:
        r7 = move-exception;
        r4 = r5;
        goto L_0x0074;
    L_0x0082:
        r0 = move-exception;
        r4 = r5;
        goto L_0x0065;
    L_0x0085:
        r0 = move-exception;
        r4 = r5;
        goto L_0x0056;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qunar.im.base.util.FileUtils.getFileSize(java.lang.String, com.qunar.im.base.util.FileUtils$FileSizeUnit):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x003b A:{SYNTHETIC, Splitter:B:28:0x003b} */
    public static java.lang.String fileToMD5(java.io.File r9) {
        /*
        r3 = 0;
        r4 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x0048 }
        r4.<init>(r9);	 Catch:{ Exception -> 0x0048 }
        r7 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r0 = new byte[r7];	 Catch:{ Exception -> 0x001f, all -> 0x0045 }
        r7 = "MD5";
        r1 = java.security.MessageDigest.getInstance(r7);	 Catch:{ Exception -> 0x001f, all -> 0x0045 }
        r6 = 0;
    L_0x0011:
        r7 = -1;
        if (r6 == r7) goto L_0x0029;
    L_0x0014:
        r6 = r4.read(r0);	 Catch:{ Exception -> 0x001f, all -> 0x0045 }
        if (r6 <= 0) goto L_0x0011;
    L_0x001a:
        r7 = 0;
        r1.update(r0, r7, r6);	 Catch:{ Exception -> 0x001f, all -> 0x0045 }
        goto L_0x0011;
    L_0x001f:
        r2 = move-exception;
        r3 = r4;
    L_0x0021:
        r7 = "";
        if (r3 == 0) goto L_0x0028;
    L_0x0025:
        r3.close();	 Catch:{ Exception -> 0x0041 }
    L_0x0028:
        return r7;
    L_0x0029:
        r5 = r1.digest();	 Catch:{ Exception -> 0x001f, all -> 0x0045 }
        r7 = convertHashToString(r5);	 Catch:{ Exception -> 0x001f, all -> 0x0045 }
        if (r4 == 0) goto L_0x0036;
    L_0x0033:
        r4.close();	 Catch:{ Exception -> 0x003f }
    L_0x0036:
        r3 = r4;
        goto L_0x0028;
    L_0x0038:
        r7 = move-exception;
    L_0x0039:
        if (r3 == 0) goto L_0x003e;
    L_0x003b:
        r3.close();	 Catch:{ Exception -> 0x0043 }
    L_0x003e:
        throw r7;
    L_0x003f:
        r8 = move-exception;
        goto L_0x0036;
    L_0x0041:
        r8 = move-exception;
        goto L_0x0028;
    L_0x0043:
        r8 = move-exception;
        goto L_0x003e;
    L_0x0045:
        r7 = move-exception;
        r3 = r4;
        goto L_0x0039;
    L_0x0048:
        r2 = move-exception;
        goto L_0x0021;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qunar.im.base.util.FileUtils.fileToMD5(java.io.File):java.lang.String");
    }

    private static String convertHashToString(byte[] hashBytes) {
        String returnVal = "";
        for (byte b : hashBytes) {
            returnVal = returnVal + Integer.toString((b & 255) + 256, 16).substring(1);
        }
        return returnVal.toLowerCase();
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:26:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0034  */
    public static void writeToFile(java.lang.String r7, java.lang.String r8, android.content.Context r9, boolean r10) {
        /*
        r0 = r9.getFilesDir();
        r2 = new java.io.File;
        r2.<init>(r0, r8);
        r5 = r2.exists();
        if (r5 == 0) goto L_0x0014;
    L_0x000f:
        if (r10 == 0) goto L_0x0014;
    L_0x0011:
        r2.delete();
    L_0x0014:
        r3 = 0;
        r4 = new java.io.PrintWriter;	 Catch:{ FileNotFoundException -> 0x002e }
        r5 = new java.io.BufferedOutputStream;	 Catch:{ FileNotFoundException -> 0x002e }
        r6 = new java.io.FileOutputStream;	 Catch:{ FileNotFoundException -> 0x002e }
        r6.<init>(r2);	 Catch:{ FileNotFoundException -> 0x002e }
        r5.<init>(r6);	 Catch:{ FileNotFoundException -> 0x002e }
        r4.<init>(r5);	 Catch:{ FileNotFoundException -> 0x002e }
        r4.println(r7);	 Catch:{ FileNotFoundException -> 0x0042, all -> 0x003f }
        if (r4 == 0) goto L_0x0045;
    L_0x0029:
        r4.close();
        r3 = r4;
    L_0x002d:
        return;
    L_0x002e:
        r1 = move-exception;
    L_0x002f:
        r1.printStackTrace();	 Catch:{ all -> 0x0038 }
        if (r3 == 0) goto L_0x002d;
    L_0x0034:
        r3.close();
        goto L_0x002d;
    L_0x0038:
        r5 = move-exception;
    L_0x0039:
        if (r3 == 0) goto L_0x003e;
    L_0x003b:
        r3.close();
    L_0x003e:
        throw r5;
    L_0x003f:
        r5 = move-exception;
        r3 = r4;
        goto L_0x0039;
    L_0x0042:
        r1 = move-exception;
        r3 = r4;
        goto L_0x002f;
    L_0x0045:
        r3 = r4;
        goto L_0x002d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qunar.im.base.util.FileUtils.writeToFile(java.lang.String, java.lang.String, android.content.Context, boolean):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x002b  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0032  */
    public static void writeToFile(java.lang.String r5, java.io.File r6, boolean r7) {
        /*
        r3 = r6.exists();
        if (r3 == 0) goto L_0x000b;
    L_0x0006:
        if (r7 == 0) goto L_0x000b;
    L_0x0008:
        r6.delete();
    L_0x000b:
        r1 = 0;
        r2 = new java.io.PrintWriter;	 Catch:{ FileNotFoundException -> 0x0025 }
        r3 = new java.io.BufferedOutputStream;	 Catch:{ FileNotFoundException -> 0x0025 }
        r4 = new java.io.FileOutputStream;	 Catch:{ FileNotFoundException -> 0x0025 }
        r4.<init>(r6);	 Catch:{ FileNotFoundException -> 0x0025 }
        r3.<init>(r4);	 Catch:{ FileNotFoundException -> 0x0025 }
        r2.<init>(r3);	 Catch:{ FileNotFoundException -> 0x0025 }
        r2.println(r5);	 Catch:{ FileNotFoundException -> 0x0039, all -> 0x0036 }
        if (r2 == 0) goto L_0x003c;
    L_0x0020:
        r2.close();
        r1 = r2;
    L_0x0024:
        return;
    L_0x0025:
        r0 = move-exception;
    L_0x0026:
        r0.printStackTrace();	 Catch:{ all -> 0x002f }
        if (r1 == 0) goto L_0x0024;
    L_0x002b:
        r1.close();
        goto L_0x0024;
    L_0x002f:
        r3 = move-exception;
    L_0x0030:
        if (r1 == 0) goto L_0x0035;
    L_0x0032:
        r1.close();
    L_0x0035:
        throw r3;
    L_0x0036:
        r3 = move-exception;
        r1 = r2;
        goto L_0x0030;
    L_0x0039:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0026;
    L_0x003c:
        r1 = r2;
        goto L_0x0024;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qunar.im.base.util.FileUtils.writeToFile(java.lang.String, java.io.File, boolean):void");
    }

    public static byte[] readFile(String fileName) throws IOException {
        return readFile(new File(fileName));
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a4 A:{SYNTHETIC, Splitter:B:29:0x00a4} */
    public static byte[] readFile(java.io.File r11) throws java.io.IOException {
        /*
        r8 = r11.exists();
        if (r8 != 0) goto L_0x001f;
    L_0x0006:
        r8 = new java.lang.RuntimeException;
        r9 = new java.lang.StringBuilder;
        r9.<init>();
        r9 = r9.append(r11);
        r10 = ": file not found";
        r9 = r9.append(r10);
        r9 = r9.toString();
        r8.<init>(r9);
        throw r8;
    L_0x001f:
        r8 = r11.isFile();
        if (r8 != 0) goto L_0x003e;
    L_0x0025:
        r8 = new java.lang.RuntimeException;
        r9 = new java.lang.StringBuilder;
        r9.<init>();
        r9 = r9.append(r11);
        r10 = ": not a file";
        r9 = r9.append(r10);
        r9 = r9.toString();
        r8.<init>(r9);
        throw r8;
    L_0x003e:
        r8 = r11.canRead();
        if (r8 != 0) goto L_0x005d;
    L_0x0044:
        r8 = new java.lang.RuntimeException;
        r9 = new java.lang.StringBuilder;
        r9.<init>();
        r9 = r9.append(r11);
        r10 = ": file not readable";
        r9 = r9.append(r10);
        r9 = r9.toString();
        r8.<init>(r9);
        throw r8;
    L_0x005d:
        r6 = r11.length();
        r5 = (int) r6;
        r8 = (long) r5;
        r8 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1));
        if (r8 == 0) goto L_0x0080;
    L_0x0067:
        r8 = new java.lang.RuntimeException;
        r9 = new java.lang.StringBuilder;
        r9.<init>();
        r9 = r9.append(r11);
        r10 = ": file too long";
        r9 = r9.append(r10);
        r9 = r9.toString();
        r8.<init>(r9);
        throw r8;
    L_0x0080:
        r0 = new java.io.ByteArrayOutputStream;
        r0.<init>(r5);
        r3 = 0;
        r4 = new java.io.BufferedInputStream;	 Catch:{ all -> 0x00b6 }
        r8 = new java.io.FileInputStream;	 Catch:{ all -> 0x00b6 }
        r8.<init>(r11);	 Catch:{ all -> 0x00b6 }
        r4.<init>(r8);	 Catch:{ all -> 0x00b6 }
        r8 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r1 = new byte[r8];	 Catch:{ all -> 0x00a0 }
        r2 = 0;
    L_0x0095:
        r2 = r4.read(r1);	 Catch:{ all -> 0x00a0 }
        if (r2 <= 0) goto L_0x00a8;
    L_0x009b:
        r8 = 0;
        r0.write(r1, r8, r2);	 Catch:{ all -> 0x00a0 }
        goto L_0x0095;
    L_0x00a0:
        r8 = move-exception;
        r3 = r4;
    L_0x00a2:
        if (r3 == 0) goto L_0x00a7;
    L_0x00a4:
        r3.close();	 Catch:{ Exception -> 0x00b4 }
    L_0x00a7:
        throw r8;
    L_0x00a8:
        if (r4 == 0) goto L_0x00ad;
    L_0x00aa:
        r4.close();	 Catch:{ Exception -> 0x00b2 }
    L_0x00ad:
        r8 = r0.toByteArray();
        return r8;
    L_0x00b2:
        r8 = move-exception;
        goto L_0x00ad;
    L_0x00b4:
        r9 = move-exception;
        goto L_0x00a7;
    L_0x00b6:
        r8 = move-exception;
        goto L_0x00a2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qunar.im.base.util.FileUtils.readFile(java.io.File):byte[]");
    }

    public static byte[] readStream(InputStream is) throws IOException {
        return readStream(is, 32768);
    }

    public static byte[] readStream(InputStream is, int initSize) throws IOException {
        if (initSize <= 0) {
            initSize = 32768;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream(initSize);
        byte[] buffer = new byte[8192];
        while (true) {
            int bytesRead = is.read(buffer);
            if (bytesRead <= 0) {
                return baos.toByteArray();
            }
            baos.write(buffer, 0, bytesRead);
        }
    }

    public static void deleteFile(Context context, String fileName) {
        File file = new File(context.getFilesDir(), fileName);
        if (file.exists()) {
            file.delete();
        }
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:23:0x0042=Splitter:B:23:0x0042, B:15:0x0033=Splitter:B:15:0x0033} */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0047 A:{SYNTHETIC, Splitter:B:26:0x0047} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0053 A:{SYNTHETIC, Splitter:B:32:0x0053} */
    public static java.lang.String readFirstLine(java.lang.String r9, android.content.Context r10) {
        /*
        r0 = r10.getFilesDir();
        r2 = new java.io.File;
        r2.<init>(r0, r9);
        r6 = 0;
        r3 = "";
        r8 = r2.exists();
        if (r8 != 0) goto L_0x0015;
    L_0x0012:
        r4 = r3;
        r5 = r3;
    L_0x0014:
        return r5;
    L_0x0015:
        r7 = new java.io.BufferedReader;	 Catch:{ FileNotFoundException -> 0x0032, IOException -> 0x0041 }
        r8 = new java.io.FileReader;	 Catch:{ FileNotFoundException -> 0x0032, IOException -> 0x0041 }
        r8.<init>(r2);	 Catch:{ FileNotFoundException -> 0x0032, IOException -> 0x0041 }
        r7.<init>(r8);	 Catch:{ FileNotFoundException -> 0x0032, IOException -> 0x0041 }
        r3 = r7.readLine();	 Catch:{ FileNotFoundException -> 0x0062, IOException -> 0x005f, all -> 0x005c }
        if (r7 == 0) goto L_0x0065;
    L_0x0025:
        r7.close();	 Catch:{ IOException -> 0x002c }
        r6 = r7;
    L_0x0029:
        r4 = r3;
        r5 = r3;
        goto L_0x0014;
    L_0x002c:
        r1 = move-exception;
        r1.printStackTrace();
        r6 = r7;
        goto L_0x0029;
    L_0x0032:
        r1 = move-exception;
    L_0x0033:
        r1.printStackTrace();	 Catch:{ all -> 0x0050 }
        if (r6 == 0) goto L_0x0029;
    L_0x0038:
        r6.close();	 Catch:{ IOException -> 0x003c }
        goto L_0x0029;
    L_0x003c:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0029;
    L_0x0041:
        r1 = move-exception;
    L_0x0042:
        r1.printStackTrace();	 Catch:{ all -> 0x0050 }
        if (r6 == 0) goto L_0x0029;
    L_0x0047:
        r6.close();	 Catch:{ IOException -> 0x004b }
        goto L_0x0029;
    L_0x004b:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0029;
    L_0x0050:
        r8 = move-exception;
    L_0x0051:
        if (r6 == 0) goto L_0x0056;
    L_0x0053:
        r6.close();	 Catch:{ IOException -> 0x0057 }
    L_0x0056:
        throw r8;
    L_0x0057:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0056;
    L_0x005c:
        r8 = move-exception;
        r6 = r7;
        goto L_0x0051;
    L_0x005f:
        r1 = move-exception;
        r6 = r7;
        goto L_0x0042;
    L_0x0062:
        r1 = move-exception;
        r6 = r7;
        goto L_0x0033;
    L_0x0065:
        r6 = r7;
        goto L_0x0029;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qunar.im.base.util.FileUtils.readFirstLine(java.lang.String, android.content.Context):java.lang.String");
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:23:0x0039=Splitter:B:23:0x0039, B:15:0x002a=Splitter:B:15:0x002a} */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x003e A:{SYNTHETIC, Splitter:B:26:0x003e} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x004a A:{SYNTHETIC, Splitter:B:32:0x004a} */
    public static java.lang.String readFirstLine(java.io.File r7, android.content.Context r8) {
        /*
        r4 = 0;
        r1 = "";
        r6 = r7.exists();
        if (r6 != 0) goto L_0x000c;
    L_0x0009:
        r2 = r1;
        r3 = r1;
    L_0x000b:
        return r3;
    L_0x000c:
        r5 = new java.io.BufferedReader;	 Catch:{ FileNotFoundException -> 0x0029, IOException -> 0x0038 }
        r6 = new java.io.FileReader;	 Catch:{ FileNotFoundException -> 0x0029, IOException -> 0x0038 }
        r6.<init>(r7);	 Catch:{ FileNotFoundException -> 0x0029, IOException -> 0x0038 }
        r5.<init>(r6);	 Catch:{ FileNotFoundException -> 0x0029, IOException -> 0x0038 }
        r1 = r5.readLine();	 Catch:{ FileNotFoundException -> 0x0059, IOException -> 0x0056, all -> 0x0053 }
        if (r5 == 0) goto L_0x005c;
    L_0x001c:
        r5.close();	 Catch:{ IOException -> 0x0023 }
        r4 = r5;
    L_0x0020:
        r2 = r1;
        r3 = r1;
        goto L_0x000b;
    L_0x0023:
        r0 = move-exception;
        r0.printStackTrace();
        r4 = r5;
        goto L_0x0020;
    L_0x0029:
        r0 = move-exception;
    L_0x002a:
        r0.printStackTrace();	 Catch:{ all -> 0x0047 }
        if (r4 == 0) goto L_0x0020;
    L_0x002f:
        r4.close();	 Catch:{ IOException -> 0x0033 }
        goto L_0x0020;
    L_0x0033:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0020;
    L_0x0038:
        r0 = move-exception;
    L_0x0039:
        r0.printStackTrace();	 Catch:{ all -> 0x0047 }
        if (r4 == 0) goto L_0x0020;
    L_0x003e:
        r4.close();	 Catch:{ IOException -> 0x0042 }
        goto L_0x0020;
    L_0x0042:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0020;
    L_0x0047:
        r6 = move-exception;
    L_0x0048:
        if (r4 == 0) goto L_0x004d;
    L_0x004a:
        r4.close();	 Catch:{ IOException -> 0x004e }
    L_0x004d:
        throw r6;
    L_0x004e:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x004d;
    L_0x0053:
        r6 = move-exception;
        r4 = r5;
        goto L_0x0048;
    L_0x0056:
        r0 = move-exception;
        r4 = r5;
        goto L_0x0039;
    L_0x0059:
        r0 = move-exception;
        r4 = r5;
        goto L_0x002a;
    L_0x005c:
        r4 = r5;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qunar.im.base.util.FileUtils.readFirstLine(java.io.File, android.content.Context):java.lang.String");
    }

    public static File getOutputMediaFile(String prefix) {
        File mediaStorageDir = CommonConfig.globalContext.getCacheDir();
        return new File(mediaStorageDir.getPath() + File.separator + "VID_" + prefix + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".mp4");
    }

    public static File getOutputFrameFile(String prefix) {
        File mediaStorageDir = getFilesDir(CommonConfig.globalContext);
        return new File(mediaStorageDir.getPath() + File.separator + "qtalk_" + prefix + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".jpg");
    }

    public static String getFristFrameOfFile(String fileName) {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(fileName);
            return saveImage(retriever.getFrameAtTime(1));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x003b A:{SYNTHETIC, Splitter:B:23:0x003b} */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x002c A:{SYNTHETIC, Splitter:B:16:0x002c} */
    public static java.lang.String saveImage(android.graphics.Bitmap r6) {
        /*
        r4 = "frame";
        r4 = getOutputFrameFile(r4);
        r1 = r4.getAbsolutePath();
        r2 = 0;
        r3 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x0026 }
        r3.<init>(r1);	 Catch:{ Exception -> 0x0026 }
        r4 = android.graphics.Bitmap.CompressFormat.JPEG;	 Catch:{ Exception -> 0x004a, all -> 0x0047 }
        r5 = 100;
        r6.compress(r4, r5, r3);	 Catch:{ Exception -> 0x004a, all -> 0x0047 }
        if (r3 == 0) goto L_0x001c;
    L_0x0019:
        r3.close();	 Catch:{ IOException -> 0x0021 }
    L_0x001c:
        r6.recycle();
        r2 = r3;
    L_0x0020:
        return r1;
    L_0x0021:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x001c;
    L_0x0026:
        r0 = move-exception;
    L_0x0027:
        r0.printStackTrace();	 Catch:{ all -> 0x0038 }
        if (r2 == 0) goto L_0x002f;
    L_0x002c:
        r2.close();	 Catch:{ IOException -> 0x0033 }
    L_0x002f:
        r6.recycle();
        goto L_0x0020;
    L_0x0033:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x002f;
    L_0x0038:
        r4 = move-exception;
    L_0x0039:
        if (r2 == 0) goto L_0x003e;
    L_0x003b:
        r2.close();	 Catch:{ IOException -> 0x0042 }
    L_0x003e:
        r6.recycle();
        throw r4;
    L_0x0042:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x003e;
    L_0x0047:
        r4 = move-exception;
        r2 = r3;
        goto L_0x0039;
    L_0x004a:
        r0 = move-exception;
        r2 = r3;
        goto L_0x0027;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qunar.im.base.util.FileUtils.saveImage(android.graphics.Bitmap):java.lang.String");
    }

    public static long getDirSize(File dir) {
        if (dir == null || !dir.exists()) {
            return 0;
        }
        long total = 0;
        if (dir.isFile()) {
            return 0 + dir.length();
        }
        if (!dir.isDirectory()) {
            return 0;
        }
        File[] getFiles = dir.listFiles();
        if (getFiles.length <= 0) {
            return 0;
        }
        for (File f : getFiles) {
            total += getDirSize(f);
        }
        return total;
    }

    public static long calculateDiskSize(File dir) {
        StatFs statFs = new StatFs(dir.getAbsolutePath());
        if (VERSION.SDK_INT >= 18) {
            return statFs.getTotalBytes();
        }
        return (long) (statFs.getBlockSize() * statFs.getBlockCount());
    }

    public static long calculateDiskFree(File dir) {
        StatFs statFs = new StatFs(dir.getAbsolutePath());
        if (VERSION.SDK_INT >= 18) {
            return statFs.getFreeBytes();
        }
        return (long) (statFs.getBlockSize() * statFs.getFreeBlocks());
    }

    public static Uri toUri(String path) {
        return Uri.parse("file:///" + path);
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
    public static boolean copy(java.io.File r9, java.io.File r10) {
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
        throw new UnsupportedOperationException("Method not decompiled: com.qunar.im.base.util.FileUtils.copy(java.io.File, java.io.File):boolean");
    }

    @TargetApi(19)
    public static String getPath(Context context, Uri uri) {
        String[] split;
        if (!DocumentsContract.isDocumentUri(context, uri) || VERSION.SDK_INT < 19) {
            if ("content".equalsIgnoreCase(uri.getScheme())) {
                return getDataColumn(context, uri, null, null);
            }
            if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        } else if (isExternalStorageDocument(uri)) {
            split = DocumentsContract.getDocumentId(uri).split(":");
            if ("primary".equalsIgnoreCase(split[0])) {
                return Environment.getExternalStorageDirectory() + "/" + split[1];
            }
        } else if (isDownloadsDocument(uri)) {
            return getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), null, null);
        } else if (isMediaDocument(uri)) {
            String type = DocumentsContract.getDocumentId(uri).split(":")[0];
            Uri contentUri = null;
            if ("image".equals(type)) {
                contentUri = Media.EXTERNAL_CONTENT_URI;
            } else if ("video".equals(type)) {
                contentUri = Video.Media.EXTERNAL_CONTENT_URI;
            } else if ("audio".equals(type)) {
                contentUri = Audio.Media.EXTERNAL_CONTENT_URI;
            }
            String selection = "_id=?";
            return getDataColumn(context, contentUri, "_id=?", new String[]{split[1]});
        }
        return uri.toString();
    }

    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        String column = "_data";
        String id = "_id";
        String string;
        try {
            cursor = context.getContentResolver().query(uri, new String[]{"_data"}, selection, selectionArgs, null);
            if (cursor == null || !cursor.moveToFirst()) {
                if (cursor != null) {
                    cursor.close();
                }
                return null;
            }
            string = cursor.getString(cursor.getColumnIndexOrThrow("_data"));
            if (cursor == null) {
                return string;
            }
            cursor.close();
            return string;
        } catch (IllegalArgumentException e) {
            String rootPre = File.separator + "root";
            string = uri.getPath().startsWith(rootPre) ? uri.getPath().replace(rootPre, "") : uri.getPath();
            if (cursor == null) {
                return string;
            }
            cursor.close();
            return string;
        } catch (Exception e2) {
            if (cursor != null) {
                cursor.close();
            }
            if (cursor == null) {
                return null;
            }
            cursor.close();
            return null;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public static String getIdColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        String column = "_id";
        try {
            cursor = context.getContentResolver().query(uri, new String[]{"_id"}, selection, selectionArgs, null);
            if (cursor == null || !cursor.moveToFirst()) {
                if (cursor != null) {
                    cursor.close();
                }
                return null;
            }
            String string = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
            if (cursor == null) {
                return string;
            }
            cursor.close();
            return string;
        } catch (Exception e) {
            if (cursor != null) {
                cursor.close();
            }
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static String formatByteSize(long bytes) {
        StringBuilder sb = new StringBuilder();
        float f;
        if (bytes == 0) {
            sb.append("0 Byte");
        } else if (bytes < 1024) {
            sb.append(bytes).append(" ").append("bytes");
        } else if (bytes < 1048576) {
            f = (((float) bytes) * 1.0f) / 1024.0f;
            sb.append(String.format("%.2f", new Object[]{Float.valueOf(f)})).append(" ").append("KB");
        } else if (bytes < 1073741824) {
            f = ((((float) bytes) * 1.0f) / 1024.0f) / 1024.0f;
            sb.append(String.format("%.2f", new Object[]{Float.valueOf(f)})).append(" ").append("MB");
        } else {
            f = (((((float) bytes) * 1.0f) / 1024.0f) / 1024.0f) / 1024.0f;
            sb.append(String.format("%.2f", new Object[]{Float.valueOf(f)})).append(" ").append("GB");
        }
        return sb.toString();
    }
}
