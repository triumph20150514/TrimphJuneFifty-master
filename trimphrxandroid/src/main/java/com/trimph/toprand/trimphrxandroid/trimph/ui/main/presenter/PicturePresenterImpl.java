package com.trimph.toprand.trimphrxandroid.trimph.ui.main.presenter;

import android.util.Log;

import com.trimph.toprand.trimphrxandroid.trimph.Iservice.PictureApiTrimph;
import com.trimph.toprand.trimphrxandroid.trimph.Iservice.PictureBean;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.TrimphMainActivity;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.impl.ResultNet;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.view.PictureViewImpl;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tao on 2016/8/10.
 */

public class PicturePresenterImpl implements PicturePresenter {

    public String TAG = PicturePresenterImpl.class.getSimpleName();

    public PicturePresenter picturePresenter;
    @Inject
    PictureApiTrimph pictureApiTrimph;

    public PictureBean pictureBean;

    public PicturePresenterImpl(TrimphMainActivity trimphMainActivity) {
    }

    @Override
    public void showLoadDialog() {

    }

    @Override
    public void loadData() {

    }

    @Override
    public void getPicture(ResultNet resultNet) {
        pictureApiTrimph.getPictureList(1, 10)
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

    @Override
    public void dismissDialog() {

    }

    @Override
    public void sendError() {

    }

    @Override
    public void start() {

    }
}
