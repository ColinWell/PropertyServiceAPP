package com.antonioleiva.mvpexample.app.Service;

import com.antonioleiva.mvpexample.app.bean.RepairApplication;

import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 85732 on 2018/4/18.
 */

public interface IRepairService {
    @POST("/addRepair.do")
    @FormUrlEncoded
    Observable<Integer> addRepair(@Field("LogName") String LogName, @Field("RepairApplication_Phone") String RepairApplication_Phone, @Field("RepairApplication_Name") String RepairApplication_Name, @Field("RepairApplication_Place") String RepairApplication_Place, @Field("RepairApplication_Type") String RepairApplication_Type, @Field("Id") String id, @Field("Processor_Id") String Processor_Id);
    //Observable<Integer> addRepair(@Field("ID") int Id, @Field("LogName") String LogName, @Field("RepairApplication_Phone") String RepairApplication_Phone, @Field("RepairApplication_Time") Date RepairApplication_Time, @Field("RepairApplication_Place") String RepairApplication_Place, @Field("RepairApplication_Type") int RepairApplication_Type);

    @POST("/getRById.do")
    @FormUrlEncoded
    Observable<List<RepairApplication>> getRById(@Field("Id") String Id);
}
