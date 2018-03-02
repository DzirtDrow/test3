package com.example.test3;

import java.util.Date;

/**
 * Created by Андрей on 26.02.2018.
 */

public class SmsData {

    private int smsID;
    private String smsNumber;
    private String smsText;
    private long smsDate;
    private int smsType;

    public SmsData(int smsID, String smsNumber, String smsText, long smsDate, int smsType) {
        this.smsID = smsID;
        this.smsNumber = smsNumber;
        this.smsText = smsText;
        this.smsDate = smsDate;
        this.smsType = smsType;
    }

    public int getSmsID() {
        return smsID;
    }

    public String getSmsNumber() {
        return smsNumber;
    }

    public String getSmsText() {
        return smsText;
    }

    public SmsData(int smsID, String smsNumber, String smsText, long smsDate) {

        this.smsID = smsID;
        this.smsNumber = smsNumber;
        this.smsText = smsText;
        this.smsDate = smsDate;
    }

    public void setSmsID(int smsID) {
        this.smsID = smsID;
    }

    public void setSmsNumber(String smsNumber) {
        this.smsNumber = smsNumber;
    }

    public void setSmsText(String smsText) {
        this.smsText = smsText;
    }

    public String getSmsDate() {
        Date date = new Date(smsDate);

        return date.toString();
    }

    public void setSmsDate(long smsDate) {
        this.smsDate = smsDate;
    }

    public int getSmsType() {
        return smsType;
    }

    public void setSmsType(int smsType) {
        this.smsType = smsType;
    }
}