package com.trimph.toprand.trimphrxandroid.trimph.ui.main.news.persenter;

import android.content.Context;

import com.trimph.toprand.trimphrxandroid.trimph.ui.main.impl.ResultNet;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.model.NewsCommenBean;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.news.NewsFragment;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.news.model.INewsGetModel;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.news.model.NewsGetModel;

/**
 * Created by tao on 2016/8/12.
 */

public class PersenterViewImpl implements PersenterView {


    public INewsGetModel iNewsGetModel;

    public PersenterViewImpl(NewsFragment newsFragment) {
        newsFragment.setPresenter(this);
        iNewsGetModel = new NewsGetModel();
    }

    @Override
    public void getModelData(Context context,ResultNet<NewsCommenBean> resultNet, String key, String type) {
        iNewsGetModel.getModel(context,resultNet, key, type);
    }

    @Override
    public void start() {

    }
}
