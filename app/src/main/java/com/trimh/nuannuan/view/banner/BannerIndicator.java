package com.trimh.nuannuan.view.banner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.flyco.banner.widget.Banner.BaseIndicaorBanner;
import com.trimh.nuannuan.R;
import com.trimh.nuannuan.bean.PictureBean;
import com.trimh.nuannuan.contantobj.ConstantObj;
import com.trimh.nuannuan.utils.LogUtlis;
import com.trimh.nuannuan.view.indicator.RoundCornerIndicaor;

/**
 * @description: Project TrimphJuneFifty
 * Package_name com.trimh.nuannuan.view.banner
 * Created by Trimph on 2016/7/10.
 */
public class BannerIndicator extends BaseIndicaorBanner<PictureBean.TngouBean, BannerIndicator> {

    public BannerIndicator(Context context) {
        super(context);
    }

    public BannerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public BannerIndicator(Context context, AttributeSet attrs, int defStyle) {
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

    RoundCornerIndicaor roundCornerIndicaor;

    @Override
    public View onCreateIndicator() {
        roundCornerIndicaor = new RoundCornerIndicaor(context);
        roundCornerIndicaor.setViewPager(vp);
        return roundCornerIndicaor;
    }

    @Override
    public void setCurrentIndicator(int position) {
        LogUtlis.e(position + "position");

//        roundCornerIndicaor.setCurrentItem(position);
        roundCornerIndicaor.setCurrentItem(position);
    }
}
