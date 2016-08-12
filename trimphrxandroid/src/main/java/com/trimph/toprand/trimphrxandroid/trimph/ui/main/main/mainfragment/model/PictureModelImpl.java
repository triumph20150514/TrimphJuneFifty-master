package com.trimph.toprand.trimphrxandroid.trimph.ui.main.main.mainfragment.model;

import android.content.Context;
import android.util.Log;

import com.trimph.toprand.trimphrxandroid.trimph.Iservice.PictureApiTrimph;
import com.trimph.toprand.trimphrxandroid.trimph.Iservice.PictureBean;
import com.trimph.toprand.trimphrxandroid.trimph.TrimphApplication;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.impl.ResultNet;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.person.custome.ProgressSubscriber;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tao on 2016/8/10.
 */

public class PictureModelImpl implements IPictureModel<PictureBean> {

    @Inject
    public PictureApiTrimph pictureApiTrimph;

    public String TAG = PictureModelImpl.class.getSimpleName();

    public PictureModelImpl() {
        ////dddddddddd***********
        TrimphApplication.component().inject(this);
    }

    @Override
    public void getPicture(ResultNet<PictureBean> resultNet, int page, int count) {
        if (pictureApiTrimph == null) {
            Log.e(TAG, "------------依赖注入为空-----------");
        }

        pictureApiTrimph.getPictureList(page, count)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<PictureBean>() {

                    @Override
                    public void onCompleted() {
                        Log.e(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError" + e.getMessage());
                        resultNet.error(e.getMessage());
                    }

                    @Override
                    public void onNext(PictureBean pictureBean) {
                        Log.e(TAG, "onNext");
                        if (pictureBean == null) {
                            return;
                        }
                        Log.e(TAG, "onNext" + pictureBean.getTngou().get(0).toString());
                        Log.e(TAG, "onNext" + pictureBean.getTngou().size());
                        resultNet.success(pictureBean);
                    }
                });
    }
}
