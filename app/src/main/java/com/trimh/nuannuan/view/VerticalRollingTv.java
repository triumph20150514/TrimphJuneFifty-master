package com.trimh.nuannuan.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.trimh.nuannuan.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * @description 垂直方向文字滚动
 * Project TrimphJuneFifty
 * Package_name com.trimh.nuannuan.view
 * Created by trimph on 2016/6/26.
 * Created by trimph on 2016/6/26.
 */
public class VerticalRollingTv extends View {

    List<String> strings = new ArrayList<>();

    Paint mPaint = new Paint();

    DataSetAdapter mDataSetAdapter;

    private int mCurrentIndex;
    private int mNextIndex;

    Rect bounds = new Rect();

    private float mCurrentOffsetY;

    private float mOrgOffsetY = -1;

    private float mOffset;

    public int lastTonext = 3000;
    public int duration = 1000;
    public InternalAnimation internalAnimation = new InternalAnimation();

    public VerticalRollingTv(Context context) {
        super(context);
        init();
    }

    private float mTextTopToAscentOffset;


    private void init() {
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(40);

        Paint.FontMetricsInt metricsInt = mPaint.getFontMetricsInt();

        mTextTopToAscentOffset = metricsInt.ascent - metricsInt.top;

        Log.e("mOragOffsetY ascent", metricsInt.ascent + "::top" + metricsInt.top + "mOffset");

        internalAnimation.setDuration(duration);
    }

    public VerticalRollingTv(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Boolean isRuning = false;

    public void run() {
        if (isRuning) {
            return;
        }
        isRuning = true;

        internalAnimation.updateValue(mCurrentOffsetY, -2 * mTextTopToAscentOffset);

        post(runnable);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            mAnimationEnded = false;
            invalidate();
//            startAnimation(internalAnimation);
            postDelayed(this, duration);
        }
    };


    public VerticalRollingTv(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    public boolean mAnimationEnded;

    public void animationEnd() {
        //1.角标+1
        mCurrentIndex++;
        //2.计算出正确的角标
        mCurrentIndex = mCurrentIndex < mDataSetAdapter.getItemCount() ? mCurrentIndex : mCurrentIndex % mDataSetAdapter.getItemCount();
        //3.计算下一个待显示文字角标
        confirmNextIndex();
        //3.位置复位
        mCurrentOffsetY = mOrgOffsetY;
        mAnimationEnded = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(width / 2, hight / 2);
        canvas.drawPoint(width / 2, hight / 2, mPaint);


        String text1 = mDataSetAdapter.getText(mCurrentIndex);
        String text2 = mDataSetAdapter.getText(mNextIndex);

        Log.e("mOragOffsetY ascent text1", text1 + "::top" + text2 + "mOffset");

        mPaint.setTextAlign(Paint.Align.CENTER);

        int wid = (int) mPaint.measureText(text1);
        int wid2 = (int) mPaint.measureText(text2);
        //
        if (mOrgOffsetY == -1) {
            mPaint.getTextBounds(text1, 0, text1.length(), bounds);
            mOffset = (bounds.height()) * 1.2f;

            Log.e("mOragOffsetY ascent mOffset", mOffset + "::top" + getHeight() + "mOffset" + bounds.height());

            mOrgOffsetY = mCurrentOffsetY = mOffset - mTextTopToAscentOffset;

            internalAnimation.updateValue(mOrgOffsetY, -2 * mTextTopToAscentOffset);

        }

        canvas.drawText(text1, -wid / 2, hight / 2, mPaint);

        canvas.drawText(text2, -wid2 / 2, hight / 2 + mOffset + mTextTopToAscentOffset, mPaint);
    }

    public void setDataSetAdapter(DataSetAdapter dataSetAdapter) {
        mDataSetAdapter = dataSetAdapter;
        confirmNextIndex();
        invalidate();
    }

    /**
     * 计算第二个角标
     */
    private void confirmNextIndex() {
        //3.计算第二个角标
        mNextIndex = mCurrentIndex + 1;
        //4.计算出正确的第二个角标
        mNextIndex = mNextIndex < mDataSetAdapter.getItemCount() ? mNextIndex : 0;
    }

    public int width, hight;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);


        width = w - getPaddingLeft() - getPaddingRight();
        hight = h - getPaddingBottom() - getPaddingTop();


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

    }

    public class InternalAnimation extends Animation {

        public int startValue, endValue;

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            if (mAnimationEnded) return;

            mCurrentOffsetY = evaluate(interpolatedTime, startValue, endValue);
            Log.e("mOragOffsetY mCurrentOffsetY", mCurrentOffsetY + "");
            if (mCurrentOffsetY == endValue) {
                animationEnd();

            }
            postInvalidate();
        }


        /*从 0--1 执行*/
        private float evaluate(float interpolatedTime, int startValue, int endValue) {
            return startValue + interpolatedTime * (endValue - startValue);
        }

        public void updateValue(float startValue, float endValue) {
            this.startValue = (int) startValue;
            this.endValue = (int) endValue;

        }

    }
}
