package com.qbc.android.app.im;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.multidex.MultiDex;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.qunar.im.base.jsonbean.NavConfigResult;
import com.qunar.im.base.protocol.ProtocolCallback;
import com.qunar.im.base.util.Constants;
import com.qunar.im.base.util.DataUtils;
import com.qunar.im.base.util.JsonUtils;
import com.qunar.im.core.services.QtalkNavicationService;
import com.qunar.im.ui.activity.IMBaseActivity;
import com.qunar.im.ui.sdk.QIMSdk;
import com.qunar.im.ui.util.NavConfigUtils;
import com.qunar.im.utils.HttpUtil;
import com.qunar.im.utils.StringUtils;

/**
 * Created by lihaibin.li on 2018/2/22.
 */

public class ImApplication extends android.app.Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        QIMSdk.getInstance().init(this);
        QIMSdk.getInstance().openDebug();

        String defaultNavName = DataUtils.getInstance(this).getPreferences(Constants.Preferences.COMPANY,"");
        if(StringUtils.isEmpty(defaultNavName)){
            //如果App未保存IM公司配置信息，初始化一个
            //获取本应用程序信息
            try {
                ApplicationInfo applicationInfo = this.getPackageManager().getApplicationInfo(this.getPackageName(), PackageManager.GET_META_DATA);
                if(applicationInfo == null){
                    return;
                }
                String initSrvUrl = applicationInfo.metaData.getString("INIT_SRV_URL");
                String initSrvName = applicationInfo.metaData.getString("INIT_SRV_NAME");
                if(StringUtils.isNullOrEmpty(initSrvUrl) || StringUtils.isNullOrEmpty(initSrvName)){
                    return;
                }
                final String url = initSrvUrl;
                final String name = initSrvName;
                Logger.i("开始初始导航信息:导航名称：" + name+",导航URL:"+url);
                HttpUtil.getServerConfigAsync(url, new ProtocolCallback.UnitCallback<NavConfigResult>() {
                    @Override
                    public void onCompleted(final NavConfigResult navConfigResult) {
                        String configStr = JsonUtils.getGson().toJson(navConfigResult);
                        Logger.i("初始化导航信息成功:" + configStr);
                        final String navName = TextUtils.isEmpty(name) ? navConfigResult.baseaddess.domain : name;
                        //保存导航信息
                        NavConfigUtils.saveNavInfo(navName,url);
                        //保存当前配置
                        NavConfigUtils.saveCurrentNavJSONInfo(navName,configStr);
                        //配置导航
                        QtalkNavicationService.getInstance().configNav(navConfigResult);
                        //设置默认公司
                        NavConfigUtils.saveCurrentNavDomain(navName);
                    }

                    @Override
                    public void onFailure(String errMsg) {
                        Logger.i("初始化导航信息错误:" + errMsg);
                    }
                });

            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            //final String url = "http://10.0.2.2:8080/newapi/nck/qtalk_nav.qunar";
            /*NavConfigUtils.saveNavInfo(name,url);
            NavConfigUtils.saveCurrentNavDomain(name);*/


        }
    }
}
