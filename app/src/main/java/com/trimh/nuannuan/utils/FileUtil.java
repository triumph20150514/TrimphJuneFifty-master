package com.trimh.nuannuan.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * @descrition 错误报告存储路径
 * Project TrimphJuneFifty
 * Package_name com.trimh.nuannuan.utils
 * Created by trimph on 2016/6/26.
 * Created by trimph  on 2016/6/26.
 */
public class FileUtil {


    public FileUtil(Context context) {
        this.mContext = context;
    }

    public static FileUtil fileUtil;
    public Context mContext;

    public String crash = "Trimph.txt";

    public static FileUtil getInstance(Context context) {
        if (context != null) {
            fileUtil = new FileUtil(context);
        }
        return fileUtil;
    }

    public File getSavePath() {
        //sdcard 存在\
        String path = "";
        File file = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            path = Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            path = mContext.getCacheDir().getAbsolutePath();
            if (path == null) {
                path = mContext.getFilesDir().getAbsolutePath();
            }
        }

        if (path != null) {
            file = new File(path, crash);
        }
        Log.e("Environment", file.getAbsolutePath());
        return file;
    }


}
