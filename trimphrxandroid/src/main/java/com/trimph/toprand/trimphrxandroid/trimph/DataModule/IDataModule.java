package com.trimph.toprand.trimphrxandroid.trimph.DataModule;

import com.trimph.toprand.trimphrxandroid.trimph.Iservice.PictureBean;

import rx.Subscriber;

/**
 * Created by tao on 2016/8/4.
 */
public interface IDataModule {
    void getPictureBean(Subscriber<PictureBean> pictureBeanSubscriber);
}
