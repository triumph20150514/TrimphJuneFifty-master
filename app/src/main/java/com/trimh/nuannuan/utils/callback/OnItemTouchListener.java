package com.trimh.nuannuan.utils.callback;

import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

/**
 * 自定义 条目触摸事件
 * Created by tao on 2016/8/17.
 */

public class OnItemTouchListener implements RecyclerView.OnItemTouchListener {

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
