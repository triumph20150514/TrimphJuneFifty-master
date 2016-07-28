package com.trimh.nuannuan.exception;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.trimh.nuannuan.utils.FileUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.net.PortUnreachableException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 异常处理  嘎嘎嘎
 * Project TrimphJuneFifty
 * Package_name com.trimh.nuannuan.exception
 * Created by trimph on 2016/6/26.
 * Created by trimph  on 2016/6/26.
 */
public class TrimphException implements Thread.UncaughtExceptionHandler {

    public String TAG = TrimphException.class.getSimpleName();
    public Thread.UncaughtExceptionHandler mDefaultHandler;
    public static TrimphException trimphException = new TrimphException();


    public static TrimphException getInstance() {
        return trimphException;
    }

    public TrimphException() {
    }

    public Context mContext;

    public void init(Context context) {
        this.mContext = context;
        //获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }


    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        Log.e("ThreadId", thread.getId() + "");

//
//        try {
//            thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        //退出程序
//        android.os.Process.killProcess(android.os.Process.myPid());
//        System.exit(1);

//        ActivityManager am = (ActivityManager) mContext.getSystemService(mContext.ACTIVITY_SERVICE);
//        am.restartPackage("com.trimh.nuannuan");

        Intent k = mContext.getPackageManager()
                .getLaunchIntentForPackage("com.trimh.nuannuan");
        k.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mContext.startActivity(k);
    }

    /**
     * 收集设备参数信息
     *
     * @param ctx
     */
    public void collectDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "an error occured when collect package info", e);
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
                Log.d(TAG, field.getName() + " : " + field.get(null));
            } catch (Exception e) {
                Log.e(TAG, "an error occured when collect crash info", e);
            }
        }
    }


    public String savaCrashTofile(Throwable ex) {

        StringBuffer stringBuffer = new StringBuffer();

        for (Map.Entry<String, String> entry : infos.entrySet()) {
            stringBuffer.append(entry.getKey() + ":" + entry.getValue() + "\n");
        }

        Writer writer = new StringWriter();
        PrintWriter printW = new PrintWriter(writer);
        ex.printStackTrace(printW);

        Throwable cas = ex.getCause();
        while (cas != null) {
            cas.printStackTrace(printW);
            cas = cas.getCause();
        }

        printW.close();
        stringBuffer.append(writer.toString());

        File f = FileUtil.getInstance(mContext).getSavePath();

        try {
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(stringBuffer.toString().getBytes());
            fos.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    //用来存储设备信息和异常信息
    private Map<String, String> infos = new HashMap<String, String>();

    //用于格式化日期,作为日志文件名的一部分
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
}
