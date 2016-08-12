package com.trimph.toprand.trimphrxandroid.trimph.ui.main.person.model;

import com.trimph.toprand.trimphrxandroid.trimph.base.BaseView;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.person.excption.HttpResutlException;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by tao on 2016/8/11.
 */

public abstract class RxBaseResutlModel<T> implements Func1<BaseModel<T>, T> {


    public abstract Observable getObservable();

    public abstract Subscriber getSubscriber();

    public RxBaseResutlModel() {

    }

    @Override
    public T call(BaseModel<T> tBaseModel) {
        if (tBaseModel == null) {
            throw new HttpResutlException("数据返回为空");
        }
        if (tBaseModel.getRet() == 1) {
            throw new HttpResutlException(tBaseModel.getRet());
        }
        return tBaseModel.getData();
    }
}
