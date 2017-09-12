package com.jason.test.testapp.base;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * Created by JiaBo on 2017/9/12.
 */

public class Config {
    /**
     * 通过该方法来统一初始化各种配置信息
     * 类似于外观模式思路
     */
    public static void init() {
        initLogger();
    }

    /**
     * 初始化Logger工具
     */
    public static void initLogger() {
        switch (Constant.envType) {
            case test:
            case preview:
                Logger.init(Constant.DEBUG_TAG).setMethodCount(4);
                break;
            case online:
                Logger.init(Constant.DEBUG_TAG).setLogLevel(LogLevel.NONE);
                break;
        }
    }
}
