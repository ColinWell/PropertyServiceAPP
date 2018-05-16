package com.antonioleiva.mvpexample.app.context;

import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.antonioleiva.mvpexample.app.util.ChildThread;
import com.mob.MobSDK;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Colin on 2018/3/21.
 */

public class MyApplication extends Application{
    private String userName = null;
    private int userId;
    private String nickName = null;
    private String roomId = null;
    private String roomName = null;
    private String birthday = null;
    private String signal = null;
    private Double propertyFee = null;
    private Double parkingFee = null;
    private Double utilitiesFee = null;
    private String propertyDateBegin = null;
    private String propertyDateFrom = null;
    private String utilitiesDateBegin = null;
    private String getUtilitiesDateFrom = null;

    private static MyApplication instance;
    public static MyApplication getInstance() {
        return  instance;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        MobSDK.init(this);
        System.out.println("---------------------------test-------------------------------");
        instance = this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getPropertyFee() {
        return propertyFee;
    }

    public void setPropertyFee(Double propertyFee) {
        this.propertyFee = propertyFee;
    }

    public Double getParkingFee() {
        return parkingFee;
    }

    public void setParkingFee(Double parkingFee) {
        this.parkingFee = parkingFee;
    }

    public Double getUtilitiesFee() {
        return utilitiesFee;
    }

    public void setUtilitiesFee(Double utilitiesFee) {
        this.utilitiesFee = utilitiesFee;
    }

    public String getUserName() {
        return userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSignal() {
        return signal;
    }

    public void setSignal(String signal) {
        this.signal = signal;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getPropertyDateBegin() {
        return propertyDateBegin;
    }

    public void setPropertyDateBegin(String propertyDateBegin) {
        this.propertyDateBegin = propertyDateBegin;
    }

    public String getPropertyDateFrom() {
        return propertyDateFrom;
    }

    public void setPropertyDateFrom(String propertyDateFrom) {
        this.propertyDateFrom = propertyDateFrom;
    }

    public String getUtilitiesDateBegin() {
        return utilitiesDateBegin;
    }

    public void setUtilitiesDateBegin(String utilitiesDateBegin) {
        this.utilitiesDateBegin = utilitiesDateBegin;
    }
}
