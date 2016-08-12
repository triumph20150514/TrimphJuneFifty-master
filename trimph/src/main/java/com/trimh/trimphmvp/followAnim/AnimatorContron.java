package com.trimh.trimphmvp.followAnim;

import java.util.ArrayList;
import java.util.List;

/**
 * 容器 哈哈
 * Created by tao on 2016/7/30.
 */

public class AnimatorContron {

    List<AimatorImage> aimatorImages = new ArrayList<>();
    AimatorImage topImage;
    AimatorImage topFollowImage;
    int length;

    public AnimatorContron() {
    }

    public static AnimatorContron create() {
        return new AnimatorContron();
    }

    public List<AimatorImage> getAimatorImages() {
        return aimatorImages;
    }

    public void setAimatorImages(List<AimatorImage> aimatorImages) {
        this.aimatorImages = aimatorImages;
    }

    public void init() {
        length = aimatorImages.size();
        topImage = aimatorImages.get(length - 1);
        topFollowImage = aimatorImages.get(length - 2);

        for (int i = 1; i < length; i++) {
            AimatorImage view1 = aimatorImages.get(i);
            AimatorImage view2 = aimatorImages.get(i - 1);

            view1.getSpringX().addListener(view2.getFollowSpringX());
            view1.getSpringY().addListener(view2.getFollowSpringY());
        }

    }

    public int originPosX, originPosY;

    /***
     * 回到起始位置
     * <p>
     * //若是不调用 手指在哪里抬起  就在那里结束
     */
    public void onRealse() {
        topImage.reSet(originPosX, originPosY);
    }

    /**
     * 顶部view改变的时候 后面的跟随改变
     */
    public void onTopViewPosChanged(int posX, int posY) {
        topFollowImage.animTo(posX, posY);
    }


    /**
     * 设置原始的位置
     *
     * @param posX
     * @param posY
     */
    public void settOriginPos(int posX, int posY) {
        this.originPosX = posX;
        this.originPosY = posY;

        for (int i = 0; i < length; i++) {
            aimatorImages.get(i).setCurrentSpringPos(posX, posX);
        }


    }

}
