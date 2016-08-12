package com.trimph.toprand.trimphrxandroid.trimph.ui.main.main.mainfragment.model;

import com.trimph.toprand.trimphrxandroid.trimph.ui.main.impl.ResultNet;

/**
 * Created by tao on 2016/8/10.
 */

public interface IPictureModel<T> {
     void getPicture(ResultNet<T> resultNet,int page,int count);
}
