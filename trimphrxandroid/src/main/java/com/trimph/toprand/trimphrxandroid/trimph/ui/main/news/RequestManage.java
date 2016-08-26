package com.trimph.toprand.trimphrxandroid.trimph.ui.main.news;

import android.content.Context;

import com.trimph.toprand.trimphrxandroid.trimph.Iservice.PictureApiTrimph;
import com.trimph.toprand.trimphrxandroid.trimph.TrimphApplication;
import com.trimph.toprand.trimphrxandroid.trimph.module.AipModuleNewsTrimph;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.impl.ResultNet;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.model.NewsCommenBean;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.person.custome.ProgressSubscriber;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 请求管理类
 * Created by tao on 2016/8/12.
 */

public class RequestManage {

    @Inject
    PictureApiTrimph pictureApiTrimph;

    public RequestManage() {
        TrimphApplication.component().inject(this);
    }

    private static RequestManage requestManage = new RequestManage();

    public static RequestManage getInstance() {
        return requestManage;
    }

    public void init(Context context, ResultNet<NewsCommenBean> resultNet, String key, String type) {
        pictureApiTrimph.getNews(key, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new ProgressSubscriber<NewsCommenBean>(resultNet, context));
    }

}
