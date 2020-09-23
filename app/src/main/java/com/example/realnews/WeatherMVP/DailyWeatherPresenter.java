package com.example.realnews.WeatherMVP;

import android.util.Log;
import com.example.realnews.BaseMVP.BasePresenter;
import com.example.realnews.Bean.ApiBean.Daily;
import com.example.realnews.Bean.ApiBean.DailyWeatherDetail;
import com.example.realnews.MainContract;
import com.example.realnews.Util.RxJavaUtil;
import java.util.ArrayList;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import static androidx.constraintlayout.widget.Constraints.TAG;

public class DailyWeatherPresenter extends BasePresenter<MainContract.IViewWeatherView, DailyWeatherModel> implements MainContract.IPresenterDailyWeather {
    DailyWeatherDetail dailyWeatherDetail;

    public void GetDailyWeather(String LocationId) {
        Observable observable = getmModel().getDailyWeather(LocationId).doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Throwable {
                addDisposable(disposable);
            }
        });
        observable = RxJavaUtil.toSubscribe(observable);
        observable.subscribe(new Observer<DailyWeatherDetail>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(DailyWeatherDetail o) {
                dailyWeatherDetail = o;
                Log.d(TAG, "成功1");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "失败"+e.getCause());
                Log.d(TAG, "失败"+e.getLocalizedMessage());
                Log.d(TAG, "失败"+e.toString());
            }

            @Override
            public void onComplete() {
                ArrayList<Daily> dailyList = dailyWeatherDetail.getDaily();
                if (dailyList == null){
                    Log.d(TAG, "789789798789798789 "+dailyWeatherDetail.getCode());
                }else {
                    getView().SetDailyWeatherData(dailyList);
                    Log.d(TAG, "成功");
                }
            }
        });
    }

    @Override
    public void subscribe() {

    }

}
