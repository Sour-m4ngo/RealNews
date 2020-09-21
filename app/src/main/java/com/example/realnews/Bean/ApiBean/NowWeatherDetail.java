package com.example.realnews.Bean.ApiBean;
import java.util.Date;
public class NowWeatherDetail {
    private String code;
    private Date updatetime;
    private String fxlink;
    private Now now;
    private Refer refer;
    public void setCode(String code) {
         this.code = code;
     }
     public String getCode() {
         return code;
     }

    public void setUpdatetime(Date updatetime) {
         this.updatetime = updatetime;
     }
     public Date getUpdatetime() {
         return updatetime;
     }

    public void setFxlink(String fxlink) {
         this.fxlink = fxlink;
     }
     public String getFxlink() {
         return fxlink;
     }

    public void setNow(Now now) {
         this.now = now;
     }
     public Now getNow() {
         return now;
     }

    public void setRefer(Refer refer) {
         this.refer = refer;
     }
     public Refer getRefer() {
         return refer;
     }

}