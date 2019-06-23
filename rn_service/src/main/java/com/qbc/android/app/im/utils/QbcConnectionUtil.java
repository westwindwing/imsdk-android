package com.qbc.android.app.im.utils;

import com.qbc.android.app.im.bean.HostUserInfo;
import com.qunar.im.base.jsonbean.NewRemoteConfig;
import com.qunar.im.base.protocol.ProtocolCallback;
import com.qunar.im.core.manager.IMNotificaitonCenter;
import com.qunar.im.core.services.QtalkNavicationService;
import com.qunar.im.other.QtalkSDK;
import com.qunar.im.utils.ConnectionUtil;
import com.qunar.im.utils.HttpUtil;

public class QbcConnectionUtil {

    public static QbcConnectionUtil instance = null;
    public QtalkSDK qtalkSDK = QtalkSDK.getInstance();
    public static String defaultUserImage = QtalkNavicationService.getInstance().getInnerFiltHttpHost() + "/file/v2/download/perm/3ca05f2d92f6c0034ac9aee14d341fc7.png";

    private QbcConnectionUtil() {
    }

    public static QbcConnectionUtil getInstance() {
        if (instance == null) {
            Class var0 = ConnectionUtil.class;
            synchronized(ConnectionUtil.class) {
                if (instance == null) {
                    instance = new QbcConnectionUtil();
                }
            }
        }

        return instance;
    }
}
