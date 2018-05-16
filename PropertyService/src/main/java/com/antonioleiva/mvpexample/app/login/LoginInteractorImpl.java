package com.antonioleiva.mvpexample.app.login;

import android.content.SharedPreferences;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.antonioleiva.mvpexample.app.Service.UserService;
import com.antonioleiva.mvpexample.app.context.MyApplication;
import com.antonioleiva.mvpexample.app.util.ChildThread;
import com.antonioleiva.mvpexample.app.util.MyDateUtil;
import com.antonioleiva.mvpexample.app.util.MyRetrofit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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

    @Override
    public void getUserInfo() {
        Retrofit retrofit = MyRetrofit.getInstance();
        // 创建 网络请求接口 的实例
        UserService request = retrofit.create(UserService.class);
        //对 发送请求 进行封装
        Call<ResponseBody> call = request.getUserInfo(MyApplication.getInstance().getUserName());

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        if(jsonObject.getString("result").equals("true")) {
                            JSONObject userInfo = jsonObject.getJSONObject("userInfo");
                            MyApplication application = MyApplication.getInstance();
                            application.setUserId(userInfo.getInt("userId"));
                            application.setNickName(userInfo.getString("nickName"));
                            application.setRoomId(userInfo.getString("roomId"));
                            application.setRoomName(userInfo.getString("roomName"));
                            String birthday = userInfo.getString("birthday");
                            if (birthday!=null){
                                application.setBirthday(birthday);
                            }
                            application.setSignal(userInfo.getString("signal"));
                            application.setPropertyFee(userInfo.getDouble("propertyFee"));
                            application.setUtilitiesFee(userInfo.getDouble("utilitiesFee"));
                        }
                        else{
                            throw new Exception("获取用户信息错误！");
                        }
                    }catch (JSONException e){
                        System.out.println(e.getMessage());
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    System.out.println(response.body());
                } catch (IOException ex){
                    System.out.println(ex.getMessage());
                }
            }


            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("调用失败:"+t.toString());
            }
        });
    }
}


