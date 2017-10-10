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

import com.didi.virtualapk.PluginManager;
import com.jason.test.testapp.R;
import com.jason.test.testapp.data.BaseEvent;
import com.jason.test.testapp.data.event.ParentEvent;
import com.jason.test.testapp.data.event.SubEventOne;
import com.jason.test.testapp.data.event.SubEventTwo;
import com.jason.test.testapp.service.MService;
import com.jason.test.testapp.utils.ToastUtils;
import com.jason.test.testapp.utils.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import static android.os.Environment.getExternalStorageDirectory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //网络状态变更广播接收器
    TextView mainTv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }

        Utils.init(getApplicationContext());
        setContentView(R.layout.activity_main);
        mainTv1 = (TextView) findViewById(R.id.main_tv1);
        EventBus.getDefault().register(this);
        setTitle("首页");
        findViewById(R.id.main_tv2).setOnClickListener(this);
        findViewById(R.id.main_tv1).setOnClickListener(this);
        reloadPlugin();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_tv1:
//                ToastTest();
//                printForegroundInfo();
//                startPluginPage();
                startActivity(new Intent(MainActivity.this, ThirdActivity.class));
                break;
            case R.id.main_tv2:
                startActivity(new Intent(MainActivity.this, GaoSiTest.class));
                break;
        }
    }

    public void reloadPlugin() {
        PluginManager pluginManager = PluginManager.getInstance(this);
        File apk = new File(getExternalStorageDirectory() + "/vtplugin/", "plugin.apk");
        if (apk.exists()) {
            try {
                pluginManager.loadPlugin(apk);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void startPluginPage() {
        if (PluginManager.getInstance(this).getLoadedPlugin("com.jason.vtplugin") == null) {
            Toast.makeText(getApplicationContext(), "插件未加载,请尝试重启APP", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent();
        intent.setClassName("com.jason.vtplugin", "com.jason.vtplugin.MainActivity");
        startActivity(intent);
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
    public void handleEventOnMain(ParentEvent event) {
        if (event instanceof SubEventOne) {
            ToastUtils.showLongToast("SubEventOne");
            mainTv1.setText(((SubEventOne) event).getSubName());
        } else if (event instanceof SubEventTwo) {
            ToastUtils.showLongToast("SubEventTwo");
            mainTv1.setText(((SubEventTwo) event).getSubName());
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
