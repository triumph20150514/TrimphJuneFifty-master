package com.trimph.toprand.trimphrxandroid.trimph.ui.main.news.newschild;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.google.common.collect.Collections2;
import com.trimph.toprand.trimphrxandroid.R;
import com.trimph.toprand.trimphrxandroid.trimph.Contants.ContantsObj;
import com.trimph.toprand.trimphrxandroid.trimph.base.BaseFragment;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.news.NewsFragment;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.news.adapter.DifferentNewsAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 新闻类
 * Created by tao on 2016/8/26.
 */

public class DifferentNewsFragment extends BaseFragment {

    //    @Bind(R.id.PagerTitle)
//    PagerTitleStrip pagerSlidingTabStrip;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    public List<Fragment> fragmentList = new ArrayList<>();
    public List<String> titles = new ArrayList<>();
    public List<String> pinyins = new ArrayList<>();
    public DifferentNewsAdapter differentNewsAdapter;


    @Override
    protected int layoutResId() {
        return R.layout.new_main_fragmnet;
    }

    @Override
    protected void init() {
        differentNewsAdapter = new DifferentNewsAdapter(this.getContext(), getActivity().getSupportFragmentManager());
        viewPager.setAdapter(differentNewsAdapter);

//        pagerSlidingTabStrip.setViewPager(viewPager);

        initFragmnet();
        differentNewsAdapter.setFragmentList(fragmentList);
        differentNewsAdapter.setTitles(titles);
        differentNewsAdapter.notifyDataSetChanged();
    }

    public void initFragmnet() {
        String[] strings = context.getResources().getStringArray(R.array.different_news);
        Collections.addAll(titles, strings);

        String[] pinyin = context.getResources().getStringArray(R.array.different_news_pinyin);
//        Collections.addAll(pinyins, pinyin);

        for (int i = 0; i < strings.length; i++) {
            NewsFragment newsFragment = new NewsFragment();
            newsFragment.setDifferent(pinyin[i]);
            fragmentList.add(newsFragment);
        }

    }


}
