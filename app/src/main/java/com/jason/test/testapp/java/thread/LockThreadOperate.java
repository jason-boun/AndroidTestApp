package com.jason.test.testapp.java.thread;

import com.jason.test.testapp.utils.LogUtil;

/**
 * Created by JiaBo on 2017/6/8.
 * 死锁测试
 */

public class LockThreadOperate {

    /**
     * 线程阻塞测试
     */
    public void lockThreadTest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                read();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                write(32, 23);
            }
        }).start();
    }

    //两把对象锁
    private Resource resourceA = new Resource();
    private Resource resourceB = new Resource();

    /**
     * 一个线程的读操作
     *
     * @return
     */
    public int read() {
        int result = 0;
        synchronized (resourceA) {
            try {
                LogUtil.print(Thread.currentThread().getName() + ":read-before");
                Thread.sleep(3000);
                synchronized (resourceB) {
                    result = resourceB.value + resourceA.value;
                    LogUtil.print(Thread.currentThread().getName() + ":read-after");
                    return result;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 另一个线程的写操作
     *
     * @param a
     * @param b
     */
    public void write(int a, int b) {
        synchronized (resourceB) {
            try {
                LogUtil.print(Thread.currentThread().getName() + ":write-before");
                Thread.sleep(2000);
                synchronized (resourceA) {
                    resourceA.value = a;
                    resourceB.value = b;
                    LogUtil.print(Thread.currentThread().getName() + ":write-after");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
