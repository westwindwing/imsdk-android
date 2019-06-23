package com.qunar.im.base.view.multilLevelTreeView;

public class Node implements Comparable<Node> {
    private String headerSrc;
    private int icon;
    private int id;
    private boolean isExpand = false;
    private boolean isRoot;
    private String key;
    private int level;
    private String name;
    private int pId = 0;
    private String xmppId;

    public String getXmppId() {
        return this.xmppId;
    }

    public void setXmppId(String xmppId) {
        this.xmppId = xmppId;
    }

    public String getHeaderSrc() {
        return this.headerSrc;
    }

    public void setHeaderSrc(String headerSrc) {
        this.headerSrc = headerSrc;
    }

    public boolean isRoot() {
        return this.isRoot;
    }

    public void setRoot(boolean isRoot) {
        this.isRoot = isRoot;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getpId() {
        return this.pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isExpand() {
        return this.isExpand;
    }

    public void setExpand(boolean isExpand) {
        this.isExpand = isExpand;
    }

    public int getIcon() {
        return this.icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int compareTo(Node another) {
        if (getId() == another.getId()) {
            return 0;
        }
        if (getId() < another.getId()) {
            return -1;
        }
        return 1;
    }

    public boolean equals(Object o) {
        return o != null && this.key != null && (o instanceof Node) && this.key.equals(((Node) o).getKey());
    }
}
