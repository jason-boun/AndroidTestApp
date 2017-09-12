package com.jason.test.testapp.test.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.jason.test.testapp.base.Constant;
import com.orhanobut.logger.Logger;

/**
 * Created by JiaBo on 2017/9/11.
 */

public class MyTextView extends TextView {
    public MyTextView(Context context) {
        super(context);
        setOnTouchListener();
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener();
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnTouchListener();
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setOnTouchListener();
    }

    private void setOnTouchListener() {
        setOnTouchListener(myTouchListener);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        Logger.i(Constant.PRINT_TAG + "MyTextView:" + "onKeyLongPress");
        return super.onKeyLongPress(keyCode, event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Logger.i(Constant.PRINT_TAG + "MyTextView:" + "onKeyDown");
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Logger.i(Constant.PRINT_TAG + "MyTextView:" + "onKeyUp");
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Logger.i("MyTextView:" + "onTouchEvent");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Logger.i(Constant.PRINT_TAG + "MyTextView:" + "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Logger.i(Constant.PRINT_TAG + "MyTextView:" + "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Logger.i(Constant.PRINT_TAG + "MyTextView:" + "ACTION_UP");
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Logger.i(Constant.PRINT_TAG + "MyTextView:" + "dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        Logger.i(Constant.PRINT_TAG + "MyTextView:" + "onGenericMotionEvent");
        return super.onGenericMotionEvent(event);
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        super.setOnTouchListener(l);
    }

    public OnTouchListener myTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Logger.i("MyTextView:" + "onTouchEvent");
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    Logger.i(Constant.PRINT_TAG + "tv111:" + "ACTION_DOWN");
                    break;
                case MotionEvent.ACTION_MOVE:
                    Logger.i(Constant.PRINT_TAG + "tv111:" + "ACTION_MOVE");
                    break;
                case MotionEvent.ACTION_UP:
                    Logger.i(Constant.PRINT_TAG + "tv111:" + "ACTION_UP");
                    break;
            }
            return false;
        }
    };
}
