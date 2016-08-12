package com.trimph.toprand.trimphrxandroid.trimph.ui.main.presenter;

import com.trimph.toprand.trimphrxandroid.trimph.base.BasePresenter;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.impl.ResultNet;

/**
 * Created by tao on 2016/8/9.
 */

public interface PicturePresenter<T> extends BasePresenter {
    void showLoadDialog();

    void loadData();

    public void getPicture(ResultNet<T> resultNet);

    void dismissDialog();

    void sendError();

}
