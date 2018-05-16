package com.antonioleiva.mvpexample.app.personalInfo;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.antonioleiva.mvpexample.app.R;
import com.antonioleiva.mvpexample.app.Service.AnnounceService;
import com.antonioleiva.mvpexample.app.bean.AnnouncementItem;
import com.antonioleiva.mvpexample.app.bean.NoticeItem;
import com.antonioleiva.mvpexample.app.context.MyApplication;
import com.antonioleiva.mvpexample.app.util.MyDateUtil;
import com.antonioleiva.mvpexample.app.util.MyRetrofit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NoticeListActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private List<NoticeItem> noticeList;
    private NoticeListAdapter adapter;


    private SwipeRefreshLayout swipeRefresh;
    private ListView listView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_list);
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        swipeRefresh.setOnRefreshListener(this);
        swipeRefresh.setColorSchemeResources(R.color.colorAccent);
        listView = (ListView) findViewById(R.id.noticeLv);
        noticeList = new ArrayList<NoticeItem>();
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        adapter = new NoticeListAdapter(noticeList,this);
        listView.setAdapter(adapter);
        setNoticeList();
        progressBar.setVisibility(View.GONE);
    }

    private void setNoticeList() {
        Retrofit retrofit = MyRetrofit.getInstance();
        AnnounceService announceService = retrofit.create(AnnounceService.class);
        Call<ResponseBody> call = announceService.getNoticeList(MyApplication.getInstance().getUserId());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    try {
                        JSONObject jsonResult = new JSONObject(result);
                        JSONArray jsonArray = (JSONArray)jsonResult.get("data");
                        noticeList.clear();
                        for(int i = 0;i<jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            NoticeItem item = new NoticeItem();
                            item.setDate(MyDateUtil.longToString(jsonObject.getLong("date"),"yyyy-MM-dd hh:mm:ss"));
                            item.setContent(jsonObject.getString("content"));
                            noticeList.add(item);
                        }
                        if(adapter != null){
                            adapter.notifyDataSetChanged();
                        }
                    }catch (JSONException e){
                        System.out.println(e.getMessage());
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                } catch (IOException ex){
                    System.out.println(ex.getMessage());
                }
            }


            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("调用失败:"+t.toString());
            }
        });
    }

    @Override
    public void onRefresh() {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                //获取数据
                setNoticeList();
                swipeRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    public void onBackPressed() {
        setResult(0);
        super.onBackPressed();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        //switch (item.getItemId()) {
        setResult(0);
        return super.onOptionsItemSelected(item);
    }
}
