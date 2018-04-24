package com.antonioleiva.mvpexample.app.Bean;

/**
 * Created by Colin on 2018/3/14.
 */

public class ListMenuItem {
    private String title;
    private String content;

    public ListMenuItem(String title, String content) {
        this.title = title;
        this.content = content;
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
}
