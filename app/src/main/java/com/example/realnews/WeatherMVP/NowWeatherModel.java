package com.example.realnews.WeatherMVP;

import com.example.realnews.BaseMVP.BaseModel;
import com.example.realnews.Api;
import com.example.realnews.Bean.ApiBean.NowWeatherDetail;
import com.example.realnews.MainContract;
import com.example.realnews.Util.RxNowWeatherService;
import io.reactivex.rxjava3.core.Observable;

public class NowWeatherModel extends BaseModel implements MainContract.IMainModel {
    private  static String key = "757b618afa3d4ace80ae4030b30f8761";
    public Observable<NowWeatherDetail> getNowWeather(String location) {
        return RxNowWeatherService.getInstance().createRs(Api.class).getNowWeather(location,key);
    }

}
