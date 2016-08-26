package com.trimh.trimphmvp.jike;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tao on 2016/8/24.
 */

public class TitleView extends SmoothViewGallery {

    public List<String> arrayList = new ArrayList<>();
    public TextView[] textViews = new TextView[2];
    public int textLineHeight;

    public TitleView(Context context) {
        this(context, null);
    }

    public TitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();

        int ml = 0, mt = 0, mr, mb;

        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);

            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) childView.getLayoutParams();

            if (isollCircle()) {
                if (i == 0) {
                    ml = marginLayoutParams.leftMargin;
                    mt = mHeight / 2 - textLineHeight + mHeight + maginTop;
                } else if (i == 1) {
                    ml = marginLayoutParams.leftMargin;
                    mt = mHeight / 2 - textLineHeight + maginTop;
                }
            } else {
                if (i == 1) {
                    ml = marginLayoutParams.leftMargin;
                    mt = mHeight / 2 - textLineHeight + maginTop;
                } else if (i == 0) {
                    ml = marginLayoutParams.leftMargin;
                    mt = mHeight / 2 - textLineHeight + mHeight + maginTop;
                }
            }
            mr = ml + mWidth;
            mb = mt + mHeight;
            childView.layout(ml, mt, mr, mb);
        }


    }

    @Override
    protected void initView() {

        if (arrayList.size() == 0) {
            return;
        }
        removeAllViews();

        MarginLayoutParams marginLayoutParams = new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        for (int i = 0; i < textViews.length; i++) {
            String text = getTextValue(i);
            textViews[i] = new TextView(getContext());
            textViews[i].setTextSize(20f);
            textViews[i].setTextSize(20f);
            textViews[i].setLines(2);
            textViews[i].setEllipsize(TextUtils.TruncateAt.END);
            addViewInLayout(textViews[i], -1, marginLayoutParams, false);
        }

    }

    public String getTextValue(int position) {
        int pt = position % arrayList.size();
        return arrayList.get(pt);
    }

    @Override
    protected void animFinish() {
        if (isollCircle()) {
            textViews[0].setAlpha(Math.abs(maginTop) / mHeight);
        }
    }

    @Override
    public void startAnim() {
        if (isollCircle()) {
            textViews[0].setText("hello textViews");
        } else {
            textViews[1].setText("miss");
        }
    }
}
