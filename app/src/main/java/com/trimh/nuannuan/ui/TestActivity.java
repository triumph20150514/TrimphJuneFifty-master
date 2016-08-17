package com.trimh.nuannuan.ui;

import com.trimh.nuannuan.base.BaseActivity;

import java.util.Random;
import java.util.UUID;

import okhttp3.internal.Util;

/**
 * Created by tao on 2016/8/16.
 */

public class TestActivity extends BaseActivity {
    String[] testData = new String[]{"0%", "2%", "2%", "0%", "1%", "0%"};

    @Override
    protected void initData() {
        Random random = new Random();
        random.nextInt(testData.length);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int layoutResID() {
        return 0;
    }
}
