package com.jason.jasonhttputil;

public interface AsyncClientInterface {
    void runForeground(Runnable runnable);

    void runBackground(Runnable runnable);

    void callBack(Response response);
}
