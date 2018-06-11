package com.jason.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.jason.jasonhttputil.BaseClient;
import com.jason.jasonhttputil.RequestParam;
import com.jason.jasonhttputil.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tv = findViewById(R.id.tv);
        final RequestParam param = new RequestParam();
        param.addParams("routeId", 103);
        param.addParams("user_uuid", "7F1495582C27EFAC68022132");
        param.addParams("token", "xfb_9C7C707025D059A60655EF3F");
        param.addParams("p", 1);
        param.addParams("name", "成都");

        new Thread() {
            @Override
            public void run() {
                super.run();
                BaseClient baseClient = new BaseClient();
                final Response response = baseClient.get("http://47.94.155.143/consume/index.php/Home/User/agencyProfit", param);
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
