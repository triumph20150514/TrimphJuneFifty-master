package com.trimh.nuannuan.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.trimh.nuannuan.R;

/**
 * Created by tao on 2016/8/1.
 */

public class DividerItemDecorationTrimph extends RecyclerView.ItemDecoration {

    //i切割线的高度
    public int DividerHight;
    //切割线的左右边距
    public int DividerMargin;

    public int getDividerMargin() {
        return DividerMargin;
    }

    public void setDividerMargin(int dividerMargin) {
        DividerMargin = dividerMargin;
    }

    public int getDividerHight() {
        return DividerHight;
    }

    public void setDividerHight(int dividerHight) {
        DividerHight = dividerHight;
    }

    public Paint paint;

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (DividerHight <= 0) {
            DividerHight = 1;
        }
        int count = parent.getChildCount();
        int left = DividerMargin;
        int right = parent.getMeasuredWidth() - DividerMargin;
        for (int i = 0; i < count; i++) {
            View view = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            int top = view.getBottom() + layoutParams.bottomMargin;
            int bottom = top + DividerHight;
            c.drawRect(left, top, right, bottom, paint);
        }
    }


    public DividerItemDecorationTrimph(Context context, int dividerHight) {
        super();
        if (dividerHight <= 0) {
            DividerHight = 1;
        } else {
            this.DividerHight = dividerHight;
        }

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor(String.valueOf(R.color.nodeColor)));

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(0, 0, 0, DividerHight);
    }
}
