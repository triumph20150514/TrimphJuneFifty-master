package com.trimph.toprand.trimphrxandroid.trimph.ui.main.impl;

/**
 * Created by tao on 2016/8/10.
 */

public interface ResultNet<T> {
    void success(T t);

    void error(String message);
}
