package com.jason.jasonhttputil;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public abstract class DefaultAsyncClient<T> implements AsyncClientInterface<T> {


    private static ExecutorService service = Executors.newFixedThreadPool(5);
    Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void runForeground(Runnable runnable) {
        handler.post(runnable);

    }

    @Override
    public void runBackground(Runnable runnable) {
        service.execute(runnable);
    }


}
