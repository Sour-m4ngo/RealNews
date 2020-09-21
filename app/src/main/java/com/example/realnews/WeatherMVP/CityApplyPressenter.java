package com.example.realnews.WeatherMVP;

import com.example.realnews.BaseMVP.BasePresenter;
import com.example.realnews.Bean.ApiBean.CityInfo;

import com.example.realnews.MainContract;
import com.example.realnews.Util.RxJavaUtil;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;

public class CityApplyPressenter extends BasePresenter<MainContract.IViewWeatherView, CityApplyModel> implements MainContract.IPressenterCitySearch {
    CityInfo cityInfo;

    @Override
    public void HandleData(String location) {
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

            }

            @Override
            public void onComplete() {
                getView().getCityId(cityInfo.getLocation().get(0).getId());
            }
        });
    }



    @Override
    public void subscribe() {

    }


}
