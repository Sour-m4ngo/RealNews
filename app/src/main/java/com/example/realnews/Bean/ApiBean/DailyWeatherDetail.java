/**
 * Copyright 2020 bejson.com
 */
package com.example.realnews.Bean.ApiBean;
import java.util.ArrayList;
import java.util.Date;

public class DailyWeatherDetail {
    private String code;
    private Date updatetime;
    private String fxlink;
    private Refer refer;
    private ArrayList<Daily> daily;
    public void setCode(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }

    public void setUpdateTime(Date updateTime) {
        this.updatetime = updateTime;
    }
    public Date getUpdateTime() {
        return updatetime;
    }

    public void setFxLink(String fxLink) {
        this.fxlink = fxLink;
    }
    public String getFxLink() {
        return fxlink;
    }

    public void setDaily(ArrayList<Daily> daily) {
        this.daily = daily;
    }
    public ArrayList<Daily> getDaily() {
        return daily;
    }

    public void setRefer(Refer refer) {
        this.refer = refer;
    }
    public Refer getRefer() {
        return refer;
    }

}