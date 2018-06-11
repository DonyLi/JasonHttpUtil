package com.jason.jasonhttputil;

public interface AsyncClientInterface<T> {
    void runForeground(Runnable runnable);

    void runBackground(Runnable runnable);

    void callBack(T response);
}
