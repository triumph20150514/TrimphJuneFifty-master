package com.trimph.toprand.trimphrxandroid.trimph.ui.main.person.model;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by tao on 2016/8/11.
 */

public class RequestModel<T> extends RxBaseResutlModel<T> {

    public boolean isa;
    public Subscriber<T> subscriber;


    public RequestModel(Subscriber<T> subscriber, boolean isall) {
        this.subscriber = subscriber;
        this.isa = isall;
    }

    @Override
    public Observable getObservable() {
        return null;
    }

    @Override
    public Subscriber getSubscriber() {
        return subscriber;
    }

}
