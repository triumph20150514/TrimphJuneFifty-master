package com.trimh.nuannuan.ui.swipe;

/**
 * 菜单是否打开
 * Created by tao on 2016/8/19.
 */

public interface Openable {

    /**
     * 菜单是否开放
     *
     * @return
     */
    public boolean isOpenMenu();

    /**
     * 左边的是否打开
     *
     * @return
     */
    boolean isOpenLeftMenu();


    /**
     * 右边是否打开
     * @return
     */
    boolean isOpenRightMenu();




}
