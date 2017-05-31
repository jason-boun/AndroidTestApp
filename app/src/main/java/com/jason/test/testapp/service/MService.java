package com.jason.test.testapp.service;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.jason.test.testapp.utils.AppInfoUtil;
import com.jason.test.testapp.utils.LogUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by JiaBo on 2017/5/18.
 */

public class MService extends Service {
    //设置轮询时间
    public static final int loopTime = 60;
    private Timer timer;
    private TimerTask task;

    @Override
    public IBinder onBind(Intent intent) {
        return new Binder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        startTimer();
        IntentService intentService = new MyIntentService("myIntentService");
        intentService.startForeground(0,null);
        intentService.onStart(new Intent(),0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopTimer();
    }

    public void printForegroundInfo() {
        boolean result = AppInfoUtil.isRunningForeground(this);
        LogUtil.printSystemInfo("isRunningForeground==" + result);
    }

    /**
     * 启动定时器
     */
    public void startTimer() {
        if (timer == null) {
            timer = new Timer();
            task = new TimerTask() {
                @Override
                public void run() {
                    printForegroundInfo();
                }
            };
            timer.schedule(task, 1000, 1000 * loopTime);
        }
    }

    /**
     * 停止定时器
     */
    public void stopTimer() {
        if (timer != null) {
            task.cancel();
            timer.cancel();
            task = null;
            timer = null;
        }
    }
}
