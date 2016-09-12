package com.trimph.toprand.trimphrxandroid.trimph.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by tao on 2016/8/27.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LayoutResId());
        ButterKnife.bind(this);
        init();
    }

    protected abstract void init();

    protected abstract int LayoutResId();
}
