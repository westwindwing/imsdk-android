package com.qunar.im.base.util;

import android.text.TextUtils;
import com.orhanobut.logger.Logger;
import com.qunar.im.common.CommonConfig;
import java.io.File;
import java.util.UUID;

public class PhoneInfoUtils {
    public static synchronized String getUniqueID() {
        String token;
        synchronized (PhoneInfoUtils.class) {
            File deviceUnique = new File(CommonConfig.globalContext.getFilesDir(), CommonConfig.currentPlat + "_" + IMUserDefaults.getStandardUserDefaults().getStringValue(CommonConfig.globalContext, "LastUserId") + "_" + IMUserDefaults.getStandardUserDefaults().getStringValue(CommonConfig.globalContext, "LastUserDomain") + "_unique");
            Logger.i("push token filename = " + deviceUnique.getName(), new Object[0]);
            if (!deviceUnique.exists()) {
                FileUtils.writeToFile(BinaryUtil.MD5(UUID.randomUUID().toString()), deviceUnique, true);
            }
            token = FileUtils.readFirstLine(deviceUnique, CommonConfig.globalContext);
            Logger.i("get push token = " + token, new Object[0]);
        }
        return token;
    }

    public static synchronized void delUniqueID() {
        synchronized (PhoneInfoUtils.class) {
            if (!TextUtils.isEmpty(getUniqueID())) {
                File deviceUnique = new File(CommonConfig.globalContext.getFilesDir(), CommonConfig.currentPlat + "_" + IMUserDefaults.getStandardUserDefaults().getStringValue(CommonConfig.globalContext, "LastUserId") + "_" + IMUserDefaults.getStandardUserDefaults().getStringValue(CommonConfig.globalContext, "LastUserDomain") + "_unique");
                Logger.i("del push token filename = " + deviceUnique.getName(), new Object[0]);
                if (deviceUnique.exists()) {
                    deviceUnique.delete();
                }
            }
        }
    }
}
