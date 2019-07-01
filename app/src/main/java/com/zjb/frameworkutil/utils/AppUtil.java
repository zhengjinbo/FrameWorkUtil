package com.zjb.frameworkutil.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;
import com.zjb.frameworkutil.base.BaseApplication;

/**
 * @author LCM
 * @date 2019/6/10 14:34
 */
public class AppUtil {
    private volatile static Gson gson = null;

    //获取单例
    public static Gson getGson() {
        if (gson == null) {
            synchronized (BaseApplication.getInstance()) {
                if (gson == null) {
                    gson = new Gson();
                }
            }
        }
        return gson;
    }


    /**
     * 描述：判断网络是否有效.
     *
     * @return true, if is network available
     */
    public static boolean isNetworkAvailable(Context context) {
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info != null && info.isConnected()) {
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

}
