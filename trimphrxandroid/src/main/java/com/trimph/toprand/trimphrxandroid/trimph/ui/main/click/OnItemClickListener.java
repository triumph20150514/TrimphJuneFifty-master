package com.trimph.toprand.trimphrxandroid.trimph.ui.main.click;

import android.view.View;
import android.widget.Toast;

/**
 * Created by tao on 2016/8/27.
 */

public interface OnItemClickListener<T> {
    void onItemClick(int position, View view, T t);
}
