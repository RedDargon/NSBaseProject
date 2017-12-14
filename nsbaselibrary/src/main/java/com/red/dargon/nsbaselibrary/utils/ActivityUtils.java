package com.red.dargon.nsbaselibrary.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.List;
import java.util.Stack;

/**
 * Activity管理工具类(包含对Fragment的添加切换)
 */

public class ActivityUtils {

    private static Stack<Activity> mActivityStack = new Stack<>();

    /**
     * 添加一个Activity到堆栈中
     *
     * @param activity
     */
    public static void addActivity(Activity activity) {
        if (null == mActivityStack) {
            mActivityStack = new Stack<>();
        }
        mActivityStack.add(activity);
    }

    public static Stack<Activity> getActivityStack() {
        return mActivityStack;
    }

    /**
     * 从堆栈中移除指定的Activity
     *
     * @param activity
     */
    public static void removeActivity(Activity activity) {
        if (activity != null && mActivityStack != null) {
            mActivityStack.remove(activity);
        }
    }

    public static void exit() {
        try {
            if (mActivityStack != null && mActivityStack.size() > 0) {
                for (Activity activity : mActivityStack) {
                    if (activity != null)
                        activity.finish();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    public static void showCurrentActTasks() {
        if (ListUtils.isEmpty(mActivityStack)) {
            return;
        }
        LogUtils.e("activitymanager", "==================start=========================");
        for (int i = mActivityStack.size() - 1; i >= 0; i--) {
            Activity activity = mActivityStack.get(i);
            LogUtils.e("activitymanager", activity.getClass().getSimpleName());
        }
        LogUtils.e("activitymanager", "==================end=========================");

    }

    /**
     * 获取顶部的Activity
     *
     * @return
     */
    public static Activity getTopActivity() {
        if (mActivityStack == null || mActivityStack.isEmpty()) {
            return null;
        } else {
            return mActivityStack.get(mActivityStack.size() - 1);
        }
    }

    /**
     * 获取当前activity的上一个activity
     */
    public static Activity getPreActivity() {
        if (mActivityStack != null && mActivityStack.size() >= 2) {
            return mActivityStack.get(mActivityStack.size() - 2);
        } else {
            return null;
        }
    }

    /**
     * 寻找最后一个某Activity的实例.
     *
     * @param activityClass
     * @return
     */
    public static Activity getActivityByClass(Class activityClass) {
        if (activityClass == null || ListUtils.isEmpty(mActivityStack)) {
            return null;
        }
        for (int i = mActivityStack.size() - 1; i >= 0; i--) {
            Activity activity = mActivityStack.get(i);
            if (activityClass.isInstance(activity)) {
                return activity;
            }
        }
        return null;
    }


    /**
     * 将一个Fragment添加到Activity中
     *
     * @param fragmentManager fragment管理器
     * @param fragment        需要添加的fragment
     * @param frameId         布局FrameLayout的Id
     */
    public static void addFragmentToActivity(FragmentManager fragmentManager, Fragment fragment, int frameId) {
        if (null != fragmentManager && null != fragment) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(frameId, fragment);
            transaction.commitAllowingStateLoss();
        }
    }

    /**
     * 将一个Fragment添加到Activity中,并添加tag标识
     *
     * @param fragmentManager fragment管理器
     * @param fragment        需要添加的fragment
     * @param frameId         布局FrameLayout的Id
     * @param tag             fragment的唯一tag标识
     * @param addToBackStack  是否添加到栈中，可通过返回键进行切换fragment
     */
    public static void addFragmentToActivity(FragmentManager fragmentManager, Fragment fragment, int frameId, String tag, boolean addToBackStack) {
        if (null != fragmentManager && null != fragment) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(frameId, fragment, tag);
            if (addToBackStack) {
                transaction.addToBackStack(tag);
            }
            transaction.commitAllowingStateLoss();
        }
    }

    /**
     * 对Fragment进行显示隐藏的切换，减少fragment的重复创建
     *
     * @param fragmentManager fragment管理器
     * @param hideFragment    需要隐藏的Fragment
     * @param showFragment    需要显示的Fragment
     * @param frameId         布局FrameLayout的Id
     * @param tag             fragment的唯一tag标识
     */
    public static void switchFragment(FragmentManager fragmentManager, Fragment hideFragment, Fragment showFragment, int frameId, String tag) {
        if (fragmentManager != null) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (!showFragment.isAdded()) {
                transaction.hide(hideFragment)
                        .add(frameId, showFragment, tag)
                        .commitAllowingStateLoss();
            } else {
                transaction.hide(hideFragment)
                        .show(showFragment)
                        .commitAllowingStateLoss();
            }
        }
    }

    public static boolean isBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();

        if (appProcesses.size() > 0) {

            ActivityManager.RunningAppProcessInfo appProcess = appProcesses.get(0); // 判断第一个是不是自己的应用就可以了

            if (appProcess.processName.equals(context.getPackageName())) {

                LogUtils.i("dutay", "前台" + appProcess.processName);

                return false;

            } else {

                LogUtils.i("dutay", "后台" + appProcess.processName);

                return true;

            }


        }

        return false;

    }

    public static boolean isAppKilled(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();

        if (appProcesses.size() > 0) {
            return false;
        }

        return true;
    }

    /**
     * 替换Activity中的Fragment
     *
     * @param fragmentManager fragment管理器
     * @param fragment        需要替换到Activity的Fragment
     * @param frameId         布局FrameLayout的Id
     */
    public static void replaceFragmentFromActivity(FragmentManager fragmentManager, Fragment fragment, int frameId) {
        if (null != fragmentManager && null != fragment) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(frameId, fragment);
            transaction.commitAllowingStateLoss();
        }
    }

}
