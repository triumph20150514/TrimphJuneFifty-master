package com.trimph.toprand.trimphrxandroid.trimph.DataModule;

import com.trimph.toprand.trimphrxandroid.trimph.Iservice.PictureApiTrimph;
import com.trimph.toprand.trimphrxandroid.trimph.Iservice.PictureBean;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by tao on 2016/8/4.
 */

public class DataModuleImpl implements IDataModule {

    @Inject
    public PictureApiTrimph pictureApiTrimph;

    @Override
    public void getPictureBean(Subscriber<PictureBean> pictureBeanSubscriber) {
        pictureApiTrimph.getPictureList(1, 10)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.newThread())
                .subscribe(pictureBeanSubscriber);
    }
}
