package com.trimph.toprand.trimphrxandroid.trimph.ui.main.news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.trimph.toprand.trimphrxandroid.R;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.click.OnItemClickListener;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.model.NewsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @description: Project TrimphJuneFifty
 * Package_name com.trimh.nuannuan.adapter
 * Created by Trimph on 2016/7/2.
 */
public class NewsAdapter extends RecyclerView.Adapter {

    public List<NewsBean.DataBean> dataBeen = new ArrayList<>();

    public Context context;

    public LayoutInflater layoutInflater;

    public OnItemClickListener<NewsBean.DataBean> dataBeanOnItemClickListener;

    public List<NewsBean.DataBean> getTngouBeans() {
        return dataBeen;
    }

    public void setDataBeanOnItemClickListener(OnItemClickListener<NewsBean.DataBean> dataBeanOnItemClickListener) {
        this.dataBeanOnItemClickListener = dataBeanOnItemClickListener;
    }

    public void setTngouBeans(List<NewsBean.DataBean> tngouBeans) {
        if (tngouBeans == null) {
            return;
        }
        this.dataBeen = tngouBeans;
//        int pos = getItemCount();
//        notifyItemRangeInserted(pos, tngouBeans.size());
    }

    public void appendTngonBeans(List<NewsBean.DataBean> tngou) {
        if (tngou != null) {
            if (dataBeen != null) {
                dataBeen.addAll(tngou);
            } else {
                this.dataBeen = tngou;
            }
        }
    }

    public NewsAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = layoutInflater.inflate(R.layout.picture_item, parent, false);
        return new PictureHolder(view);
    }


    /**
     * mei ren ti ni gui hua suoyoud quanbuzong huimimang mimang
     * zzao zijizai zuoshenm cao cao
     **/
    /*zhi dao ziji yaozuoshenm gaizuosehnm gaiwang nage rangx tamei zenm mei  sil  aaaa*/
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (dataBeen == null) {
            return;
        }
        NewsBean.DataBean newsBean = dataBeen.get(position);

        PictureHolder pictureHolder = (PictureHolder) holder;
        pictureHolder.tv.setText(newsBean.getTitle());
        pictureHolder.newsDate.setText(newsBean.getDate());

        Glide.with(context).load(newsBean.getThumbnail_pic_s())
                .error(R.mipmap.ic_launcher)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(pictureHolder.newsIv);

        pictureHolder.contanit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataBeanOnItemClickListener != null) {
                    dataBeanOnItemClickListener.onItemClick(position, v, newsBean);
                }
            }
        });
        /**
         * 图片点击事件
         */
        pictureHolder.newsIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }


    @Override
    public int getItemCount() {
        if (dataBeen != null) {
            if (dataBeen.size() > 0) {
                return dataBeen.size();
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public class PictureHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv)
        TextView tv;
        @Bind(R.id.news_date)
        TextView newsDate;
        @Bind(R.id.news_iv)
        ImageView newsIv;
        @Bind(R.id.contanit)
        LinearLayout contanit;

        public PictureHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
