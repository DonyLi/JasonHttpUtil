package com.jason.jasonhttputil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BaseClient implements Client {
    private int timeOut = 5000;
    private String charset = "UTF-8";
    private HttpURLConnection urlConnection;


    @Override
    public Response post(String urlString, RequestParam param) {
        Response response = new Response();
        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            this.urlConnection = urlConnection;
            urlConnection.setRequestMethod("POST");
            // 超时时间
            urlConnection.setConnectTimeout(timeOut);
            // 设置是否输出
            urlConnection.setDoOutput(true);
            // 设置是否读入
            urlConnection.setDoInput(true);
            // 设置是否使用缓存
            urlConnection.setUseCaches(false);

            urlConnection.setRequestProperty("Accept-Charset", charset);
            urlConnection.setRequestProperty("Connection", "keep-alive");
            urlConnection.connect();

            String outParams = param.splitParams("POST");
            if (outParams.length() > 0) {
                OutputStream out = urlConnection.getOutputStream();
                out.write(outParams.getBytes());
                out.close();
            }
            response.setCode(urlConnection.getResponseCode());
            InputStream inputStream = urlConnection.getInputStream();
            response.setResult(inputStream2Byte(inputStream));
            inputStream.close();


        } catch (Exception e) {
            e.printStackTrace();
            response.setThrowable(e);
        } finally {
            this.urlConnection = null;
        }

        return response;
    }

    @Override
    public Response get(String urlString, RequestParam param) {

        Response response = new Response();

        try {
            String outParams = param.splitParams("GET");
            if (outParams.length() > 0) {
                urlString = urlString + "?" + outParams;
            }
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            this.urlConnection = urlConnection;
            urlConnection.setRequestMethod("GET");
            // 超时时间
            urlConnection.setConnectTimeout(timeOut);
            // 设置是否输出
            urlConnection.setDoOutput(false);
            // 设置是否读入
            urlConnection.setDoInput(true);
            // 设置是否使用缓存
            urlConnection.setUseCaches(false);
            urlConnection.setRequestProperty("Accept-Charset", charset);
            urlConnection.connect();


            response.setCode(urlConnection.getResponseCode());
            InputStream inputStream = urlConnection.getInputStream();
            response.setResult(inputStream2Byte(inputStream));
            inputStream.close();


        } catch (Exception e) {
            e.printStackTrace();
            response.setThrowable(e);
        } finally {
            this.urlConnection = null;
        }

        return response;
    }

    @Override
    public boolean cancle() {
        if (this.urlConnection != null) {
            this.urlConnection.disconnect();
            return true;

        }
        return false;

    }

    private byte[] inputStream2Byte(InputStream inputStream) throws IOException {
        List<byte[]> bytesList = new ArrayList<>();
        byte[] bytes = new byte[1024];
        int total = 0;
        int length = 0;
        while ((total = inputStream.read(bytes)) != -1) {
            byte[] bytes1 = new byte[total];
            System.arraycopy(bytes, 0, bytes1, 0, total);
            bytesList.add(bytes1);
            length += total;
        }
        byte[] result = new byte[length];
        int position = 0;
        for (int i = 0; i < bytesList.size(); i++) {
            byte[] bytes2 = bytesList.get(i);
            System.arraycopy(bytes2, 0, result, position, bytes2.length);
            position += bytes2.length;
        }
        return result;
    }


    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }


}
