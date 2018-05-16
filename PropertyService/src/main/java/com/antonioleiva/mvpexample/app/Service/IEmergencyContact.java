package com.antonioleiva.mvpexample.app.Service;

import com.antonioleiva.mvpexample.app.bean.EmergencyContact;

import java.util.List;


import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 85732 on 2018/4/23.
 */

public interface IEmergencyContact {
    @POST("/getContactById.do")
    @FormUrlEncoded
    Observable<EmergencyContact> getContactByTime(@Field("emergencyId") int emergencyId);

}
