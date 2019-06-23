package com.qunar.im.base.view.faceGridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmoticionMap {
    public int count;
    public Map<String, EmoticonEntity> emoticonEntityMap = new HashMap(this.count);
    private List<String> indexes = new ArrayList(this.count);
    public int line;
    public String packgeId;
    public int showAll;
    public String version;

    public EmoticionMap(String v, int c, int s, int l, String p) {
        this.count = c;
        this.version = v;
        this.showAll = s;
        this.line = l;
        this.packgeId = p;
    }

    public void pusEntity(String key, EmoticonEntity entity) {
        this.indexes.add(key);
        this.emoticonEntityMap.put(key, entity);
    }

    public EmoticonEntity getEntity(String key) {
        return (EmoticonEntity) this.emoticonEntityMap.get(key);
    }

    public EmoticonEntity getEntity(int index) {
        return getEntity((String) this.indexes.get(index));
    }

    public boolean containKey(String key) {
        return this.emoticonEntityMap.containsKey(key);
    }
}
