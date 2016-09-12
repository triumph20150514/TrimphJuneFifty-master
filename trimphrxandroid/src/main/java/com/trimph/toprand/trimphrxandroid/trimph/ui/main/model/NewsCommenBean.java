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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.result, flags);
    }

    public NewsCommenBean() {
    }

    protected NewsCommenBean(Parcel in) {
        super(in);
        this.result = in.readParcelable(NewsBean.class.getClassLoader());
    }

    public static final Creator<NewsCommenBean> CREATOR = new Creator<NewsCommenBean>() {
        @Override
        public NewsCommenBean createFromParcel(Parcel source) {
            return new NewsCommenBean(source);
        }

        @Override
        public NewsCommenBean[] newArray(int size) {
            return new NewsCommenBean[size];
        }
    };
}
