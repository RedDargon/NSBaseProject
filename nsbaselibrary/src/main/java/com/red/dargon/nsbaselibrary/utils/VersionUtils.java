package com.red.dargon.nsbaselibrary.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.red.dargon.nsbaselibrary.base.BaseApplication;


/**
 * 版本相关工具类
 */

public class VersionUtils {

    private VersionUtils() {
        throw new IllegalArgumentException("cannot create VersionUtils constructor!");
    }

    public static PackageInfo getPackageInfo() {
        PackageInfo info = null;
        try {
            PackageManager manager = BaseApplication.getApplicationInstance().getPackageManager();
            info = manager.getPackageInfo(BaseApplication.getApplicationInstance().getPackageName(), PackageManager.GET_CONFIGURATIONS);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return info;
    }

    public static String getPackageName() {
        PackageInfo packageInfo = getPackageInfo();
        if (packageInfo == null) {
            return "";
        }
        return packageInfo.packageName;
    }

    /**
     * 获取应用版本名
     *
     * @return 版本名
     */
    public static String getVersionName() {
        PackageInfo packageInfo = getPackageInfo();
        if (packageInfo == null) {
            return "";
        }
        return packageInfo.versionName;
    }

    /**
     * 获取应用版本号
     *
     * @return 版本号
     */
    public static int getVersionCode() {
        PackageInfo packageInfo = getPackageInfo();
        if (packageInfo == null) {
            return -1;
        }
        return packageInfo.versionCode;
    }

    /**
     * 获取application中指定的meta-data
     *
     * @return 如果没有获取成功(没有对应值, 或者异常)，则返回值为空
     */
    public static String getAppMetaData(Context ctx, String key) {
        if (ctx == null || TextUtils.isEmpty(key)) {
            return null;
        }
        String resultData = null;
        try {
            PackageManager packageManager = ctx.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(ctx.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        resultData = applicationInfo.metaData.getString(key);
                    }
                }

            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return resultData;
    }
}
