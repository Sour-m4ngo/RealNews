package com.example.realnews.WeatherMVP;

import com.example.realnews.BaseMVP.BaseModel;
import com.example.realnews.Bean.DailyWeatherDetail;
import com.example.realnews.Bean.NewsDetails;
import com.example.realnews.Api;
import com.example.realnews.Bean.NowWeatherDetail;
import com.example.realnews.MainContract;
import com.example.realnews.Util.RxNewsService;
import com.example.realnews.Util.RxWeatherService;

import io.reactivex.rxjava3.core.Observable;

public class WeatherApplyModel extends BaseModel implements MainContract.IMainModel {
    private  static String key = "757b618afa3d4ace80ae4030b30f8761";
    public Observable<NowWeatherDetail> getNowWeather(String location) {

        return RxWeatherService.getInstance().createRs(Api.class).getNowWeather(location,key);
    }

    public Observable<DailyWeatherDetail> getDailyWeather(String location){
        return RxWeatherService.getInstance().createRs(Api.class).getDaliyWeather(location,key);
    }
}
