package com.trimph.toprand.trimphrxandroid.trimph.utils;

import android.util.Log;

import com.squareup.okhttp.Headers;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

import okio.BufferedSink;
import retrofit.http.Header;

/**
 * Created by tao on 2016/8/11.
 */

public class LogIntercepter implements Interceptor {

    public String TAG = LogIntercepter.class.getSimpleName();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Log.e(TAG, "------------chain----------");
        Request request = chain.request();

        long t1 = System.nanoTime();
        Log.e("trimph loggInterceptor" , String.format("发送请求: [%s] %s%n%s",
                request.url(),  request.method(), chain.connection(), request.headers()));

        Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        Log.e("trimph loggInterceptor" , String.format("接收响应: [%s] %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        return chain.proceed(request);
    }
}
