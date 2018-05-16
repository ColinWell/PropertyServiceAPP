package com.antonioleiva.mvpexample.app.Complaint;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.antonioleiva.mvpexample.app.Adapter.MyCRecyclerAdapter;
import com.antonioleiva.mvpexample.app.Adapter.MyDRecyclerAdapter;
import com.antonioleiva.mvpexample.app.R;
import com.antonioleiva.mvpexample.app.Service.IComplaint;
import com.antonioleiva.mvpexample.app.Service.ServiceGenerator;
import com.antonioleiva.mvpexample.app.bean.Complaint;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 85732 on 2018/5/6.
 */

public class CHistory extends Activity {
    IComplaint client;
    MyCRecyclerAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_c);
        initRecyclerView();
        client = ServiceGenerator.createService(IComplaint.class);
    }
    @Override
    protected void onStart() {
        super.onStart();
        getCById("1");
    }
    private void initRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerc_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyCRecyclerAdapter();
        recyclerView.setAdapter(adapter);
    }

    @SuppressLint("CheckResult")
    public void getCById(String Id){
        client.getCById(Id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Complaint>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Complaint> list) {
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
