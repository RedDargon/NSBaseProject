package com.red.dargon.nsbaselibrary.adapter;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.red.dargon.nsbaselibrary.R;

/**
 * 定制的加载更多view
 * Created by Dargon on 2017/8/1.
 */

public class HLCustomLoadMoreView extends LoadMoreView {

    private ObjectAnimator anim;

    @Override
    public int getLayoutId() {
        return R.layout.custom_view_load_more;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }

    @Override
    public void convert(BaseViewHolder holder) {
        super.convert(holder);
        //旋转
        if (getLoadMoreStatus() == STATUS_LOADING) {
            ImageView ivLoading = holder.getView(R.id.loading_progress);
            if (anim == null) {
                anim = ObjectAnimator.ofFloat(ivLoading, "rotation", 0f, 360f);
                anim.setDuration(600);
                anim.setRepeatCount(ValueAnimator.INFINITE);
                anim.setRepeatMode(ValueAnimator.RESTART);
            }
            if (anim != null && !anim.isRunning()) {
                anim.start();
            }
        } else {
            if (anim != null && anim.isRunning()) {
                anim.cancel();
            }
        }
    }
}
