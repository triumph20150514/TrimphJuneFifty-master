package com.trimph.toprand.trimphrxandroid.trimph.ui.main.news.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tao on 2016/8/26.
 */

public class DifferentNewsAdapter extends FragmentPagerAdapter {

    public List<Fragment> fragmentList = new ArrayList<>();
    public List<String> titles = new ArrayList<>();
    public Context context;

    public DifferentNewsAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }


    @Override
    public Fragment getItem(int position) {
        if (fragmentList == null) {
            return null;
        }
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size() > 0 ? fragmentList.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                Log.e("trimph", "titles titles----------" + titles.get(position));
                return titles.get(position);
            case 1:
                return titles.get(position);
            case 2:
                return titles.get(position);
            case 3:
                return titles.get(position);
            case 4:
                return titles.get(position);
            case 5:
                return titles.get(position);
            case 6:
                return titles.get(position);
            case 7:
                return titles.get(position);
            case 8:
                return titles.get(position);
            case 9:
                return titles.get(position);
        }
        return super.getPageTitle(position);
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
        notifyDataSetChanged();
    }

    public List<Fragment> getFragmentList() {
        return fragmentList;
    }

    public void setFragmentList(List<Fragment> fragmentList) {
        this.fragmentList = fragmentList;
    }
}
