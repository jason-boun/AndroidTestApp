package com.jason.test.testapp.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by JiaBo on 2017/10/18.
 */

public class ViewUtil {

    /**
     * 得到屏幕高度
     *
     * @param context
     * @return
     */
    public static int getHeight(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        Display display = getResolution(context);
        display.getMetrics(dm);
        return dm.heightPixels;
    }

    // 分辨率
    public static Display getResolution(Context context) {
        return ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
    }

}
