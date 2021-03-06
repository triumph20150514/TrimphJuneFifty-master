package com.trimh.trimphmvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.trimh.trimphmvp.copy.NodeProgressView;
import com.trimh.trimphmvp.load.FloxLoadView;
import com.trimh.trimphmvp.view.node.LogisticsData;
import com.trimh.trimphmvp.view.node.NodeProgressAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<LogisticsData> logisticsDatas;
    FloxLoadView floxLoadView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();

        floxLoadView = (FloxLoadView) findViewById(R.id.floxLoadView);
        floxLoadView.startAnim();
        NodeProgressView nodeProgressView = (NodeProgressView) findViewById(R.id.npv_NodeProgressView);
        nodeProgressView.setNodeProgressAdapter(new com.trimh.trimphmvp.copy.NodeProgressAdapter() {
            @Override
            public List<LogisticsData> getDatas() {
                return logisticsDatas;
            }

            @Override
            public int getCount() {
                return logisticsDatas.size();
            }
        });

    }

    private void initData() {
        logisticsDatas = new ArrayList<>();
        logisticsDatas.add(new LogisticsData().setTime("2016-6-28 15:13:02").setContext("快件在【相城中转仓】装车,正发往【无锡分拨中心】已签收,签收人是【王漾】,签收网点是【忻州原平】"));
        logisticsDatas.add(new LogisticsData().setTime("2016-6-28 15:13:02").setContext("快件在【相城中转仓】装车,正发往【无锡分拨中心】"));
        logisticsDatas.add(new LogisticsData().setTime("2016-6-28 15:13:02").setContext("【北京鸿运良乡站】的【010058.269】正在派件"));
        logisticsDatas.add(new LogisticsData().setTime("2016-6-28 15:13:02").setContext("快件到达【潍坊市中转部】,上一站是【】"));
        logisticsDatas.add(new LogisticsData().setTime("2016-6-28 15:13:02").setContext("快件在【潍坊市中转部】装车,正发往【潍坊奎文代派】"));
        logisticsDatas.add(new LogisticsData().setTime("2016-6-28 15:13:02").setContext("快件到达【潍坊】,上一站是【潍坊市中转部】"));
        logisticsDatas.add(new LogisticsData().setTime("2016-6-28 15:13:02").setContext("快件在【武汉分拨中心】装车,正发往【晋江分拨中心】"));
        logisticsDatas.add(new LogisticsData().setTime("2016-6-28 15:13:02").setContext("【北京鸿运良乡站】的【010058.269】正在派件"));
        logisticsDatas.add(new LogisticsData().setTime("2016-6-28 15:13:02").setContext("【北京鸿运良乡站】的【010058.269】正在派件"));
        logisticsDatas.add(new LogisticsData().setTime("2016-6-28 15:13:02").setContext("【北京鸿运良乡站】的【010058.269】正在派件"));
        logisticsDatas.add(new LogisticsData().setTime("2016-6-28 15:13:02").setContext("【北京鸿运良乡站】的【010058.269】正在派件"));
    }
}
