package com.trimph.toprand.trimphrxandroid.trimph.utils;

import android.util.Log;

import com.squareup.okhttp.Headers;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by tao on 2016/8/11.
 */

public class NetIntercepter implements Interceptor {

    public String TAG = NetIntercepter.class.getSimpleName();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Log.e(TAG, "------------chain----------");
        Request request = chain.request();
        Headers headers = request.headers();

        Log.e(TAG, "------------chain----------" + headers.name(0) + "   " );
        return chain.proceed(request);
    }
}
