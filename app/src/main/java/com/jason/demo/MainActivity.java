package com.jason.demo;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.jason.jasonhttputil.AsyncBaseClient;
import com.jason.jasonhttputil.AsyncClientInterface;
import com.jason.jasonhttputil.BaseClient;
import com.jason.jasonhttputil.DefaultAsyncClient;
import com.jason.jasonhttputil.DownLoadProgressListener;
import com.jason.jasonhttputil.FileBody;
import com.jason.jasonhttputil.HttpClient;
import com.jason.jasonhttputil.RequestParam;
import com.jason.jasonhttputil.Response;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tv = findViewById(R.id.tv);
        final RequestParam params = new RequestParam();
        File file = new File(Environment.getExternalStorageDirectory(), "Pictures/Screenshots/Screenshot_2018-01-24-17-43-51.png");
        Log.e("log", file.exists() + "");
        params.addParams("user_uuid", "18081917451");
        params.addParams("area_id", 322);

        HttpClient baseClient = new HttpClient();

        baseClient.asyncPost("http://47.94.155.143/consume/index.php/home/merchant/homepage", params, new DefaultAsyncClient<Response>() {
            @Override
            public void callBack(Response response) {
                tv.setText(response.toString());
                if (response.getCookies() != null) {
                    for (String key : response.getCookies().keySet()) {
                        Log.e(key, response.getCookies().get(key) + "TAG");
                    }
                } else {
                    Log.e("log", "TAG");
                }
                Log.e("log", response.toString());
            }
        });

        AsyncBaseClient asyncBaseClient = new AsyncBaseClient();
        asyncBaseClient.downLoad("http://47.94.155.143/consume/index.php/home/merchant/homepage", "path");
        asyncBaseClient.asyncDownload("http://47.94.155.143/consume/index.php/home/merchant/homepage", "path", new DefaultAsyncClient<Response>() {
            @Override
            public void callBack(Response response) {

            }
        });
        asyncBaseClient.setProgressListener(new DownLoadProgressListener() {
            @Override
            public void downLoading(int progress, int total) {

            }
        });
    }


}
