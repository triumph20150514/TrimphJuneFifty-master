package com.trimh.trimphmvp.copy;

import com.trimh.trimphmvp.view.node.LogisticsData;

import java.util.List;

/**
 * Created by tao on 2016/7/28.
 */

public interface NodeProgressAdapter {

    List<LogisticsData> getDatas();

    int getCount();
}
