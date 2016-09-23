package com.trimph.toprand.trimphrxandroid.trimph.ui.main.news;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.trimph.toprand.trimphrxandroid.R;
import com.trimph.toprand.trimphrxandroid.trimph.base.BaseActivity;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.model.NewsBean;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tao on 2016/8/27.
 */

public class NewsDetailActivity extends BaseActivity {

    @Bind(R.id.webView)
    WebView webView;

    public NewsBean.DataBean dataBean;
    @Bind(R.id.progressbar)
    ProgressBar progressbar;
    @Bind(R.id.toolbar_detail)
    Toolbar toolbarDetail;

    @Override
    protected void init() {
        if (getIntent().hasExtra(NewsFragment.ENWSBEAN)) {
            dataBean = getIntent().getParcelableExtra(NewsFragment.ENWSBEAN);
            if (dataBean == null) {
                return;
            }
        }

        toolbarDetail.setBackgroundColor(R.color.colorAccent);
        toolbarDetail.setTitle(dataBean.getTitle());
        toolbarDetail.setNavigationIcon(R.mipmap.icon_arrow_left);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    progressbar.setVisibility(View.GONE);
                } else {
                    if (progressbar.getVisibility() == View.GONE) {
                        progressbar.setVisibility(View.VISIBLE);
                        progressbar.setProgress(newProgress);
                    }
                }
                super.onProgressChanged(view, newProgress);
            }


            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                return super.onShowFileChooser(webView, filePathCallback, fileChooserParams);
            }
        });
        webView.loadUrl(dataBean.getUrl());
    }

    @Override
    protected int LayoutResId() {
        return R.layout.news_detail_layout;
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


  /*  文／简名（简书作者）
    原文链接：http://www.jianshu.com/p/7f5a9969be53
    著作权归作者所有，转载请联系作者获得授权，并标注“简书作者”。*/
}
