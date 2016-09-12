package com.trimph.toprand.trimphrxandroid.trimph.ui.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.trimph.toprand.trimphrxandroid.R;
import com.trimph.toprand.trimphrxandroid.trimph.Iservice.PictureBean;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.main.presenter.MainPresenterImpl;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.main.view.MainView;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.news.newschild.DifferentNewsFragment;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.presenter.PicturePresenterImpl;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.view.PictureViewImpl;
import com.trimph.toprand.trimphrxandroid.trimph.utils.ActivityUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tao on 2016/8/4.
 */

public class TrimphMainActivity extends AppCompatActivity implements MainView {


    public String TAG = TrimphMainActivity.class.getSimpleName();
    public PictureViewImpl pictureView;
    public PictureBean pictureBean;
    AlertDialog alertDialog;
    PicturePresenterImpl picturePresenter;
    @Bind(R.id.frameContent)
    FrameLayout frameContent;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.navigation)
    NavigationView navigation;
    @Bind(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_main_activity);
        ButterKnife.bind(this);
        MainPresenterImpl mainPresenter = new MainPresenterImpl(this);

        setSupportActionBar(toolbar);
        toolbar.setTitle("首页");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                return false;
            }
        });

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
//        drawerLayout.openDrawer(GravityCompat.END);
        DifferentNewsFragment differentNewsFragment = new DifferentNewsFragment();
        ActivityUtils.swichFragment(getSupportFragmentManager(), R.id.frameContent, differentNewsFragment);
    }

    @Override
    public void switch2Images() {
        /**
         * 夜间
         */
        setTheme(R.style.NightTheme);
    }

    @Override
    public void switch2Weather() {
        /**
         * 日间
         */
        setTheme(R.style.DayTheme);
    }

    @Override
    public void switch2About() {

    }
}
