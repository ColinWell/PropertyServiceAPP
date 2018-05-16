package com.antonioleiva.mvpexample.app.Decoration;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.antonioleiva.mvpexample.app.Adapter.MyDRecyclerAdapter;
import com.antonioleiva.mvpexample.app.R;
import com.antonioleiva.mvpexample.app.Service.IDecorationService;
import com.antonioleiva.mvpexample.app.Service.ServiceGenerator;
import com.antonioleiva.mvpexample.app.bean.DecorationApplication;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 85732 on 2018/4/28.
 */

public class DecorationHistoryActivity extends Activity {
    IDecorationService client;
    MyDRecyclerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_d);
        initRecyclerView();
        client = ServiceGenerator.createService(IDecorationService.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getDById("1");
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerd_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyDRecyclerAdapter();
        recyclerView.setAdapter(adapter);
    }

    @SuppressLint("CheckResult")
    public void getDById(String Id) {
        client.getDById(Id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<DecorationApplication>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<DecorationApplication> list) {
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
