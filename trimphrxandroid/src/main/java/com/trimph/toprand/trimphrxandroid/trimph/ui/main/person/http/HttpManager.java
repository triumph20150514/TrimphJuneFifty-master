package com.trimph.toprand.trimphrxandroid.trimph.ui.main.person.http;


import com.squareup.okhttp.OkHttpClient;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.person.model.BaseModel;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.person.model.RequestModel;

import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * http交互处理类
 * Created by WZG on 2016/7/16.
 */
public class HttpManager {
    public static final String BASE_URL = "http://www.izaodao.com/Api/";
    private static final int DEFAULT_TIMEOUT = 6;
    private HttpService httpService;
    private volatile static HttpManager INSTANCE;

    //构造方法私有
    private HttpManager() {
        //手动创建一个OkHttpClient并设置超时时间
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        httpService = retrofit.create(HttpService.class);
    }

    //获取单例
    public static HttpManager getInstance() {
        if (INSTANCE == null) {
            synchronized (HttpManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpManager();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 处理http请求
     *
     * @param basePar 封装的请求数据
     */
    public void doHttpDeal(RequestModel basePar) {
        Observable observable = httpService.getAllVedioBy(true)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(basePar);

        observable.subscribe(basePar.getSubscriber());
    }
}
