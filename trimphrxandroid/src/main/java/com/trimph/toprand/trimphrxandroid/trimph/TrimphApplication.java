package com.trimph.toprand.trimphrxandroid.trimph;

import android.app.Application;
import android.util.Log;

import com.trimph.toprand.trimphrxandroid.trimph.component.ComponentTrimph;
import com.trimph.toprand.trimphrxandroid.trimph.component.PictureComponentTrimph;


/**
 * application 初始化需要用到application 的地方 嘎嘎
 * Created by tao on 2016/8/4.
 */

public class TrimphApplication extends Application {

    public static ComponentTrimph componentTrimph;

    public static TrimphApplication trimphApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        ///初始化入戏 哈哈哈 ran后就可以使用了
        trimphApplication=this;
        initComponentTrimph();
    }

    public static void initComponentTrimph() {
        componentTrimph = PictureComponentTrimph.Instantis.getConponent(trimphApplication);
        if (componentTrimph == null) {
            Log.e("Trimph", "componentTrimph 容器为空");
        }
    }

    public static ComponentTrimph component() {
        if (componentTrimph == null) {
            Log.e("Trimph", "容器为空");
        }
        return componentTrimph;
    }

}
