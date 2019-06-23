package com.qbc.android.app.im.utils;

import android.text.TextUtils;

import com.google.gson.JsonObject;
import com.orhanobut.logger.Logger;
import com.qbc.android.app.im.bean.HostUserInfo;
import com.qbc.android.app.im.bean.VCardInfo;
import com.qunar.im.base.jsonbean.BaseJsonResult;
import com.qunar.im.base.jsonbean.NewRemoteConfig;
import com.qunar.im.base.module.UserConfigData;
import com.qunar.im.base.protocol.Protocol;
import com.qunar.im.base.protocol.ProtocolCallback;
import com.qunar.im.base.util.JsonUtils;
import com.qunar.im.core.manager.IMLogicManager;
import com.qunar.im.core.services.QtalkHttpService;
import com.qunar.im.core.services.QtalkNavicationService;
import com.qunar.im.protobuf.common.CurrentPreference;
import com.qunar.im.utils.ConnectionUtil;
import com.qunar.im.utils.UrlCheckUtil;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

public class QbcHttpUtil {


    public static void getVCardInfo(final String userId,
                                   final ProtocolCallback.UnitCallback<VCardInfo> callback) {
        if (TextUtils.isEmpty(IMLogicManager.getInstance().getRemoteLoginKey())) {
            callback.doFailure();
        } else {
            try {
                String ckey = Protocol.getCKEY();
                HashMap checkMap = new HashMap();
                checkMap.put("Cookie", "q_ckey=" + ckey + ";");
                String httpUrl = QtalkNavicationService.getInstance().getHttpUrl();
                //httpUrl = "http://10.0.2.2:8085/newapi";
                String url = String.format("%s//domain/get_vcard_info_one.qunar", httpUrl);
                Logger.i("获取个人卡片信息 参数:" + userId, new Object[0]);
                QtalkHttpService.asyncPostJsonforString(url, userId, checkMap, new QtalkHttpService.CallbackJson() {
                    public final void onJsonResponse(JSONObject jsonObject) {
                        try {
                            Logger.i("新版个人配置接口set:" + jsonObject.toString(), new Object[0]);
                            if (((BaseJsonResult)JsonUtils.getGson().fromJson(jsonObject.toString(), BaseJsonResult.class)).ret) {
                                JSONObject tmp = (JSONObject)jsonObject.get("data");
                                String name = tmp.getString("username");
                                String tel = tmp.getString("tel");
                                String email = tmp.getString("email");
                                String mood = tmp.getString("mood");
                                String imageurl = tmp.getString("imageurl");
                                VCardInfo vCardInfo = new VCardInfo();
                                vCardInfo.setName(name);
                                vCardInfo.setUserId(userId);
                                vCardInfo.setEmail(email);
                                vCardInfo.setTel(tel);
                                vCardInfo.setHeaderSrc(UrlCheckUtil.checkUrlForHttp(
                                        QtalkNavicationService.getInstance().getInnerFiltHttpHost(), imageurl));
                                vCardInfo.setMood(mood);
                                vCardInfo.setDescInfo("未知");//这个需要完善
                                callback.onCompleted(vCardInfo);
                            } else {
                                callback.onFailure("");
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    public final void onFailure(Call var1x, IOException var2) {
                        Logger.i("更新用户信息失败" + var2.getMessage(), new Object[0]);
                        var2.printStackTrace();
                        callback.onFailure("");
                    }
                });
            } catch (Exception var5) {
                var5.printStackTrace();
            }
        }
    }

    public static void setHostUser(String userId,String userName,String tel,String email,
                                   final ProtocolCallback.UnitCallback<HostUserInfo> callback) {
        if (TextUtils.isEmpty(IMLogicManager.getInstance().getRemoteLoginKey())) {
            callback.doFailure();
        } else {
            try {
                String ckey = Protocol.getCKEY();
                HashMap checkMap = new HashMap();
                checkMap.put("Cookie", "q_ckey=" + ckey + ";");
                String httpUrl = QtalkNavicationService.getInstance().getHttpUrl();
                //httpUrl = "http://10.0.2.2:8085/newapi";
                String url = String.format("%s//user/save_host_user.qunar", httpUrl);
                Map<String,Object> params = new HashMap<String,Object>();
                params.put("userId",userId);
                params.put("name",userName);
                params.put("tel",tel);
                params.put("email",email);
                String jsonString = JsonUtils.getGson().toJson(params);
                Logger.i("新版个人配置接口set 参数:" + jsonString, new Object[0]);
                QtalkHttpService.asyncPostJsonforString(url, jsonString, checkMap, new QtalkHttpService.CallbackJson() {
                    public final void onJsonResponse(JSONObject jsonObject) {
                        try {
                            Logger.i("新版个人配置接口set:" + jsonObject.toString(), new Object[0]);
                            if (((BaseJsonResult)JsonUtils.getGson().fromJson(jsonObject.toString(), BaseJsonResult.class)).ret) {
                                HostUserInfo hostUserInfo = (HostUserInfo)JsonUtils.getGson().fromJson(jsonObject.toString(), HostUserInfo.class);
                                callback.onCompleted(hostUserInfo);
                            } else {
                                callback.onFailure("");
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    public final void onFailure(Call var1x, IOException var2) {
                        Logger.i("更新用户信息失败" + var2.getMessage(), new Object[0]);
                        var2.printStackTrace();
                        callback.onFailure("");
                    }
                });
            } catch (Exception var5) {
                var5.printStackTrace();
            }
        }
    }
}
