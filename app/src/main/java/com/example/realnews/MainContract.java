package com.example.realnews;

import com.example.realnews.BaseInterface.IBasePresenter;
import com.example.realnews.BaseInterface.IBaseView;
import com.example.realnews.Bean.Detail;
import com.example.realnews.Bean.Now;

import java.util.ArrayList;

import retrofit2.Callback;


public interface MainContract {
    interface IMainModel {

    }

    interface IViewNewsFragment extends IBaseView {
        void show(String s);
        void SetAdapterData(ArrayList<Detail> details);

    }
    interface IViewWeatherFragment extends IBaseView{
        void SetWeatherData(Now now);
        void show(String s);
    }
    interface IMainViewActivity extends IBaseView {
        void show(String s);

    }
    interface IMainPresenter extends IBasePresenter {
        void HandleData();
        void Subscribe();
    }
}