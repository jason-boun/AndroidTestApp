package com.jason.test.testapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.jason.test.testapp.R;
import com.jason.test.testapp.data.MsgEvent;
import com.jason.test.testapp.utils.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    //网络状态变更广播接收器
    TextView mainTv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.init(getApplicationContext());
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainTv1 = (TextView) findViewById(R.id.main_tv1);
        EventBus.getDefault().register(this);
        setTitle("首页");
        findViewById(R.id.main_tv2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
    }


    @OnClick({R.id.main_tv1, R.id.main_tv2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_tv1:
                break;
            case R.id.main_tv2:
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEventOnMain(MsgEvent msgEvent) {
        if (msgEvent != null) {
            mainTv1.setText(msgEvent.getMsg());
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
