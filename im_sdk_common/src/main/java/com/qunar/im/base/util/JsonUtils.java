package com.qunar.im.base.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtils {
    private static final Gson gson = new Gson();
    private static final Gson gsonEscaping = new GsonBuilder().disableHtmlEscaping().create();

    public static Gson getGson() {
        return gson;
    }

    public static Gson getGsonEscaping() {
        return gsonEscaping;
    }
}
