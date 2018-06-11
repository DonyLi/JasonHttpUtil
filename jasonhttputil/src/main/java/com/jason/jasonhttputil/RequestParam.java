package com.jason.jasonhttputil;

import java.util.HashMap;

public class RequestParam {
    HashMap<String, String> map = new HashMap<>();

    public RequestParam addParams(String key, String value) {
        if (key != null) {
            map.put(key, value == null ? "" : value);
        }
        return this;
    }

    public RequestParam addParams(String key, long value) {
        if (key != null) {
            map.put(key, String.valueOf(value));
        }
        return this;
    }

    public RequestParam addParams(String key, double value) {
        if (key != null) {
            map.put(key, String.valueOf(value));
        }
        return this;
    }

    public RequestParam addParams(String key, boolean value) {
        if (key != null) {
            map.put(key, String.valueOf(value));
        }
        return this;
    }

    public String splitParams(String method) {
        StringBuilder builder = new StringBuilder();
        for (String key : map.keySet()) {
            builder.append(key).append("=").append(map.get(key)).append("&");
        }
        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }
}
