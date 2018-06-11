package com.jason.jasonhttputil;

import android.text.TextUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;

public class HttpsClient extends AsyncBaseClient {
    private static String hostName = "any";

    public static void setHostName(String hostName) {
        HttpsClient.hostName = hostName;
    }


    public void init(String path, String password) throws Exception {
        init(new FileInputStream(path), password);
    }

    public void init(InputStream inputStream, String password) throws Exception {

        SSLContext sslcontext = SSLContext.getInstance("SSL", "SunJSSE");
        sslcontext.init(null, new TrustManager[]{new CertificateManagement(inputStream, password)},
                new java.security.SecureRandom());
        HostnameVerifier ignoreHostnameVerifier = new HostnameVerifier() {
            public boolean verify(String s, SSLSession sslsession) {
                boolean verify = (!TextUtils.isEmpty(hostName) && hostName.equals(s)) || "any".equals(hostName);
                if (!verify) {
                    System.out.println("WARNING: Hostname is not matched for cert.");
                } else if ("any".equals(hostName)) {
                    System.out.println("WARNING: Hostname is setted not verify.");
                }
                return verify;
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
        HttpsURLConnection.setDefaultSSLSocketFactory(sslcontext.getSocketFactory());


    }
}
