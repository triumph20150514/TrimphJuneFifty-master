package com.trimph.toprand.trimphrxandroid.trimph.ui.main.news.newschild;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.trimph.toprand.trimphrxandroid.R;
import com.trimph.toprand.trimphrxandroid.trimph.base.BaseFragment;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.news.NewsFragment;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.news.adapter.DifferentNewsAdapter;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.news.view.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;

/**
 * 新闻类
 * Created by tao on 2016/8/26.
 */

public class DifferentNewsFragment extends BaseFragment {

    @Bind(R.id.PagerTitle)
    PagerSlidingTabStrip pagerSlidingTabStrip;
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

        initFragmnet();
        differentNewsAdapter.setFragmentList(fragmentList);
        differentNewsAdapter.setTitles(titles);
        Log.e("trimph", "titles----------" + titles.toString());
        differentNewsAdapter.notifyDataSetChanged();
        pagerSlidingTabStrip.setViewPager(viewPager);
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
