package com.trimh.trimphmvp.followAnim;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.os.Build;
import android.support.v4.BuildConfig;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.trimh.trimphmvp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tao on 2016/7/30.
 */

public class AnimatorLayout extends RelativeLayout {

    public int[] imageRes = new int[]{R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5, R.drawable.image6};
    public int[] imageBgRes = new int[]{R.color.float_background1, R.color.float_background2, R.color.float_background3,
            R.color.float_background4, R.color.float_background5, R.color.float_background6};
    List<AimatorImage> aimatorImages = new ArrayList<>();
    public GestureDetectorCompat gestureDetector;
    public Context context;
    public AnimatorContron animatorContron;

    public ViewDragHelper viewDragHelper;

    public AimatorImage topImage;
    public AimatorImage topFollowImage;

    public AnimatorLayout(Context context) {
        this(context, null);
    }

    public AnimatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        /***/
        viewDragHelper = ViewDragHelper.create(this, 10, new ViewDragHelperCallBack());
        viewDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_BOTTOM);

        gestureDetector = new GestureDetectorCompat(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return Math.abs(distanceY) + Math.abs(distanceX) > 5;
            }
        });

        animatorContron = AnimatorContron.create();
    }

    public AnimatorLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("Animator", "dispatchTouchEvent---------");

        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            viewDragHelper.abort();
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("Animator", "onInterceptTouchEvent--------");

        boolean isGesture = gestureDetector.onTouchEvent(ev);
        boolean isShould = viewDragHelper.shouldInterceptTouchEvent(ev);

        if (ev.getActionMasked() == MotionEvent.ACTION_DOWN) {
            viewDragHelper.processTouchEvent(ev);
        }
        return isGesture && isShould;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("Animator", "onTouchEvent-------");
        try {
            viewDragHelper.processTouchEvent(event);
        } catch (Exception e) {
            new Throwable("--------------出错了-----------------");
        }
        return true;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.e("Animator", "onLayout------" + topImage.getLeft() + " top" + topImage.getTop());
        animatorContron.settOriginPos(topImage.getLeft(), topImage.getTop());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("Animator", "onMeasure-----");
    }

    /***
     * 把试图添加
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.e("Animator", "onFinishInflate");
        for (int i = 0; i < imageRes.length; i++) {
            AimatorImage aimatorImage = new AimatorImage(context);
            aimatorImage.setImageResource(imageRes[i]);
            aimatorImage.setBackgroundTintList(getResources().getColorStateList(imageBgRes[i]));
            aimatorImages.add(aimatorImage);

            LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 0, 100, 100);
            layoutParams.addRule(ALIGN_PARENT_BOTTOM);
            layoutParams.addRule(ALIGN_PARENT_RIGHT);
            addView(aimatorImage, layoutParams);

            if (i == (imageRes.length - 1)) {
                topImage = aimatorImage;
            } else {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                    aimatorImage.setElevation(0f);
                }
            }
        }

        animatorContron.setAimatorImages(aimatorImages);
        animatorContron.init();
    }


    public class ViewDragHelperCallBack extends ViewDragHelper.Callback {

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
            animatorContron.onTopViewPosChanged(left, top);
        }

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            if (child == topImage) {
                topImage.reStop();
                return true;
            }
            return false;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return top;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return left;
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return 1;
        }

        @Override
        public int getViewVerticalDragRange(View child) {
            return 1;
        }

        @Override
        public void onEdgeTouched(int edgeFlags, int pointerId) {
            super.onEdgeTouched(edgeFlags, pointerId);
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            animatorContron.onRealse();
        }

        @Override
        public boolean onEdgeLock(int edgeFlags) {
            return super.onEdgeLock(edgeFlags);
        }

        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
            super.onEdgeDragStarted(edgeFlags, pointerId);
        }

        @Override
        public int getOrderedChildIndex(int index) {
            return super.getOrderedChildIndex(index);
        }
    }


}
