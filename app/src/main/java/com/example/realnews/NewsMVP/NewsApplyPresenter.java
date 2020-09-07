package com.example.realnews.NewsMVP;


import android.util.Log;
import android.widget.Toast;

import com.example.realnews.BaseMVP.BasePresenter;
import com.example.realnews.Bean.Detail;
import com.example.realnews.Bean.NewsDetails;
import com.example.realnews.MainContract;
import com.example.realnews.Util.RxJavaUtil;

import java.util.ArrayList;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;

import static android.content.ContentValues.TAG;


public class NewsApplyPresenter extends BasePresenter<MainContract.IViewNewsFragment, NewsApplyModel> implements MainContract.IMainPresenter {
    NewsDetails mNewsDetails ;
    public void HandleData(){

        Observable observable = getmModel().request().doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                    addDisposable(disposable);
            }
        });
        observable = RxJavaUtil.toSubscribe(observable);
        observable.subscribe(new Observer<NewsDetails>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext( NewsDetails newsDetails) {
                    mNewsDetails = newsDetails;
                    ArrayList<Detail> MetaData = mNewsDetails.getdata();
                    getView().show("1"+mNewsDetails.title());
                    getView().SetAdapterData(MetaData);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                getView().show("失败");
                //失败
            }

            @Override
            public void onComplete() {
                CancelSubscription();


            }
        });
    }

    @Override
    public void Subscribe() {

    }

    @Override
    public void detach() {
        super.detach();
    }

    @Override
    public void subscribe() {

    }

}
