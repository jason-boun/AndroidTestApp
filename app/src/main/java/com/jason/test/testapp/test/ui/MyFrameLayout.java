package com.jason.test.testapp.test.ui;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.jason.test.testapp.base.Constant;
import com.orhanobut.logger.Logger;

/**
 * Created by JiaBo on 2017/9/11.
 */

public class MyFrameLayout extends FrameLayout {
    public MyFrameLayout(@NonNull Context context) {
        super(context);
    }

    public MyFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        Logger.i(Constant.PRINT_TAG + "MyFrameLayout:" + "onKeyLongPress");
        return super.onKeyLongPress(keyCode, event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Logger.i(Constant.PRINT_TAG + "MyFrameLayout:" + "onKeyDown");
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Logger.i(Constant.PRINT_TAG + "MyFrameLayout:" + "onKeyUp");
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Logger.i(Constant.PRINT_TAG + "MyFrameLayout:" + "onTouchEvent");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Logger.i(Constant.PRINT_TAG + "MyFrameLayout:" + "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Logger.i(Constant.PRINT_TAG + "MyFrameLayout:" + "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Logger.i(Constant.PRINT_TAG + "MyFrameLayout:" + "ACTION_UP");
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Logger.i(Constant.PRINT_TAG + "MyFrameLayout:" + "onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
//        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Logger.i(Constant.PRINT_TAG + "MyFrameLayout:" + "dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
//        return true;
    }

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        Logger.i(Constant.PRINT_TAG + "MyFrameLayout:" + "onGenericMotionEvent");
        return super.onGenericMotionEvent(event);
    }
}
