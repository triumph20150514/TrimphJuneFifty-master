package com.trimh.nuannuan.utils.copy;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.trimh.nuannuan.utils.LogUtlis;
import com.trimh.nuannuan.utils.pinned.callback.PinnedHeaderNotifyer;

/**
 * 自定义 recycleView头部固定不动
 * Created by tao on 2016/8/17.
 */

public class PinnedHeaderFixed extends RecyclerView.ItemDecoration {


    //j*分割线
    public int divider = 10;
    // 距离左右的
    public int maginSize = 10;
    public Paint paint = new Paint();
    public int mPinnedHeaderPosition = -1;//初始值

    public PinnedHeaderFixed() {
        super();

        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);


    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        createTag(c, parent);


        drawDivider(c, parent);

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
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) parent.getLayoutParams();
        for (int i = 0; i < count; i++) {
            View view = parent.getChildAt(i);
            //画一个矩形
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

            //强制布局顶部
            mLeft = mRecyclerViewPaddingLeft + mHeaderLeftMargin;
            mRight = mPinnedHanderView.getMeasuredWidth() + mRecyclerViewPaddingRight + mHeaderRightMargin;
            mBottom = mPinnedHanderView.getMeasuredHeight() + mRecyclerViewPaddingBottom + mHeaderBottomMargin;
            mTop = mRecyclerViewPaddingTop + mHeaderTopMargin;
            mPinnedHanderView.layout(mLeft, mTop, mRight, mBottom);







        }

    }


    View mPinnedHanderView;
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
