package com.trimh.nuannuan.view.banner;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.flyco.banner.transform.ZoomOutSlideTransformer;
import com.flyco.banner.widget.Banner.base.BaseBanner;
import com.trimh.nuannuan.R;
import com.trimh.nuannuan.bean.PictureBean;
import com.trimh.nuannuan.contantobj.ConstantObj;
import com.trimh.nuannuan.view.indicator.RoundCornerIndicaor;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: banner
 * Package_name com.trimh.nuannuan.view.banner
 * Created by Trimph on 2016/7/10.
 */
public class SimpleBanner extends BaseBanner<PictureBean.TngouBean, SimpleBanner> {


    public Context mContext;


    public SimpleBanner(Context context) {
        this(context, null);
        this.mContext = context;
    }

    public SimpleBanner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleBanner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public View onCreateItemView(int position) {
        View view = View.inflate(context, R.layout.banner_view_layout, null);
//        View view = LayoutInflater.from(mContext).inflate(R.layout.banner_view_layout, this, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.banner_image);
        Glide.with(context)
                .load(ConstantObj.BASE_IMG_URL + list.get(position).getImg())
                .error(R.mipmap.ic_launcher)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView);
        return view;
    }

    BannerIndicator ba;

    @Override
    public View onCreateIndicator() {
       /* ba = new BannerIndicator(context);
        ba.setHorizontalGravity(ALIGN_RIGHT);
        ba.setIndicatorSelectorRes(R.mipmap.ic_unselect, R.mipmap.ic_select);
        return ba;*/

        return null;
    }

    public RoundCornerIndicaor roundCornerIndicaor;
    @Override
    public void setCurrentIndicator(int position) {
//        ba.setCurrentIndicator(position);

    }

    @Override
    public SimpleBanner setTransformerClass(Class<? extends ViewPager.PageTransformer> transformerClass) {
        return super.setTransformerClass(transformerClass);
    }

}
