package com.trimph.toprand.trimphrxandroid.trimph.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.trimph.toprand.trimphrxandroid.trimph.utils.NetUtils;

/**
 * Created by tao on 2016/8/26.
 */

public class NetConnectBroadCast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action == context.NETWORK_STATS_SERVICE) {
            if (!NetUtils.isConnected(context)) {
                Toast.makeText(context, "网络没有连接！！", Toast.LENGTH_LONG).show();
            }
        }
    }
}
