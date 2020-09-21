package com.example.realnews;

import com.example.realnews.Bean.ApiBean.CityInfo;
import com.example.realnews.Bean.ApiBean.DailyWeatherDetail;
import com.example.realnews.Bean.ApiBean.NewsDetails;
import com.example.realnews.Bean.ApiBean.NowWeatherDetail;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("?channel=头条&start=0&num=10&appkey=a5a325e59edd66c8")
    Observable<NewsDetails> getNews();
    @GET("7d?")
    Observable<DailyWeatherDetail> getDailyWeather(@Query("location") String location,@Query("key") String key);
    @GET("now?")
    Observable<NowWeatherDetail> getNowWeather(@Query("location") String location,@Query("key") String key);
    @GET("lookup?")
    Observable<CityInfo> getCityInfo(@Query("location") String location,@Query("key") String key);

}
/*
* public interface MovieService {
            @GET("top250")Observable<MovieEntity>
            getTopMovie(@Query("start") int start, @Query("count") int count);
        }
        * */
