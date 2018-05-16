package com.antonioleiva.mvpexample.app.Repair;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.antonioleiva.mvpexample.app.Adapter.MyDRecyclerAdapter;
import com.antonioleiva.mvpexample.app.Adapter.MyRRecyclerAdapter;
import com.antonioleiva.mvpexample.app.R;
import com.antonioleiva.mvpexample.app.Service.IDecorationService;
import com.antonioleiva.mvpexample.app.Service.IRepairService;
import com.antonioleiva.mvpexample.app.Service.ServiceGenerator;
import com.antonioleiva.mvpexample.app.bean.RepairApplication;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 85732 on 2018/5/5.
 */

public class RHistory extends Activity {
    IRepairService client;
    MyRRecyclerAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_r);
        initRecyclerView();
        client = ServiceGenerator.createService(IRepairService.class);
    }
    @Override
    protected void onStart() {
        super.onStart();
        getRById("1");
    }
    private void initRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_R);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyRRecyclerAdapter();
        recyclerView.setAdapter(adapter);
    }
    @SuppressLint("CheckResult")
    public void getRById(String Id) {
        client.getRById(Id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<RepairApplication>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<RepairApplication> list) {
                        Log.d("TAG", list.toString());
                        adapter.add(list);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
