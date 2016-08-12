package com.trimph.toprand.trimphrxandroid.trimph.ui.main.news.model;

import android.content.Context;

import com.trimph.toprand.trimphrxandroid.trimph.TrimphApplication;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.impl.ResultNet;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.model.NewsCommenBean;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.news.RequestManage;

/**
 * Created by tao on 2016/8/12.
 */

public class NewsGetModel implements INewsGetModel {

    @Override
    public void getModel(Context context, ResultNet<NewsCommenBean> resultNet, String key, String type) {
        RequestManage.getInstance().init(context, resultNet, key, type);
    }
}
