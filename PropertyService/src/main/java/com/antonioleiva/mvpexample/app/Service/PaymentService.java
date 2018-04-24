package com.antonioleiva.mvpexample.app.util;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Colin on 2018/4/4.
 */

public interface GetRequest_Interface {

    @GET("payment/getRuleVer.do?userName=admin&ruleType=1")
    Call<ResponseBody> getRuleVer();

}
