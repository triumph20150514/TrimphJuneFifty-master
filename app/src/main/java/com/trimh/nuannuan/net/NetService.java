package com.trimh.nuannuan.net;

import android.graphics.Picture;
import android.telecom.Call;

import com.trimh.nuannuan.bean.MoviceBean;
import com.trimh.nuannuan.bean.PictureBean;

import java.util.Observer;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.observers.Observers;

/**
 * @description:网络部分 Project TrimphJuneFifty
 * Package_name com.trimh.nuannuan.net
 * Created by trimph on 2016/6/29.
 * Created by trimph  on 2016/6/29.
 */
public interface NetService {

    @GET("top250")
    Observable<MoviceBean> getMovice(@Query("start") int start, @Query("count") int count);

    @GET("list")
    Observable<PictureBean> getPictureList(@Query("page") int page, @Query("rows") int rows);




}
