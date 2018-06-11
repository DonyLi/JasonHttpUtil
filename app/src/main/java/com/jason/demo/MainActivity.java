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
        final RequestParam param = new RequestParam();
        File file = new File(Environment.getExternalStorageDirectory(), "Pictures/Screenshots/Screenshot_2018-01-24-17-43-51.png");
        Log.e("log", file.exists() + "");
        param.addParams("type", "admin/user/header")
                .addParams("imgFile", new FileBody("tem1.jpg", "image/png", file));

        new Thread() {
            @Override
            public void run() {
                super.run();
                BaseClient baseClient = new BaseClient();
                final Response response = baseClient.post("http://47.94.155.143/kunerUploader/php/upload_json.php", param);
                tv.post(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText(response.toString());
                    }
                });

                Log.e("log", response.toString());

            }
        }.start();

    }


}
