package com.example.amazinglu.logback_demo.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ModelUtil {
    private static Gson gsonForSerialization = new Gson();
    private static Gson gsonForDeserialization = new Gson();

    public static String toJson(Object object) {
        return gsonForSerialization.toJson(object);
    }

    public static<T> T toObject(String json, TypeToken<T> typeToken) {
        return gsonForDeserialization.fromJson(json, typeToken.getType());
    }
}
