package com.qunar.im.common;

import android.content.Context;
import android.os.Handler;

public class CommonConfig {
    public static int DEFAULT_GRAVATAR = 0;
    public static int DEFAULT_GROUP = 0;
    public static int DEFAULT_NEW_MSG = 0;
    public static final int DOWNLOAD_LINE_MAX = 100;
    public static int SYS_ICON = 0;
    public static final int UPLOAD_LINE_MAX = 10;
    public static final String XMPP_Resource = (isQtalk ? "P[Android_QTALK]" : "P[Android_QCHAT]");
    public static String chanelId = "";
    public static String currentPlat = "qtalk";
    public static String currentUserId = "";
    public static long divideTime = 0;
    public static boolean fireafterread = false;
    public static int fontSizeMode = 1;
    public static Context globalContext;
    public static boolean isDebug = false;
    public static boolean isPbProtocol = true;
    public static boolean isPlayVoice = false;
    public static boolean isQtalk = true;
    public static boolean leave = true;
    public static boolean loginViewHasShown = false;
    public static Handler mainhandler = new Handler();
    public static String schema = "qtalkaphone";
    public static boolean showFireAfierread = false;
    public static boolean showHongbao = false;
    public static boolean showLocation = false;
    public static boolean showQchatGroup = true;
    public static boolean showVideoCommunication = true;
    public static String verifyKey = "";
    public static int versionCode = 190;
    public static String versionName = "8.20";
}
