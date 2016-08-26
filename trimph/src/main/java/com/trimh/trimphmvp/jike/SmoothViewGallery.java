package com.trimh.trimphmvp.jike;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * Created by tao on 2016/8/24.
 */

public abstract class SmoothViewGallery extends ViewGroup {

    //滑动状态
    protected int swipe_state = 0;
    //停止状态
    protected int stop_state = 1;

    //当前状态
    public int state = stop_state;
    //视图宽高
    public int mWidth, mHeight;

    //距离顶部的距离
    public int maginTop;

    //重复次数
    public int repeatTime = 0;

    public SmoothViewGallery(Context context) {
        this(context, null);
    }

    public SmoothViewGallery(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        initView();
    }

    protected abstract void initView();

    public SmoothViewGallery(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.e("Trimph", "---------------onLayout:");

    }

    public void smoothStart() {
        if (state != stop_state) {
            return;
        }

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(-mHeight, 0);
        valueAnimator.setDuration(5000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int topValue = (int) animation.getAnimatedValue();
                Log.e("Trimph", "---------------topValue:" + topValue);
                maginTop = topValue;

                if (maginTop == 0) {//动画结束
                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            repeatTime++;
                            animFinish();
                            state = stop_state;
                        }
                    }, 50);
                } else {
                    //执行动画
                    startAnim();
                }
            }
        });
        //动画开始
        valueAnimator.start();
        state = swipe_state;
    }

    protected abstract void animFinish();


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.e("Trimph", "---------------------onFinishInflate");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e("Trimph", "---------------------onSizeChanged");
        mWidth = w;
        mHeight = h;
        maginTop = -h;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("Trimph", "---------------------onMeasure");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("Trimph", "---------------------onDraw");
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("Trimph", "---------------------dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("Trimph", "---------------------onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("Trimph", "---------------------onTouchEvent");
        return super.onTouchEvent(event);
    }

    public abstract void startAnim();


    public boolean isollCircle() {
        return repeatTime % 2 == 1;
    }
}
