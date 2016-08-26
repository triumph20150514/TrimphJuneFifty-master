package com.trimh.nuannuan.ui.swipe;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tao on 2016/8/19.
 */

public abstract class BaseSwipeMenuAdapter<VH extends RecyclerView.ViewHolder, T> extends RecyclerView.Adapter<VH> {

    public List<T> tList = new ArrayList<>();

    public List<T> getData() {
        return tList;
    }

    public void appendData(List<T> data) {
        if (data == null) {
            return;
        }
        this.tList.addAll(data);
    }

    public void setData(List<T> data) {
        if (data == null) {
            return;
        }
        this.tList = data;
    }

    @Override
    public void onBindViewHolder(VH holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    /**
     * 绑定数据 15分钟 chachacha
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(VH holder, int position) {


    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View contentView = createContentView(parent, viewType);


        return onCompontCreateViewHolder(contentView, viewType);
    }

    /**
     * 创建ViewHolder
     *
     * @param contentView
     * @return
     */
    protected abstract VH onCompontCreateViewHolder(View contentView, int viewType);

    /**
     * 创建View
     *
     * @param parent
     * @param viewType
     * @return
     */
    protected abstract View createContentView(ViewGroup parent, int viewType);

    @Override
    public int getItemCount() {
        return 0;
    }
}
