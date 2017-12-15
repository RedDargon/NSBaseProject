package com.red.dargon.nsbaselibrary.utils;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.red.dargon.nsbaselibrary.R;


/**
 * 加载对话框工具类
 */

public class ProgressDialogUtils {

    private static final long TIME_DISMISS_DEFAULT = 1500;
    private Dialog mDialog;
    private View mDialogContentView;
    private ImageView iv_loadImage;
    private ObjectAnimator anim;
    private Context mContext;

    public ProgressDialogUtils(Context context) {
        this(context, 0);
    }

    @SuppressLint("InflateParams")
    public ProgressDialogUtils(Context context, int style) {
        mDialog = new Dialog(context, style);
        mContext = context;
        mDialogContentView = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
        iv_loadImage = (ImageView) mDialogContentView.findViewById(R.id.iv_load_image);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setContentView(mDialogContentView);
        Window window = mDialog.getWindow();
        if (null != window) {
            window.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        }
    }

    /**
     * 显示加载的ProgressDialog
     */
    public void showProgressDialog() {
        if (mContext == null || ((Activity) mContext).isFinishing()) {
            return;
        }
        if (mDialog != null && !mDialog.isShowing()) {
            anim = ObjectAnimator.ofFloat(iv_loadImage, "rotation", 0f, 360f);
            anim.setDuration(600);
            anim.setRepeatCount(ValueAnimator.INFINITE);
            anim.setRepeatMode(ValueAnimator.RESTART);
            anim.start();
            mDialog.show();
            iv_loadImage.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 显示有加载文字ProgressDialog，文字显示在ProgressDialog的下面
     *
     * @param text 需要显示的文字
     */
    public void showProgressDialogWithText(String text) {
        if (TextUtils.isEmpty(text)) {
            showProgressDialog();
        } else {
            if (mDialog != null && !mDialog.isShowing()) {
                iv_loadImage.setVisibility(View.GONE);
                mDialog.show();
            }
        }
    }

    /**
     * 显示加载成功的ProgressDialog，文字显示在ProgressDialog的下面
     *
     * @param message 加载成功需要显示的文字
     * @param time    需要显示的时间长度(以毫秒为单位)
     */
    public void showProgressSuccess(String message, long time) {
        if (TextUtils.isEmpty(message)) {
            return;
        }

        if (mDialog != null && !mDialog.isShowing()) {
            iv_loadImage.setBackgroundResource(R.drawable.ic_load_success_white);
            iv_loadImage.setVisibility(View.VISIBLE);
            mDialog.show();

            mDialogContentView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mDialog.dismiss();
                }
            }, time);
        }
    }

    /**
     * 显示加载成功的ProgressDialog，文字显示在ProgressDialog的下面
     * ProgressDialog默认消失时间为1秒(1000毫秒)
     *
     * @param message 加载成功需要显示的文字
     */
    public void showProgressSuccess(String message) {
        showProgressSuccess(message, TIME_DISMISS_DEFAULT);
    }

    /**
     * 显示加载失败的ProgressDialog，文字显示在ProgressDialog的下面
     *
     * @param message 加载失败需要显示的文字
     * @param time    需要显示的时间长度(以毫秒为单位)
     */
    public void showProgressFail(String message, long time) {
        if (TextUtils.isEmpty(message)) {
            return;
        }

        if (mDialog != null && !mDialog.isShowing()) {
            iv_loadImage.setBackgroundResource(R.drawable.ic_load_fail_white);
            iv_loadImage.setVisibility(View.VISIBLE);
            mDialog.show();

            mDialogContentView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mDialog.dismiss();
                }
            }, time);
        }
    }

    /**
     * 显示加载失败的ProgressDialog，文字显示在ProgressDialog的下面
     * ProgressDialog默认消失时间为1秒(1000毫秒)
     *
     * @param message 加载成功需要显示的文字
     */
    public void showProgressFail(String message) {
        showProgressFail(message, TIME_DISMISS_DEFAULT);
    }

    /**
     * 隐藏加载的ProgressDialog
     */
    public void dismissProgressDialog() {
        if (mContext == null || ((Activity) mContext).isFinishing()) {
            return;
        }
        if (mDialog != null && mDialog.isShowing()) {
            if (anim != null) {
                anim.cancel();
            }
            mDialog.dismiss();
        }
    }
}
