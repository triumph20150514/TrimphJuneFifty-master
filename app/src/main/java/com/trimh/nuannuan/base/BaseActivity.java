package com.trimh.nuannuan.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.Toast;

import com.trimh.nuannuan.view.LoadDialog;

/**
 * @description: Project TrimphJuneFifty
 * Package_name com.trimh.nuannuan.base
 * Created by Trimph on 2016/7/2.
 */
public abstract class BaseActivity extends AppCompatActivity {

    public LoadDialog loadDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutResID());
        loadDialog = new LoadDialog(this);
        init();
    }

    public void init() {
        initView();
        initData();
        initListener();
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void initListener();

    protected abstract int layoutResID();


    public void showToast(String message) {
        if (!TextUtils.isEmpty(message))
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    /**
     * 长按退出
     */
    public void exitApp() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            showToast("长按退出");
        } else if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() >= 2) {
            System.exit(0);
        }
        return false;
    }
}
