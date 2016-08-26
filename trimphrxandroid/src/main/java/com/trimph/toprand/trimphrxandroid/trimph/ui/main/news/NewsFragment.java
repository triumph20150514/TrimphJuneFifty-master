package com.trimph.toprand.trimphrxandroid.trimph.ui.main.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.trimph.toprand.trimphrxandroid.R;
import com.trimph.toprand.trimphrxandroid.trimph.Contants.ContantsObj;
import com.trimph.toprand.trimphrxandroid.trimph.Iservice.PictureBean;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.impl.ResultNet;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.main.mainfragment.presenter.IPicturePresenter;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.main.mainfragment.presenter.PicturePresenterImpl;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.main.mainfragment.view.IPictureView;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.model.NewsCommenBean;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.news.adapter.NewsAdapter;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.news.persenter.PersenterView;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.news.persenter.PersenterViewImpl;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.news.view.NewsView;
import com.trimph.toprand.trimphrxandroid.trimph.utils.DividerItemDecoration;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    //新闻类型
    public String different;

    public NewsAdapter pictureAdapter;

    public int page = 1;
    public int count = 20;

    public boolean isFrefsh = true;

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
        /***
         *
         */
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page += 1;
                isFrefsh = false;
                persenterView.getModelData(getContext(), resultNet, ContantsObj.news_key, "shehui");
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public ResultNet<NewsCommenBean> resultNet = new ResultNet<NewsCommenBean>() {
        @Override
        public void success(NewsCommenBean newsCommenBean) {
            if (newsCommenBean == null) {
                Toast.makeText(NewsFragment.this.getContext(), "没有数据返回", Toast.LENGTH_LONG).show();
                return;
            }
            pictureAdapter.setTngouBeans(newsCommenBean.getNewsBean().getData());
            pictureAdapter.notifyDataSetChanged();
        }

        @Override
        public void error(String message) {

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

}
