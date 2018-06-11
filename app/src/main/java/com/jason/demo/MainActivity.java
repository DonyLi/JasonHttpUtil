package com.jason.demo;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.jason.jasonhttputil.BaseClient;
import com.jason.jasonhttputil.FileBody;
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

        new Thread() {
            @Override
            public void run() {
                super.run();
                BaseClient baseClient = new BaseClient();
                final Response response = baseClient.post("http://47.94.155.143/consume/index.php/home/merchant/homepage", params);
                tv.post(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText(response.toString());

                    }
                });
                if (response.getCookies() != null) {
                    for (String key : response.getCookies().keySet()) {
                        Log.e(key, response.getCookies().get(key) + "xxxxxxx");
                    }
                } else {
                    Log.e("log", "xxxxxxxxxx");
                }
                Log.e("log", response.toString());

            }
        }.start();

    }


}
