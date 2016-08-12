package com.trimph.toprand.trimphrxandroid.trimph.ui.main.person;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trimph.toprand.trimphrxandroid.trimph.ui.main.impl.ResultNet;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.person.custome.ProgressSubscriber;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.person.http.HttpManager;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.person.model.NewsModel;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.person.model.RequestModel;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.person.model.RxBaseResutlModel;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by tao on 2016/8/10.
 */

public class PersonFragment extends Fragment implements ResultNet<NewsModel> {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        init();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void init() {

        RequestModel requestModel=new RequestModel(new ProgressSubscriber<NewsModel>(this,getContext()),true);
        HttpManager.getInstance().doHttpDeal(requestModel);
    }


    @Override
    public void success(NewsModel newsModel) {

    }

    @Override
    public void error(String message) {

    }
}
