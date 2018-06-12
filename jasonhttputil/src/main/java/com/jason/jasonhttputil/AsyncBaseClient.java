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

    public void asyncDownload(final String urlString, final String path, final AsyncClientInterface<Response> anInterface) {
        this.asyncClientInterface = anInterface;
        anInterface.runBackground(new Runnable() {
            @Override
            public void run() {
                final Response response = downLoad(urlString, path);
                anInterface.runForeground(new Runnable() {
                    @Override
                    public void run() {
                        anInterface.callBack(response);
                    }
                });
                AsyncBaseClient.this.asyncClientInterface = null;
            }
        });

    }

    private AsyncClientInterface asyncClientInterface;

    @Override
    protected void downLoading(final int length, final int totalLength) {
        if (asyncClientInterface == null) {
            super.downLoading(length, totalLength);
        } else {
            asyncClientInterface.runForeground(new Runnable() {
                @Override
                public void run() {
                    AsyncBaseClient.super.downLoading(length, totalLength);
                }
            });
        }
    }
}
