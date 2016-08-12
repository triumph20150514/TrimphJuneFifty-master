package com.trimph.toprand.trimphrxandroid.trimph.ui.main.person.http;


import com.trimph.toprand.trimphrxandroid.trimph.ui.main.person.model.BaseModel;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.person.model.NewsModel;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;
import rx.Observable;

/**
 * service统一接口数据
 * Created by WZG on 2016/7/16.
 */
public interface HttpService {

    @retrofit.http.POST("AppFiftyToneGraph/videoLink")
    Call<BaseModel> getAllVedio(@retrofit.http.Body boolean once_no);

    @POST("AppFiftyToneGraph/videoLink")
    Observable<BaseModel> getAllVedioBy(@Body boolean once_no);

    @POST("AppFiftyToneGraph/videoLink")
    Observable<BaseModel<List<NewsModel>>> getAllVedioBys(@Body boolean once_no);

}
