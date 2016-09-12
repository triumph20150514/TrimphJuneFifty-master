package com.trimph.toprand.trimphrxandroid.trimph.ui.main.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by tao on 2016/8/12.
 */

public class NewsBean implements Parcelable {

    /**
     * stat : 1
     * data : [
     * "title":"对口援疆情暖巴州 河北与新疆人民永远心连心",
     * "date":"2016-08-12 13:13","category":"社会",
     * "author_name":"长城网",
     * "thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20160812/20160812131345_27e509b748b9121edaa6d2aa7fc6a0e5_1_mwpm_03200403.jpeg",
     * "url":"http://mini.eastday.com/mobile/160812131345851.html?qid=juheshuju",
     * "thumbnail_pic_s03":"http://09.imgmini.eastday.com/mobile/20160812/20160812131345_27e509b748b9121edaa6d2aa7fc6a0e5_1_mwpl_05500201.jpeg"}]
     */

    private String stat;
    /**
     * title : 对口援疆情暖巴州 河北与新疆人民永远心连心
     * date : 2016-08-12 13:13
     * category : 社会
     * author_name : 长城网
     * thumbnail_pic_s : http://09.imgmini.eastday.com/mobile/20160812/20160812131345_27e509b748b9121edaa6d2aa7fc6a0e5_1_mwpm_03200403.jpeg
     * url : http://mini.eastday.com/mobile/160812131345851.html?qid=juheshuju
     * thumbnail_pic_s03 : http://09.imgmini.eastday.com/mobile/20160812/20160812131345_27e509b748b9121edaa6d2aa7fc6a0e5_1_mwpl_05500201.jpeg
     */

    private List<DataBean> data;

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable{
        private String title;
        private String date;
        private String category;
        private String author_name;
        private String thumbnail_pic_s;
        private String thumbnail_pic_s02;
        private String url;
        private String thumbnail_pic_s03;

        public String getThumbnail_pic_s02() {
            return thumbnail_pic_s02;
        }

        public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
            this.thumbnail_pic_s02 = thumbnail_pic_s02;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getAuthor_name() {
            return author_name;
        }

        public void setAuthor_name(String author_name) {
            this.author_name = author_name;
        }

        public String getThumbnail_pic_s() {
            return thumbnail_pic_s;
        }

        public void setThumbnail_pic_s(String thumbnail_pic_s) {
            this.thumbnail_pic_s = thumbnail_pic_s;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getThumbnail_pic_s03() {
            return thumbnail_pic_s03;
        }

        public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
            this.thumbnail_pic_s03 = thumbnail_pic_s03;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.title);
            dest.writeString(this.date);
            dest.writeString(this.category);
            dest.writeString(this.author_name);
            dest.writeString(this.thumbnail_pic_s);
            dest.writeString(this.thumbnail_pic_s02);
            dest.writeString(this.url);
            dest.writeString(this.thumbnail_pic_s03);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.title = in.readString();
            this.date = in.readString();
            this.category = in.readString();
            this.author_name = in.readString();
            this.thumbnail_pic_s = in.readString();
            this.thumbnail_pic_s02 = in.readString();
            this.url = in.readString();
            this.thumbnail_pic_s03 = in.readString();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.stat);
        dest.writeTypedList(this.data);
    }

    public NewsBean() {
    }

    protected NewsBean(Parcel in) {
        this.stat = in.readString();
        this.data = in.createTypedArrayList(DataBean.CREATOR);
    }

    public static final Creator<NewsBean> CREATOR = new Creator<NewsBean>() {
        @Override
        public NewsBean createFromParcel(Parcel source) {
            return new NewsBean(source);
        }

        @Override
        public NewsBean[] newArray(int size) {
            return new NewsBean[size];
        }
    };
}
