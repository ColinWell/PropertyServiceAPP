package com.antonioleiva.mvpexample.app.bean;

import java.util.Date;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DecorationApplication {

    @SerializedName("DecorationApplication_Id")
    @Expose
    private int decorationApplicationId;
    @SerializedName("DecorationApplication_Status")
    @Expose
    private int decorationApplicationStatus;
    @SerializedName("DecorationApplication_Reply")
    @Expose
    private Object decorationApplicationReply;
    @SerializedName("DecorationApplication_Place")
    @Expose
    private String decorationApplicationPlace;
    @SerializedName("DecorationApplication_Phone")
    @Expose
    private String decorationApplicationPhone;
    @SerializedName("Id")
    @Expose
    private int id;
    @SerializedName("Processor_Id")
    @Expose
    private int processorId;
    @SerializedName("DecorationApplication_StartTime")
    @Expose
    private Date decorationApplicationStartTime;
    @SerializedName("DecorationApplication_EndTime")
    @Expose
    private Date decorationApplicationEndTime;
    @SerializedName("logname")
    @Expose
    private String logname;

    public int getDecorationApplicationId() {
        return decorationApplicationId;
    }

    public void setDecorationApplicationId(int decorationApplicationId) {
        this.decorationApplicationId = decorationApplicationId;
    }

    public int getDecorationApplicationStatus() {
        return decorationApplicationStatus;
    }

    public void setDecorationApplicationStatus(int decorationApplicationStatus) {
        this.decorationApplicationStatus = decorationApplicationStatus;
    }

    public Object getDecorationApplicationReply() {
        return decorationApplicationReply;
    }

    public void setDecorationApplicationReply(Object decorationApplicationReply) {
        this.decorationApplicationReply = decorationApplicationReply;
    }

    public String getDecorationApplicationPlace() {
        return decorationApplicationPlace;
    }

    public void setDecorationApplicationPlace(String decorationApplicationPlace) {
        this.decorationApplicationPlace = decorationApplicationPlace;
    }

    public String getDecorationApplicationPhone() {
        return decorationApplicationPhone;
    }

    public void setDecorationApplicationPhone(String decorationApplicationPhone) {
        this.decorationApplicationPhone = decorationApplicationPhone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProcessorId() {
        return processorId;
    }

    public void setProcessorId(int processorId) {
        this.processorId = processorId;
    }

    public Date getDecorationApplicationStartTime() {
        return decorationApplicationStartTime;
    }

    public void setDecorationApplicationStartTime(Date decorationApplicationStartTime) {
        this.decorationApplicationStartTime = decorationApplicationStartTime;
    }

    public Date getDecorationApplicationEndTime() {
        return decorationApplicationEndTime;
    }

    public void setDecorationApplicationEndTime(Date decorationApplicationEndTime) {
        this.decorationApplicationEndTime = decorationApplicationEndTime;
    }

    public String getLogname() {
        return logname;
    }

    public void setLogname(String logname) {
        this.logname = logname;
    }

}