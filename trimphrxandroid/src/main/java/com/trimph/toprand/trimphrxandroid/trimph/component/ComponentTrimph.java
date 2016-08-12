package com.trimph.toprand.trimphrxandroid.trimph.component;


import com.trimph.toprand.trimphrxandroid.MainActivity;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.main.mainfragment.model.PictureModelImpl;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.news.RequestManage;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.news.model.NewsGetModel;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.presenter.PicturePresenter;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.presenter.PicturePresenterImpl;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.view.PictureViewImpl;

/**
 * Created by tao on 2016/8/4.
 */

public interface ComponentTrimph {
    /**
     * 需要注入的地方
     *
     * @param mainActivity
     */
    void inject(MainActivity mainActivity);

    void inject(PictureViewImpl pictureView);

    void inject(PicturePresenterImpl picturePresenter);

    void inject(PictureModelImpl pictureModel);

    void inject(RequestManage requestManage);


}
