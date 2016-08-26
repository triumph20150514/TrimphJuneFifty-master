package com.trimh.nuannuan.ui.swipe;

/**
 * 关闭菜单选项
 * Created by tao on 2016/8/19.
 */

public interface Closeable {

    public void closeLeftSwipeMenu();

    public void closeRightSwipeMenu();

    void closeSwipeMenu();

    void closeSwipeMenuPosition(int position);
}
