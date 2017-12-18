package com.red.dargon.nsbaselibrary.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.red.dargon.nsbaselibrary.R;
import com.red.dargon.nsbaselibrary.base.BaseActivity;
import com.red.dargon.nsbaselibrary.constant.BaseConstant;


/**
 * 页面加载工具类
 */

public class PageLoadingUtils implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {

    public static final long TIME_DISMISS_DEFAULT = 600;
    private Dialog mDialog;
    private ImageView iv_loadImage;
    private Toolbar toolbar;
    private long showTime;
    private Context mContext;
    private Handler mHandler;
    private CountTimeRunnable runnable;
    private boolean isNetResponse = false;//网络请求是否已经回调接口了
    private FrameLayout flContent;

    public PageLoadingUtils(Context context) {
        this(context, null, 0);
    }

    @SuppressLint("InflateParams")
    public PageLoadingUtils(Context context, Handler handle, int style) {
        mDialog = new Dialog(context, style);
        mContext = context;
        mHandler = handle;
        runnable = new CountTimeRunnable();

        View mDialogContentView = LayoutInflater.from(context).inflate(R.layout.dialog_page_loading, null);
        iv_loadImage = (ImageView) mDialogContentView.findViewById(R.id.iv_load_image);
        flContent = (FrameLayout) mDialogContentView.findViewById(R.id.flContent);
        toolbar = (Toolbar) mDialogContentView.findViewById(R.id.toolbar);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setContentView(mDialogContentView);
        Window window = mDialog.getWindow();
        if (null != window) {
            window.setWindowAnimations(R.style.anim_page_dialog);
//            window.setType(WindowManager.LayoutParams.TYPE_TOAST);

            window.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams attr = window.getAttributes();
            if (attr != null) {
                attr.height = ViewGroup.LayoutParams.MATCH_PARENT;
                attr.width = ViewGroup.LayoutParams.MATCH_PARENT;
                window.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                window.setAttributes(attr);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                window.setFlags(
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }
        if (mDialog.getWindow() != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        mDialog.setOnCancelListener(this);
        mDialog.setOnDismissListener(this);

    }

    /**
     * 显示加载的ProgressDialog
     */
    public void showPageLoadingDialog(String title) {
        if (mContext == null || ((Activity) mContext).isFinishing()) {
            return;
        }
        if (mDialog != null && !mDialog.isShowing()) {
//            iv_loadImage.setVisibility(View.VISIBLE);
            toolbar.setTitle(title);
            ImageLoader.loadGif(mContext, iv_loadImage, BaseConstant.RES_IC_LOADING_GIF);
            mDialog.show();
            showTime = System.currentTimeMillis();
            if (mHandler != null) {
                mHandler.postDelayed(runnable, TIME_DISMISS_DEFAULT);
            }
        }
    }

    /**
     * 是否正在显示
     */
    public boolean isShowLoadingNow() {
        return mDialog != null && mDialog.isShowing();
    }


    /**
     * 隐藏加载的ProgressDialog 会有最短存在时间
     */
    public void dismissPageLoadingDialog() {
        isNetResponse = true;
        if (mContext == null || ((Activity) mContext).isFinishing()) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (mDialog != null && mDialog.isShowing() && (currentTimeMillis - showTime > TIME_DISMISS_DEFAULT)) {
//            if (iv_loadImage != null) {
//                Glide.clear(iv_loadImage);
//            }
            mDialog.dismiss();
        }
    }

    /**
     * 立即销毁
     */
    public void removeDialogImmediately() {
        try {

            if (mDialog != null) {
//                if (iv_loadImage != null) {
//                    Glide.clear(iv_loadImage);
//                }
                mDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        cancelAllHandler();
    }

    private void cancelAllHandler() {
        //监听取消按钮
        if (mHandler != null && runnable != null) {
            mHandler.removeCallbacks(runnable);
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        cancelAllHandler();
    }

    private class CountTimeRunnable implements Runnable {
        @Override
        public void run() {
            // TODO: 2017/12/15 等移过来后 打开屏蔽
            if (mDialog != null
                    && mContext != null
                    && mContext instanceof BaseActivity
                    && !((BaseActivity) mContext).isFinishing()
                    && mDialog.isShowing()
                    && isNetResponse) {
//                if (iv_loadImage != null) {
//                    Glide.clear(iv_loadImage);
//                }
                mDialog.dismiss();
            }
        }
    }
}
