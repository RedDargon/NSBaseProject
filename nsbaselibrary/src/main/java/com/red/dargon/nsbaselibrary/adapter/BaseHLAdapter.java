package com.red.dargon.nsbaselibrary.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * 所有adapter 父类
 * Created by Dargon on 2017/8/1.
 */

public abstract class BaseHLAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {

    public BaseHLAdapter(@LayoutRes int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
        setLoadMoreView(new HLCustomLoadMoreView());
    }

    @Override
    protected void convert(BaseViewHolder helper, T item) {

    }
}
