package com.trimph.toprand.trimphrxandroid.trimph.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by tao on 2016/8/26.
 */

public abstract class BaseFragment extends Fragment {
    protected Context context;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(layoutResId(), container, false);
        ButterKnife.bind(this, view);
        context=getContext();
        init();
        return view;
    }

    protected abstract int layoutResId();

    protected abstract void init();
}
