package com.trimh.nuannuan.net;

import com.trimh.nuannuan.bean.MoviceBean;
import com.trimh.nuannuan.bean.PictureBean;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Project TrimphJuneFifty
 * Package_name com.trimh.nuannuan.net
 * Created by trimph on 2016/6/29.
 * Created by trimph  on 2016/6/29.
 */
public class PictureApi {

    NetService netService;
    private static final int DEFAULT_TIMEOUT = 5;
    public String base_url = "http://www.tngou.net/tnfs/api/";

    public PictureApi() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        //jianli
        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        netService = retrofit.create(NetService.class);
    }


    public void getPictureList(Subscriber<PictureBean> subscriber, int start, int cont) {
        netService.getPictureList(start, cont)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final PictureApi INSTANCE = new PictureApi();
    }

    //获取单例
    public static PictureApi getInstance() {
        return SingletonHolder.INSTANCE;
    }

}
