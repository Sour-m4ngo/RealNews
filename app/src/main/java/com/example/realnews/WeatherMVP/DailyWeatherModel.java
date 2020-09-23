package com.example.realnews.WeatherMVP;

import com.example.realnews.Api;
import com.example.realnews.BaseMVP.BaseModel;
import com.example.realnews.Bean.ApiBean.DailyWeatherDetail;

import com.example.realnews.Util.RxDailyWeatherService;
import io.reactivex.rxjava3.core.Observable;

public class DailyWeatherModel extends BaseModel {
    private  static String key = "757b618afa3d4ace80ae4030b30f8761";
    public Observable<DailyWeatherDetail> getDailyWeather(String location){
        return RxDailyWeatherService.getInstance().createRs(Api.class).getDailyWeather(location,key);
    }
}
