package com.trimh.nuannuan.ui.swipe;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * 自定义 菜单项
 * 包括多个 MenuItem
 * Created by tao on 2016/8/19.
 */

public class SwipeMenuView extends LinearLayout {

    private SwipeSwitch swipeSwitch;

    public SwipeMenuView(Context context) {
        this(context, null);
    }

    public SwipeMenuView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }
}
