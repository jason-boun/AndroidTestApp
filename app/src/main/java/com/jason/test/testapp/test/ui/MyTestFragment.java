package com.jason.test.testapp.test.ui;

import android.view.View;
import android.widget.RelativeLayout;

import com.jason.test.testapp.R;
import com.jason.test.testapp.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by JiaBo on 2017/9/11.
 */

public class MyTestFragment extends BaseFragment {


    @BindView(R.id.tv1)
    MyTextView tv1;
    @BindView(R.id.tv2)
    MyTextView tv2;
    @BindView(R.id.my_test_fragment)
    MyFrameLayout myTestFragment;
    @BindView(R.id.home_root_container)
    RelativeLayout homeRootContainer;
    Unbinder unbinder;

    public static MyTestFragment newInstance() {
        return new MyTestFragment();
    }

    @Override
    protected void initLocalData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);

    }

    @Override
    protected void initRemoteData() {
//        tv1.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Logger.i("MyTextView:" + "onTouchEvent");
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        Logger.i(GSConstant.PRINT_TAG + "tv1:" + "ACTION_DOWN");
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        Logger.i(GSConstant.PRINT_TAG + "tv1:" + "ACTION_MOVE");
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        Logger.i(GSConstant.PRINT_TAG + "tv1:" + "ACTION_UP");
//                        break;
//                }
//                return false;
//            }
//        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv1, R.id.tv2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv1:
                break;
            case R.id.tv2:
                break;
        }
    }
}
