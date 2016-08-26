package com.trimh.nuannuan.utils.copy;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.trimh.nuannuan.R;
import com.trimh.nuannuan.utils.LogUtlis;
import com.trimh.nuannuan.utils.pinned.callback.OnHeaderClickListener;
import com.trimh.nuannuan.utils.pinned.callback.OnItemTouchListener;
import com.trimh.nuannuan.utils.pinned.callback.PinnedHeaderNotifyer;
import com.trimh.nuannuan.utils.pinned.entity.ClickBounds;

/**
 * 自定义 recycleView头部固定不动
 * Created by tao on 2016/8/17.
 */

public class PinnedHeaderFixed<T> extends RecyclerView.ItemDecoration {


    //j*分割线
    public int divider = 10;
    // 距离左右的
    public int maginSize = 10;
    public Paint paint = new Paint();
    public int mPinnedHeaderPosition = -1;//初始值

    public OnItemTouchListener<T> onItemTouchListener;
    public OnHeaderClickListener<T> onHeaderClickListener;

    public int[] clickIds;

    private PinnedHeaderFixed(Builder<T> b) {
        this.clickIds = b.clickIds;
        this.onItemTouchListener = b.onItemTouchLisener;
        this.onHeaderClickListener = b.onHeaderClickListerner;
        init();
    }

    public void init() {
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
    }

    // 用于锁定画布绘制范围
    private Rect mClipBounds;

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        createTag(c, parent);

        /**
         * 固定头部的位置
         */

        if (mPinnedHanderView != null) {
            mClipBounds = c.getClipBounds();

            //获取顶部所处的位置
            int heandEnd = mPinnedHanderView.getTop() + mPinnedHanderView.getHeight();

            //根据标签所处的位置 获取下面的view
            View belowView = parent.findChildViewUnder(c.getWidth() / 2, heandEnd + 1);

            //判断是否为标签 若是便签 就和缓存的标签同步移动
            if (ifPinnedHeander(parent, belowView)) {
                //计算偏移量
                mPinnedHeaderOffset = belowView.getTop() - (mPinnedHanderView.getHeight() + mHeaderTopMargin + mRecyclerViewPaddingTop);

                /// 从 mPinnedHanderView.getHeight()---》0
                mClipBounds.top = belowView.getTop();
            } else {
                mPinnedHeaderOffset = 0;
                mClipBounds.top = mRecyclerViewPaddingTop + mHeaderTopMargin + mPinnedHanderView.getHeight();
            }
            c.getClipBounds(mClipBounds);
        }

        /**
         *
         */
        drawDivider(c, parent);

    }

    private boolean ifPinnedHeander(RecyclerView parent, View belowView) {
        int position = parent.getChildAdapterPosition(belowView);
        if (position == RecyclerView.NO_POSITION) {
            return false;
        }
        int type = adapter.getItemViewType(position);
        return isHanderType(type);
    }

    /**
     * 画分割线
     *
     * @param c
     * @param parent
     */
    private void drawDivider(Canvas c, RecyclerView parent) {
        int count = parent.getChildCount();
        int left = maginSize;
        int right = parent.getMeasuredWidth() - maginSize;

        for (int i = 0; i < count; i++) {
            View view = parent.getChildAt(i);
            //画一个矩形
//            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
            Rect rect = new Rect();
            rect.left = left;
            rect.right = right;
            rect.top = view.getBottom() + divider;
            rect.bottom = view.getBottom();
            c.drawRect(rect, paint);
        }
    }


    /**
     * 绘制头部标签
     *
     * @param c
     * @param parent
     */
    private void createTag(Canvas c, RecyclerView parent) {
        // 检测到标签存在的时候，将标签强制固定在顶部
        //首先获取第一个标签所在的位置
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

        int firstVisibleItemPosition = getFirstItemPositon(parent);

        int pinnedHanderPositon = FindPinnedPosition(firstVisibleItemPosition);

        if (firstVisibleItemPosition >= 0 && mPinnedHeaderPosition != pinnedHanderPositon) {
            /*标签位置有效 并且和缓存位置不同*/
            mPinnedHeaderPosition = pinnedHanderPositon;
            //huo去变迁的type
            int type = adapter.getItemViewType(pinnedHanderPositon);

            //创建标签
            RecyclerView.ViewHolder holder = adapter.createViewHolder(parent, type);
            adapter.bindViewHolder(holder, mPinnedHeaderPosition);
            //缓存标签
            mPinnedHanderView = holder.itemView;

            ViewGroup.LayoutParams lp = mPinnedHanderView.getLayoutParams();

            //若为空创建
            if (lp == null) {
                lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                mPinnedHanderView.setLayoutParams(lp);
            }

            //对条目高度进行处理
            int heightMode = View.MeasureSpec.getMode(lp.height);
            int heightSize = View.MeasureSpec.getSize(lp.height);

            if (heightMode == View.MeasureSpec.UNSPECIFIED) {
                heightMode = View.MeasureSpec.EXACTLY;
            }

            mRecyclerViewPaddingLeft = parent.getPaddingLeft();
            mRecyclerViewPaddingTop = parent.getPaddingTop();
            mRecyclerViewPaddingRight = parent.getPaddingRight();
            mRecyclerViewPaddingBottom = parent.getPaddingBottom();

            if (lp instanceof ViewGroup.MarginLayoutParams) {
                LogUtlis.e("trimph------------position--------------是否进来----MarginLayoutParams");
                final ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) lp;
                mHeaderLeftMargin = mlp.leftMargin;
                mHeaderTopMargin = mlp.topMargin;
                mHeaderRightMargin = mlp.rightMargin;
                mHeaderBottomMargin = mlp.bottomMargin;
            }

            //最大高度
            int maxHeight = parent.getHeight() - mRecyclerViewPaddingBottom - mRecyclerViewPaddingTop;

            //
            heightSize = Math.min(maxHeight, heightSize);
            //标签的高度
            int widthSpc = View.MeasureSpec.makeMeasureSpec(parent.getWidth() - mHeaderLeftMargin - mHeaderRightMargin -
                    mRecyclerViewPaddingLeft - mRecyclerViewPaddingRight, View.MeasureSpec.EXACTLY);
            int heightSpc = View.MeasureSpec.makeMeasureSpec(heightSize, heightMode);
            //强制测绘
            mPinnedHanderView.measure(widthSpc, heightSpc);

            mLeft = mRecyclerViewPaddingLeft + mHeaderLeftMargin;
            mTop = mRecyclerViewPaddingTop + mHeaderTopMargin;
            mRight = mPinnedHanderView.getMeasuredWidth() + mRecyclerViewPaddingLeft + mHeaderLeftMargin + mHeaderRightMargin;
            mBottom = mPinnedHanderView.getMeasuredHeight() + mRecyclerViewPaddingTop + mHeaderTopMargin + mHeaderBottomMargin;
            //强制布局顶部
//            mLeft = mRecyclerViewPaddingLeft + mHeaderLeftMargin;
//            mRight = mPinnedHanderView.getMeasuredWidth() + mRecyclerViewPaddingRight + mHeaderRightMargin;
//            mBottom = mPinnedHanderView.getMeasuredHeight() + mRecyclerViewPaddingBottom + mHeaderBottomMargin;
//            mTop = mRecyclerViewPaddingTop + mHeaderTopMargin;

            mPinnedHanderView.layout(mLeft, mTop, mRight, mBottom);

            /***
             * 条目点击事件添加
             */
            if (onItemTouchListener == null) {
                onItemTouchListener = new OnItemTouchListener<>(parent.getContext());
                parent.addOnItemTouchListener(onItemTouchListener);

                if (onHeaderClickListener != null) {
                    onItemTouchListener.setHeaderClickListener(onHeaderClickListener);
                }

                //固定标签的点击范围
                onItemTouchListener.setViewAndBounds(OnItemTouchListener.HEADER_ID,
                        new ClickBounds(mLeft, mTop, mRight, mBottom));

                if (onHeaderClickListener != null && clickIds.length > 0) {
                    for (int ids : clickIds) {
                        View view = mPinnedHanderView.findViewById(ids);
                        onItemTouchListener.setViewAndBounds(ids, new ClickBounds(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
                    }
                }

            }

        }

    }


    View mPinnedHanderView;
    public int mPinnedHeaderOffset;//偏移量
    int mLeft, mRight, mBottom, mTop;
    int mHeaderLeftMargin, mHeaderTopMargin, mHeaderRightMargin, mHeaderBottomMargin;
    int mRecyclerViewPaddingLeft, mRecyclerViewPaddingRight, mRecyclerViewPaddingTop, mRecyclerViewPaddingBottom;

    private int FindPinnedPosition(int firstVisibleItemPosition) {

        /***循环获取**/
        for (int position = firstVisibleItemPosition; position >= 0; position--) {
            //根据位置获取 条目类别
            int type = adapter.getItemViewType(position);
            //判断他是不是 title 标签
            if (isHanderType(type)) {
                return position;
            }
        }

        return 0;
    }

    /**
     * 判断条目是不是标签
     *
     * @param type
     * @return
     */
    public boolean isHanderType(int type) {
        PinnedHeaderNotifyer p = (PinnedHeaderNotifyer) adapter;
        return p.isPinnedHeaderType(type);
    }

    private int getFirstItemPositon(RecyclerView parent) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }
        return 0;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        if (mPinnedHanderView != null) {
            c.save();
        }

        mClipBounds.top = mRecyclerViewPaddingTop + mHeaderTopMargin;

        c.clipRect(mClipBounds, Region.Op.UNION);
        c.translate(mHeaderLeftMargin + mRecyclerViewPaddingLeft, mPinnedHeaderOffset + mHeaderTopMargin);
        mPinnedHanderView.draw(c);
    }

    /**
     * 测绘内容偏移量
     *
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        chackChace(parent);

        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

        // 设置每个条目的偏移量
        if (layoutManager instanceof LinearLayoutManager) {
            outRect.set(maginSize, 0, maginSize, divider);
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {

        } else if (layoutManager instanceof GridLayoutManager) {

        }
    }


    /***
     * 创建Builder
     *
     * @param <T>
     */
    public static class Builder<T> {
        // 需要添加点击时间的Id
        public int[] clickIds;

        public OnHeaderClickListener<T> onHeaderClickListerner;
        public OnItemTouchListener<T> onItemTouchLisener;

        public Builder() {

        }

        public Builder<T> addClickIds(int... clickids) {
            this.clickIds = clickids;
            return this;
        }

        public Builder<T> setHeaderClickListener(OnHeaderClickListener<T> o) {
            this.onHeaderClickListerner = o;
            return this;
        }

        public Builder<T> setItemClickListener(OnItemTouchListener<T> onItemTouchLisener) {
            this.onItemTouchLisener = onItemTouchLisener;
            return this;
        }

        public PinnedHeaderFixed<T> create() {
            return new PinnedHeaderFixed<T>(this);
        }

    }


    public RecyclerView.Adapter adapter;

    private void chackChace(RecyclerView parent) {
        if (parent == null) {
            return;
        }
        adapter = parent.getAdapter();
    }


    public int getDivider() {
        return divider;
    }

    public void setDivider(int divider) {
        this.divider = divider;
    }

    public int getMaginSize() {
        return maginSize;
    }

    public void setMaginSize(int maginSize) {
        this.maginSize = maginSize;
    }
}
