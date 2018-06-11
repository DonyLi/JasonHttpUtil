package com.jason.jasonhttputil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RequestParam {
    HashMap<String, String> map = new HashMap<>();
    List<FileBody> fileBodies = new ArrayList<>();
    private HashMap<String, String> cookies = new HashMap<>();



    public HashMap<String, String> getCookies() {
        return cookies;
    }

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

    public RequestParam addParams(String key, FileBody fileBody) {
        fileBody.setKey(key);
        fileBodies.add(fileBody);

        return this;
    }

    public RequestParam addHeader(String key, String value) {
        cookies.put(key, value);
        return this;
    }

    public String splitParams(String method) {

        StringBuilder builder = new StringBuilder();
        if (method.hashCode() == "POST".hashCode() || method.hashCode() == "GET".hashCode()) {
            for (String key : map.keySet()) {
                builder.append(key).append("=").append(map.get(key)).append("&");
            }
            if (builder.length() > 0) {
                builder.deleteCharAt(builder.length() - 1);
            }
        } else {
            for (String key : map.keySet()) {
                builder.append(Config.PREFIX)
                        .append(Config.BOUNDARY)
                        .append(Config.LINEND)
                        .append("Content-Disposition: form-data; name=\"" + key + "\"" + Config.LINEND)
                        .append("Content-Type: text/plain; charset=UTF-8" + Config.LINEND)
                        .append("Content-Transfer-Encoding: 8bit" + Config.LINEND)
                        .append(Config.LINEND)
                        .append(map.get(key))
                        .append(Config.LINEND);
            }

        }
        return builder.toString();
    }


}
