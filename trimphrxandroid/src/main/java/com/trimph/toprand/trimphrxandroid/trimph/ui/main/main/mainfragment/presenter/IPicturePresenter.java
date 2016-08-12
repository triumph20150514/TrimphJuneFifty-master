package com.trimph.toprand.trimphrxandroid.trimph.ui.main.main.mainfragment.presenter;

import com.trimph.toprand.trimphrxandroid.trimph.Iservice.PictureBean;
import com.trimph.toprand.trimphrxandroid.trimph.base.BasePresenter;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.impl.ResultNet;

/**
 * Created by tao on 2016/8/10.
 */

public interface IPicturePresenter extends BasePresenter{
    void loadPicture(int page,int count);
}
