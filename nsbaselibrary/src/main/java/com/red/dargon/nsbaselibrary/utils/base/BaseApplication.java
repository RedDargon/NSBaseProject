package com.red.dargon.nsbaselibrary.utils.base;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.multidex.MultiDexApplication;

/**
 * 最基本的application  再让子类去做具体的初始化操作
 * Created by dargon on 2017/6/20.
 */

public class BaseApplication extends MultiDexApplication {
    private static Context applicationContext;
    private static Handler applicationHandler;
//    protected static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
//        initGreenDao(this);
        applicationContext = this;
        applicationHandler = new Handler(Looper.myLooper());
    }


//    private static void initGreenDao(Context context) {
//        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(context, "heatedloan.db", null);
//        DaoMaster daoMaster = new DaoMaster(openHelper.getWritableDb());
//        daoSession = daoMaster.newSession();
//    }

    /**
     * 获取数据库实例
     */
//    public static DaoSession getDaoSession() {
//        if (daoSession == null) {
//            initGreenDao(getApplicationInstance());
//        }
//        return daoSession;
//    }


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
