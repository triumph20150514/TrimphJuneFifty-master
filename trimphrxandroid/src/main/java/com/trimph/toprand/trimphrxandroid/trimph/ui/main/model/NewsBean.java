package com.trimph.toprand.trimphrxandroid.trimph.ui.main.model;

import java.util.List;

/**
 * Created by tao on 2016/8/12.
 */

public class NewsBean {

    /**
     * stat : 1
     * data : [{"title":"对口援疆情暖巴州 河北与新疆人民永远心连心",
     * "date":"2016-08-12 13:13","category":"社会",
     * "author_name":"长城网","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20160812/20160812131345_27e509b748b9121edaa6d2aa7fc6a0e5_1_mwpm_03200403.jpeg","url":"http://mini.eastday.com/mobile/160812131345851.html?qid=juheshuju",
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

    public static class DataBean {
        private String title;
        private String date;
        private String category;
        private String author_name;
        private String thumbnail_pic_s;
        private String url;
        private String thumbnail_pic_s03;

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
    }
}
