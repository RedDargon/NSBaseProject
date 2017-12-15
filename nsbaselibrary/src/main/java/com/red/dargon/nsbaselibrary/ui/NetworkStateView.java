package com.red.dargon.nsbaselibrary.ui;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.red.dargon.nsbaselibrary.R;
import com.red.dargon.nsbaselibrary.constant.BaseConstant;
import com.red.dargon.nsbaselibrary.utils.ImageLoader;
import com.red.dargon.nsbaselibrary.utils.UIUtils;


/**
 * 加载状态的自定义View
 */

public class NetworkStateView extends LinearLayout {

    //当前的加载状态
    private int mCurrentState;
    public static final int STATE_SUCCESS = 0;
    public static final int STATE_LOADING = 1;
    public static final int STATE_NO_NETWORK = 3;

    private int mLoadingViewId;


    private int mNoNetworkViewId;


    private View mLoadingView;
    private View mNoNetworkView;

    private LayoutInflater mInflater;
    private ViewGroup.LayoutParams params;

    private OnRefreshListener mRefreshListener;

    public NetworkStateView(@NonNull Context context) {
        this(context, null);
    }

    public NetworkStateView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, R.attr.styleNetworkStateView);
    }

    public NetworkStateView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NetworkStateView, defStyleAttr, R.style.NetworkStateView_Style);
        mLoadingViewId = typedArray.getResourceId(R.styleable.NetworkStateView_loadingView, R.layout.view_page_loading);
        mNoNetworkViewId = typedArray.getResourceId(R.styleable.NetworkStateView_noNetworkView, R.layout.view_no_network);

        typedArray.recycle();

        mInflater = LayoutInflater.from(context);
        params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setBackgroundColor(UIUtils.getColor(R.color.white));
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        showSuccess();
    }

    /**
     * 加载成功，隐藏加载状态的View
     */
    public void showSuccess() {
        mCurrentState = STATE_SUCCESS;
        showViewByState(mCurrentState);
    }


    /**
     * 获取netview当前的状态
     */
    public int getNetViewStatus() {
        return mCurrentState;
    }

    /**
     * 显示加载中的状态
     */
    public void showLoading() {
        mCurrentState = STATE_LOADING;
        if (null == mLoadingView) {
            mLoadingView = mInflater.inflate(mLoadingViewId, null);
            ImageView ivLoading = (ImageView) mLoadingView.findViewById(R.id.iv_load_image);
            addView(mLoadingView, 0, params);
            if (ivLoading != null) {
                ImageLoader.loadGif(getContext(), ivLoading, BaseConstant.RES_IC_LOADING_GIF);
            }
        }
        showViewByState(mCurrentState);
    }


    /**
     * 显示没有网络状态
     */
    public void showNoNetwork() {
        mCurrentState = STATE_NO_NETWORK;
        if (null == mNoNetworkView) {
            mNoNetworkView = mInflater.inflate(mNoNetworkViewId, null);
            ImageView noNetworkImage = (ImageView) mNoNetworkView.findViewById(R.id.no_network_image);
            Button networkRefreshView = (Button) mNoNetworkView.findViewById(R.id.refresh_view);

            image(noNetworkImage, R.drawable.ic_re_loading);
            if (null != networkRefreshView) {
                networkRefreshView.setBackgroundResource(R.drawable.bg_btn_login_red);
                networkRefreshView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (null != mRefreshListener) {
//                            if (CodeUtils.isFastDoubleClick()) {
//                                return;
//                            }
                            mRefreshListener.onNetStateRefresh();
                        }
                    }
                });
            }
            addView(mNoNetworkView, 0, params);
        }
        showViewByState(mCurrentState);
    }


    private void showViewByState(int state) {
        //如果当前状态为加载成功，隐藏此View，反之显示
        this.setVisibility(state == STATE_SUCCESS ? View.GONE : View.VISIBLE);

        checkGifAndRemove(state);
        if (null != mLoadingView) {
            mLoadingView.setVisibility(state == STATE_LOADING ? View.VISIBLE : View.GONE);
        }


        if (null != mNoNetworkView) {
            mNoNetworkView.setVisibility(state == STATE_NO_NETWORK ? View.VISIBLE : View.GONE);
        }

    }

    private void checkGifAndRemove(int state) {
        if (mLoadingView != null && (mLoadingView.getVisibility() == VISIBLE) && (state != STATE_LOADING)) {
            ImageView ivLoading = (ImageView) mLoadingView.findViewById(R.id.iv_load_image);
            try {
                if (ivLoading != null) {
                    if (getContext() != null) {
                        if (getContext() instanceof Activity) {
                            if (((Activity) getContext()).isFinishing()) {
                                return;
                            }
                        }
                        Glide.with(getContext())
                                .load(BaseConstant.RES_IC_LOADING_GIF)
                                .asBitmap()
                                .diskCacheStrategy(DiskCacheStrategy.NONE)
                                .into(ivLoading);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void image(ImageView view, int imageId) {
        if (null != view && imageId != NO_ID) {
            view.setImageResource(imageId);
        }
    }


    public void setOnRefreshListener(OnRefreshListener listener) {
        mRefreshListener = listener;
    }

    public interface OnRefreshListener {
        void onNetStateRefresh();
    }
}
