package com.example.realnews.WeatherMVP;

import com.example.realnews.BaseMVP.BasePresenter;
import com.example.realnews.Bean.Now;
import com.example.realnews.Bean.NowWeatherDetail;
import com.example.realnews.MainContract;
import com.example.realnews.Util.RxJavaUtil;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;

public class WeatherApplyPressenter extends BasePresenter<MainContract.IViewWeatherFragment, WeatherApplyModel> implements MainContract.IMainPresenter {
    NowWeatherDetail nowWeatherDetail;
    public void HandleData() {


        Observable observable = getmModel().getNowWeather("101010100").doOnSubscribe(new Consumer<Disposable>() {
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
                getView().show("失败");
            }

            @Override
            public void onComplete() {
                Now now = nowWeatherDetail.getNow();
                getView().SetWeatherData(now);


            }
        });
    }

    @Override
    public void Subscribe() {

    }

    @Override
    public void subscribe() {

    }
}
