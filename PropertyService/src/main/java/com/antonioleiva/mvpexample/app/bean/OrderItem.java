package com.antonioleiva.mvpexample.app.bean;

/**
 * Created by Colin on 2018/4/10.
 */

public class OrderItem {
    private String date;
    private String months;
    private String type;
    private String amount;

    public OrderItem() {
    }

    public OrderItem(String date, String months, String type, String amount) {
        this.date = date;
        this.months = months;
        this.type = type;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
