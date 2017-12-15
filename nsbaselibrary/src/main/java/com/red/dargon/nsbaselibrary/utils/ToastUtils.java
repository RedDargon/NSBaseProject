package com.red.dargon.nsbaselibrary.utils;

import android.view.Gravity;
import android.widget.Toast;

import com.red.dargon.nsbaselibrary.base.BaseApplication;

/**
 * Toast工具类
 *
 */
public class ToastUtils {

    private static Toast mToast;

    public static void showLong(final String text) {
        BaseApplication.getHandler().post(new Runnable() {
            @Override
            public void run() {
                if (mToast == null) {
                    mToast = Toast.makeText(BaseApplication.getApplicationInstance(), text, Toast.LENGTH_LONG);
                } else {
                    mToast.cancel();
                    mToast = Toast.makeText(BaseApplication.getApplicationInstance(), text, Toast.LENGTH_LONG);
                }
                mToast.setGravity(Gravity.CENTER, 0, 0);
                mToast.show();
            }
        });
    }

    public static void showShort(final String text) {
        BaseApplication.getHandler().post(new Runnable() {
            @Override
            public void run() {
                if (mToast == null) {
                    mToast = Toast.makeText(BaseApplication.getApplicationInstance(), text, Toast.LENGTH_SHORT);
                } else {
                    mToast.cancel();
                    mToast = Toast.makeText(BaseApplication.getApplicationInstance(), text, Toast.LENGTH_SHORT);
                }
                mToast.setGravity(Gravity.CENTER, 0, 0);
                mToast.show();
            }
        });
    }

}
