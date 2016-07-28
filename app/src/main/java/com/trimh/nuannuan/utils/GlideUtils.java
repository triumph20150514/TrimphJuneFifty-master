package com.trimh.nuannuan.utils;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.disklrucache.DiskLruCache;
import com.trimh.nuannuan.R;
import com.trimh.nuannuan.contantobj.ConstantObj;

/**
 * @description: Project TrimphJuneFifty
 * Package_name com.trimh.nuannuan.utils
 * Created by Trimph on 2016/7/2.
 */
public class GlideUtils {


    /**
     * 加载本地资源
     *
     * @param activity
     * @param string
     * @param imageView
     */
    public static void loadLocalImge(Activity activity, String string, ImageView imageView) {
        Glide.with(activity)
                .load(string)
                .centerCrop()
                .error(R.mipmap.ic_launcher)
                .into(imageView);
    }


    /**
     * 加载网络资源
     *
     * @param activity
     * @param string
     * @param imageView
     */
    public static void loadLNetImge(Activity activity, String string, ImageView imageView) {
        Glide.with(activity)
                .load(ConstantObj.BASE_IMG_URL + string)
                .centerCrop()
                .error(R.mipmap.ic_launcher)
                .into(imageView);
    }

    /**
     * 加载圆形网络资源
     *
     * @param activity
     * @param string
     * @param imageView
     */
    public static void loadLNetRoundImge(Activity activity, String string, ImageView imageView) {
        Glide.with(activity)
                .load(ConstantObj.BASE_IMG_URL + string)
                .centerCrop()
                .error(R.mipmap.ic_launcher)
                .into(imageView);
    }


}
