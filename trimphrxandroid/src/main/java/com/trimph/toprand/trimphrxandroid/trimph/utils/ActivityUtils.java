package com.trimph.toprand.trimphrxandroid.trimph.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by tao on 2016/8/10.
 */

public class ActivityUtils {

    public static String TAG = ActivityUtils.class.getSimpleName();

    public static void swichFragment(FragmentManager fragmentManager, int ResId, Fragment fragment) {
        checkNotNull(fragmentManager, "fragmentManager 不能为空");
        checkNotNull(ResId, "ResId 不能为空");
        checkNotNull(fragment, "fragment 不能为空");
        if (fragment.isDetached()) {
            Log.e(TAG, "已经添加进去");
        }
        FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
        fragmentTransaction1.replace(ResId, fragment, fragment.getClass().getSimpleName());
        fragmentTransaction1.commitAllowingStateLoss();
    }

}
