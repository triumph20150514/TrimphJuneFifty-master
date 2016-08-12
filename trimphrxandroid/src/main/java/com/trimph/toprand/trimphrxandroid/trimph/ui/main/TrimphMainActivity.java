package com.trimph.toprand.trimphrxandroid.trimph.ui.main;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.trimph.toprand.trimphrxandroid.R;
import com.trimph.toprand.trimphrxandroid.trimph.Iservice.PictureBean;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.main.mainfragment.MainFragment;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.main.presenter.MainPresenterImpl;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.main.view.MainView;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.news.NewsFragment;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.presenter.PicturePresenterImpl;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.view.PictureViewImpl;
import com.trimph.toprand.trimphrxandroid.trimph.utils.ActivityUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tao on 2016/8/4.
 */

public class TrimphMainActivity extends FragmentActivity implements MainView {


    public String TAG = TrimphMainActivity.class.getSimpleName();
    public PictureViewImpl pictureView;
    public PictureBean pictureBean;
    AlertDialog alertDialog;
    PicturePresenterImpl picturePresenter;
    @Bind(R.id.frameContent)
    FrameLayout frameContent;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_main_activity);
        ButterKnife.bind(this);
        MainPresenterImpl mainPresenter = new MainPresenterImpl(this);
        toolbar.setTitle("首页");
        switch2News();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_item_news:
                switch2News();
                break;
            case R.id.navigation_item_about:

                break;
            case R.id.navigation_item_images:

                break;
        }
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        return true;
    }

    public void showLoadDialog() {
        Log.e(TAG, "showloadDialog");
        alertDialog = new AlertDialog.Builder(TrimphMainActivity.this)
                .setMessage("正在加载。。。。")
                .setIcon(R.mipmap.ic_launcher)
                .create();
        alertDialog.show();
    }


    @Override
    public void switch2News() {
        NewsFragment mainFragment = new NewsFragment();
        ActivityUtils.swichFragment(getSupportFragmentManager(), R.id.frameContent, mainFragment);
    }

    @Override
    public void switch2Images() {

    }

    @Override
    public void switch2Weather() {

    }

    @Override
    public void switch2About() {

    }
}
