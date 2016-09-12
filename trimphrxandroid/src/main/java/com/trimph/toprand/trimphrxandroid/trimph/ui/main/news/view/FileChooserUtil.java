package com.trimph.toprand.trimphrxandroid.trimph.ui.main.news.view;

import android.content.Intent;
import android.webkit.WebChromeClient;

/**
 * Created by tao on 2016/9/5.
 */

public class FileChooserUtil extends WebChromeClient.FileChooserParams {
    @Override
    public int getMode() {
        return 0;
    }

    @Override
    public String[] getAcceptTypes() {
        return new String[0];
    }

    @Override
    public boolean isCaptureEnabled() {
        return false;
    }

    @Override
    public CharSequence getTitle() {
        return null;
    }

    @Override
    public String getFilenameHint() {
        return null;
    }

    @Override
    public Intent createIntent() {
        return null;
    }
}
