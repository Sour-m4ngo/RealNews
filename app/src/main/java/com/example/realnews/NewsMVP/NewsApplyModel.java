package com.example.realnews.NewsMVP;


import com.example.realnews.BaseMVP.BaseModel;
import com.example.realnews.Bean.ApiBean.NewsDetails;
import com.example.realnews.Api;
import com.example.realnews.MainContract;
import com.example.realnews.Util.RxNewsService;

import io.reactivex.rxjava3.core.Observable;

public class NewsApplyModel extends BaseModel implements MainContract.IMainModel {

    public NewsApplyModel(){}

    public Observable<NewsDetails> request() {
        // return RetrofitManager.getInstance().createRs(GetPoetryEntity.class).getPoetry();

        //Log.d(TAG, "popoppopop");
        return RxNewsService.getInstance().createRs(Api.class).getNews();
    }
}
