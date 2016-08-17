package com.trimh.nuannuan.ui.pineed.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.trimh.nuannuan.R;
import com.trimh.nuannuan.ui.pineed.StockActivity;
import com.trimh.nuannuan.ui.pineed.entitiy.StockEntity;
import com.trimh.nuannuan.utils.pinned.FullSpanUtil;
import com.trimh.nuannuan.utils.pinned.callback.PinnedHeaderNotifyer;


import java.util.List;

/**
 * Created by Oubowu on 2016/8/3 14:43.
 * 直接继承BaseMultiItemQuickAdapter单独实现一个适配器的写法
 */
public class StockAdapter extends BaseMultiItemQuickAdapter<StockEntity.StockInfo> implements PinnedHeaderNotifyer<StockEntity.StockInfo> {

    public StockAdapter(List<StockEntity.StockInfo> data) {
        super(data);
        addItemType(StockEntity.StockInfo.TYPE_HEADER, R.layout.item_stock_header);
        addItemType(StockEntity.StockInfo.TYPE_DATA, R.layout.item_stock_data);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        FullSpanUtil.onAttachedToRecyclerView(recyclerView, this, StockEntity.StockInfo.TYPE_HEADER);
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        FullSpanUtil.onViewAttachedToWindow(holder, this, StockEntity.StockInfo.TYPE_HEADER);
    }

    @Override
    protected void convert(BaseViewHolder holder, StockEntity.StockInfo item) {
        switch (holder.getItemViewType()) {

            case StockEntity.StockInfo.TYPE_HEADER:
                holder.setText(R.id.tv_stock_name, item.pinnedHeaderName);
                break;

            case StockEntity.StockInfo.TYPE_DATA:

                final String stockNameAndCode = item.stock_name + "\n" + item.stock_code;
                SpannableStringBuilder ssb = new SpannableStringBuilder(stockNameAndCode);
                ssb.setSpan(new ForegroundColorSpan(Color.parseColor("#a4a4a7")), item.stock_name.length(), stockNameAndCode.length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                ssb.setSpan(new AbsoluteSizeSpan(StockActivity.dip2px(holder.itemView.getContext(), 13)), item.stock_name.length(), stockNameAndCode.length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                holder.setText(R.id.tv_stock_name_code, ssb).setText(R.id.tv_current_price, item.current_price)
                        .setText(R.id.tv_rate, (item.rate < 0 ? String.format("%.2f", item.rate) : "+" + String.format("%.2f", item.rate)) + "%");
                break;

        }
    }

    @Override
    public boolean isPinnedHeaderType(int viewType) {
        return viewType == StockEntity.StockInfo.TYPE_HEADER;
    }

    @Override
    public StockEntity.StockInfo getPinnedHeaderInfo(int position) {
        return ((StockEntity.StockInfo) getData().get(position));
    }
}
