package com.trimh.trimphmvp.jike;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by tao on 2016/8/24.
 */

public class ImageSmoothView extends View {

    public Paint paint;

    public ImageView imageView;

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public ImageSmoothView(Context context) {
        this(context, null);
    }

    public ImageSmoothView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 从ImageView对象中获取图像的方法，就是ImageView类中的getDrawingCache()方法，比如下面的代码就是从一个ImageView对象iv_photo中获取图像：
     * <p>
     * Bitmap obmp = Bitmap.createBitmap(iv_photo.getDrawingCache());
     * <p>
     * 但是需要说明的是：
     * <p>
     * 1.     在调用getDrawingCache()方法从ImageView对象获取图像之前，一定要调用setDrawingCacheEnabled(true)方法：
     * <p>
     * iv_photo.setDrawingCacheEnabled(true);
     * <p>
     * 否则，无法从ImageView对象iv_photo中获取图像；
     * <p>
     * 2.     在调用getDrawingCache()方法从ImageView对象获取图像之后，一定要调用setDrawingCacheEnabled(false)方法：
     * <p>
     * iv_photo.setDrawingCacheEnabled(false);
     * <p>
     * 以清空画图缓冲区，否则，下一次从ImageView对象iv_photo中获取的图像，还是原来的图像。
     */
    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);

        if (imageView == null) {
            throw new NullPointerException("imageView not is null");
        }
        imageView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(imageView.getDrawingCache());
        imageView.setDrawingCacheEnabled(false);

    }

    public ImageSmoothView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
}
