package com.antonioleiva.mvpexample.app.main.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.antonioleiva.mvpexample.app.EmergencyContact.EmergencyContactActivity;
import com.antonioleiva.mvpexample.app.R;
import com.antonioleiva.mvpexample.app.Service.IEmergencyContact;
import com.antonioleiva.mvpexample.app.Service.ServiceGenerator;
import com.antonioleiva.mvpexample.app.bean.EmergencyContact;

import java.util.Calendar;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {
    private View contentView;
    IEmergencyContact client= ServiceGenerator.createService(IEmergencyContact.class);
    public MovieFragment() {
        super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        contentView=inflater.inflate(R.layout.fragment_movie,null);
        final Button help=(Button)contentView.findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.help:
                        int i=getTimeId();
                        getById(i);
                }
            }
        });
        return contentView;
    }

    public void processEntity(EmergencyContact emergencyContact) {
        Intent intent = new Intent(getContext(), EmergencyContactActivity.class);
        intent.putExtra("name","刘三石");
        intent.putExtra("phone",emergencyContact.getEmergencyContact_Phone());
        startActivity(intent);
    }

    public int getTimeId(){
        Calendar now = Calendar.getInstance();
//一周第一天是否为星期天
        boolean isFirstSunday = (now.getFirstDayOfWeek() == Calendar.SUNDAY);
//获取周几
        int weekDay = now.get(Calendar.DAY_OF_WEEK);
//若一周第一天为星期天，则-1
        if(isFirstSunday){
            weekDay = weekDay - 1;
            if(weekDay == 0){
                weekDay = 7;
            }
        }
        int hour=now.get(Calendar.DAY_OF_YEAR);
        if (hour>6){
            weekDay=2*weekDay;
        }
        else {
            weekDay=2*weekDay-1;
        }
        return weekDay;
    }
    @SuppressLint("CheckResult")
    public void getById(int id){
        client.getContactByTime(id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<EmergencyContact>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(EmergencyContact emergencyContact) {
                        processEntity(emergencyContact);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
