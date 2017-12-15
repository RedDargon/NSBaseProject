package com.red.dargon.nsbaselibrary.base;

/**
 * Presenter的基本接口，定义Presenter共有的方法
 */

public abstract class BasePresenter<T extends IBaseView> {
    protected T mView;

    public BasePresenter(T mView) {
        this.mView = mView;
    }

   protected abstract void attachView();

    protected abstract void detachView();



}
