package com.qunar.im.base.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.text.TextUtils;
import java.util.List;

public class RunningApp {
    public static boolean isForeground(Context context) {
        if (context == null) {
            return false;
        }
        List<RunningAppProcessInfo> appProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (appProcesses == null) {
            return false;
        }
        for (RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                if (appProcess.importance == 100) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public static boolean isEmptyProcess(Context context) {
        List<RunningAppProcessInfo> appProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (appProcesses == null) {
            return false;
        }
        for (RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                if (appProcess.importance == 500) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public static boolean isService(Context context) {
        List<RunningAppProcessInfo> appProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (appProcesses == null) {
            return false;
        }
        for (RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                if (appProcess.importance == 300) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public static boolean isForeground(Context context, String className) {
        if (context == null || TextUtils.isEmpty(className)) {
            return false;
        }
        List<RunningTaskInfo> list = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
        if (list == null || list.size() <= 0 || !className.equals(((RunningTaskInfo) list.get(0)).topActivity.getClassName())) {
            return false;
        }
        return true;
    }
}
