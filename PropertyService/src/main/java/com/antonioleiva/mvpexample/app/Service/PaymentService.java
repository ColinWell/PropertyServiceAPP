package com.antonioleiva.mvpexample.app.Service;

import com.antonioleiva.mvpexample.app.bean.Payment;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Colin on 2018/4/4.
 */

public interface PaymentService {

    @GET("payment/getRuleVer.do")
    Call<ResponseBody> getRuleVer(@Query("userName") String userName,@Query("ruleType") int ruleType);

    @POST("logout")
    Call<ResponseBody> logout();

    @POST("payment/pay.do")
    Call<ResponseBody> pay(@Body Payment payment);

    @GET("payment/getOrderList.do")
    Call<ResponseBody> getOrderList(@Query("userId") int userId);

    @GET("payment/getPaymentState.do")
    Call<ResponseBody> getPaymentState(@Query("userId") int userId);

}
