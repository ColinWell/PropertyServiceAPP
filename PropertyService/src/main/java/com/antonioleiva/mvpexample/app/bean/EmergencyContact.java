package com.antonioleiva.mvpexample.app.bean;

import java.util.Date;

public class EmergencyContact {
    private int EmergencyContact_Id;
    private Date EmergencyContact_StartTime;
    private Date EmergencyContact_EndTime;
    private String EmergencyContact_Name;
    private String EmergencyContact_Phone;
    private int EmergencyContact_Type;

    public String getEmergencyContact_Time() {
        return EmergencyContact_Time;
    }

    public void setEmergencyContact_Time(String emergencyContact_Time) {
        EmergencyContact_Time = emergencyContact_Time;
    }

    private String EmergencyContact_Time;

    public void setEmergencyContact_Id(int emergencyContact_Id) {
        EmergencyContact_Id = emergencyContact_Id;
    }

    public void setEmergencyContact_StartTime(Date emergencyContact_StartTime) {
        EmergencyContact_StartTime = emergencyContact_StartTime;
    }

    public void setEmergencyContact_EndTime(Date emergencyContact_EndTime) {
        EmergencyContact_EndTime = emergencyContact_EndTime;
    }

    public void setEmergencyContact_Name(String emergencyContact_Name) {
        EmergencyContact_Name = emergencyContact_Name;
    }

    public void setEmergencyContact_Phone(String emergencyContact_Phone) {
        EmergencyContact_Phone = emergencyContact_Phone;
    }

    public void setEmergencyContact_Type(int emergencyContact_Type) {
        EmergencyContact_Type = emergencyContact_Type;
    }

    public int getEmergencyContact_Id() {
        return EmergencyContact_Id;
    }

    public Date getEmergencyContact_StartTime() {
        return EmergencyContact_StartTime;
    }

    public Date getEmergencyContact_EndTime() {
        return EmergencyContact_EndTime;
    }

    public String getEmergencyContact_Name() {
        return EmergencyContact_Name;
    }

    public String getEmergencyContact_Phone() {
        return EmergencyContact_Phone;
    }

    public int getEmergencyContact_Type() {
        return EmergencyContact_Type;
    }
}
