package com.trimh.trimphmvp.copy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.trimh.trimphmvp.view.node.LogisticsData;

import java.util.ArrayList;
import java.util.List;

/**
 * 仿物流  嘎嘎
 * Created by tao on 2016/7/28.
 */

public class NodeProgressView extends View {

    public int NodeWidth;
    public Context context;
    public Paint paint = new Paint();
    public List<LogisticsData> logisticsDatas = new ArrayList<>();
    public int Count;

    /*阶段间隔*/
    public int hightIndicator;
    public int nodeRadius;

    public int hight, width;

    public NodeProgressAdapter nodeProgressAdapter;

    public List<LogisticsData> getLogisticsDatas() {
        return logisticsDatas;
    }

    public void setLogisticsDatas(List<LogisticsData> logisticsDatas) {
        this.logisticsDatas = logisticsDatas;
    }

    public NodeProgressView(Context context) {
        this(context, null);
    }

    public NodeProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    public NodeProgressAdapter getNodeProgressAdapter() {
        return nodeProgressAdapter;
    }

    public void setNodeProgressAdapter(NodeProgressAdapter nodeProgressAdapter) {
        this.nodeProgressAdapter = nodeProgressAdapter;
    }

    private void init() {
        nodeRadius = dip2px(context, 10);
        NodeWidth = dip2px(context, 20);
        hightIndicator = dip2px(context, 80);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);

        leftMargin = dip2px(context, 20);
        topMargin = dip2px(context, 20);
        //获取拼命快高
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        width = windowManager.getDefaultDisplay().getWidth();
        hight = windowManager.getDefaultDisplay().getHeight();
    }

    public NodeProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e("height", Count * hightIndicator + topMargin + "");

        setMeasuredDimension(widthMeasureSpec, Count * hightIndicator + topMargin);
    }

    int leftMargin, topMargin;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Count = nodeProgressAdapter.getCount();
        logisticsDatas = nodeProgressAdapter.getDatas();

        paint.setColor(Color.WHITE);
        /**画一个范围*/
        canvas.drawRect(leftMargin, topMargin, NodeWidth / 5 + leftMargin + 10, Count * hightIndicator + topMargin, paint);

        /**划线*/
        for (int i = 0; i < Count; i++) {

            if (i == 0) {
                //子
                Paint mPaint = new Paint();
                mPaint.setAntiAlias(true);
                mPaint.setColor(Color.parseColor("#59595D"));
                mPaint.setTextSize(35F);
                //时间
                canvas.drawText(logisticsDatas.get(i).getTime(), NodeWidth + leftMargin + 10, nodeRadius + topMargin + 10, mPaint);

                /*内容 必须韩行*/
                TextPaint textPaint = new TextPaint();
                textPaint.setColor(Color.GRAY);
                textPaint.setAntiAlias(true);
                textPaint.setTextSize(35F);
                StaticLayout staticLayout = new StaticLayout(logisticsDatas.get(i).getContext(), textPaint, (int) (width * 0.8), Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);

                canvas.save();
                canvas.translate(leftMargin + nodeRadius, topMargin + hightIndicator / 2);
                staticLayout.draw(canvas);
                canvas.restore();

                //花园
                canvas.drawCircle(leftMargin, topMargin, nodeRadius, mPaint);

                mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
                //花园
                canvas.drawCircle(leftMargin, topMargin, (float) (nodeRadius * 1.2), mPaint);
            } else {

                paint.setColor(Color.GRAY);
                canvas.drawCircle(leftMargin, topMargin + hightIndicator * i, nodeRadius, paint);
                canvas.drawLine(leftMargin, topMargin + hightIndicator * i, width, topMargin + hightIndicator * i, paint);

                //时间
                paint.setTextSize(35f);
                canvas.drawText(logisticsDatas.get(i).getTime(), NodeWidth + leftMargin + 10, nodeRadius + topMargin + i * hightIndicator, paint);

                /*内容 必须韩行*/
                TextPaint textPaint = new TextPaint();
                textPaint.setColor(Color.GRAY);
                textPaint.setAntiAlias(true);
                textPaint.setTextSize(35F);

                StaticLayout staticLayout = new StaticLayout(logisticsDatas.get(i).getContext(), textPaint, (int) (width * 0.8), Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);

                canvas.save();
                canvas.translate(leftMargin + nodeRadius, topMargin + i * hightIndicator + hightIndicator / 2);
                staticLayout.draw(canvas);
                canvas.restore();
            }

        }


    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
