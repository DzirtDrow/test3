package com.example.test3;

import java.util.Date;

/**
 * Created by Андрей on 28.02.2018.
 */

public class Dialog {

    int id;
    String title;
    String lastSms;
    String lastSmsTime;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLastSms() {
        return lastSms;
    }

    public void setLastSms(String lastSms) {
        this.lastSms = lastSms;
    }

    public String getLastSmsTime() {
        Date date = new Date(Long.parseLong(lastSmsTime));
        return date.toString();
    }

    public void setLastSmsTime(String lastSmsTime) {
        this.lastSmsTime = lastSmsTime;
    }

    public Dialog(int id, String title, String lastSms, String lastSmsTime) {

        this.id = id;
        this.title = title;
        this.lastSms = lastSms;
        this.lastSmsTime = lastSmsTime;
    }
}
