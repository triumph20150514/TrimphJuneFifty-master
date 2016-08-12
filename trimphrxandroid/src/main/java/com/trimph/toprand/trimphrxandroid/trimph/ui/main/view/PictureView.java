package com.trimph.toprand.trimphrxandroid.trimph.ui.main.view;

import com.trimph.toprand.trimphrxandroid.trimph.Iservice.PictureBean;
import com.trimph.toprand.trimphrxandroid.trimph.base.BaseView;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.impl.ResultNet;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.presenter.PicturePresenter;

/**
 * Created by tao on 2016/8/9.
 */

public interface PictureView<T> extends BaseView<PicturePresenter> {

    public void reFreshUI();
}
