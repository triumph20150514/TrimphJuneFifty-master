package com.trimh.nuannuan.ui.swipe;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * 菜单类
 *  可能有多个 菜单   如 ： 删除 置顶
 * Created by tao on 2016/8/19.
 */

public class SwipeMenuItem {

    public Context context;
    public int colors;
    public String tv;
    public int selector;
    public Drawable drawable;
    public int width;
    public int height;

    public int getSelector() {
        return selector;
    }

    public void setSelector(int selector) {
        this.selector = selector;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public String getTv() {
        return tv;
    }

    public void setTv(String tv) {
        this.tv = tv;
    }

    public int getColors() {
        return colors;
    }

    public void setColors(int colors) {
        this.colors = colors;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
