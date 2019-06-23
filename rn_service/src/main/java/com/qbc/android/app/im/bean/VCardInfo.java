package com.qbc.android.app.im.bean;

import java.io.Serializable;

public class VCardInfo implements Serializable {
    private static final long serialVersionUID = 1002L;
    private int id;
    private boolean root;
    private String UserId = "";
    private String XmppId = "";
    private String DescInfo = "";
    private String UserInfo = "";
    private String SearchIndex = "";
    private String IncrementVersion = "";
    private String mark = "";
    private String mood = "";
    private String GroupId = "";
    private String Introduce = "";
    private String Topic = "";
    private String ExtendedFlag = "";
    private String Name = "";
    private String LastUpdateTime = "";
    private String HeaderSrc = "";
    private int collectionBind;
    private int collectionUnReadCount;
    //添加手机号+邮箱字段
    private String tel = "";
    private String email = "";

    public VCardInfo() {
    }

    public String getMark() {
        return this.mark;
    }

    public void setMark(String var1) {
        this.mark = var1;
    }

    public int getCollectionUnReadCount() {
        return this.collectionUnReadCount;
    }

    public void setCollectionUnReadCount(int var1) {
        this.collectionUnReadCount = var1;
    }

    public int getCollectionBind() {
        return this.collectionBind;
    }

    public void setCollectionBind(int var1) {
        this.collectionBind = var1;
    }

    public boolean isRoot() {
        return this.root;
    }

    public void setRoot(boolean var1) {
        this.root = var1;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int var1) {
        this.id = var1;
    }

    public String getIncrementVersion() {
        return this.IncrementVersion;
    }

    public void setIncrementVersion(String var1) {
        this.IncrementVersion = var1;
    }

    public String getSearchIndex() {
        return this.SearchIndex;
    }

    public void setSearchIndex(String var1) {
        this.SearchIndex = var1;
    }

    public String getDescInfo() {
        return this.DescInfo;
    }

    public void setDescInfo(String var1) {
        this.DescInfo = var1;
    }

    public String getUserId() {
        return this.UserId;
    }

    public void setUserId(String var1) {
        this.UserId = var1;
    }

    public String getXmppId() {
        return this.XmppId;
    }

    public void setXmppId(String var1) {
        this.XmppId = var1;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String var1) {
        this.Name = var1;
    }

    public String getUserInfo() {
        return this.UserInfo;
    }

    public void setUserInfo(String var1) {
        this.UserInfo = var1;
    }

    public String getGroupId() {
        return this.GroupId;
    }

    public void setGroupId(String var1) {
        this.GroupId = var1;
    }

    public String getIntroduce() {
        return this.Introduce;
    }

    public void setIntroduce(String var1) {
        this.Introduce = var1;
    }

    public String getTopic() {
        return this.Topic;
    }

    public void setTopic(String var1) {
        this.Topic = var1;
    }

    public String getExtendedFlag() {
        return this.ExtendedFlag;
    }

    public void setExtendedFlag(String var1) {
        this.ExtendedFlag = var1;
    }

    public String getLastUpdateTime() {
        return this.LastUpdateTime;
    }

    public void setLastUpdateTime(String var1) {
        this.LastUpdateTime = var1;
    }

    public String getHeaderSrc() {
        return this.HeaderSrc;
    }

    public void setHeaderSrc(String var1) {
        this.HeaderSrc = var1;
    }

    public void setMood(String var1) {
        this.mood = var1;
    }

    public String getMood() {
        return this.mood;
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
}
