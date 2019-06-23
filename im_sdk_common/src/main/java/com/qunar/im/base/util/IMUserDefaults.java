package com.qunar.im.base.util;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import java.util.Set;
import org.json.JSONObject;

public class IMUserDefaults {
    private static IMUserDefaults standardUserDefaults = new IMUserDefaults("userdefault");
    private String filename;

    public class IMUserDefaultsEditor {
        private Editor editor;

        protected IMUserDefaultsEditor(Editor imUserDefaults) {
            this.editor = imUserDefaults;
        }

        public IMUserDefaultsEditor putObject(String key, String value) {
            this.editor.putString(key, value);
            return this;
        }

        public IMUserDefaultsEditor putObject(String key, boolean value) {
            this.editor.putBoolean(key, value);
            return this;
        }

        public IMUserDefaultsEditor putObject(String key, float value) {
            this.editor.putFloat(key, value);
            return this;
        }

        public IMUserDefaultsEditor putObject(String key, int value) {
            this.editor.putInt(key, value);
            return this;
        }

        public IMUserDefaultsEditor putObject(String key, long value) {
            this.editor.putLong(key, value);
            return this;
        }

        public IMUserDefaultsEditor putObject(String key, Set<String> value) {
            this.editor.putStringSet(key, value);
            return this;
        }

        public IMUserDefaultsEditor putObject(String key, JSONObject value) {
            this.editor.putString(key, value.toString());
            return this;
        }

        public IMUserDefaultsEditor removeObject(String key) {
            this.editor.remove(key);
            return this;
        }

        public boolean synchronize() {
            return this.editor.commit();
        }
    }

    protected IMUserDefaults(String filename) {
        this.filename = filename;
    }

    public static IMUserDefaults getStandardUserDefaults() {
        return standardUserDefaults;
    }

    public IMUserDefaultsEditor newEditor(Context context) {
        return new IMUserDefaultsEditor(context.getSharedPreferences(this.filename, 0).edit());
    }

    public String getStringValue(Context context, String key) {
        return getStringValue(context, key, "");
    }

    public String getStringValue(Context context, String key, String defaultValue) {
        return context.getSharedPreferences(this.filename, 0).getString(key, defaultValue);
    }

    public int getIntValue(Context context, String key, int defaultValue) {
        return context.getSharedPreferences(this.filename, 0).getInt(key, defaultValue);
    }

    public boolean getBooleanValue(Context context, String key, boolean defaultValue) {
        return context.getSharedPreferences(this.filename, 0).getBoolean(key, defaultValue);
    }
}
