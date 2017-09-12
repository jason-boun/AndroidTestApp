package com.jason.test.testapp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWindowTheme();
        intContentView();
        initLocalData();
        initView();
        initRemoteData();
    }

    /**
     * 定制化window特性如全屏等，子类可选择覆写
     */
    protected void setWindowTheme() {
    }

    /**
     * 加载本地layout文件，初始化contentView
     */
    protected void intContentView() {
        getSupportActionBar().hide();
        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
        }
    }

    /**
     * 加载本地layout文件，初始化contentView
     */
    protected abstract int getLayoutId();

    /**
     * 加载本地必要的数据，为view绑定数据做准备
     */
    protected abstract void initLocalData();

    /**
     * 初始化各种view控件
     */
    protected abstract void initView();

    /**
     * 加载数据，注意耗时操作在workThread操作
     */
    protected abstract void initRemoteData();

    /**
     * 点击事件处理，子类可覆写，覆写注意super调用
     * @param v
     */
    @Override
    public void onClick(View v) {

    }
}
