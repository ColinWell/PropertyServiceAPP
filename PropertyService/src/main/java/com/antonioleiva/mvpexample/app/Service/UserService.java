package com.antonioleiva.mvpexample.app.Service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Colin on 2018/4/10.
 */

public interface UserService {

    @GET("user/getUserInfo.do")
    Call<ResponseBody> getUserInfo(@Query("userName") String userName);

    @FormUrlEncoded
    @POST("user/setSignal.do")
    Call<ResponseBody> setSignal(@Field("userId") int userId,@Field("signal") String signal);

    @FormUrlEncoded
    @POST("user/setNickName.do")
    Call<ResponseBody> setNickName(@Field("userId") int userId,@Field("nickName") String nickName);

    @FormUrlEncoded
    @POST("user/setBirthday.do")
    Call<ResponseBody> setBirthday(@Field("userId") int userId,@Field("birthday") String birthday);

}
