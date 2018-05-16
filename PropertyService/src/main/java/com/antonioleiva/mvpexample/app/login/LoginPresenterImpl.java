/*
 *
 *  * Copyright (C) 2014 Antonio Leiva Gordillo.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.antonioleiva.mvpexample.app.login;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.OnLoginFinishedListener {

    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView,LoginInteractorImpl loginInteractor) {
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
    }

    @Override
    public void saveLoginState(SharedPreferences config, String userName, String password) {
        SharedPreferences.Editor editor = config.edit();
        editor.putString("userName",userName);
        editor.putString("password",password);
        editor.apply();
    }

    @Override
    public void validateLoginState(SharedPreferences config) {
        if(loginView!=null){
            loginView.showProgress();
        }
        String userName = config.getString("userName",null);
        String password = config.getString("password",null);
        if(userName != null && password !=null){
            loginView.setUserName(userName);
            loginView.setPassword(password);
            loginInteractor.login(userName,password,this);
        }
        else{
            loginView.hideProgress();
        }
    }

    @Override public void validateCredentials(String username, String password) {
        if (loginView != null) {
            loginView.showProgress();
        }

        loginInteractor.login(username, password, this);
    }

    @Override
    public void showRegisterPage(Context context) {
        // 打开注册页面
        RegisterPage registerPage = new RegisterPage();
        // 使用自定义短信模板(不设置则使用默认模板)
        registerPage.setTempCode("1319972");
        registerPage.setRegisterCallback(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                // 解析注册结果
                if (result == SMSSDK.RESULT_COMPLETE) {
                    @SuppressWarnings("unchecked")
                    HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
                    String country = (String) phoneMap.get("country");
                    String phone = (String) phoneMap.get("phone");
                    // 提交用户信息
//						registerUser(country, phone);
                }
            }
        });
        registerPage.show(context);
    }

    @Override
    public void getUserInfo() {
        loginInteractor.getUserInfo();
    }

    @Override public void onDestroy() {
        loginView = null;
    }

    @Override
    public void showForgotPage(Context context) {

    }

    @Override public void onUsernameError() {
        if (loginView != null) {
            loginView.setUsernameError();
            loginView.hideProgress();
        }
    }

    @Override public void onPasswordError() {
        if (loginView != null) {
            loginView.setPasswordError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onUsernameOrPasswordError() {
        if(loginView!= null){
            loginView.setUsernameOrPasswordError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onSuccess() {

        getUserInfo();
        if (loginView != null) {
            loginView.navigateToHome();
        }
    }
}
