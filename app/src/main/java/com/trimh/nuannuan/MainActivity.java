package com.trimh.nuannuan;

import android.graphics.Paint;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.animation.SlideInLeftAnimation;
import com.flyco.banner.transform.ZoomOutSlideTransformer;
import com.trimh.nuannuan.adapter.PictureAdapter;
import com.trimh.nuannuan.base.BaseActivity;
import com.trimh.nuannuan.bean.PictureBean;
import com.trimh.nuannuan.net.PictureApi;
import com.trimh.nuannuan.view.banner.BannerIndicator;
import com.trimh.nuannuan.view.banner.SimpleBanner;
import com.trimh.nuannuan.view.indicator.RoundCornerIndicaor;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.Locale;

import rx.Subscriber;

/**
 * @description: Project TrimphJuneFifty
 * Package_name com.trimh.nuannuan
 * Created by Trimph on 2016/7/4.
 */
public class MainActivity extends BaseActivity {


    public RecyclerView recyclerView;
    public PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    public PictureAdapter pictureAdapter;
    public int page;
    public boolean isRefresh = true;
    public BannerIndicator simpleBanner;

    @Override
    protected void initData() {
        pullLoadMoreRecyclerView.setRefreshing(true);
        httpMethod(1);
    }

    @Override
    protected void initView() {

        View bann = LayoutInflater.from(this).inflate(R.layout.banner_layout, null);

        simpleBanner = (BannerIndicator) bann.findViewById(R.id.banner);

//        if (simpleBanner != null) {
//            simpleBanner.setTransformerClass(ZoomOutSlideTransformer.class);
//        }

//        simpleBanner = new BannerIndicator(this);

        simpleBanner.setIndicatorStyle(0);

        simpleBanner.setIndicatorSelectorRes(R.mipmap.ic_unselect, R.mipmap.ic_select);

        simpleBanner.setIndicatorCornerRadius(10);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);


        pullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) findViewById(R.id.pull_Refresh);

        if (pictureAdapter == null) {
            pictureAdapter = new PictureAdapter(MainActivity.this);
        }
        pictureAdapter.setHanderView(bann);
        recyclerView.setAdapter(pictureAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initListener() {
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


    public void httpMethod(final int page) {

//        Locale locale=new Locale(Locale.);
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
                simpleBanner.setSource(pictureBean.getTngou()).startScroll();
                if (isRefresh) {
                    pictureAdapter.setTngouBeans(pictureBean.getTngou());
                } else {
                    pictureAdapter.appendTngonBeans(pictureBean.getTngou());
                }
                pictureAdapter.notifyDataSetChanged();

            }
        }, page, 10);

    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_main;
    }


}
