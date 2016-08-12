package com.trimph.toprand.trimphrxandroid.trimph.ui.main.main.mainfragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trimph.toprand.trimphrxandroid.R;
import com.trimph.toprand.trimphrxandroid.trimph.Iservice.PictureBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

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
        if (tngouBeans == null) {
            return;
        }
        PictureHolder pictureHolder = (PictureHolder) holder;
        pictureHolder.tv.setText(tngouBeans.get(position).getTitle());
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

        @Bind(R.id.tv)
        TextView tv;

        public PictureHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
