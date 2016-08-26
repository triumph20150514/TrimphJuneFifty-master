package com.trimh.nuannuan.ui.swipe.direction;

import android.view.View;

/**
 * 水平 滑动 功能
 * Created by tao on 2016/8/19.
 */

public abstract class SwipeHorizontal {
    public int direction;//方向
    public View menuView; //菜单
    public Checker checker;

    public SwipeHorizontal(int direction, View menuView) {
        this.direction = direction;
        this.menuView = menuView;
        checker = new Checker();
    }




    /**
     *
     */
    public class Checker {
        public int x;
        public int y;
        boolean isResetSwipe;
    }

}
