package com.qunar.im.permission;

import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PermissionDispatcher {
    private static AtomicInteger AUTO_INCREASE_KEY = new AtomicInteger(0);
    public static final int REQUEST_ACCESS_COARSE_LOCATION = 4;
    public static final int REQUEST_ACCESS_FINE_LOCATION = 8;
    public static final int REQUEST_CALL = 128;
    public static final int REQUEST_CAMERA = 1;
    public static final int REQUEST_READ_CALENDAR = 256;
    public static final int REQUEST_READ_EXTERNAL_STORAGE = 32;
    public static final int REQUEST_READ_PHONE_STATE = 2;
    public static final int REQUEST_RECORD_AUDIO = 16;
    public static final int REQUEST_WRITE_CALENDAR = 512;
    public static final int REQUEST_WRITE_EXTERNAL_STORAGE = 64;
    public static final SparseArray<String> permissions = new SparseArray();
    private static final SparseArray<PermissionCallback> stashCallback = new SparseArray();

    static {
        permissions.append(1, "android.permission.CAMERA");
        permissions.append(2, "android.permission.READ_PHONE_STATE");
        permissions.append(4, "android.permission.ACCESS_COARSE_LOCATION");
        permissions.append(8, "android.permission.ACCESS_FINE_LOCATION");
        permissions.append(16, "android.permission.RECORD_AUDIO");
        permissions.append(32, "android.permission.READ_EXTERNAL_STORAGE");
        permissions.append(64, "android.permission.WRITE_EXTERNAL_STORAGE");
        permissions.append(128, "android.permission.CALL_PHONE");
        permissions.append(256, "android.permission.READ_CALENDAR");
        permissions.append(512, "android.permission.WRITE_CALENDAR");
    }

    public static int getRequestCode() {
        return AUTO_INCREASE_KEY.getAndIncrement();
    }

    public static void requestPermissionWithCheck(Activity ctx, int[] permissionFlags, PermissionCallback callback, int requestCode) {
        List<String> permissionStr = new ArrayList(permissionFlags.length);
        for (int key : permissionFlags) {
            String permission = (String) permissions.get(key, null);
            if (permission != null) {
                permissionStr.add(permission);
            }
        }
        permissionStr = PermissionUtils.hasSelfPermissions(ctx, permissionStr);
        if (permissionStr.size() == 0) {
            callback.responsePermission(requestCode, true);
            return;
        }
        stashCallback.append(requestCode, callback);
        ActivityCompat.requestPermissions(ctx, (String[]) permissionStr.toArray(new String[permissionStr.size()]), requestCode);
    }

    public static void onRequestPermissionsResult(int requestCode, int[] grantResults) {
        PermissionCallback callback = (PermissionCallback) stashCallback.get(requestCode, null);
        if (callback != null) {
            callback.responsePermission(requestCode, PermissionUtils.verifyPermissions(grantResults));
            stashCallback.remove(requestCode);
        }
    }
}
