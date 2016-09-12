package com.trimph.toprand.trimphrxandroid.trimph.ui.main.news;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import com.trimph.toprand.trimphrxandroid.R;
import com.trimph.toprand.trimphrxandroid.trimph.Contants.ContantsObj;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.click.OnItemClickListener;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.impl.ResultNet;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.model.NewsBean;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.model.NewsCommenBean;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.news.adapter.NewsAdapter;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.news.persenter.PersenterView;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.news.persenter.PersenterViewImpl;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.news.view.NewsView;
import com.trimph.toprand.trimphrxandroid.trimph.utils.DividerItemDecoration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tao on 2016/8/10.
 */

public class NewsFragment extends Fragment implements NewsView {

    public PersenterView persenterView;
    public AlertDialog alertDialog;

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    //新闻类型
    public String different;
    public NewsAdapter pictureAdapter;
    public int page = 1;
    public int count = 20;
    public static String ENWSBEAN = NewsFragment.class.getSimpleName();
    public boolean isFrefsh = true;
    @Bind(R.id.floating_button)
    FloatingActionButton floatingButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.picture_main_fragment, container, false);
        ButterKnife.bind(this, view);
        init();
        persenterView = new PersenterViewImpl(this);
        persenterView.getModelData(getContext(), resultNet, ContantsObj.news_key, different);
        return view;
    }


    private void init() {
        pictureAdapter = new NewsAdapter(getContext());
        recyclerView.setAdapter(pictureAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), 10));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                Log.e("Trimph", "dy" + dy);
            }
        });
        recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                //通过recyclerView滑动状态

                Log.e("Trimph", "v.getTop()" + v.getTop());
                Log.e("Trimph", "scrollY" + scrollY + " oldScrollY" + oldScrollY);
                if (scrollY - oldScrollY > 5) {
                    startAnim(floatingButton.getHeight() + floatingButton.getBottom());
                } else {
                    startAnim(-floatingButton.getHeight() + floatingButton.getBottom());
                }
            }
        });

        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page += 1;
                isFrefsh = true;
                persenterView.getModelData(getContext(), resultNet, ContantsObj.news_key, different);

            }
        });
        /***
         *
         */
        /*
        swipeRefreshLayout.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                page += 1;
                isFrefsh = true;
                persenterView.getModelData(getContext(), resultNet, ContantsObj.news_key, different);

            }

            @Override
            public void onLoadMore() {
                page += 1;
                isFrefsh = false;
                persenterView.getModelData(getContext(), resultNet, ContantsObj.news_key, different);

            }
        });
*/

        /**
         *
         */
        pictureAdapter.setDataBeanOnItemClickListener(new OnItemClickListener<NewsBean.DataBean>() {
            @Override
            public void onItemClick(int position, View view, NewsBean.DataBean dataBean) {
                Intent intent = new Intent(getContext(), NewsDetailActivity.class);
                intent.putExtra(ENWSBEAN, dataBean);
                startActivity(intent);
            }
        });
    }

    private void startAnim(int dance) {
        floatingButton.animate().translationY(dance)
                .setDuration(2000)
                .setInterpolator(new LinearInterpolator())
                .start();
    }

    public ResultNet<NewsCommenBean> resultNet = new ResultNet<NewsCommenBean>() {
        @Override
        public void success(NewsCommenBean newsCommenBean) {
//            swipeRefreshLayout.setPullLoadMoreCompleted();
            swipeRefreshLayout.setRefreshing(false);
            if (newsCommenBean == null) {
                Toast.makeText(NewsFragment.this.getContext(), "没有数据返回", Toast.LENGTH_LONG).show();
                return;
            }
            Log.e("Trimph", "result:" + newsCommenBean.getNewsBean().getData().get(0).getTitle());
            pictureAdapter.setTngouBeans(newsCommenBean.getNewsBean().getData());
            pictureAdapter.notifyDataSetChanged();
        }

        @Override
        public void error(String message) {
            swipeRefreshLayout.setRefreshing(false);
            Log.e("Trimph", "error:" + message);
//            swipeRefreshLayout.setPullLoadMoreCompleted();
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    public void loadData() {

    }

    @Override
    public void setPresenter(PersenterView presenter) {
        this.persenterView = presenter;
    }

    public String getDifferent() {
        return different;
    }

    public void setDifferent(String different) {
        this.different = different;
    }


    public static int getScreenW(Context c) {
        return c.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenH(Context c) {
        return c.getResources().getDisplayMetrics().heightPixels;
    }

    public Bitmap myShot(Activity activity) {
        // 获取windows中最顶层的view
        View view = activity.getWindow().getDecorView();
        view.buildDrawingCache();

        // 获取状态栏高度
        Rect rect = new Rect();
        view.getWindowVisibleDisplayFrame(rect);
        int statusBarHeights = rect.top;
        DisplayMetrics displayMetrics = new DisplayMetrics();

        // 获取屏幕宽和高
        int widths = getScreenW(getActivity());
        int heights = getScreenH(getActivity());

        // 允许当前窗口保存缓存信息
        view.setDrawingCacheEnabled(true);

        // 去掉状态栏
        Bitmap bmp = Bitmap.createBitmap(view.getDrawingCache(), 0, statusBarHeights, widths,
                heights - statusBarHeights);

        // 销毁缓存信息
        view.destroyDrawingCache();

        if (bmp == null) {
            Toast.makeText(getActivity(), "bmp==null", Toast.LENGTH_LONG).show();
        }
        return bmp;
    }

    private void saveBitmap() {
        // TODO Auto-generated method stub
        Bitmap bitmap = null;
//        relativeLayout.buildDrawingCache();
//        relativeLayout.setDrawingCacheEnabled(true);
//        bitmap = relativeLayout.getDrawingCache();
//        relativeLayout.setDrawingCacheEnabled(false);
        File appDir = new File(Environment.getExternalStorageDirectory(), "Boohee");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        if (bitmap == null) {
            Toast.makeText(getContext(), "Bitmap==null", Toast.LENGTH_LONG).show();
            bitmap = myShot(getActivity());

        }
        String fileName = "main2015cache" + ".png";

        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
