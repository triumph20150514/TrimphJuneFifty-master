package com.trimph.toprand.trimphrxandroid.trimph.ui.main.main.mainfragment.view;

import com.trimph.toprand.trimphrxandroid.trimph.Iservice.PictureBean;
import com.trimph.toprand.trimphrxandroid.trimph.base.BaseView;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.impl.ResultNet;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.main.mainfragment.presenter.IPicturePresenter;

/**
 * Created by tao on 2016/8/10.
 */

public interface IPictureView extends BaseView<IPicturePresenter> {
    void showDialog();

    void loadData(PictureBean pictureBean);

    void dismissDialog();
}
