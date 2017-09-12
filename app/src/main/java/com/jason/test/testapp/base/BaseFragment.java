package com.jason.test.testapp.base;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment implements View.OnClickListener, ReleaseInterface {

    protected View contentView;
    protected boolean hidden = false;

    /**
     * 返回属于该fragment的自定义Tag
     *
     * @return
     */
    public String getFragmentTag() {
        return this.getClass().getCanonicalName();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (contentView == null && getLayoutId() > 0) {
            contentView = inflater.inflate(getLayoutId(), container, false);
        }
        initLocalData(savedInstanceState);
        initLocalData();
        initView(contentView);
        initView(contentView, savedInstanceState);
        initRemoteData();
        ViewGroup parent = (ViewGroup) contentView.getParent();
        if (parent != null) {
            parent.removeView(contentView);
        }
        return contentView;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        this.hidden = hidden;
        if (hidden) {
            onHideFragment();
        } else {
            onShowFragment();
        }
    }

    /**
     * fragment显示回调
     */
    protected void onShowFragment() {
    }

    /**
     * fragment隐藏回调
     */
    protected void onHideFragment() {
    }

    /**
     * 点击事件回调，子类可覆写
     *
     * @param v
     */
    @Override
    public void onClick(View v) {

    }

    /**
     * 初始化view之前加载本地数据，比如通过intent或者arguments传递的数据
     */
    protected abstract void initLocalData();

    /**
     * {@link #initLocalData()} 重载方法，初始化view之前加载本地数据
     */
    protected void initLocalData(Bundle savedInstanceState) {
    }

    /**
     * fragment构建需要的布局文件id
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化布局内的控件
     *
     * @param view
     */
    protected abstract void initView(View view);

    /**
     * 用于需要 savedInstance 的 View 的初始化
     *
     * @param view 根视图
     * @param savedInstance bundle
     */
    protected void initView(View view, Bundle savedInstance){}

    /**
     * 获取数据，注意耗时操作在工作线程处理
     */
    protected abstract void initRemoteData();

    /**
     * activity是否可用
     *
     * @return
     */
    public boolean actAvailable() {
        return getActivity() != null;
    }

    /**
     * 销毁view
     */
    @Override
    public void onDestroy() {
        releaseData();
        super.onDestroy();
    }

    @Override
    public void releaseData(){
        contentView = null;
    }
}
