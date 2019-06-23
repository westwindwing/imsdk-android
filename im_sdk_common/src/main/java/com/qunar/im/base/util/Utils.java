package com.qunar.im.base.util;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;
import android.widget.Toast;
import java.io.IOException;
import java.util.regex.Pattern;

public class Utils {
    private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";
    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    public static final String[][] MIME_MapTable;

    public static void showToast(Context context, String showText) {
        Toast.makeText(context, showText, 0).show();
    }

    public static int dipToPixels(Context context, float dipValue) {
        return (int) TypedValue.applyDimension(1, dipValue, context.getResources().getDisplayMetrics());
    }

    public static float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static int dpToPx(Context context, int dp) {
        return dipToPixels(context, (float) dp);
    }

    public static int sp2px(Context context, float sp) {
        return (int) ((sp * context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static void jump2Desktop(Context context) {
        ((Activity) context).moveTaskToBack(true);
    }

    public static void dropIntoClipboard(String text, Context context) {
        ((ClipboardManager) context.getSystemService("clipboard")).setText(text);
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }

    public static void sendLocalBroadcast(Intent intent, Context context) {
        context.sendBroadcast(intent);
    }

    public static boolean isGifUrl(String url) {
        return url.endsWith(".gif") || url.contains(".gif");
    }

    public static boolean isMIUI() {
        try {
            BuildProperties prop = BuildProperties.newInstance();
            if (prop.getProperty("ro.miui.ui.version.code", null) == null && prop.getProperty("ro.miui.ui.version.name", null) == null && prop.getProperty("ro.miui.internal.storage", null) == null) {
                return false;
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean IsUrl(String str) {
        return match("http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?", str);
    }

    private static boolean match(String regex, String str) {
        return Pattern.compile(regex).matcher(str).find();
    }

    static {
        r0 = new String[66][];
        r0[0] = new String[]{".3gp", "video/3gpp"};
        r0[1] = new String[]{".apk", "application/vnd.android.package-archive"};
        r0[2] = new String[]{".asf", "video/x-ms-asf"};
        r0[3] = new String[]{".avi", "video/x-msvideo"};
        r0[4] = new String[]{".bin", "application/octet-stream"};
        r0[5] = new String[]{".bmp", "image/bmp"};
        r0[6] = new String[]{".c", "text/plain"};
        r0[7] = new String[]{".class", "application/octet-stream"};
        r0[8] = new String[]{".conf", "text/plain"};
        r0[9] = new String[]{".cpp", "text/plain"};
        r0[10] = new String[]{".doc", "application/msword"};
        r0[11] = new String[]{".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"};
        r0[12] = new String[]{".xls", "application/vnd.ms-excel"};
        r0[13] = new String[]{".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"};
        r0[14] = new String[]{".exe", "application/octet-stream"};
        r0[15] = new String[]{".gif", "image/gif"};
        r0[16] = new String[]{".gtar", "application/x-gtar"};
        r0[17] = new String[]{".gz", "application/x-gzip"};
        r0[18] = new String[]{".h", "text/plain"};
        r0[19] = new String[]{".htm", "text/html"};
        r0[20] = new String[]{".html", "text/html"};
        r0[21] = new String[]{".jar", "application/java-archive"};
        r0[22] = new String[]{".java", "text/plain"};
        r0[23] = new String[]{".jpeg", "image/jpeg"};
        r0[24] = new String[]{".jpg", "image/jpeg"};
        r0[25] = new String[]{".js", "application/x-javascript"};
        r0[26] = new String[]{".log", "text/plain"};
        r0[27] = new String[]{".m3u", "audio/x-mpegurl"};
        r0[28] = new String[]{".m4a", "audio/mp4a-latm"};
        r0[29] = new String[]{".m4b", "audio/mp4a-latm"};
        r0[30] = new String[]{".m4p", "audio/mp4a-latm"};
        r0[31] = new String[]{".m4u", "video/vnd.mpegurl"};
        r0[32] = new String[]{".m4v", "video/x-m4v"};
        r0[33] = new String[]{".mov", "video/quicktime"};
        r0[34] = new String[]{".mp2", "audio/x-mpeg"};
        r0[35] = new String[]{".mp3", "audio/x-mpeg"};
        r0[36] = new String[]{".mp4", "video/mp4"};
        r0[37] = new String[]{".mpc", "application/vnd.mpohun.certificate"};
        r0[38] = new String[]{".mpe", "video/mpeg"};
        r0[39] = new String[]{".mpeg", "video/mpeg"};
        r0[40] = new String[]{".mpg", "video/mpeg"};
        r0[41] = new String[]{".mpg4", "video/mp4"};
        r0[42] = new String[]{".mpga", "audio/mpeg"};
        r0[43] = new String[]{".msg", "application/vnd.ms-outlook"};
        r0[44] = new String[]{".ogg", "audio/ogg"};
        r0[45] = new String[]{".pdf", "application/pdf"};
        r0[46] = new String[]{".png", "image/png"};
        r0[47] = new String[]{".pps", "application/vnd.ms-powerpoint"};
        r0[48] = new String[]{".ppt", "application/vnd.ms-powerpoint"};
        r0[49] = new String[]{".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"};
        r0[50] = new String[]{".prop", "text/plain"};
        r0[51] = new String[]{".rc", "text/plain"};
        r0[52] = new String[]{".rmvb", "audio/x-pn-realaudio"};
        r0[53] = new String[]{".rtf", "application/rtf"};
        r0[54] = new String[]{".sh", "text/plain"};
        r0[55] = new String[]{".tar", "application/x-tar"};
        r0[56] = new String[]{".tgz", "application/x-compressed"};
        r0[57] = new String[]{".txt", "text/plain"};
        r0[58] = new String[]{".wav", "audio/x-wav"};
        r0[59] = new String[]{".wma", "audio/x-ms-wma"};
        r0[60] = new String[]{".wmv", "audio/x-ms-wmv"};
        r0[61] = new String[]{".wps", "application/vnd.ms-works"};
        r0[62] = new String[]{".xml", "text/plain"};
        r0[63] = new String[]{".z", "application/x-compress"};
        r0[64] = new String[]{".zip", "application/x-zip-compressed"};
        r0[65] = new String[]{"", "*/*"};
        MIME_MapTable = r0;
    }
}
