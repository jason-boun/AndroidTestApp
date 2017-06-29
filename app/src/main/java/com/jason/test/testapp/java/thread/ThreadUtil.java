package com.jason.test.testapp.java.thread;

import android.support.annotation.NonNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by JiaBo on 2017/6/8.
 */

public class ThreadUtil {

    public static void main(String[] args) {
        new LockThreadOperate().lockThreadTest();
        HashMap map = new HashMap();
        map.keySet();
        map.entrySet();
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        HashSet set = new HashSet();
    }

    public static void testThread() {

        Future mFuture = new Future() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public Object get() throws InterruptedException, ExecutionException {
                return null;
            }

            @Override
            public Object get(long timeout, @NonNull TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return null;
            }
        };

        Callable mCallable = new Callable() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        };

        Runnable mRunnable = new Runnable() {
            @Override
            public void run() {

            }
        };

        RunnableFuture mRunnableFuture = new RunnableFuture() {
            @Override
            public void run() {

            }

            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public Object get() throws InterruptedException, ExecutionException {
                return null;
            }

            @Override
            public Object get(long timeout, @NonNull TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return null;
            }
        };
    }


}
