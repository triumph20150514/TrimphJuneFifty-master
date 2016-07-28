package com.trimh.nuannuan.utils.loading;


import com.trimh.nuannuan.utils.loading.sprite.Sprite;
import com.trimh.nuannuan.utils.loading.style.ChasingDots;
import com.trimh.nuannuan.utils.loading.style.Circle;
import com.trimh.nuannuan.utils.loading.style.CubeGrid;
import com.trimh.nuannuan.utils.loading.style.DoubleBounce;
import com.trimh.nuannuan.utils.loading.style.FadingCircle;
import com.trimh.nuannuan.utils.loading.style.FoldingCube;
import com.trimh.nuannuan.utils.loading.style.Pulse;
import com.trimh.nuannuan.utils.loading.style.RotatingCircle;
import com.trimh.nuannuan.utils.loading.style.RotatingPlane;
import com.trimh.nuannuan.utils.loading.style.ThreeBounce;
import com.trimh.nuannuan.utils.loading.style.WanderingCubes;
import com.trimh.nuannuan.utils.loading.style.Wave;

/**
 * Created by ybq.
 */
public class SpriteFactory {

    public static Sprite create(Style style) {
        Sprite sprite = null;
        switch (style) {
            case ROTATING_PLANE:
                sprite = new RotatingPlane();
                break;
            case DOUBLE_BOUNCE:
                sprite = new DoubleBounce();
                break;
            case WAVE:
                sprite = new Wave();
                break;
            case WANDERING_CUBES:
                sprite = new WanderingCubes();
                break;
            case PULSE:
                sprite = new Pulse();
                break;
            case CHASING_DOTS:
                sprite = new ChasingDots();
                break;
            case THREE_BOUNCE:
                sprite = new ThreeBounce();
                break;
            case CIRCLE:
                sprite = new Circle();
                break;
            case CUBE_GRID:
                sprite = new CubeGrid();
                break;
            case FADING_CIRCLE:
                sprite = new FadingCircle();
                break;
            case FOLDING_CUBE:
                sprite = new FoldingCube();
                break;
            case ROTATING_CIRCLE:
                sprite = new RotatingCircle();
                break;
            default:
                break;
        }
        return sprite;
    }
}
