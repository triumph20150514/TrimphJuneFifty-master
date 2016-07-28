package com.trimh.nuannuan.view.indicator;

import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPropertyAnimatorUpdateListener;

/**
 * @description: Project TrimphJuneFifty
 * Package_name com.trimh.nuannuan.view.indicator
 * Created by Trimph on 2016/7/16.
 */

public interface IPageListener extends ViewPager.OnPageChangeListener {

    public void setViewPager(ViewPager viewPager);

    public void setViewPager(ViewPager viewPager, int realCount);


}
