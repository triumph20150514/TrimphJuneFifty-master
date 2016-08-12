package com.trimph.toprand.trimphrxandroid.trimph.ui.main.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.trimph.toprand.trimphrxandroid.trimph.ui.main.person.model.NewsModel;

import java.util.List;

/**
 * Created by tao on 2016/8/12.
 */

public class NewsCommenBean extends BaseBean implements Parcelable {
    public NewsBean result;

    public NewsBean getNewsBean() {
        return result;
    }

    public void setNewsBean(NewsBean result) {
        this.result = result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

}
