package com.antonioleiva.mvpexample.app.Service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Colin on 2018/4/10.
 */

public interface AnnounceService {
    @GET("notice/getAnnounceList.do")
    Call<ResponseBody> getAnnounceList();

    @GET("notice/getNoticeList.do")
    Call<ResponseBody> getNoticeList(@Query("userId") int userId);
}
