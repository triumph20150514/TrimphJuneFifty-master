package com.trimph.toprand.trimphrxandroid.trimph.ui.main.view;

import android.util.Log;

import com.trimph.toprand.trimphrxandroid.trimph.Iservice.PictureApiTrimph;
import com.trimph.toprand.trimphrxandroid.trimph.Iservice.PictureBean;
import com.trimph.toprand.trimphrxandroid.trimph.TrimphApplication;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.impl.ResultNet;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.presenter.PicturePresenter;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by tao on 2016/8/9.
 */

public class PictureViewImpl implements PictureView<PictureBean> {

    public String TAG = PictureViewImpl.class.getSimpleName();

    public PicturePresenter picturePresenter;

    @Inject
    PictureApiTrimph pictureApiTrimph;

    public PictureBean pictureBean;

    public PictureViewImpl() {
        TrimphApplication.component().inject(PictureViewImpl.this);
    }

    public static PictureViewImpl instance() {
        return new PictureViewImpl();
    }

    public void reFreshUI() {
        picturePresenter.start();
    }


    @Override
    public void setPresenter(PicturePresenter presenter) {
        this.picturePresenter = presenter;
    }

    public void setData(PictureBean pictureBean) {
        this.pictureBean = pictureBean;
    }

    public PictureBean getPictureBean() {
        return pictureBean;
    }
}
