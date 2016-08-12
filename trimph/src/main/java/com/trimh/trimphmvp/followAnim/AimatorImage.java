package com.trimh.trimphmvp.followAnim;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringSystem;

/**
 * Created by tao on 2016/7/30.
 */

public class AimatorImage extends FloatingActionButton {

    Spring springX, springY;
    SimpleSpringListener followSpringX, followSpringY;

    public AimatorImage(Context context) {
        this(context, null);
    }

    public AimatorImage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AimatorImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        SpringSystem springSystem = SpringSystem.create();

        springY = springSystem.createSpring();
        springX = springSystem.createSpring();
        springX.addListener(new SimpleSpringListener() {
            @Override
            public void onSpringUpdate(Spring spring) {
                super.onSpringUpdate(spring);
                int value = (int) spring.getCurrentValue();
                setSceenX(value);
            }
        });
        springY.addListener(new SimpleSpringListener() {
            @Override
            public void onSpringUpdate(Spring spring) {
                super.onSpringUpdate(spring);
                int value = (int) spring.getCurrentValue();
                setSceenY(value);
            }
        });

        followSpringX = new SimpleSpringListener() {
            @Override
            public void onSpringUpdate(Spring spring) {
                super.onSpringUpdate(spring);
                int value = (int) spring.getCurrentValue();
                springX.setEndValue(value);
            }
        };

        followSpringY = new SimpleSpringListener() {
            @Override
            public void onSpringUpdate(Spring spring) {
                super.onSpringUpdate(spring);
                int value = (int) spring.getCurrentValue();
                springY.setEndValue(value);
            }
        };

    }

    public void animTo(float scrollX, float scrollY) {
        springX.setEndValue(scrollX);
        springY.setEndValue(scrollY);
    }

    /**
     * 只有顶部的可以跳跃 哈哈
     */
    public void reSet(float scrollX, float scrollY) {
        setCurrentSpringPos(getLeft(), getTop());
        animTo(scrollX, scrollY);
    }

    public void setCurrentSpringPos(int left, int top) {
        springX.setCurrentValue(left);
        springY.setCurrentValue(top);
    }

    /**
     * 停止
     */
    public void reStop() {
        springX.setAtRest();
        springY.setAtRest();
    }

    public Spring getSpringX() {
        return springX;
    }

    public void setSpringX(Spring springX) {
        this.springX = springX;
    }

    public Spring getSpringY() {
        return springY;
    }

    public void setSpringY(Spring springY) {
        this.springY = springY;
    }

    public SimpleSpringListener getFollowSpringX() {
        return followSpringX;
    }

    public void setFollowSpringX(SimpleSpringListener followSpringX) {
        this.followSpringX = followSpringX;
    }

    public SimpleSpringListener getFollowSpringY() {
        return followSpringY;
    }

    public void setFollowSpringY(SimpleSpringListener followSpringY) {
        this.followSpringY = followSpringY;
    }

    private void setSceenX(int value) {
        this.offsetLeftAndRight(value - getLeft());
    }


    private void setSceenY(int value) {
        this.offsetTopAndBottom(value - getTop());
    }
}
