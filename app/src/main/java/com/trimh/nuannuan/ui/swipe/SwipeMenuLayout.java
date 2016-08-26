package com.trimh.nuannuan.ui.swipe;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * 条目布局
 * Created by tao on 2016/8/19.
 */

public class SwipeMenuLayout extends FrameLayout implements SwipeSwitch {

    public static final int DEFAULT_SCROLLER_DURATION = 300;

    private int mLeftViewId=0;
    private int mRightViewId=0;
    private int mContentViewId;

    private float openPercent=0.5f; //菜单所在比例
    private int mScrollerDuration=DEFAULT_SCROLLER_DURATION; //复位时间


    public SwipeMenuLayout(Context context) {
        this(context, null);
    }

    public SwipeMenuLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeMenuLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void closeLeftSwipeMenu() {

    }

    @Override
    public void closeRightSwipeMenu() {

    }

    @Override
    public void closeSwipeMenu() {

    }

    @Override
    public void closeSwipeMenuPosition(int position) {

    }

    @Override
    public boolean isOpenMenu() {
        return false;
    }

    @Override
    public boolean isOpenLeftMenu() {
        return false;
    }

    @Override
    public boolean isOpenRightMenu() {
        return false;
    }
}
