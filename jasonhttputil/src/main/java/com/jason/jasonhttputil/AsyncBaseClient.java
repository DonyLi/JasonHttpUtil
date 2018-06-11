package com.jason.jasonhttputil;

public class AsyncBaseClient extends BaseClient {
    public void asyncPost(final String url, final RequestParam param, final AsyncClientInterface<Response> anInterface) {
        anInterface.runBackground(new Runnable() {
            @Override
            public void run() {
                final Response response = post(url, param);
                anInterface.runForeground(new Runnable() {
                    @Override
                    public void run() {
                        anInterface.callBack(response);
                    }
                });
            }
        });


    }

    public void asyncGet(final String url, final RequestParam param, final AsyncClientInterface<Response> anInterface) {
        anInterface.runBackground(new Runnable() {
            @Override
            public void run() {
                final Response response = get(url, param);
                anInterface.runForeground(new Runnable() {
                    @Override
                    public void run() {
                        anInterface.callBack(response);
                    }
                });
            }
        });

    }


}
