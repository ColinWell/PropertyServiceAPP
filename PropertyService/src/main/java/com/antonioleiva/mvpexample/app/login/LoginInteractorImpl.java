package com.antonioleiva.mvpexample.app.Login;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.antonioleiva.mvpexample.app.Context.MyApplication;
import com.antonioleiva.mvpexample.app.R;
import com.antonioleiva.mvpexample.app.Utils.ChildThread;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginInteractorImpl implements LoginInteractor {

    @Override
    public void login(final String username, final String password, final OnLoginFinishedListener listener) {
        if (TextUtils.isEmpty(username)) {
            listener.onUsernameError();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            listener.onPasswordError();
            return;
        }


        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle data = msg.getData();
                String requestResult = data.getString("requestResult");
                //JSONArray jsonArray = new JSONArray(jsonString.toString());
                try {
                    JSONObject jsonObject = new JSONObject(requestResult);
                    String result = jsonObject.getString("loginResult");
                    System.out.println(result);
                    if (result.equals("true")) {
                        // UI界面的更新等相关操作
                        listener.onSuccess();
                    } else {
                        listener.onUsernameOrPasswordError();
                    }
                } catch (JSONException ex) {
                    System.out.println("Json字符串转换出错！");
                    listener.onUsernameOrPasswordError();
                }
            }
        };
        ChildThread loginThread = new ChildThread();
        MyApplication application = MyApplication.getInstance();
        application.setUserName(username);
        String Url = new String("http://119.29.175.171:8080/login?j_username=" + username + "&j_password=" + password);
        loginThread.setUrl(Url);
        loginThread.setHandler(handler);
        loginThread.setRequestMethod("POST");
        Thread thread = new Thread(loginThread);
        thread.start();
    }
}


