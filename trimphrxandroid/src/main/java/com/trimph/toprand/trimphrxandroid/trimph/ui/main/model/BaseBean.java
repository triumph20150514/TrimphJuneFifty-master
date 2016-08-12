package com.trimph.toprand.trimphrxandroid.trimph.ui.main.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tao on 2016/8/12.
 */

public class BaseBean implements Parcelable{
    public String reason; // 提示
    public int error_code; //错误码


    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.reason);
        dest.writeInt(this.error_code);
    }

    public BaseBean() {
    }

    protected BaseBean(Parcel in) {
        this.reason = in.readString();
        this.error_code = in.readInt();
    }

}
