package com.trimph.toprand.trimphrxandroid.trimph.ui.main.person.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tao on 2016/8/11.
 */

public class NewsModel implements Parcelable{

    private int id;
    private String name;
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.title);
    }

    public NewsModel() {
    }

    protected NewsModel(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.title = in.readString();
    }

    public static final Creator<NewsModel> CREATOR = new Creator<NewsModel>() {
        @Override
        public NewsModel createFromParcel(Parcel source) {
            return new NewsModel(source);
        }

        @Override
        public NewsModel[] newArray(int size) {
            return new NewsModel[size];
        }
    };
}
