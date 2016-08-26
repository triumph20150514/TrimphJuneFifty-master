package com.trimh.nuannuan.ui.swipe;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单 装载所有的SwipeMenuItem
 * Created by tao on 2016/8/19.
 */

public class SwipeMenu {

    private List<SwipeMenuItem> swipeMenuItems = new ArrayList<>();

    private SwipeMenuLayout swipeMenuLayout;

    private int viewTyep;

    public SwipeMenu(SwipeMenuLayout swipeMenuLayout, int viewTyep) {
        this.swipeMenuLayout = swipeMenuLayout;
        this.viewTyep = viewTyep;
    }
}
