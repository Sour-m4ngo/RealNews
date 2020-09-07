package com.example.realnews.WeatherMVP;

import com.example.realnews.Api;
import com.example.realnews.BaseMVP.BaseModel;
import com.example.realnews.Bean.CityInfo;
import com.example.realnews.Util.RxCityService;


import io.reactivex.rxjava3.core.Observable;

public class CityApplyModel extends BaseModel {
    private  static String key = "757b618afa3d4ace80ae4030b30f8761";
    public Observable<CityInfo> getCityInfo(String location) {

        return RxCityService.getInstance().createRs(Api.class).getCityInfo(location,key);
    }
}
