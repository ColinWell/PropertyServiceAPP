package com.antonioleiva.mvpexample.app.bean;

/**
 * Created by Colin on 2018/4/18.
 */

public class NoticeItem {
    private int id;
    private String content;
    private String date;

    public NoticeItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
