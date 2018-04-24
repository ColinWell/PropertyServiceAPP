package com.antonioleiva.mvpexample.app.Context;

import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.antonioleiva.mvpexample.app.Utils.ChildThread;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Colin on 2018/3/21.
 */

public class MyApplication extends Application{
    private String userName = null;

    private static MyApplication instance;
    public static MyApplication getInstance() {
        return  instance;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        System.out.println("---------------------------test-------------------------------");
        instance = this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName(){
        if(userName == null){
            Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    Bundle data = msg.getData();
                    String requestResult = data.getString("requestResult");
                    //JSONArray jsonArray = new JSONArray(jsonString.toString());
                    try {
                        JSONObject jsonObject = new JSONObject(requestResult);
                        userName = jsonObject.getString("userName");
                        System.out.println(userName);
                    } catch (JSONException ex) {
                        System.out.println("Json字符串转换出错！");
                    }
                }
            };
            ChildThread loginThread = new ChildThread();
            String Url = new String("http://119.29.175.171:8080/getUserName.do");
            loginThread.setUrl(Url);
            loginThread.setHandler(handler);
            loginThread.setRequestMethod("GET");
            Thread thread = new Thread(loginThread);
            thread.start();
        }
        return userName;
    }
}
