package com.example.linpiaohsin.weicotest.entity;

/**
 * Created by linpiaohsin on 2017/11/14.
 */

public class Dialog {
    int mod;
    String title;
    String content;
    String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getMod() {
        return mod;
    }

    public void setMod(int mod) {
        this.mod = mod;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {

        return title;
    }

    public String getContent() {
        return content;
    }

    public Dialog(int icon, String title, String content){
        this.mod=icon;

        this.content=content;
        this.title=title;
    }
}
