package com.example.realnews.Util;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RxCityLocationService  {
    private volatile  static RxCityLocationService apiRetrofit;
    private Retrofit mRetrofit;
    private static final int READ_TIMEOUT = 60;//读取超时时间,单位秒
    private static final int CONN_TIMEOUT = 50;//连接超时时间,单位秒
    private RxCityLocationService(){
        OkHttpClient client = new OkHttpClient.Builder()//初始化一个client,不然retrofit会自己默认添加一个
                .connectTimeout(CONN_TIMEOUT, TimeUnit.MINUTES)//设置连接时间为50s
                .readTimeout(READ_TIMEOUT, TimeUnit.MINUTES)//设置读取时间为一分钟
                .build();

        mRetrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://geoapi.heweather.net/v2/city/")
                .addConverterFactory(GsonConverterFactory.create())//返回值为Gson的支持(以实体类返回)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())//返回值为Oservable<T>的支持
                .build();
    }
    public static RxCityLocationService getInstance(){
        if (apiRetrofit == null){
            synchronized (Object.class){
                if (apiRetrofit == null){
                    apiRetrofit = new RxCityLocationService();
                }
            }
        }

        return apiRetrofit;
    }
    public <T> T createRs(Class<T> service){
        return mRetrofit.create(service);
    }

}
