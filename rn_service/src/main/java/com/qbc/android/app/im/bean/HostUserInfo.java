package com.qbc.android.app.im.bean;

public class HostUserInfo {
    //userId+domain 唯一确认一笔用户信息
    private String userId;
    private String domain;
    //用户名称
    private String name;
    //联系手机
    private String tel;
    //电子邮箱
    private String email;
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

}
