package com.example.realnews.WeatherMVP;

import com.example.realnews.BaseMVP.BasePresenter;
import com.example.realnews.Bean.CityInfo;
import com.example.realnews.Bean.Now;
import com.example.realnews.Bean.NowWeatherDetail;
import com.example.realnews.MainContract;
import com.example.realnews.Util.RxJavaUtil;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;

public class CityApplyPressenter extends BasePresenter<MainContract.IMainViewActivity, CityApplyModel> implements MainContract.IMainPresenter {
    CityInfo cityInfo;
    private String location;
    @Override
    public void HandleData() {
        Observable observable = getmModel().getCityInfo(location).doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Throwable {
                addDisposable(disposable);
            }
        });
        observable = RxJavaUtil.toSubscribe(observable);
        observable.subscribe(new Observer<CityInfo>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(CityInfo resultData) {
                cityInfo = resultData;

            }

            @Override
            public void onError(@NonNull Throwable e) {
                getView().show("失败");
            }

            @Override
            public void onComplete() {
                //getView().show(cityInfo.getLocation());
            }
        });
    }

    @Override
    public void Subscribe() {

    }

    @Override
    public void subscribe() {

    }

    public void searchCity(String location){
        this.location = location;
        HandleData();
    }
}
