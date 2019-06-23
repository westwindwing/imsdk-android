package com.qbc.android.app.im.mplugins;

import android.app.Activity;
import android.text.TextUtils;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.orhanobut.logger.Logger;
import com.qbc.android.app.im.bean.HostUserInfo;
import com.qbc.android.app.im.bean.VCardInfo;
import com.qbc.android.app.im.utils.QbcConnectionUtil;
import com.qbc.android.app.im.utils.QbcHttpUtil;
import com.qunar.im.base.jsonbean.NewRemoteConfig;
import com.qunar.im.base.module.Nick;
import com.qunar.im.base.module.UserConfigData;
import com.qunar.im.base.protocol.ProtocolCallback;
import com.qunar.im.base.util.Constants;
import com.qunar.im.base.util.IMUserDefaults;
import com.qunar.im.common.CommonConfig;
import com.qunar.im.core.manager.IMLogicManager;
import com.qunar.im.core.manager.IMNotificaitonCenter;
import com.qunar.im.core.services.QtalkNavicationService;
import com.qunar.im.other.CacheDataType;
import com.qunar.im.permission.PermissionCallback;
import com.qunar.im.protobuf.Event.QtalkEvent;
import com.qunar.im.protobuf.common.CurrentPreference;
import com.qunar.im.utils.ConnectionUtil;
import com.qunar.im.utils.HttpUtil;

import java.util.HashMap;
import java.util.Map;

public class QbcImRNBModule extends ReactContextBaseJavaModule
        implements IMNotificaitonCenter.NotificationCenterDelegate, PermissionCallback {
    public Activity mActivity;//activity 为华为push要用


    public QbcImRNBModule(ReactApplicationContext reactContext) {
        super(reactContext);
        addEvent();
    }

    public QbcImRNBModule(ReactApplicationContext reactContext, Activity mActivity) {
        super(reactContext);
        this.mActivity = mActivity;
        addEvent();
    }

    private void addEvent() {

    }

    @Override
    public String getName() {
        return "QbcImRNBModule";
    }

    @Override
    public void didReceivedNotification(String s, Object... objects) {

    }

    @Override
    public void responsePermission(int i, boolean b) {

    }

    /**
     * 获取自己的信息
     *
     * @param callback
     */
    @ReactMethod
    public void getVCardInfo(final Callback callback) {
        String user = IMUserDefaults.getStandardUserDefaults().getStringValue(CommonConfig.globalContext, Constants.Preferences.lastuserid);
        String domain = QtalkNavicationService.getInstance().getXmppdomain();
        if (TextUtils.isEmpty(user) || TextUtils.isEmpty(domain)) {
            return;
        }
        final String userId = user + "@" + domain;
        final WritableNativeMap re = new WritableNativeMap();
        QbcHttpUtil.getVCardInfo(userId, new ProtocolCallback.UnitCallback<VCardInfo>() {
            public void onCompleted(VCardInfo cardInfo) {
                IMNotificaitonCenter.getInstance().postMainThreadNotificationName(QtalkEvent.REFRESH_NICK);
                re.putBoolean("ok", true);
                WritableMap userInfo = new WritableNativeMap();
                userInfo.putString("Name", cardInfo.getName());
                userInfo.putString("HeaderUri", cardInfo.getHeaderSrc());
                userInfo.putString("Department", TextUtils.isEmpty(cardInfo.getDescInfo()) ? "未知" : cardInfo.getDescInfo());
                userInfo.putString("UserId", cardInfo.getUserId());
                userInfo.putString("Mood", cardInfo.getMood());
                userInfo.putString("Tel", cardInfo.getTel());
                userInfo.putString("Department", cardInfo.getDescInfo());

                re.putMap("UserInfo", userInfo);
                callback.invoke(re);
            }

            public void onFailure(String var1) {
                re.putBoolean("ok", false);
                com.orhanobut.logger.Logger.i("设置用户卡片信息返回为null，失败");
                callback.invoke(re);
            }
        });
    }


    /**
     * 设置用户信息
     *
     * @param params
     * @param callback
     */
    @ReactMethod
    public void setHostUserName(ReadableMap params, final Callback callback) {
        Logger.i("开始进行设置用户信息操作");
        final String userId = params.getString("UserId");
        final String userName = params.getString("Name");

        final WritableNativeMap re = new WritableNativeMap();

        QbcHttpUtil.setHostUser(userId,userName,null,null, new ProtocolCallback.UnitCallback<HostUserInfo>() {
            public void onCompleted(HostUserInfo var1) {
                IMNotificaitonCenter.getInstance().postMainThreadNotificationName(QtalkEvent.REFRESH_NICK);
                re.putBoolean("ok", true);
                callback.invoke(re);
            }

            public void onFailure(String var1) {
                re.putBoolean("ok", false);
                com.orhanobut.logger.Logger.i("设置备注漫游返回为null失败");
                callback.invoke(re);
            }
        });
    }

    @ReactMethod
    public void setHostUserTel(ReadableMap params, final Callback callback) {
        Logger.i("开始进行设置用户信息操作");
        final String userId = params.getString("UserId");
        final String tel = params.getString("Tel");

        final WritableNativeMap re = new WritableNativeMap();

        QbcHttpUtil.setHostUser(userId,null,tel,null, new ProtocolCallback.UnitCallback<HostUserInfo>() {
            public void onCompleted(HostUserInfo var1) {
                IMNotificaitonCenter.getInstance().postMainThreadNotificationName(QtalkEvent.REFRESH_NICK);
                re.putBoolean("ok", true);
                callback.invoke(re);
            }

            public void onFailure(String var1) {
                re.putBoolean("ok", false);
                com.orhanobut.logger.Logger.i("设置备注漫游返回为null失败");
                callback.invoke(re);
            }
        });
    }

    /**
     * 获取自己的信息
     * 补充电话，电子邮箱两个信息
     *
     * @param callback
     */
    @ReactMethod
    public void getMyInfo(final Callback callback) {

        String user = IMUserDefaults.getStandardUserDefaults().getStringValue(CommonConfig.globalContext, Constants.Preferences.lastuserid);
        String domain = QtalkNavicationService.getInstance().getXmppdomain();
        if (TextUtils.isEmpty(user) || TextUtils.isEmpty(domain)) {
            return;
        }
        final String lastid = user + "@" + domain;
        ConnectionUtil.getInstance().getUserCard(lastid, new IMLogicManager.NickCallBack() {
            @Override
            public void onNickCallBack(Nick nick) {
                WritableMap hm = new WritableNativeMap();

                hm.putString("Name", nick.getName());
                hm.putString("HeaderUri", nick.getHeaderSrc());

                hm.putString("Department", TextUtils.isEmpty(nick.getDescInfo()) ? "未知" : nick.getDescInfo());
                hm.putString("UserId", nick.getXmppId());
                hm.putString("Mood", nick.getMood());
                WritableNativeMap map = new WritableNativeMap();
                map.putMap("MyInfo", hm);
                callback.invoke(map);
            }
        }, true, true);
    }
}
