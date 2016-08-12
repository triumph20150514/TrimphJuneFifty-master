package com.trimh.trimphmvp;

import android.app.Activity;
import android.os.Bundle;

import com.trimh.trimphmvp.gradually.GraduallyTextTrimph;

/**
 * Created by tao on 2016/8/1.
 */

public class AnimatorImageActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animator_layout);

        GraduallyTextTrimph graduallyTextView = (GraduallyTextTrimph) findViewById(R.id.graduallyText);
        graduallyTextView.onStartAnimation();
    }
}
