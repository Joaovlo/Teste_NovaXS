
package com.example.rickstarter.util;

import com.google.gson.*;

public class JsonUtil {
    private static final Gson G = new Gson();

    public static String toJson(Object o) throws Exception { return G.toJson(o); }
    public static <T> T fromJson(String j, Class<T> c) throws Exception { return G.fromJson(j, c); }
    public static JsonElement readTree(String j) throws Exception { return JsonParser.parseString(j); }
    public static <T> T treeToValue(JsonElement n, Class<T> c) {
        try { return G.fromJson(n, c); } catch(Exception e){ throw new RuntimeException(e); }
    }
}
