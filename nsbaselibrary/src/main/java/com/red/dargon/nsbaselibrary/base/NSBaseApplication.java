package com.red.dargon.nsbaselibrary.base;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.multidex.MultiDexApplication;

/**
 * 最基本的application  再让子类去做具体的初始化操作
 * Created by dargon on 2017/6/20.
 */

public class NSBaseApplication extends MultiDexApplication {
    private static Context applicationContext;
    private static Handler applicationHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
        applicationHandler = new Handler(Looper.myLooper());
    }


    //提供一个公共handler使用
    public static Handler getHandler() {
        if (applicationHandler == null) {
            applicationHandler = new Handler(Looper.myLooper());
        }
        return applicationHandler;
    }

    //提供一个静态方法获取实例
    public static Context getApplicationInstance() {
        return applicationContext;
    }
}
