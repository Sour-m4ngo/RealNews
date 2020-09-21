package com.example.realnews.WeatherMVP;

import com.example.realnews.BaseMVP.BasePresenter;
import com.example.realnews.Bean.ApiBean.Now;
import com.example.realnews.Bean.ApiBean.NowWeatherDetail;
import com.example.realnews.MainContract;
import com.example.realnews.Util.RxJavaUtil;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;

public class NowWeatherPressenter extends BasePresenter<MainContract.IViewWeatherView, NowWeatherModel> implements MainContract.IMainWeatherPresenter {
    NowWeatherDetail nowWeatherDetail;

    public void GetWeather(String LocationId) {
        Observable observable = getmModel().getNowWeather(LocationId).doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Throwable {
                addDisposable(disposable);
            }
        });
        observable = RxJavaUtil.toSubscribe(observable);
        observable.subscribe(new Observer<NowWeatherDetail>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(NowWeatherDetail o) {
                nowWeatherDetail = o;
            }

            @Override
            public void onError(@NonNull Throwable e) {
            }

            @Override
            public void onComplete() {
                Now now = nowWeatherDetail.getNow();
                getView().SetNowWeatherData(now);
            }
        });
    }



    @Override
    public void subscribe() {

    }


}
