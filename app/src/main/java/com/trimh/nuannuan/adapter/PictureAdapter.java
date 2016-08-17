package com.trimh.nuannuan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.trimh.nuannuan.R;
import com.trimh.nuannuan.bean.PictureBean;
import com.trimh.nuannuan.utils.ConstantObj;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Project TrimphJuneFifty
 * Package_name com.trimh.nuannuan.adapter
 * Created by Trimph on 2016/7/2.
 */
public class PictureAdapter extends RecyclerView.Adapter {

    public List<PictureBean.TngouBean> tngouBeans = new ArrayList<>();

    public Context context;

    public LayoutInflater layoutInflater;

    public List<PictureBean.TngouBean> getTngouBeans() {
        return tngouBeans;
    }

    public void setTngouBeans(List<PictureBean.TngouBean> tngouBeans) {
        this.tngouBeans = tngouBeans;
        int pos = getItemCount();
        notifyItemRangeInserted(pos, tngouBeans.size());
    }

    public void appendTngonBeans(List<PictureBean.TngouBean> tngou) {
        if (tngou != null) {
            if (tngouBeans != null) {
                tngouBeans.addAll(tngou);
            } else {
                this.tngouBeans = tngou;
            }
        }
    }


    public PictureAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public int BANNER_TYPE = 0;
    public int RECYCLELIST_TYPE = 1;

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return BANNER_TYPE;
            default:
                return RECYCLELIST_TYPE;
        }
    }

    public View handerView;

    public View getHanderView() {
        return handerView;
    }

    public void setHanderView(View handerView) {
        this.handerView = handerView;
        notifyItemChanged(0);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case 0:
                view = handerView;
                return new BannerViewholder(view);
            case 1:
                view = layoutInflater.inflate(R.layout.activity_picture_item, parent, false);
                return new PictureHolder(view);
        }
        return null;
    }


    /**
     * mei ren ti ni gui hua suoyoud quanbuzong huimimang mimang
     * zzao zijizai zuoshenm cao cao
     **/
    /*zhi dao ziji yaozuoshenm gaizuosehnm gaiwang nage rangx tamei zenm mei  sil  aaaa*/
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof PictureHolder) {
            PictureHolder pictureHolder = (PictureHolder) holder;
            PictureBean.TngouBean tngouBean = tngouBeans.get(position);
            if (tngouBean == null) {
                return;
            }

            pictureHolder.textView.setText(TextUtils.isEmpty(tngouBean.getTitle()) ? "" : tngouBean.getTitle());

            Glide.with(context).load(ConstantObj.BASE_IMG_URL + tngouBean.getImg())
                    .error(R.mipmap.ic_launcher)
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .into(pictureHolder.imageView);
        }


    }


    @Override
    public int getItemCount() {
        if (tngouBeans != null) {
            if (tngouBeans.size() > 0) {
                return tngouBeans.size();
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }


    public class PictureHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public ImageView imageView;

        public PictureHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.picture_img);
            textView = (TextView) itemView.findViewById(R.id.picture_title);

        }
    }


    public class BannerViewholder extends RecyclerView.ViewHolder {

        public BannerViewholder(View itemView) {
            super(itemView);
        }
    }


}
