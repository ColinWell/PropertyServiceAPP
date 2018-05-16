package com.antonioleiva.mvpexample.app.bean;

import java.util.Date;

/**
 * Created by 85732 on 2018/4/18.
 */

public class Complaint {
    private int Complaints_Id;
    private int Processor_Id;
    private Date Complaints_Time;
    private Date ComplaintsProcessor_Time;
    private String Complaints_Reply;
    private int Id;
    private int Complaints_Status;

    public void setComplaint_Name(String complaint_Name) {
        Complaint_Name = complaint_Name;
    }

    private String Complaints_Content;

    public String getComplaint_Name() {
        return Complaint_Name;
    }

    private String Complaint_Name;

    public String getComplaint_Phone() {
        return Complaint_Phone;
    }

    public void setComplaint_Phone(String complaint_Phone) {
        Complaint_Phone = complaint_Phone;
    }

    private String Complaint_Phone;

    public void setComplaints_Id(int complaints_Id) {
        Complaints_Id = complaints_Id;
    }

    public void setProcessor_Id(int processor_Id) {
        Processor_Id = processor_Id;
    }

    public void setComplaints_Time(Date complaints_Time) {
        Complaints_Time = complaints_Time;
    }

    public void setComplaintsProcessor_Time(Date complaintsProcessor_Time) {
        ComplaintsProcessor_Time = complaintsProcessor_Time;
    }

    public void setComplaints_Reply(String complaints_Reply) {
        Complaints_Reply = complaints_Reply;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setComplaints_Status(int complaints_Status) {
        Complaints_Status = complaints_Status;
    }

    public void setComplaints_Content(String complaints_Content) {
        Complaints_Content = complaints_Content;
    }

    public int getComplaints_Id() {
        return Complaints_Id;
    }

    public int getProcessor_Id() {
        return Processor_Id;
    }

    public Date getComplaints_Time() {
        return Complaints_Time;
    }

    public Date getComplaintsProcessor_Time() {
        return ComplaintsProcessor_Time;
    }

    public String getComplaints_Reply() {
        return Complaints_Reply;
    }

    public int getId() {
        return Id;
    }

    public int getComplaints_Status() {
        return Complaints_Status;
    }

    public String getComplaints_Content() {
        return Complaints_Content;
    }
}
