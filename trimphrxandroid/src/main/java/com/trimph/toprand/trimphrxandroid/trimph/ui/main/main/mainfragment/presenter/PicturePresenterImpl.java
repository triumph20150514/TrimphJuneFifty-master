package com.trimph.toprand.trimphrxandroid.trimph.ui.main.main.mainfragment.presenter;

import android.util.Log;

import com.trimph.toprand.trimphrxandroid.trimph.Iservice.PictureBean;
import com.trimph.toprand.trimphrxandroid.trimph.base.BasePresenter;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.impl.ResultNet;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.main.mainfragment.model.IPictureModel;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.main.mainfragment.model.PictureModelImpl;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.main.mainfragment.view.IPictureView;

/**
 * Created by tao on 2016/8/10.
 */

public class PicturePresenterImpl implements IPicturePresenter {

    public IPictureModel iPictureModel;
    public IPictureView iPictureView;

    public String TAG = PicturePresenterImpl.class.getSimpleName();


    public PicturePresenterImpl(IPictureView iPictureView) {
        this.iPictureView = iPictureView;
        iPictureView.setPresenter(this);
        iPictureModel = new PictureModelImpl();
    }

    @Override
    public void start() {

    }

    @Override
    public void loadPicture(int page,int count) {
        iPictureView.showDialog();
        iPictureModel.getPicture(new ResultNet<PictureBean>() {
            @Override
            public void success(PictureBean pictureBean) {
                iPictureView.dismissDialog();
                if (pictureBean != null) {
                    Log.e(TAG, pictureBean.toString());
                }
                iPictureView.loadData(pictureBean);
            }

            @Override
            public void error(String message) {
                iPictureView.dismissDialog();
                Log.e(TAG, message.toString());
            }
        },page,count);
    }
}
