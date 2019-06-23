package com.qunar.im.base.util;

import android.util.Log;
import com.qunar.im.common.CommonConfig;

public class LogUtil {
    private static final String LOG_TAG = "QIM";

    public static void v(String msg) {
        v("QIM", msg);
    }

    public static void v(String tag, String msg) {
        if (CommonConfig.isDebug) {
            if (msg == null) {
                msg = "value is null";
            }
            Log.v(tag, msg);
        }
    }

    public static void v(String tag, String msg, Throwable tr) {
        if (CommonConfig.isDebug) {
            if (msg == null) {
                msg = "value is null";
            }
            Log.v(tag, msg, tr);
        }
    }

    public static void d(String msg) {
        d("QIM", msg);
    }

    public static void d(String tag, String info) {
        if (CommonConfig.isDebug) {
            if (info == null) {
                info = "value is null";
            }
            Log.d(tag, info);
        }
    }

    public static void d(String tag, String info, Throwable tr) {
        if (CommonConfig.isDebug) {
            if (info == null) {
                info = "value is null";
            }
            Log.d(tag, info, tr);
        }
    }

    public static void i(String info) {
        i("QIM", info);
    }

    public static void i(String tag, String info) {
        if (CommonConfig.isDebug) {
            if (info == null) {
                info = "value is null";
            }
            Log.i(tag, info);
        }
    }

    public static void i(String tag, String info, Throwable tr) {
        if (CommonConfig.isDebug) {
            if (info == null) {
                info = "value is null";
            }
            Log.i(tag, info, tr);
        }
    }

    public static void w(String msg) {
        w("QIM", msg);
    }

    public static void w(String tag, String info) {
        if (CommonConfig.isDebug) {
            if (info == null) {
                info = "value is null";
            }
            Log.w(tag, info);
        }
    }

    public static void w(String tag, String info, Throwable tr) {
        if (CommonConfig.isDebug) {
            if (info == null) {
                info = "value is null";
            }
            Log.w(tag, info, tr);
        }
    }

    public static void e(String msg) {
        e("QIM", msg);
    }

    public static void e(String tag, String info) {
        if (CommonConfig.isDebug) {
            if (info == null) {
                info = "value is null";
            }
            Log.e(tag, info);
        }
    }

    public static void e(String tag, String info, Throwable tr) {
        if (CommonConfig.isDebug) {
            if (info == null) {
                info = "value is null";
            }
            Log.e(tag, info, tr);
        }
    }
}
