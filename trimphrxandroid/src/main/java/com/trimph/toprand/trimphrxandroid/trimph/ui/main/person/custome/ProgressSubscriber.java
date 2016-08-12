package com.trimph.toprand.trimphrxandroid.trimph.ui.main.person.custome;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import com.trimph.toprand.trimphrxandroid.trimph.ui.main.impl.ResultNet;

import java.lang.ref.WeakReference;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * Created by tao on 2016/8/11.
 */

public class ProgressSubscriber<T> extends Subscriber<T> {

    public ResultNet<T> resultNet;
    public WeakReference<Context> weakReference;

    public boolean cancel;

    public ProgressDialog progressDialog;

    public ProgressSubscriber(ResultNet<T> resultNet, Context context) {
        weakReference = new WeakReference<Context>(context);
        this.resultNet = resultNet;
        initDialog();
    }

    private void initDialog() {
        progressDialog = new ProgressDialog(weakReference.get());
        progressDialog.setMessage("正在加载中。。。");
        progressDialog.setCancelable(cancel);
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (cancel) {
                    progressDialog.dismiss();
                }
            }
        });
    }

    public ProgressSubscriber(ResultNet<T> resultNet, Context context, boolean cencle) {
        weakReference = new WeakReference<Context>(context);
        this.resultNet = resultNet;
        this.cancel = cencle;
        initDialog();
    }

    public void showDialog() {
        if (progressDialog != null) {
            if (!progressDialog.isShowing()) {
                progressDialog.show();
            }
        }
    }

    public void dismissDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onStart() {
        showDialog();
    }

    @Override
    public void onCompleted() {
        dismissDialog();
    }

    @Override
    public void onError(Throwable e) {
        dismissDialog();
        if (e instanceof SocketTimeoutException) {
            Toast.makeText(weakReference.get(), "网络异常", Toast.LENGTH_LONG).show();
        } else if (e instanceof ConnectException) {
            Toast.makeText(weakReference.get(), "网络异常", Toast.LENGTH_LONG).show();
        }
        resultNet.error(e.getMessage());
    }

    @Override
    public void onNext(T t) {
        dismissDialog();
        resultNet.success(t);
    }
}
