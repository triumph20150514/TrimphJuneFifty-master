package com.trimph.toprand.trimphrxandroid.trimph.Iservice;


import com.trimph.toprand.trimphrxandroid.trimph.ui.main.model.NewsCommenBean;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;
import rx.Observable;

/**
 * 数据接口类
 * Created by tao on 2016/8/4.
 */

public interface PictureApiTrimph {

    @GET("list")
    Observable<PictureBean> getPictureList(@Query("page") int page, @Query("rows") int rows);

    @POST("/allvideo")
    Observable<PictureBean> getItem();

    @POST
    Observable<PictureBean> getItems();


    @GET("index")
    Observable<NewsCommenBean> getNews(@Query("key") String key, @Query("type") String type);



}
