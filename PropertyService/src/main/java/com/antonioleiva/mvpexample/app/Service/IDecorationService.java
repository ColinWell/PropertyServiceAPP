package com.antonioleiva.mvpexample.app.Service;



import com.antonioleiva.mvpexample.app.bean.DecorationApplication;

import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 85732 on 2018/4/15.
 */

public interface IDecorationService {
    @POST("/addDecoration.do")
    @FormUrlEncoded
    Observable<Integer> addDecoration(@Field("Id") String Id, @Field("LogName") String LogName, @Field("DecorationApplication_Phone") String DecorationApplication_Phone, @Field("DecorationApplication_StartTime") String DecorationApplication_StartTime, @Field("DecorationApplication_EndTime") String DecorationApplication_EndTime, @Field("DecorationApplication_Place") String DecorationApplication_Place, @Field("DecorationApplication_Status") String DecorationApplication_Status);

    @POST("/getDById.do")
    @FormUrlEncoded
    Observable<List<DecorationApplication>> getDById(@Field("Id") String Id);
}
