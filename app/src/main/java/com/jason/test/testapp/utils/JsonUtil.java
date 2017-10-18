package com.jason.test.testapp.utils;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Created by JiaBo on 2017/10/12.
 */

public class JsonUtil {

    public static <T> T fromJson(String jsonString, Class<T> classType) {
        return new Gson().fromJson(jsonString, classType);
    }

    public static String toJson(Object obj) {
        return new Gson().toJson(obj);
    }

    public static String toJson(Object src, final Class<?> clazz) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return false;
            }

            @Override
            public boolean shouldSkipClass(Class<?> incomingClass) {
                return incomingClass == clazz;
            }
        });
        return gsonBuilder.create().toJson(src);
    }

    public static JsonObject Str2JsonObject(String jsonStr) {
        return new JsonParser().parse(jsonStr).getAsJsonObject();
    }
}
