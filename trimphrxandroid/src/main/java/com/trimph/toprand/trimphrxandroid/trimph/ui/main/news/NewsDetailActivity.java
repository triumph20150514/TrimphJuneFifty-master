package com.trimph.toprand.trimphrxandroid.trimph.ui.main.news;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.trimph.toprand.trimphrxandroid.R;
import com.trimph.toprand.trimphrxandroid.trimph.base.BaseActivity;
import com.trimph.toprand.trimphrxandroid.trimph.ui.main.model.NewsBean;

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

}
