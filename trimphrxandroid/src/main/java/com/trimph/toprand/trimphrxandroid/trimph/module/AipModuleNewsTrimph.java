package com.trimph.toprand.trimphrxandroid.trimph.module;

import com.squareup.okhttp.OkHttpClient;
import com.trimph.toprand.trimphrxandroid.trimph.Contants.ContantsObj;
import com.trimph.toprand.trimphrxandroid.trimph.Iservice.PictureApiTrimph;
import com.trimph.toprand.trimphrxandroid.trimph.utils.LogIntercepter;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by tao on 2016/8/4.
 */

@Module
public class AipModuleNewsTrimph {

    //天气
    public String BASE_WEALTHER_URL = "http://v.juhe.cn/weather/ip";
    public String BASE_PICTURE_URL = "http://www.tngou.net/tnfs/api/";

    @Provides
    public Retrofit providesRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ContantsObj.Base_url)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }


    @Provides
    public OkHttpClient providesOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(5, TimeUnit.SECONDS);
        okHttpClient.setReadTimeout(6, TimeUnit.SECONDS);
        okHttpClient.interceptors().add(new LogIntercepter());
        return okHttpClient;
    }


    @Provides
    public PictureApiTrimph providesPictureApiTrimph(Retrofit retrofit) {
        return retrofit.create(PictureApiTrimph.class);
    }
}