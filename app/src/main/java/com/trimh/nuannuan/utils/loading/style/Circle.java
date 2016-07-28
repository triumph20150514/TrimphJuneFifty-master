package com.trimh.nuannuan.utils.loading.style;

import android.animation.ValueAnimator;

import com.trimh.nuannuan.utils.loading.animation.SpriteAnimatorBuilder;
import com.trimh.nuannuan.utils.loading.sprite.CircleContainer;
import com.trimh.nuannuan.utils.loading.sprite.CircleSprite;
import com.trimh.nuannuan.utils.loading.sprite.Sprite;


/**
 * Created by ybq.
 */
public class Circle extends CircleContainer {

    @Override
    public Sprite[] onCreateChild() {
        Dot[] dots = new Dot[12];
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new Dot();
            dots[i].setAnimationDelay(1200 / 12 * i + -1200);
        }
        return dots;
    }

    class Dot extends CircleSprite {

        public Dot() {
            setScale(0f);
        }

        @Override
        public ValueAnimator onCreateAnimation() {
            float fractions[] = new float[]{0f, 0.5f, 1f};
            return new SpriteAnimatorBuilder(this).
                    scale(fractions, 0f, 1f, 0f).
                    duration(1200).
                    easeInOut(fractions)
                    .build();
        }
    }
}
