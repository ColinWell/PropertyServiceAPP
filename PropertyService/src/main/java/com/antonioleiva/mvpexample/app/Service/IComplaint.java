package com.antonioleiva.mvpexample.app.Service;



import com.antonioleiva.mvpexample.app.bean.Complaint;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 85732 on 2018/4/23.
 */

public interface IComplaint {
    @POST("/addComplaint.do")
    @FormUrlEncoded
    Observable<Integer> addComplaint(@Field("Id") String Id, @Field("Processor_Id") String Processor_Id, @Field("Complaint_Name") String Complaint_Name, @Field("Complaint_Phone") String Complaint_Phone, @Field("Complaint_Content") String Complaint_Content);

    @POST("/getCById.do")
    @FormUrlEncoded
    Observable<List<Complaint>> getCById(@Field("Id") String Id);
}
