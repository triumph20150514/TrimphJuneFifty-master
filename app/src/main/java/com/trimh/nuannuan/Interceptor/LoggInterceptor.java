package com.trimh.nuannuan.Interceptor;

import com.trimh.nuannuan.utils.LogUtlis;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by tao on 2016/8/17.
 */

public class LoggInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        long t1 = System.nanoTime();
        LogUtlis.e("trimph loggInterceptor" + String.format("发送请求: [%s] %s%n%s",
                request.url(),  request.method(), chain.connection(), request.headers()));

        Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        LogUtlis.e("trimph loggInterceptor" + String.format("接收响应: [%s] %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        return response;
    }
}
