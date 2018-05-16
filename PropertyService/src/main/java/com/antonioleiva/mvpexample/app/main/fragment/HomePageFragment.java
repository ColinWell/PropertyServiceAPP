package com.antonioleiva.mvpexample.app.main.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.antonioleiva.mvpexample.app.R;
import com.antonioleiva.mvpexample.app.Service.AnnounceService;
import com.antonioleiva.mvpexample.app.bean.AnnouncementItem;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private View contentView;
    private List<AnnouncementItem> announceList;
    private AnnounceListViewAdapter adapter;


    private SwipeRefreshLayout swipeRefresh;
    private ListView listView;
    private ProgressBar progressBar;
    public HomePageFragment() {
        setAnnounceList();
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(contentView == null){
            contentView = inflater.inflate(R.layout.fragment_home_page, null);
            ImageView homeImg = (ImageView)contentView.findViewById(R.id.homeImg);
            homeImg.setImageResource(R.drawable.home_img);

            swipeRefresh = (SwipeRefreshLayout) contentView.findViewById(R.id.swipeRefresh);
            swipeRefresh.setOnRefreshListener(this);
            swipeRefresh.setColorSchemeResources(R.color.colorAccent);
            listView = (ListView) contentView.findViewById(R.id.announcementLv);
            announceList = new ArrayList<AnnouncementItem>();
            progressBar = (ProgressBar) contentView.findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);
            adapter = new AnnounceListViewAdapter(announceList,getContext());
            listView.setAdapter(adapter);
            progressBar.setVisibility(View.GONE);
        }else{
            ViewGroup parent = (ViewGroup) contentView.getParent();
            if(parent != null){
                parent.removeView(contentView);
            }
        }

        // Inflate the layout for this fragment
        return contentView;
    }

    private void setAnnounceList() {
        Retrofit retrofit = MyRetrofit.getInstance();
        AnnounceService announceService = retrofit.create(AnnounceService.class);
        Call<ResponseBody> call = announceService.getAnnounceList();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    try {
                        JSONObject jsonResult = new JSONObject(result);
                        JSONArray jsonArray = (JSONArray)jsonResult.get("data");
                        announceList.clear();
                        for(int i = 0;i<jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            AnnouncementItem item = new AnnouncementItem();
                            item.setTitle(jsonObject.getString("title"));
                            item.setContent(jsonObject.getString("content"));
                            item.setPubDate(MyDateUtil.longToString(jsonObject.getLong("date"),"yyyy-MM-dd"));
                            item.setType(jsonObject.getInt("type"));
                            announceList.add(item);
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
        //手动添加数据刷新
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                announceList.add(new AnnouncementItem("停水两小时！！","今天下午3点至傍晚5点，因小区施工停水两小时",1,"2018-04-09"));
//                announceList.add(new AnnouncementItem("最美物业人员评选大赛","就是在那个时间，我们要评选最美物业",2,"2018-04-08"));
//                adapter.notifyDataSetChanged();
//                //结束后停止刷新
//                swipeRefresh.setRefreshing(false);
//            }
//        }, 3000);
//  一般会从网络获取数据
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                //获取数据
                setAnnounceList();
                swipeRefresh.setRefreshing(false);
            }
        });
    }
}
