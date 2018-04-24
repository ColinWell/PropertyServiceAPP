package com.antonioleiva.mvpexample.app.Utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Colin on 2018/3/5.
 */

public class ChildThread implements Runnable {
    private String Url;     // 请求链接
    private String params;
    private Handler handler;    // 结果处理器
    private String requestMethod;   // 请求方法
    @Override
    public void run() {
        Message msg = new Message();
        Bundle data = new Bundle();
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(Url).openConnection();
            conn.setRequestMethod(requestMethod);
            if(conn.getResponseCode() == 200){
                InputStream json = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(json));
                StringBuffer jsonString = new StringBuffer();
                String temp = "";
                while((temp=reader.readLine())!=null){
                    jsonString.append(temp).append('\n');
                }
                data.putString("requestResult",jsonString.toString());
                msg.setData(data);
                handler.sendMessage(msg);
            }
            else{
                data.putString("requestResult","{\"result\":false}");
                msg.setData(data);
                handler.sendMessage(msg);
            }
        }catch (Exception ex){
            data.putString("requestResult","error");
            msg.setData(data);
            handler.sendMessage(msg);
        }
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }
}
