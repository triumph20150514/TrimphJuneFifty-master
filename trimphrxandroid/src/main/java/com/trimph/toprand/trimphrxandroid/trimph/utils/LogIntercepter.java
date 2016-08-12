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
        Headers headers = request.headers();

        Log.e(TAG, "------------chain----------" + headers.name(0) + "   " );
        return chain.proceed(request);
    }
}
