package com.antonioleiva.mvpexample.app.util;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Colin on 2018/4/4.
 */

public class MyRetrofit {

    private static Retrofit retrofit = null;
    private static String defaultUrl = new String("http://119.29.175.171:8080/");

    public static Retrofit getInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(defaultUrl) // 设置网络请求的Url地址
                    .addConverterFactory(JacksonConverterFactory.create()) // 设置数据解析器
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 支持RxJava平台
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getInstance(String baseUrl){
        return retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl) // 设置网络请求的Url地址
                .addConverterFactory(JacksonConverterFactory.create()) // 设置数据解析器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 支持RxJava平台
                .build();
    }
}