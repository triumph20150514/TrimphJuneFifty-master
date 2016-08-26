package com.trimh.nuannuan.ui.swipe;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * 他妹怎么写出来的 ,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
 * ,,,,,,,,,,,,,,,,,,,,
 * Created by tao on 2016/8/22.
 */

public class SwipeRecycleView extends RecyclerView {

    public static int LEFT_DICATION = 1;//左边
    public static int RIGHT_DICATION = -1;//右边



    public SwipeRecycleView(Context context) {
        super(context);
    }

    public SwipeRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public SwipeRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
