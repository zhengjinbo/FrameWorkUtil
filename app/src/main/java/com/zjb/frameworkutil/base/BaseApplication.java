package com.zjb.frameworkutil.base;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;


/**
 * @author LCM
 * @date 2019/5/7 11:50
 * 全局的Application
 */
public class BaseApplication extends Application {

    private static BaseApplication mApplication;
    public static int mWidthPixels;
    public static int mHeightPixels;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        DisplayMetrics dm = new DisplayMetrics();
        mHeightPixels = dm.heightPixels;
        mWidthPixels = dm.widthPixels;

    }

    public static Context getInstance() {
        return mApplication;
    }
}
