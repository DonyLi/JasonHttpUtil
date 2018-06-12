package com.jason.jasonhttputil;

public interface Client {
    Response post(String url, RequestParam param);

    Response get(String url, RequestParam param);

    boolean cancle();

    Response downLoad(String url, String path);
}
