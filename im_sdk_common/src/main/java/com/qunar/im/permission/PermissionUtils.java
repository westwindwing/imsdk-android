package com.qunar.im.permission;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.PermissionChecker;
import java.util.ArrayList;
import java.util.List;

public class PermissionUtils {
    private PermissionUtils() {
    }

    public static boolean verifyPermissions(int... grantResults) {
        for (int result : grantResults) {
            if (result != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkPermissionGranted(Context context, String permission) {
        return PermissionChecker.checkSelfPermission(context, permission) == 0;
    }

    public static List<String> hasSelfPermissions(Context context, List<String> permissions) {
        List<String> result = new ArrayList(permissions.size());
        for (String permission : permissions) {
            if (PermissionChecker.checkSelfPermission(context, permission) != 0) {
                result.add(permission);
            }
        }
        return result;
    }

    public static boolean shouldShowRequestPermissionRationale(Activity activity, String... permissions) {
        for (String permission : permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                return true;
            }
        }
        return false;
    }

    public static boolean shouldShowRequestPermissionRationale(Fragment fragment, String... permissions) {
        for (String permission : permissions) {
            if (fragment.shouldShowRequestPermissionRationale(permission)) {
                return true;
            }
        }
        return false;
    }
}
