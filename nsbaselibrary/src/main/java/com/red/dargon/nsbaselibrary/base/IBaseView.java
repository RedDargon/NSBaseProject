package com.red.dargon.nsbaselibrary.base;

import com.red.dargon.nsbaselibrary.action.TwAction;

/**
 * View的基本接口，可以定义View中共有的方法
 */

public interface IBaseView {

    void showLoadingView();

    void showLoadSuccessView();


    void showNoNetworkView();


    void finishSelf();

    void showToast(String text);

    void showLongToast(String text);

    void showProgressDialog();

    void dismissProgressDialog();

    void showPageLoadingDialog(String title);

    void dismissPageLoadingDialog();

    void canClick(boolean enable);

    String getTwActionStr();


    TwAction getTwAction();

}
