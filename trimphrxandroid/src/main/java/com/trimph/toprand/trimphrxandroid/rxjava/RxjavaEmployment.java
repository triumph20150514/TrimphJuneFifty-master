package com.trimph.toprand.trimphrxandroid.rxjava;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.util.Observer;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by tao on 2016/8/12.
 */

public class RxjavaEmployment {
    public void IeteraterFolder(File[] files, Activity activity) {
        new Thread() {
            @Override
            public void run() {
                for (File file : files) {
                    File[] files1 = file.listFiles();
                    for (File file1 : files1) {
                        if (file1.getName().endsWith(".png")) {
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    // 主线操作
                                }
                            });
                        }
                    }
                }
            }
        };
    }

    public void RxFloder(File[] files, Activity activity) {
        Observable.from(files).flatMap(new Func1<File, Observable<File>>() {
            @Override
            public Observable<File> call(File file) {
                // 迭代除下一级
                return Observable.from(file.listFiles());
            }
        }).filter(new Func1<File, Boolean>() {
            @Override
            public Boolean call(File file) {
                //帅选除 png jiewei d
                return file.getName().endsWith(".png");
            }
        }).map(new Func1<File, Bitmap>() {
            @Override
            public Bitmap call(File file) {
                //把文件转化为bitmap
                return BitmapFactory.decodeFile(file.getAbsolutePath());
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {
                        /* 主线程中做操作*/

                    }
                });

        Subscriber<String> s = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        };

    }

    public void rxCreate() {

        // OBservable 被观察者的创建
        Observable<String> o = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                //subscriber.isUnsubscribed()
                subscriber.onNext("s");
            }
        });
        //  just(T...): 将传入的参数依次发送出来。
        /// Observable just(数组 或 iterater);
        Observable.just("").subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        });

        /*********RxJava Action 使用******/
        Action0 a = new Action0() {
            @Override
            public void call() {

            }
        };

        ////
        Action1 a1 = new Action1() {
            @Override
            public void call(Object o) {

            }
        };

        /////*******************RxJava 后台执行 前台调用
        Observable.from(new String[]{})
                .subscribeOn(Schedulers.io()) //前面的操作在流 现场中
                .observeOn(AndroidSchedulers.mainThread()) // subscriber 在主线程中
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {

                    }
                });

        //******** doOnSubscribe 制定现场
        // 而如果在 doOnSubscribe() 之后有 subscribeOn() 的话，它将执行在离它最近的 subscribeOn()
        // 所指定的线程。 也就是在主线程中执行
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

            }
        }).doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).doOnNext(new Action1<String>() {
            @Override
            public void call(String s) {

            }
        });

        //********Rxjava

    }
}
