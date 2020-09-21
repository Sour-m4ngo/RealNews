package com.example.realnews;

import com.example.realnews.BaseInterface.IBasePresenter;
import com.example.realnews.BaseInterface.IBaseView;


import com.example.realnews.Bean.ApiBean.Daily;
import com.example.realnews.Bean.ApiBean.DailyWeatherDetail;
import com.example.realnews.Bean.ApiBean.Detail;
import com.example.realnews.Bean.ApiBean.Now;

import java.util.ArrayList;
import java.util.List;


public interface MainContract {
    interface IMainModel {
    }

    interface IViewNewsFragment extends IBaseView {
        void show(String s);
        void SetAdapterData(ArrayList<Detail> details);
    }

    interface IViewWeatherView extends IBaseView{
        void SetNowWeatherData(Now now);
        void SetDailyWeatherData(ArrayList<Daily> dailyList);
        void getCityId(String Id);
    }

    interface IMainViewActivity extends IBaseView {
    }

    interface IPressenterCitySearch extends IBasePresenter{
        void HandleData(String arg1);
    }
    interface IMainPresenter extends IBasePresenter {
        void HandleData(String arg1);
    }
    interface IMainWeatherPresenter extends IBasePresenter{
        void GetWeather(String arg1);
    }
    interface IPresenterDailyWeather extends IBasePresenter{
        void GetDailyWeather(String arg1);
    }
}