package com.trimph.toprand.trimphrxandroid.trimph.ui.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.FrameLayout;

import com.jaeger.library.StatusBarUtil;
import com.trimph.toprand.trimphrxandroid.R;
import com.trimph.toprand.trimphrxandroid.trimph.Iservice.PictureBean;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.main.presenter.MainPresenterImpl;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.main.view.MainView;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.news.newschild.DifferentNewsFragment;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.presenter.PicturePresenterImpl;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.view.PictureViewImpl;
import com.trimph.toprand.trimphrxandroid.trimph.utils.ActivityUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

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
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.picture_main_activity);
        ButterKnife.bind(this);
        MainPresenterImpl mainPresenter = new MainPresenterImpl(this);
        setStatusBarColor();
        MIUISetStatusBarLightMode(getWindow(),true);
        setSupportActionBar(toolbar);
        toolbar.setTitle("首页");
//        toolbar.inflateMenu(R.menu.my_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /**
         *
         */
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

    public void setStatusBarColor() {
        StatusBarUtil.setColorForDrawerLayout(this, drawerLayout, getResources().getColor(R.color.nodeColor));
//        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorAccent), 120);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
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


    /**
     * 设置状态栏字体图标为深色，需要MIUIV6以上
     * @param window 需要设置的窗口
     * @param dark 是否把状态栏字体及图标颜色设置为深色
     * @return  boolean 成功执行返回true
     *
     */
    public static boolean MIUISetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            Class clazz = window.getClass();
            try {
                int darkModeFlag = 0;
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                if(dark){
                    extraFlagField.invoke(window,darkModeFlag,darkModeFlag);//状态栏透明且黑色字体
                }else{
                    extraFlagField.invoke(window, 0, darkModeFlag);//清除黑色字体
                }
                result=true;
            }catch (Exception e){

            }
        }
        return result;
    }
}
