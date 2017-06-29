package com.jason.test.testapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jason.test.testapp.R;
import com.jason.test.testapp.data.MsgEvent;
import com.jason.test.testapp.service.MService;
import com.jason.test.testapp.utils.AppInfoUtil;
import com.jason.test.testapp.utils.LogUtil;
import com.jason.test.testapp.utils.ToastUtils;
import com.jason.test.testapp.utils.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //网络状态变更广播接收器
    TextView mainTv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.init(getApplicationContext());
        setContentView(R.layout.activity_main);
        mainTv1 = (TextView) findViewById(R.id.main_tv1);
        EventBus.getDefault().register(this);
        setTitle("首页");
        findViewById(R.id.main_tv2).setOnClickListener(this);
        findViewById(R.id.main_tv1).setOnClickListener(this);
        ViewGroup viewGroup = new ViewGroup(this) {
            @Override
            protected void onLayout(boolean changed, int l, int t, int r, int b) {

            }
        };
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_tv1:
//                ToastTest();
                printForegroundInfo();
                break;
            case R.id.main_tv2:
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
                break;
        }
    }

    public void ObserverTest() {
        BaseAdapter baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                return null;
            }
        };
        ListView listView = new ListView(this);
        listView.setAdapter(baseAdapter);
    }

    public void printForegroundInfo() {
        startService(new Intent(this, MService.class));
    }

    public void ToastTest() {
        for (int i = 0; i < 5; i++) {
            Toast.makeText(getApplication(), "" + i, Toast.LENGTH_SHORT).show();
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
