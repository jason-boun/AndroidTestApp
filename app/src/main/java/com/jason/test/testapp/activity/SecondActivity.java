package com.jason.test.testapp.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.BaseMovementMethod;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jason.test.testapp.R;
import com.jason.test.testapp.component.customtextview.SpannableStringUtils;
import com.jason.test.testapp.data.MsgEvent;
import com.jason.test.testapp.utils.NetUtils;
import com.jason.test.testapp.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

/**
 * Created by jasonbook on 2017/4/8.
 */

public class SecondActivity extends AppCompatActivity {

    protected NetworkStateChangeReceiver netChangeReceiver;
    private TextView tv_multi_test;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sencond);
        ButterKnife.bind(this);
        setTitle("第二页面");
        registerNetWorkChangeReceiver();

        findViewById(R.id.second_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new MsgEvent("fuck u!"));
                Toast.makeText(SecondActivity.this, "fuck u", Toast.LENGTH_SHORT).show();
            }
        });

        processMultiTest();
    }

    private void processMultiTest() {
        tv_multi_test = (TextView) findViewById(R.id.tv_multi_test);
        tv_multi_test.setMovementMethod(LinkMovementMethod.getInstance());
        SpannableStringBuilder builder = SpannableStringUtils.getBuilder("")
                .append("用户昵称：").setClickSpan(clickableSpan).setBackgroundColor(Color.parseColor("#ffffff"))
                .append("消息内容按地方骄傲的覅敌法到地方阿萨德发的发的说法手动阀大事发生地方")
                .append("图片").setResourceId(R.drawable.icon_women).create();
        tv_multi_test.setText(builder);

    }

    private ClickableSpan clickableSpan = new ClickableSpan() {
        @Override
        public void onClick(View widget) {
            ToastUtils.showShortToast("事件触发了");
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(Color.BLUE);
            ds.setUnderlineText(false);
        }
    };

    /**
     * 处理超链接捕获和跳转
     */
    class MyMovementMethod extends BaseMovementMethod {
        @Override
        public boolean onTouchEvent(TextView widget, Spannable text, MotionEvent event) {
            int action = event.getAction();

            if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_DOWN) {
                int x = (int) event.getX();
                int y = (int) event.getY();

                x -= widget.getTotalPaddingLeft();
                y -= widget.getTotalPaddingTop();

                x += widget.getScrollX();
                y += widget.getScrollY();

                Layout layout = widget.getLayout();
                int line = layout.getLineForVertical(y);
                int off = layout.getOffsetForHorizontal(line, x);

                ClickableSpan[] link = text.getSpans(off, off, ClickableSpan.class);

                if (link.length != 0) {
                    if (action == MotionEvent.ACTION_UP) {
                        if (link[0] instanceof URLSpan) {
                            String linkStr = ((URLSpan) link[0]).getURL();
                        }
                    }
                    return true;
                }
            }
            return false;
        }
    }


    /**
     * 动态注册网络变更广播接收器
     */
    protected void registerNetWorkChangeReceiver() {
        if (netChangeReceiver == null) {
            netChangeReceiver = new NetworkStateChangeReceiver();
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netChangeReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        unregisterNetWorkChangeReceiver();
        super.onDestroy();
    }

    /**
     * 动态注销网络变更广播接收器
     */
    protected void unregisterNetWorkChangeReceiver() {
        if (netChangeReceiver != null) {
            unregisterReceiver(netChangeReceiver);
            netChangeReceiver = null;
        }
    }

    public class NetworkStateChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
                if (NetUtils.isMobileNet(context))
                    Toast.makeText(context, "NetworkStateChangeReceiver", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
