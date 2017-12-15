package com.red.dargon.nsbaselibrary.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.red.dargon.nsbaselibrary.R;
import com.red.dargon.nsbaselibrary.action.TwAction;
import com.red.dargon.nsbaselibrary.ui.NetworkStateView;
import com.red.dargon.nsbaselibrary.utils.NetworkUtils;
import com.red.dargon.nsbaselibrary.utils.PageLoadingUtils;
import com.red.dargon.nsbaselibrary.utils.ProgressDialogUtils;
import com.red.dargon.nsbaselibrary.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

/**
 * Fragment基类
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements NetworkStateView.OnRefreshListener, IBaseView {

    private View mView;


    private ProgressDialogUtils progressDialog;
    private PageLoadingUtils pageLoadingDialog;

    private NetworkStateView networkStateView;
    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView();
        }
    }


    @Override
    public void showToast(String text) {
        ToastUtils.showShort(text);
    }

    protected abstract T createPresenter();

    protected View getRootView() {
        return mView;
    }


    /**
     * 获取当前netview 的状态 如果view为空  返回-1
     */
    protected int getNetViewStatus() {
        if (networkStateView != null) {
            return networkStateView.getNetViewStatus();
        }
        return -1;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_base, container, false);
        ViewGroup parent = (ViewGroup) mView.getParent();
        if (null != parent) {
            parent.removeView(mView);
        }
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        addChildView(inflater);

        ButterKnife.bind(this, mView);

        initDialog();
        initLoadingDialog();

        initView(savedInstanceState);

        return mView;
    }


    @Override
    public void showLongToast(String text) {
        ToastUtils.showLong(text);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        if (!NetworkUtils.isConnected(BaseApplication.getApplicationInstance())) {
            showNoNetworkView();
        }
        super.onStart();

    }

    @Override
    public void onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        if (pageLoadingDialog != null) {
            pageLoadingDialog.removeDialogImmediately();
        }
        super.onDestroy();
    }

    @Override
    public void onStop() {
        super.onStop();

    }




    protected void initToolbar(Toolbar toolbar) {
        initToolbar(R.drawable.ic_arrow_white_left, toolbar);
    }

    protected void initToolbar(int arrowResId, Toolbar toolbar) {
        if (toolbar == null) {
            return;
        }
        toolbar.setNavigationIcon(arrowResId);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (getActivity() != null && !getActivity().isFinishing()) {
                        getActivity().onBackPressed();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void canClick(boolean enable) {

    }

    /**
     * 添加子Fragment的布局文件
     */
    private void addChildView(LayoutInflater inflater) {
        networkStateView = (NetworkStateView) mView.findViewById(R.id.nsv_state_view);
        FrameLayout container = (FrameLayout) mView.findViewById(R.id.fl_fragment_child_container);
        View child = inflater.inflate(getLayoutId(), null);
        container.addView(child, 0);
    }

    protected abstract int getLayoutId();

    protected abstract void initView(Bundle savedInstanceState);

    private void initDialog() {
        progressDialog = new ProgressDialogUtils(getActivity(), R.style.dialog_transparent_style_1);
    }

    private void initLoadingDialog() {
        pageLoadingDialog = new PageLoadingUtils(getActivity(), ((BaseActivity) getActivity()).getActHandler(), R.style.dialog_transparent_style_1);
    }


    /**
     * 显示加载中的布局
     */
    @Override
    public void showLoadingView() {

        if (networkStateView == null) {
            return;
        }
        networkStateView.showLoading();
    }

    /**
     * 显示加载完成后的布局(即子类Activity的布局)
     */
    @Override
    public void showLoadSuccessView() {
        if (networkStateView == null) {
            return;
        }
        networkStateView.showSuccess();
    }

    /**
     * 显示没有网络的布局
     */
    @Override
    public void showNoNetworkView() {
        if (networkStateView == null) {
            return;
        }
        networkStateView.showNoNetwork();
        networkStateView.setOnRefreshListener(this);
    }


    @Override
    public void onNetStateRefresh() {
        onNetworkViewRefresh();
    }

    /**
     * 重新请求网络
     */
    public void onNetworkViewRefresh() {
    }

    /**
     * 显示加载的ProgressDialog
     */
    public void showProgressDialog() {
        progressDialog.showProgressDialog();
    }

    /**
     * 显示加载的ProgressDialog
     */
    public void showPageLoadingDialog(String title) {
        pageLoadingDialog.showPageLoadingDialog(title);
    }


    /**
     * 显示有加载文字ProgressDialog，文字显示在ProgressDialog的下面
     *
     * @param text 需要显示的文字
     */
    public void showProgressDialogWithText(String text) {
        progressDialog.showProgressDialogWithText(text);
    }

    public void start(Context context, Class aClass, Bundle bundle) {
        Intent intent = new Intent(context, aClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    /**
     * 隐藏加载的ProgressDialog
     */
    public void dismissProgressDialog() {
        progressDialog.dismissProgressDialog();
    }

    /**
     * 隐藏加载的PageLoadingDialog
     */
    public void dismissPageLoadingDialog() {
        pageLoadingDialog.dismissPageLoadingDialog();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        ButterKnife.unbind(this);
    }


    @Override
    public void finishSelf() {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).finishSelf();
        }
    }

    @Override
    public String getTwActionStr() {
        if (getActivity() != null) {
            return ((BaseActivity) getActivity()).getTwActionStr();
        }
        return null;
    }

    @Override
    public TwAction getTwAction() {
        if (getActivity() != null) {
            return ((BaseActivity) getActivity()).getTwAction();
        }
        return null;
    }
}
