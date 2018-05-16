package com.antonioleiva.mvpexample.app.bean;

/**
 * Created by Colin on 2018/4/10.
 */

public class AnnouncementItem {
    private String title;
    private String content;
    private int type;
    private String pubDate;
    private String pubName;

    public AnnouncementItem() {
    }

    public AnnouncementItem(String title, String content, int type, String pubDate) {
        this.title = title;
        this.content = content;
        this.type = type;
        this.pubDate = pubDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getPubName() {
        return pubName;
    }

    public void setPubName(String pubName) {
        this.pubName = pubName;
    }
}
