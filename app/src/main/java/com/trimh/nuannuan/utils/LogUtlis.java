package com.trimh.nuannuan.utils;

import android.nfc.tech.IsoDep;
import android.util.Log;

/**
 * @description: Project TrimphJuneFifty
 * Package_name com.trimh.nuannuan.utils
 * Created by Trimph on 2016/7/18.
 */

public class LogUtlis {

    public static String TAG = LogUtlis.class.getSimpleName();

    public static boolean isDebug = true;

    private LogUtlis() {

    }

    public static void e(String string) {
        if (isDebug) {
            Log.e(TAG, string);
        }
    }


}
