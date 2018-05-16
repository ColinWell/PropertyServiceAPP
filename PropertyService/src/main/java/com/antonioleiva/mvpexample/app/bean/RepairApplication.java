package com.antonioleiva.mvpexample.app.bean;

import java.util.Date;

public class RepairApplication {
    private int RepairApplication_Id;
    private int RepairApplication_Status;
    private String RepairApplication_Place;
    private int RepairApplication_Type;
    private String RepairApplication_Name;

    public void setRepairApplication_Id(int repairApplication_Id) {
        RepairApplication_Id = repairApplication_Id;
    }

    public void setRepairApplication_Status(int repairApplication_Status) {
        RepairApplication_Status = repairApplication_Status;
    }

    public void setRepairApplication_Place(String repairApplication_Place) {
        RepairApplication_Place = repairApplication_Place;
    }

    public void setRepairApplication_Type(int repairApplication_Type) {
        RepairApplication_Type = repairApplication_Type;
    }

    public void setRepairApplication_Name(String repairApplication_Name) {
        RepairApplication_Name = repairApplication_Name;
    }

    public void setRepairApplication_Phone(String repairApplication_Phone) {
        RepairApplication_Phone = repairApplication_Phone;
    }

    public void setDamage_Status(int damage_Status) {
        Damage_Status = damage_Status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRepairApplication_Time(Date repairApplication_Time) {
        RepairApplication_Time = repairApplication_Time;
    }

    public void setProcessor_Id(int processor_Id) {
        Processor_Id = processor_Id;
    }

    public void setLogname(String logname) {
        this.logname = logname;
    }

    private String RepairApplication_Phone;
    private int Damage_Status;
    private int id;

    public int getRepairApplication_Id() {
        return RepairApplication_Id;
    }

    public int getRepairApplication_Status() {
        return RepairApplication_Status;
    }

    public String getRepairApplication_Place() {
        return RepairApplication_Place;
    }

    public int getRepairApplication_Type() {
        return RepairApplication_Type;
    }

    public String getRepairApplication_Name() {
        return RepairApplication_Name;
    }

    public String getRepairApplication_Phone() {
        return RepairApplication_Phone;
    }

    public int getDamage_Status() {
        return Damage_Status;
    }

    public int getId() {
        return id;
    }

    public Date getRepairApplication_Time() {
        return RepairApplication_Time;
    }

    public int getProcessor_Id() {
        return Processor_Id;
    }

    public String getLogname() {
        return logname;
    }

    private Date RepairApplication_Time;
    private int Processor_Id;
    private String logname;

}
