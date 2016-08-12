package com.trimph.toprand.trimphrxandroid.trimph.ui.main.news.model;

import android.content.Context;

import com.trimph.toprand.trimphrxandroid.trimph.ui.main.impl.ResultNet;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.model.NewsCommenBean;

/**
 * Created by tao on 2016/8/12.
 */

public interface INewsGetModel {
    public void getModel(Context context, ResultNet<NewsCommenBean> resultNet, String key, String type);
}
