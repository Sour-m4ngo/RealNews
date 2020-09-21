package com.example.realnews.Bean.DataBaseBean;

import org.litepal.crud.LitePalSupport;

public class CityBaseInfoBean extends LitePalSupport {
    private String CityName;
    private String DistrictName;
    private String CityId;


    public CityBaseInfoBean() {

    }

    public CityBaseInfoBean(String cityName, String districtName) {
        CityName = cityName;
        DistrictName = districtName;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public String getDistrictName() {
        return DistrictName;
    }

    public void setDistrictName(String districtName) {
        DistrictName = districtName;
    }

    public String getCityId() {
        return CityId;
    }

    public void setCityId(String cityId) {
        CityId = cityId;
    }

}
