package com.red.dargon.nsbaselibrary.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.red.dargon.nsbaselibrary.R;
import com.red.dargon.nsbaselibrary.action.TwAction;
import com.red.dargon.nsbaselibrary.ui.HLToolbar;
import com.red.dargon.nsbaselibrary.ui.NetworkStateView;
import com.red.dargon.nsbaselibrary.utils.ActivityUtils;
import com.red.dargon.nsbaselibrary.utils.NetworkUtils;
import com.red.dargon.nsbaselibrary.utils.PageLoadingUtils;
import com.red.dargon.nsbaselibrary.utils.ProgressDialogUtils;
import com.red.dargon.nsbaselibrary.utils.SystemBarTintManager;
import com.red.dargon.nsbaselibrary.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;


/**
 * activity 基类
 * Created by Dargon on 2017/6/20.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements NetworkStateView.OnRefreshListener, IBaseView {
    private ProgressDialogUtils progressDialog;
    private PageLoadingUtils pageLoadingDialog;

    private NetworkStateView networkStateView;


    protected static final String USER = "USER_BASE";
    protected T mPresenter;
    protected boolean isShowNoNetView = true;//是否要显示无网络的状态

    private String mTwActionStr;//协议
    private TwAction mTwAction;//参数拼接
    private Handler handler = new Handler(Looper.myLooper());


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initButterKnife();
        initSystemBarTint();
        recoverUserInfo(savedInstanceState);
        initEventBus();
        initDialog();
        initProtocolParams();
        ActivityUtils.addActivity(this);
        initPresenter();
        ActivityUtils.showCurrentActTasks();
        initView(savedInstanceState);

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


    private void initEventBus() {
        EventBus.getDefault().register(this);
    }

    private void initButterKnife() {
        ButterKnife.bind(this);
    }

    private void initProtocolParams() {
        mTwActionStr = getIntent().getStringExtra("twaction");
//        mTwActionStr = getIntent().getStringExtra(Bundlekey.TWACTION);
        mTwAction = new TwAction.Builder(mTwActionStr).build();
    }

    private void initDialog() {
        initProgressDialog();
        initPageLoadingDialog();
    }

    private void initPresenter() {
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView();
        }
    }


    protected void recoverUserInfo(Bundle savedInstanceState) {
    }

    @Override
    public String getTwActionStr() {
        return mTwActionStr;
    }


    @Override
    public TwAction getTwAction() {
        return mTwAction;
    }

    private void initSystemBarTint() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            win.setAttributes(winParams);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            initSystemBarColor(tintManager);
            tintManager.setStatusBarDarkMode(false, this);
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (!NetworkUtils.isConnected(BaseApplication.getApplicationInstance()) && isShowNoNetView) {
            showNoNetworkView();
        }
    }

    @Override
    public void canClick(boolean enable) {

    }


    /**
     * 设置状态栏颜色 可以用resid 或者颜色 或者图片 重写这个方法便可
     */
    protected void initSystemBarColor(SystemBarTintManager tintManager) {
        // TODO: 2017/12/15 仔细思考下 这里该如何处理  抽出去之后 真正作用多个项目会有什么问题
        tintManager.setStatusBarTintResource(R.drawable.bg_systembar_fea16e);
    }

    @Override
    public void showToast(String text) {
        ToastUtils.showShort(text);
    }

    @Override
    public void showLongToast(String text) {
        ToastUtils.showLong(text);
    }

    @SuppressLint("InflateParams")
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        View view = getLayoutInflater().inflate(R.layout.activity_base, null);
        //设置填充activity_base布局
        super.setContentView(view);

        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            view.setFitsSystemWindows(true);
        }

        //加载子类Activity的布局
        initDefaultView(layoutResID);
    }

    /**
     * 针对一些统计用的方法  子类需要统计的话 就重写这个方法
     */
    @SuppressLint("NewApi")
    public String getActivityName() {
        return getClass().getSimpleName();
    }

    /**
     * 初始化默认布局的View
     *
     * @param layoutResId 子View的布局id
     */
    private void initDefaultView(int layoutResId) {
        // TODO: 2017/8/17 可以再优化下NetworkStateView
        networkStateView = (NetworkStateView) findViewById(R.id.nsv_state_view);
        FrameLayout container = (FrameLayout) findViewById(R.id.fl_activity_child_container);
        View childView = LayoutInflater.from(this).inflate(layoutResId, null);
        container.addView(childView, 0);
    }

    protected abstract T createPresenter();

    protected abstract int getLayoutId();

    protected abstract void initView(Bundle savedInstanceState);

    private void initProgressDialog() {
        progressDialog = new ProgressDialogUtils(this, R.style.dialog_transparent_style_1);
    }

    public Handler getActHandler() {
        return handler;
    }


    private void initPageLoadingDialog() {
        pageLoadingDialog = new PageLoadingUtils(this, handler, R.style.dialog_transparent_style_1);
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


    /**
     * 要重写 也是下面那个方法
     */
    @Override
    public void onNetStateRefresh() {
        onNetworkViewRefresh();
    }

    /**
     * 重新请求网络
     */
    public abstract void onNetworkViewRefresh();

    /**
     * 显示加载的ProgressDialog
     */
    @Override
    public void showProgressDialog() {
        if (progressDialog != null) {
            progressDialog.showProgressDialog();
        }
    }


    /**
     * 显示加载的PageLoadingDialog
     */
    @Override
    public void showPageLoadingDialog(String title) {
        if (pageLoadingDialog != null) {
            pageLoadingDialog.showPageLoadingDialog(title);
        }
    }


    /**
     * 隐藏加载的ProgressDialog
     */
    @Override
    public void dismissProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismissProgressDialog();
        }
    }


    /**
     * 隐藏加载的PageLoadingDialog
     */
    @Override
    public void dismissPageLoadingDialog() {
        if (pageLoadingDialog != null) {
            pageLoadingDialog.dismissPageLoadingDialog();
            pageLoadingDialog = null;
        }
    }


    protected void initToolbar(HLToolbar toolbar) {
        initToolbar(R.drawable.ic_arrow_white_left, toolbar);
    }

    protected void initToolbar(int arrowResId, HLToolbar toolbar) {
        if (toolbar == null) {
            return;
        }
        toolbar.setNavigationIcon(arrowResId);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }


        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
        detachPresenter();
        destoryDialog();

        ActivityUtils.removeActivity(this);
    }

    private void detachPresenter() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    /**
     * 界面销毁时  主动销毁dialog
     */
    private void destoryDialog() {
        if (pageLoadingDialog != null) {
            pageLoadingDialog.removeDialogImmediately();
        }
        if (progressDialog != null) {
            progressDialog.dismissProgressDialog();
        }
        if (progressDialog != null) {
            progressDialog = null;
        }
        if (pageLoadingDialog != null) {
            pageLoadingDialog = null;
        }
    }

    /**
     * 跳转界面方法
     *
     * @param activity 想要跳转的界面
     * @param bundle   如果有数据要传输 放到这里
     */
    public void start(@NonNull Context context, @NonNull Class activity, Bundle bundle) {
        Intent intent = new Intent(context, activity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 跳转界面方法 requestCode除非有特殊需求 不然不要等于-1
     *
     * @param activity 想要跳转的界面
     * @param bundle   如果有数据要传输 放到这里
     */
    @SuppressLint("NewApi")
    public void startForResult(@NonNull Context context, @NonNull Class activity, Bundle bundle, int requestCode) {
        Intent intent = new Intent(context, activity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }


    @Override
    public void finishSelf() {
        finish();
    }

//    @SuppressLint("NewApi")
//    public void replaceFragment(BaseFragment baseFragment, int resId, boolean isAddBackStack) {
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.replace(resId, baseFragment, baseFragment.getClass().getSimpleName());
//        if (isAddBackStack) {
//            fragmentTransaction.addToBackStack(null);
//        }
//        fragmentTransaction.commitAllowingStateLoss();
//    }


}
