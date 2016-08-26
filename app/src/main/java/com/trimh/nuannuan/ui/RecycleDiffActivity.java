package com.trimh.nuannuan.ui;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.animation.BaseAnimation;

import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.entity.SectionEntity;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.trimh.nuannuan.MainActivity;
import com.trimh.nuannuan.R;
import com.trimh.nuannuan.adapter.PictureAdapter;
import com.trimh.nuannuan.base.BaseActivity;
import com.trimh.nuannuan.bean.PictureBean;
import com.trimh.nuannuan.net.PictureApi;
import com.trimh.nuannuan.ui.pineed.SecondActivity;
import com.trimh.nuannuan.ui.pineed.StockActivity;
import com.trimh.nuannuan.utils.ConstantObj;
import com.trimh.nuannuan.view.banner.BannerIndicator;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by tao on 2016/8/15.
 */

public class RecycleDiffActivity extends BaseActivity {

    public RecyclerView recyclerView;
    public PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    public PictureAdapter pictureAdapter;
    public int page;
    public boolean isRefresh = true;
    public TextView textDesign, textCheck;
    RecycleAdapter recycleAdapter;
    List<PictureBean.TngouBean> tngouBeanList = new ArrayList<>();

    @Override
    protected void initData() {
        pullLoadMoreRecyclerView.setRefreshing(true);
        httpMethod(1);
    }

    @Override
    protected void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        textCheck = (TextView) findViewById(R.id.text_check);

        textDesign = (TextView) findViewById(R.id.text_design);

        pullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) findViewById(R.id.pull_Refresh);
        pullLoadMoreRecyclerView.setLinearLayout();
        recycleAdapter = new RecycleAdapter(R.layout.activity_picture_item, tngouBeanList);

//        recycleAdapter.addHeaderView();

        MySectionItms();

        recycleSection = new RecycleSectionQuickAdapter(R.layout.activity_picture_item, R.layout.section_layout, section);

        recyclerView.setAdapter(recycleAdapter);

        pullLoadMoreRecyclerView.setEmptyView(LayoutInflater.from(this).inflate(R.layout.empty_layout, null));

        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(recycleAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        // enable drag items
        recycleAdapter.enableDragItem(itemTouchHelper, R.id.picture_title, true);
        recycleAdapter.setOnItemDragListener(onItemDragListerner);

        // enable swipe items
        recycleAdapter.enableSwipeItem();
        recycleAdapter.setOnItemSwipeListener(onItemSwipeListener);


    }


    public void animationDialog() {
        new AlertDialog.Builder(this).setItems(new String[]{"ALPHAIN", "SCALEIN", "SLIDEIN_BOTTOM", "SLIDEIN_LEFT", "SLIDEIN_RIGHT", "sfsf"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.e("trimph", "--------2---------" + which);
                switch (which) {
                    case 0:
                        recycleAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
                        pullLoadMoreRecyclerView.setRefreshing(true);
                        httpMethod(1);
//                        pullLoadMoreRecyclerView.setStaggeredGridLayout(4);
                        Log.e("trimph", "/---------------1---------");
                        break;
                    case 1:
                        recycleAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
                        pullLoadMoreRecyclerView.setRefreshing(true);
                        httpMethod(1);
//                        pullLoadMoreRecyclerView.setGridLayout(4);
                        Log.e("trimph", "/---------------1---------");
                        break;
                    case 2:
                        recycleAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
                        pullLoadMoreRecyclerView.setRefreshing(true);
                        httpMethod(1);
                        Log.e("trimph", "--------2---------");
                        break;
                    case 3:

                        recycleAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
                        pullLoadMoreRecyclerView.setRefreshing(true);
                        httpMethod(1);
                        Log.e("trimph", "--------2---------");
                        break;
                    case 4:
                        recycleAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
                        pullLoadMoreRecyclerView.setRefreshing(true);
                        httpMethod(1);
                        Log.e("trimph", "--------2---------");
                        break;
                    case 5:
                        recycleAdapter.openLoadAnimation(new BaseAnimation() {
                            @Override
                            public Animator[] getAnimators(View view) {
                                return new Animator[]{
                                        ObjectAnimator.ofFloat(view, "scaleY", 1, 0.6f, 1),
                                        ObjectAnimator.ofFloat(view, "scaleX", 1, 0.6f, 1)
                                };
                            }
                        });
                        Log.e("trimph", "--------2---------");
                        break;
                }
            }
        }).create().show();
    }

    public void dialog() {
        new AlertDialog.Builder(this).setItems(new String[]{"Linearlayout", "Staggered", "GridLayout", "调到下个界面", "调到同类个界面", "sfsf"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.e("trimph", "--------2---------" + which);
                switch (which) {
                    case 0:
                        pullLoadMoreRecyclerView.setLinearLayout();
                        Log.e("trimph", "/---------------1---------");
                        break;
                    case 1:
                        pullLoadMoreRecyclerView.setStaggeredGridLayout(3);
                        Log.e("trimph", "/---------------1---------");
                        break;
                    case 2:
                        pullLoadMoreRecyclerView.setGridLayout(4);
                        Log.e("trimph", "--------2---------");
                        break;
                    case 3:
                        startActivity(new Intent(RecycleDiffActivity.this, SecondActivity.class));
                        Log.e("trimph", "--------2---------");
                        break;
                    case 4:
                        startActivity(new Intent(RecycleDiffActivity.this, StockActivity.class));
                        Log.e("trimph", "--------2---------");
                        break;
                }
            }
        }).create().show();


    }


    public class MulltiItem extends MultiItemEntity {
        public String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public List<MulltiItem> mulls = new ArrayList<>();
    public List<MySection> section = new ArrayList<>();

    public void Itms() {
        for (int i = 0; i < 3; i++) {
            MulltiItem m = new MulltiItem();
            m.setItemType(i);
            m.setContent("contnt" + i);
            mulls.add(m);
        }
    }

    public void MySectionItms() {
        for (int i = 0; i < 30; i++) {
            MySection m;
            if (i % 3 == 0) {
                m = new MySection(true, "hander" + i);
            } else {
                m = new MySection("Conrtent" + i);
            }
            section.add(m);
        }
    }

    public class MySection extends SectionEntity<String> {

        public MySection(boolean isHeader, String header) {
            super(isHeader, header);
        }

        public MySection(String s) {
            super(s);
        }
    }

    public RecycleSectionQuickAdapter recycleSection;

    public class RecycleSectionQuickAdapter extends BaseSectionQuickAdapter<MySection> {

        public RecycleSectionQuickAdapter(int layoutResId, int sectionHeadResId, List data) {
            super(layoutResId, sectionHeadResId, data);
        }

        @Override
        protected void convertHead(BaseViewHolder baseViewHolder, MySection mySection) {
            baseViewHolder.setText(R.id.section_tv, mySection.header);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, MySection mySection) {
            baseViewHolder.setText(R.id.picture_title, mySection.t);
        }
    }

    public class RecycleHander extends BaseMultiItemQuickAdapter<MulltiItem> {

        public RecycleHander(List<MulltiItem> data) {
            super(data);
            addItemType(data.get(0).getItemType(), R.layout.activity_picture_item);
            addItemType(data.get(1).getItemType(), R.layout.viewtype_one);
            addItemType(data.get(2).getItemType(), R.layout.viewtype_one);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, MulltiItem mulltiItem) {
            switch (mulltiItem.getItemType()) {
                case 0:
                    baseViewHolder.setText(R.id.picture_title, mulltiItem.getContent());
                    Glide.with(RecycleDiffActivity.this).load(ConstantObj.BASE_IMG_URL + "")
                            .error(R.mipmap.ic_launcher)
                            .centerCrop()
                            .placeholder(R.mipmap.ic_launcher)
                            .into((ImageView) baseViewHolder.getView(R.id.picture_img));
                    break;
                case 1:
                    baseViewHolder.setText(R.id.picture_title, mulltiItem.getContent());
                    break;
                case 2:
                    baseViewHolder.setText(R.id.picture_title, mulltiItem.getContent());
                    break;

            }
        }
    }


    public class RecycleAdapter extends BaseQuickAdapter<PictureBean.TngouBean> {

        public RecycleAdapter(int layoutResId, List<PictureBean.TngouBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, PictureBean.TngouBean tngouBean) {

            baseViewHolder.setText(R.id.picture_title, tngouBean.getTitle());
//            helper.setOnClickListener(R.id.tweetAvatar, new OnItemChildClickListener())
            baseViewHolder.setOnClickListener(R.id.picture_img, new OnItemChildClickListener());
            Glide.with(RecycleDiffActivity.this).load(ConstantObj.BASE_IMG_URL + tngouBean.getImg())
                    .error(R.mipmap.ic_launcher)
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .into((ImageView) baseViewHolder.getView(R.id.picture_img));
        }

    }

    /***
     * 拖拽监听
     */
    public OnItemDragListener onItemDragListerner = new OnItemDragListener() {
        @Override
        public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int i) {
            pullLoadMoreRecyclerView.setPullRefreshEnable(false);
        }

        @Override
        public void onItemDragMoving(RecyclerView.ViewHolder viewHolder, int i, RecyclerView.ViewHolder viewHolder1, int i1) {

        }

        @Override
        public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int i) {
            pullLoadMoreRecyclerView.setPullRefreshEnable(true);
        }
    };

    /***
     * 滑动
     */
    public OnItemSwipeListener onItemSwipeListener = new OnItemSwipeListener() {
        @Override
        public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int i) {

        }

        @Override
        public void clearView(RecyclerView.ViewHolder viewHolder, int i) {

        }

        @Override
        public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int i) {

        }
    };

    @Override
    protected void initListener() {


        textCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog();
            }
        });
        textDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationDialog();
            }
        });

        /**
         * 条目点击
         */
        recycleAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                Log.e("trimph", "setOnRecyclerViewItemClickListener--------2---------" + i);
            }
        });
        /***
         * 条目中子类被点击
         */
        recycleAdapter.setOnRecyclerViewItemChildClickListener(new BaseQuickAdapter.OnRecyclerViewItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Log.e("trimph", "setOnRecyclerViewItemChildClickListener--------2---------" + i);
            }
        });


        pullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                page = 1;
                isRefresh = true;
                httpMethod(page);
            }

            @Override
            public void onLoadMore() {
                page = 1 + page;
                isRefresh = false;
                httpMethod(page);
            }
        });
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_main;
    }

    public void httpMethod(final int page) {

        loadDialog.showDialog();

        PictureApi.getInstance().getPictureList(new Subscriber<PictureBean>() {
            @Override
            public void onCompleted() {
                loadDialog.closeDialog();
                pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
            }

            @Override
            public void onError(Throwable e) {
                pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                loadDialog.closeDialog();
            }

            @Override
            public void onNext(PictureBean pictureBean) {
                loadDialog.closeDialog();
                if (pictureBean == null) {
                    return;
                }
                if (pictureBean.getTngou() == null) {
                    return;
                }

                if (isRefresh) {
                    tngouBeanList = pictureBean.getTngou();
                    recycleAdapter.setNewData(pictureBean.getTngou());
                } else {
                    tngouBeanList.addAll(pictureBean.getTngou());
                    recycleAdapter.setNewData(tngouBeanList);
                }
                recycleAdapter.notifyDataSetChanged();
            }
        }, page, 20);

    }
}
