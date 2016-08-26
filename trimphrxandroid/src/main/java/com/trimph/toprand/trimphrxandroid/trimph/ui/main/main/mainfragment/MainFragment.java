package com.trimph.toprand.trimphrxandroid.trimph.ui.main.main.mainfragment;

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

import com.trimph.toprand.trimphrxandroid.R;
import com.trimph.toprand.trimphrxandroid.trimph.Iservice.PictureBean;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.main.mainfragment.presenter.IPicturePresenter;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.main.mainfragment.presenter.PicturePresenterImpl;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.main.mainfragment.view.IPictureView;
import com.trimph.toprand.trimphrxandroid.trimph.utils.DividerItemDecoration;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tao on 2016/8/10.
 */

public class MainFragment extends Fragment implements IPictureView {

    public IPicturePresenter iPicturePresenter;
    public AlertDialog alertDialog;

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    public PictureAdapter pictureAdapter;

    public int page = 1;
    public int count = 20;

    public boolean isFrefsh = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.picture_main_fragment, container, false);
        ButterKnife.bind(this, view);
        init();
        iPicturePresenter = new PicturePresenterImpl(this);
        iPicturePresenter.loadPicture(page, count);
        try {
            URLEncoder.encode("ssfe","utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return view;
    }

    private void init() {
        pictureAdapter = new PictureAdapter(getContext());
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
                iPicturePresenter.loadPicture(page, count);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void showDialog() {
        alertDialog = new AlertDialog.Builder(getContext())
                .setMessage("正在加载。。。。")
                .setIcon(R.mipmap.ic_launcher)
                .create();
        alertDialog.show();
    }

    @Override
    public void loadData(PictureBean pictureBean) {
        if (!isFrefsh) {
            pictureAdapter.appendTngonBeans(pictureBean.getTngou());
        } else {
            pictureAdapter.setTngouBeans(pictureBean.getTngou());
        }
        pictureAdapter.notifyDataSetChanged();
    }

    @Override
    public void dismissDialog() {
        alertDialog.dismiss();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void setPresenter(IPicturePresenter presenter) {
        iPicturePresenter = presenter;
    }
}
