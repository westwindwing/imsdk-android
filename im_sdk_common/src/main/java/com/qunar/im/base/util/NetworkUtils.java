package com.qunar.im.base.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtils {
    private static ConnectivityManager connectivityManager;

    public enum ConnectStatus {
        connected,
        disconnected,
        unkown
    }

    public static ConnectStatus isConnection(Context context) {
        if (connectivityManager == null) {
            connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return ConnectStatus.unkown;
            }
        }
        boolean isConnected = false;
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            isConnected = networkInfo.isConnected();
        }
        return isConnected ? ConnectStatus.connected : ConnectStatus.disconnected;
    }

    public static int getNetworkType() {
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return -100;
        }
        return networkInfo.getType();
    }

    public static boolean isWifi(Context context) {
        if (connectivityManager == null) {
            connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return false;
            }
        }
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return false;
        }
        return networkInfo.getType() == 1;
    }
}
