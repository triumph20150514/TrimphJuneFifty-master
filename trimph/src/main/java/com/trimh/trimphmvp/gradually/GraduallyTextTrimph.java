package com.trimh.trimphmvp.gradually;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;

/**
 * Created by tao on 2016/8/3.
 */

public class GraduallyTextTrimph extends EditText {

    public int duration = 1000;
    public Paint paint;
    public Context mContext;
    public String text;
    public int textLength;

    public int prograss;
    public ValueAnimator valueAnimator;

    public GraduallyTextTrimph(Context context) {
        this(context, null);
    }

    public GraduallyTextTrimph(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    public boolean isRunning = false;
    public boolean isStop = true;

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(60f);

        setEnabled(false);
        setFocusable(false);
        setBackground(null);
        setCursorVisible(false);
        setClickable(false);


        //动画
        valueAnimator = ValueAnimator.ofFloat(0, 100);
        valueAnimator.setDuration(duration);
        valueAnimator.setRepeatCount(Animation.INFINITE);
        valueAnimator.setRepeatMode(Animation.RESTART);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                prograss = (int) animation.getAnimatedValue();
                //重绘
                GraduallyTextTrimph.this.invalidate();
            }
        });
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);

        if (visibility == View.VISIBLE) {
            valueAnimator.resume();
        } else {
            valueAnimator.pause();
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        ///绘制文字 最终     tongguogaibian tongmingdu gaibian // TODO: 2016/8/3
        if (!isStop) {
            paint.setAlpha(255);
            if (prograss / singerTextLength >= 1) {
                canvas.drawText(String.valueOf(text), 0,
                        (int) (prograss / singerTextLength), ScaleX, startY,
                        paint);
            }
            paint.setAlpha(
                    (int) (255 * ((prograss % singerTextLength) / singerTextLength)));
            int lastOne = (int) (prograss / singerTextLength);
            if (lastOne < textLength) {
                canvas.drawText(String.valueOf(text.charAt(lastOne)), 0, 1,
                        ScaleX + getPaint().measureText(
                                text.subSequence(0, lastOne).toString()),
                        startY, paint);
            }
        }


    }


    public void onStartAnimation() {

        if (!isStop) {
            return;
        }
        text = getText().toString();

        textLength = text.length();

        isStop = false;
        setMinWidth(getWidth());
        ScaleX = (int) (getTextScaleX() * 10);
        startY = 70; //ding zhi
        valueAnimator.start();
        singerTextLength = 100 / textLength;
    }

    public int ScaleX;
    public int startY;
    public int singerTextLength;

    public GraduallyTextTrimph(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
}
