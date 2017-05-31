package com.jason.test.testapp.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.text.TextUtils;

/**
 * Created by JiaBo on 2017/5/18.
 */

public class AppInfoUtil {

    public static boolean isRunningForeground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        String currentPackageName = cn.getPackageName();
        if (!TextUtils.isEmpty(currentPackageName) && currentPackageName.equals(getPackageName(context))) {
            return true;
        }
        return false;
    }

    public static String getPackageName(Context context) {
        String packageName = "";
        try {
            return context.getPackageName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return packageName;
    }
}
