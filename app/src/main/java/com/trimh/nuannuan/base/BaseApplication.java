package com.trimh.nuannuan.base;

import android.app.Application;

import com.trimh.nuannuan.exception.TrimphException;

/**
 * Project TrimphJuneFifty
 * Package_name com.trimh.nuannuan.base
 * Created by trimph on 2016/6/25.
 * Created by trimph  on 2016/6/25.
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        TrimphException trimphException = TrimphException.getInstance();
//        trimphException.init(getApplicationContext());
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }


}
