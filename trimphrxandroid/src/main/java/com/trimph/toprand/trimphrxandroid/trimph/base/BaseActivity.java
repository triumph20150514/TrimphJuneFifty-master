package com.trimph.toprand.trimphrxandroid.trimph.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import com.jaeger.library.StatusBarUtil;
import com.trimph.toprand.trimphrxandroid.R;

import butterknife.ButterKnife;

/**
 * Created by tao on 2016/8/27.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LayoutResId());
        setStatusBarColor();
        ButterKnife.bind(this);
        init();
    }

    public void setStatusBarColor() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.nodeColor));
    }

    protected abstract void init();

    protected abstract int LayoutResId();
}
