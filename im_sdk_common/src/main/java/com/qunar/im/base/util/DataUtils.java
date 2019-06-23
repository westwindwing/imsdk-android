package com.qunar.im.base.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.google.gson.reflect.TypeToken;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtils {
    public static final String PREFERENCE_NAME = "QunarIMPreferences";
    public static final String S = "E50C75C5";
    private static DataUtils instance = null;
    private SharedPreferences sharedPreferences;

    private DataUtils(Context context) {
        this.sharedPreferences = context.getSharedPreferences("QunarIMPreferences", 4);
    }

    public static DataUtils getInstance(Context context) {
        if (instance == null) {
            instance = createSharedPreference(context);
        }
        return instance;
    }

    private static synchronized DataUtils createSharedPreference(Context context) {
        DataUtils dataUtils;
        synchronized (DataUtils.class) {
            if (instance == null) {
                instance = new DataUtils(context);
            }
            dataUtils = instance;
        }
        return dataUtils;
    }

    public void removePreferences(String key) {
        if (key != null) {
            Editor editor = this.sharedPreferences.edit();
            editor.remove(key);
            editor.apply();
        }
    }

    public synchronized void putPreferences(String key, String value) {
        if (!(key == null || value == null)) {
            Editor editor = this.sharedPreferences.edit();
            try {
                editor.putString(key, DESUtils.encrypt("E50C75C5", value));
            } catch (Exception e) {
                e.printStackTrace();
            }
            editor.apply();
        }
        return;
    }

    public void putPreferences(String key, boolean value) {
        Editor editor = this.sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public void putPreferences(String key, int value) {
        Editor editor = this.sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public void putPreferences(String key, long value) {
        Editor editor = this.sharedPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public void putPreferences(String key, float value) {
        Editor editor = this.sharedPreferences.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public void putPreferences(String key, Serializable value) {
        Editor editor = this.sharedPreferences.edit();
        editor.putString(key, JsonUtils.getGson().toJson(value));
        editor.apply();
    }

    public boolean getPreferences(String key, boolean defaultValue) {
        try {
            return this.sharedPreferences.getBoolean(key, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    public int getPreferences(String key, int defValue) {
        try {
            return this.sharedPreferences.getInt(key, defValue);
        } catch (Exception e) {
            e.printStackTrace();
            return defValue;
        }
    }

    public String getPreferences(String key, String defValue) {
        try {
            return DESUtils.decrypt("E50C75C5", this.sharedPreferences.getString(key, defValue));
        } catch (Exception e) {
            e.printStackTrace();
            return defValue;
        }
    }

    public long getPreferences(String key, long defValue) {
        try {
            return this.sharedPreferences.getLong(key, defValue);
        } catch (Exception e) {
            e.printStackTrace();
            return defValue;
        }
    }

    public float getPreferences(String key, float defValue) {
        try {
            return this.sharedPreferences.getFloat(key, defValue);
        } catch (Exception e) {
            e.printStackTrace();
            return defValue;
        }
    }

    public <T extends Serializable> T getPreferencesT(String key, TypeToken<T> type) {
        try {
            return (Serializable) JsonUtils.getGson().fromJson(this.sharedPreferences.getString(key, null), type.getType());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0024 A:{SYNTHETIC, Splitter:B:17:0x0024} */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0029 A:{Catch:{ IOException -> 0x002d }} */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0037 A:{SYNTHETIC, Splitter:B:27:0x0037} */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x003c A:{Catch:{ IOException -> 0x0040 }} */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0047 A:{SYNTHETIC, Splitter:B:35:0x0047} */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x004c A:{Catch:{ IOException -> 0x0050 }} */
    public boolean saveObject(android.content.Context r7, java.lang.Object r8, java.lang.String r9) {
        /*
        r6 = this;
        r4 = 0;
        r1 = 0;
        r2 = 0;
        r5 = 0;
        r1 = r7.openFileOutput(r9, r5);	 Catch:{ FileNotFoundException -> 0x0021, IOException -> 0x0031 }
        r3 = new java.io.ObjectOutputStream;	 Catch:{ FileNotFoundException -> 0x0021, IOException -> 0x0031 }
        r3.<init>(r1);	 Catch:{ FileNotFoundException -> 0x0021, IOException -> 0x0031 }
        r3.writeObject(r8);	 Catch:{ FileNotFoundException -> 0x005a, IOException -> 0x0057, all -> 0x0054 }
        r4 = 1;
        if (r3 == 0) goto L_0x0016;
    L_0x0013:
        r3.close();	 Catch:{ IOException -> 0x001d }
    L_0x0016:
        if (r1 == 0) goto L_0x001b;
    L_0x0018:
        r1.close();	 Catch:{ IOException -> 0x001d }
    L_0x001b:
        r2 = r3;
    L_0x001c:
        return r4;
    L_0x001d:
        r0 = move-exception;
        r2 = 0;
        r1 = 0;
        goto L_0x001c;
    L_0x0021:
        r5 = move-exception;
    L_0x0022:
        if (r2 == 0) goto L_0x0027;
    L_0x0024:
        r2.close();	 Catch:{ IOException -> 0x002d }
    L_0x0027:
        if (r1 == 0) goto L_0x001c;
    L_0x0029:
        r1.close();	 Catch:{ IOException -> 0x002d }
        goto L_0x001c;
    L_0x002d:
        r0 = move-exception;
        r2 = 0;
        r1 = 0;
        goto L_0x001c;
    L_0x0031:
        r0 = move-exception;
    L_0x0032:
        r7.deleteFile(r9);	 Catch:{ all -> 0x0044 }
        if (r2 == 0) goto L_0x003a;
    L_0x0037:
        r2.close();	 Catch:{ IOException -> 0x0040 }
    L_0x003a:
        if (r1 == 0) goto L_0x001c;
    L_0x003c:
        r1.close();	 Catch:{ IOException -> 0x0040 }
        goto L_0x001c;
    L_0x0040:
        r0 = move-exception;
        r2 = 0;
        r1 = 0;
        goto L_0x001c;
    L_0x0044:
        r4 = move-exception;
    L_0x0045:
        if (r2 == 0) goto L_0x004a;
    L_0x0047:
        r2.close();	 Catch:{ IOException -> 0x0050 }
    L_0x004a:
        if (r1 == 0) goto L_0x004f;
    L_0x004c:
        r1.close();	 Catch:{ IOException -> 0x0050 }
    L_0x004f:
        throw r4;
    L_0x0050:
        r0 = move-exception;
        r2 = 0;
        r1 = 0;
        goto L_0x004f;
    L_0x0054:
        r4 = move-exception;
        r2 = r3;
        goto L_0x0045;
    L_0x0057:
        r0 = move-exception;
        r2 = r3;
        goto L_0x0032;
    L_0x005a:
        r5 = move-exception;
        r2 = r3;
        goto L_0x0022;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qunar.im.base.util.DataUtils.saveObject(android.content.Context, java.lang.Object, java.lang.String):boolean");
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:32:0x0043=Splitter:B:32:0x0043, B:50:0x0066=Splitter:B:50:0x0066} */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0048 A:{SYNTHETIC, Splitter:B:35:0x0048} */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x004d A:{Catch:{ IOException -> 0x0051 }} */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0058 A:{SYNTHETIC, Splitter:B:43:0x0058} */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x005d A:{Catch:{ IOException -> 0x0061 }} */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x006b A:{SYNTHETIC, Splitter:B:53:0x006b} */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0070 A:{Catch:{ IOException -> 0x0074 }} */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x007b A:{SYNTHETIC, Splitter:B:61:0x007b} */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0080 A:{Catch:{ IOException -> 0x0084 }} */
    public java.lang.Object loadObject(android.content.Context r7, java.lang.String r8) {
        /*
        r6 = this;
        r1 = 0;
        r3 = 0;
        r1 = r7.openFileInput(r8);	 Catch:{ FileNotFoundException -> 0x0032, IOException -> 0x0042, ClassNotFoundException -> 0x0055, ClassCastException -> 0x0065 }
        r4 = new java.io.ObjectInputStream;	 Catch:{ FileNotFoundException -> 0x0032, IOException -> 0x0042, ClassNotFoundException -> 0x0055, ClassCastException -> 0x0065 }
        r4.<init>(r1);	 Catch:{ FileNotFoundException -> 0x0032, IOException -> 0x0042, ClassNotFoundException -> 0x0055, ClassCastException -> 0x0065 }
        r2 = r4.readObject();	 Catch:{ FileNotFoundException -> 0x0094, IOException -> 0x0091, ClassNotFoundException -> 0x008e, ClassCastException -> 0x008b, all -> 0x0088 }
        if (r2 == 0) goto L_0x0021;
    L_0x0011:
        if (r4 == 0) goto L_0x0016;
    L_0x0013:
        r4.close();	 Catch:{ IOException -> 0x001d }
    L_0x0016:
        if (r1 == 0) goto L_0x001b;
    L_0x0018:
        r1.close();	 Catch:{ IOException -> 0x001d }
    L_0x001b:
        r3 = r4;
    L_0x001c:
        return r2;
    L_0x001d:
        r0 = move-exception;
        r3 = 0;
        r1 = 0;
        goto L_0x001c;
    L_0x0021:
        if (r4 == 0) goto L_0x0026;
    L_0x0023:
        r4.close();	 Catch:{ IOException -> 0x002e }
    L_0x0026:
        if (r1 == 0) goto L_0x002b;
    L_0x0028:
        r1.close();	 Catch:{ IOException -> 0x002e }
    L_0x002b:
        r3 = r4;
    L_0x002c:
        r2 = 0;
        goto L_0x001c;
    L_0x002e:
        r0 = move-exception;
        r3 = 0;
        r1 = 0;
        goto L_0x002c;
    L_0x0032:
        r5 = move-exception;
    L_0x0033:
        if (r3 == 0) goto L_0x0038;
    L_0x0035:
        r3.close();	 Catch:{ IOException -> 0x003e }
    L_0x0038:
        if (r1 == 0) goto L_0x002c;
    L_0x003a:
        r1.close();	 Catch:{ IOException -> 0x003e }
        goto L_0x002c;
    L_0x003e:
        r0 = move-exception;
        r3 = 0;
        r1 = 0;
        goto L_0x002c;
    L_0x0042:
        r0 = move-exception;
    L_0x0043:
        r7.deleteFile(r8);	 Catch:{ all -> 0x0078 }
        if (r3 == 0) goto L_0x004b;
    L_0x0048:
        r3.close();	 Catch:{ IOException -> 0x0051 }
    L_0x004b:
        if (r1 == 0) goto L_0x002c;
    L_0x004d:
        r1.close();	 Catch:{ IOException -> 0x0051 }
        goto L_0x002c;
    L_0x0051:
        r0 = move-exception;
        r3 = 0;
        r1 = 0;
        goto L_0x002c;
    L_0x0055:
        r5 = move-exception;
    L_0x0056:
        if (r3 == 0) goto L_0x005b;
    L_0x0058:
        r3.close();	 Catch:{ IOException -> 0x0061 }
    L_0x005b:
        if (r1 == 0) goto L_0x002c;
    L_0x005d:
        r1.close();	 Catch:{ IOException -> 0x0061 }
        goto L_0x002c;
    L_0x0061:
        r0 = move-exception;
        r3 = 0;
        r1 = 0;
        goto L_0x002c;
    L_0x0065:
        r0 = move-exception;
    L_0x0066:
        r7.deleteFile(r8);	 Catch:{ all -> 0x0078 }
        if (r3 == 0) goto L_0x006e;
    L_0x006b:
        r3.close();	 Catch:{ IOException -> 0x0074 }
    L_0x006e:
        if (r1 == 0) goto L_0x002c;
    L_0x0070:
        r1.close();	 Catch:{ IOException -> 0x0074 }
        goto L_0x002c;
    L_0x0074:
        r0 = move-exception;
        r3 = 0;
        r1 = 0;
        goto L_0x002c;
    L_0x0078:
        r5 = move-exception;
    L_0x0079:
        if (r3 == 0) goto L_0x007e;
    L_0x007b:
        r3.close();	 Catch:{ IOException -> 0x0084 }
    L_0x007e:
        if (r1 == 0) goto L_0x0083;
    L_0x0080:
        r1.close();	 Catch:{ IOException -> 0x0084 }
    L_0x0083:
        throw r5;
    L_0x0084:
        r0 = move-exception;
        r3 = 0;
        r1 = 0;
        goto L_0x0083;
    L_0x0088:
        r5 = move-exception;
        r3 = r4;
        goto L_0x0079;
    L_0x008b:
        r0 = move-exception;
        r3 = r4;
        goto L_0x0066;
    L_0x008e:
        r5 = move-exception;
        r3 = r4;
        goto L_0x0056;
    L_0x0091:
        r0 = move-exception;
        r3 = r4;
        goto L_0x0043;
    L_0x0094:
        r5 = move-exception;
        r3 = r4;
        goto L_0x0033;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qunar.im.base.util.DataUtils.loadObject(android.content.Context, java.lang.String):java.lang.Object");
    }

    public static String formationDate(long time) {
        String dateString = "未知";
        Date date = new Date(time);
        Date now = new Date();
        try {
            long seconds = now.getTime() - date.getTime();
            if (seconds < 10000) {
                return "刚刚";
            }
            if (seconds < 60000) {
                return (seconds / 1000) + "秒前";
            }
            if (seconds < 3600000) {
                return ((seconds / 1000) / 60) + "分钟前";
            }
            if (seconds < 86400000) {
                return (((seconds / 1000) / 60) / 60) + "小时前";
            }
            if (seconds < 2592000000L) {
                return ((((seconds / 1000) / 60) / 60) / 24) + "天前";
            }
            if (date.getYear() == now.getYear()) {
                return new SimpleDateFormat("MM-dd").format(Long.valueOf(date.getTime()));
            }
            if (date.getYear() != now.getYear()) {
                return new SimpleDateFormat("yyyy-MM-dd").format(Long.valueOf(date.getTime()));
            }
            return new SimpleDateFormat("yyyy-MM-dd").format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return dateString;
        }
    }
}
