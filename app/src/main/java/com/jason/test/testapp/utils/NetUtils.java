package com.jason.test.testapp.utils;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Looper;
import android.support.v4.net.ConnectivityManagerCompat;
import android.telephony.TelephonyManager;


public class NetUtils {

    /**
     * 判断网络是否可用并做出提示
     *
     * @return
     */
    public static boolean checkNetWorkStatusAndTip(Application context) {
        if (isNetAvailable(context)) {
            return true;
        } else {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                if (context != null) {
//                    showToast(PlatformConfig.getAppContext().getResources().getString(R.string.app_name));
                }
            }
            return false;
        }
    }

    /**
     * 当前网络是否可用
     *
     * @return
     */
    public static boolean isNetAvailable(Application context) {
        if (context == null) {
            return false;
        }
        ConnectivityManager mConnectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = mConnectivity.getActiveNetworkInfo();
        return info != null && info.isAvailable() && info.isConnected();
    }

    /**
     * 当前网络是否为WIFI状态
     *
     * @param context
     * @return
     */
    public static boolean isWifiNet(Context context) {
        ConnectivityManager mConnectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = mConnectivity.getActiveNetworkInfo();
        return info != null && info.getType() == ConnectivityManager.TYPE_WIFI && info.isConnected();
    }

    /**
     * 当前网络是否为移动网络状态
     *
     * @param context
     * @return
     */
    public static boolean isMobileNet(Context context) {
        ConnectivityManager mConnectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = mConnectivity.getActiveNetworkInfo();
        return info != null && info.getType() == ConnectivityManager.TYPE_MOBILE && info.isConnected();
    }

    /**
     * 检查当前WIFI是否连接，两层意思——是否连接，连接是不是WIFI
     *
     * @param context
     * @return true表示当前网络处于连接状态，且是WIFI，否则返回false
     */
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnected() && ConnectivityManager.TYPE_WIFI == info.getType();
    }

    /**
     * 检查当前GPRS是否连接，两层意思——是否连接，连接是不是GPRS
     *
     * @param context
     * @return true表示当前网络处于连接状态，且是GPRS，否则返回false
     */
    public static boolean isGPRSConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnected() && ConnectivityManager.TYPE_MOBILE == info.getType();
    }

    /**
     * 检查当前是否连接
     *
     * @param context
     * @return true表示当前网络处于连接状态，否则返回false
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }

    /**
     * 对大数据传输时，需要调用该方法做出判断，如果流量敏感，应该提示用户
     *
     * @param context
     * @return true表示流量敏感，false表示不敏感
     */
    public static boolean isActiveNetworkMetered(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return ConnectivityManagerCompat.isActiveNetworkMetered(cm);
    }

    /**
     * 没有联网
     */
    public static final int NETWORK_TYPE_NONE = -1;
    /**
     * WIFI网络
     */
    public static final int NETWORK_TYPE_WIFI = 0;
    /**
     * 2G网络
     */
    public static final int NETWORK_TYPE_2G = 1;
    /**
     * 3G网络
     */
    public static final int NETWORK_TYPE_3G = 2;
    /**
     * 4G网络
     */
    public static final int NETWORK_TYPE_4G = 3;
    /**
     * 未知网络类型
     */
    public static final int NETWORK_TYPE_UNKNOWN = 4;

    /***
     * 获取网络类型接口
     * TODO：mtk和展讯平台的双卡双待兼容性
     *
     * @param context
     * @return
     */
    public static int getNetworkType(Context context) {
        int type = NETWORK_TYPE_NONE;
        final ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo info = cm.getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            if (ConnectivityManager.TYPE_MOBILE == info.getType()) {
                final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                type = getNetworkClass(tm.getNetworkType());
            } else {
                // 其他网络作为wifi网络看待
                type = NETWORK_TYPE_WIFI;
            }
        }
        return type;
    }

    /**
     * copy of hided Method {@code android.telephony.TelephonyManager#getNetworkClass}
     * <p>
     * Return general class of network type, such as "3G" or "4G". In cases
     * where classification is contentious, this method is conservative.
     */
    private static int getNetworkClass(int networkType) {
        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return NETWORK_TYPE_2G;
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return NETWORK_TYPE_3G;
            case TelephonyManager.NETWORK_TYPE_LTE:
                return NETWORK_TYPE_4G;
            default:
                return NETWORK_TYPE_UNKNOWN;
        }
    }

    public final static boolean isConnected(int netType) {
        return netType != NetUtils.NETWORK_TYPE_NONE;
    }

    public final static boolean isNoneOr2g(int netType) {
        return netType == NetUtils.NETWORK_TYPE_2G || netType == NetUtils.NETWORK_TYPE_NONE;
    }

    public final static boolean is3gOrUnknown(int netType) {
        return netType == NetUtils.NETWORK_TYPE_3G || netType == NetUtils.NETWORK_TYPE_UNKNOWN;
    }

    public final static boolean is4gOrWifi(int netType) {
        return netType == NetUtils.NETWORK_TYPE_4G || netType == NetUtils.NETWORK_TYPE_WIFI;
    }

}
