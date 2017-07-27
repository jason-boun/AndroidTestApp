package com.jason.test.testapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.jason.test.testapp.R;
import com.jason.test.testapp.ui.BlurringView;

/**
 * Created by JiaBo on 2017/7/27.
 */

public class GaoSiTest extends Activity {

    private View resourceView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaosi);
        resourceView = findViewById(R.id.ll_resource);
        resourceView.postDelayed(new Runnable() {
            @Override
            public void run() {
                BlurringView blurringView = (BlurringView) findViewById(R.id.blurringView);
                blurringView.setBlurredView(resourceView);
                blurringView.invalidate();
            }
        }, 2000);

    }
}
