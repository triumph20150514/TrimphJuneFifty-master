package com.trimph.toprand.trimphrxandroid.trimph.ui.main.news.persenter;

import android.content.Context;

import com.trimph.toprand.trimphrxandroid.trimph.base.BasePresenter;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.impl.ResultNet;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.model.NewsCommenBean;

/**
 * Created by tao on 2016/8/12.
 */

public interface PersenterView extends BasePresenter {
    void getModelData(Context context,ResultNet<NewsCommenBean> resultNet, String key, String type);
}
