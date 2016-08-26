package com.trimh.nuannuan.utils.copy.callback;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.trimh.nuannuan.utils.pinned.entity.ClickBounds;

/**
 * 自定义 条目触摸事件
 * Created by tao on 2016/8/17.
 */

public class OnItemTouchListener<T> implements RecyclerView.OnItemTouchListener {


    public SparseArray<ClickBounds> sparseArray;

    public GestureDetector gestureDetector;

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

    /**
     * id 的点击范围
     *
     * @param id
     * @param clickBounds
     */
    public void setViewAndBound(int id, ClickBounds clickBounds) {
        sparseArray.put(id, clickBounds);
    }


    public class MyGestureDetector implements GestureDetector.OnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }
    }

}
