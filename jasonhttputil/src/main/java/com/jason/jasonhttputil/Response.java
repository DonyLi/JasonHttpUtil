package com.jason.jasonhttputil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Response {
    private int code;
    private byte[] result;
    private HashMap<String, String> cookies;
    private Throwable throwable;
    private Map<String, List<String>> headers;

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }

    public int getCode() {
        return code;
    }

    public byte[] getResult() {
        return result;
    }

    public HashMap<String, String> getCookies() {
        return cookies;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setResult(byte[] result) {
        this.result = result;
    }

    public void setCookies(HashMap<String, String> cookies) {
        this.cookies = cookies;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public String toString() {

        return new String(result);
    }

}
