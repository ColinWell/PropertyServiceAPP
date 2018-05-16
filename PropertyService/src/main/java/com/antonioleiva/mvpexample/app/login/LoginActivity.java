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

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.antonioleiva.mvpexample.app.main.MainActivity;
import com.antonioleiva.mvpexample.app.R;


public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener {

    private ProgressBar progressBar;
    private EditText username;
    private EditText password;
    private LoginPresenter presenter;
    private SharedPreferences config;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar = (ProgressBar) findViewById(R.id.progress);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        findViewById(R.id.login_button).setOnClickListener(this);
        findViewById(R.id.forgot).setOnClickListener(this);
        findViewById(R.id.sign_in).setOnClickListener(this);

        presenter = new LoginPresenterImpl(this,new LoginInteractorImpl());

        if(config == null){
            config = getSharedPreferences("config",MODE_PRIVATE);
        }
        presenter.validateLoginState(config);


    }

    @Override protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setUserName(String userName) {
        username.setText(userName);
    }

    @Override
    public void setPassword(String pass) {
        password.setText(pass);
    }

    @Override public void setUsernameError() {
        username.setError(getString(R.string.username_error));
    }

    @Override public void setPasswordError() {
        password.setError(getString(R.string.password_error));
    }

    @Override
    public void setUsernameOrPasswordError() {
        username.setError(getString(R.string.username_password_error));
    }

    @Override public void navigateToHome() {
        if(config == null){
            config = getSharedPreferences("config",MODE_PRIVATE);
        }
        presenter.saveLoginState(config,username.getText().toString(),password.getText().toString());
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void navigateToForgotPassword() {

    }

    @Override
    public void navigateToSign() {
        startActivityForResult(new Intent(this, RegisterActivity.class),1);
    }

    @Override public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button:
                presenter.validateCredentials(username.getText().toString(), password.getText().toString());
                break;
            case R.id.forgot:
                presenter.showForgotPage(this);
                break;
            case R.id.sign_in:
//                presenter.showRegisterPage(this);
                navigateToSign();
                break;
        }

    }
}
