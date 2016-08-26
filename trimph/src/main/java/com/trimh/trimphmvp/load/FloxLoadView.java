package com.trimh.trimphmvp.load;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.trimh.trimphmvp.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by tao on 2016/8/25.
 */

public class FloxLoadView extends View {
    public Context context;
    public Paint paint;

    public int mDegressCircle = 60;

    public int mLineMinLength = dp2px(getContext(), 40);

    public int mLineMaxLength = dp2px(getContext(), 100);

    public int mLineLength = mLineMinLength; //线长度

    public int mCircleRaduis; // 半径
    public int mHeight, mWidth;

    public int degress;

    public float mAllLength;

    public int[] colors = new int[]{R.color.colorAccent, R.color.colorPrimaryDark, R.color.nodeTextColor, R.color.color3};

    public FloxLoadView(Context context) {
        this(context, null);
    }

    public FloxLoadView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FloxLoadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initData();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mHeight = h;
        mWidth = w;
        initData();
    }

    public int step = 0; //步骤

    @Override
    protected void onDraw(Canvas canvas) {

        switch (step) {
            case 0:
                //绘制矩形
                for (int i = 0; i < colors.length; i++) {
                    paint.setColor(colors[i]);
                    Log.e("trimph", "----------------------------" + (mHeight / 2 - mAllLength));
                    DrawCCLC(canvas, mWidth / 2 - mLineLength / 2,
                            (mHeight / 2 - mAllLength),
                            mWidth / 2 - mLineLength / 2,
                            mHeight / 2 + mLineLength, paint, degress + i * 90);
                }
                break;
            case 1:

                break;
            case 2:

                break;
            case 3:
                break;
        }
    }

    public void startAnim() {
        startCCLC();
    }

    /**
     * 画布线条开始变化
     */
    public void startCCLC() {

        Collection<Animator> animators = new ArrayList<>();

        ValueAnimator valueAnimator = ValueAnimator.ofInt(mDegressCircle + 0, mDegressCircle + 360);
        valueAnimator.setInterpolator(new LinearInterpolator());

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                degress = (int) animation.getAnimatedValue();
            }
        });

        animators.add(valueAnimator);
        ValueAnimator lineLength = ValueAnimator.ofFloat(mLineLength, -mLineLength);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAllLength = (float) animation.getAnimatedValue();
                Log.e("trimph", "----------------------------mAllLength" + animation.getAnimatedValue());
                invalidate();
            }
        });

        animators.add(lineLength);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.playTogether(animators);
        animatorSet.setDuration(6000);
        animatorSet.addListener(new CustomAnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                step++;
                //第一次动画结束   开始第二次动画
            }
        });

        animatorSet.start();
    }

    public void DrawCCLC(Canvas canvas, float startX, float startY, float stopX, float stopY, Paint paint, int degress) {
        canvas.rotate(degress, mWidth / 2, mHeight / 2); //先旋转 在绘图
        canvas.drawLine(startX, startY, stopX, stopY, paint);
        canvas.rotate(-degress, mWidth / 2, mHeight / 2); //旋转回去
    }

    private void initData() {
        degress = mDegressCircle;
        paint = new Paint();

        mAllLength = mLineLength;

        mCircleRaduis = mLineLength / 5;

        paint.setAntiAlias(true);
        paint.setStrokeWidth(mCircleRaduis * 2);

    }

    private int dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
