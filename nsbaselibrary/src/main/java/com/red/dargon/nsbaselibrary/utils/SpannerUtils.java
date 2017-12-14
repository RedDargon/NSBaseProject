package com.red.dargon.nsbaselibrary.utils;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;


/**
 * 对textview的内容处理
 * Created by Dargon on 2017/3/13.
 */

public class SpannerUtils {
    /**
     * 修改 tv中字体颜色 包括大小
     *
     * @param textView 控件
     * @param start    这里开始 包含
     * @param end      这里结束 不包含
     * @param textSize 字体大小  不改直接给0  要给dp
     * @param color    修改的字体颜色 不修改可以为0
     */
    public static void setSpanner(TextView textView, int start, int end, int textSize, int color) {
        if (textView != null && start >= 0 && end >= 0 && start < end && end <= textView.getText().length()) {
            SpannableStringBuilder builder = new SpannableStringBuilder(textView.getText().toString());
            ForegroundColorSpan colorSpan = null;
            if (color != 0) {
                colorSpan = new ForegroundColorSpan(color);
                builder.setSpan(colorSpan, start, end, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                textView.setText(builder);
            }
            if (textSize != 0) {
                AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(textSize);
                builder.setSpan(sizeSpan, start, end, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                textView.setText(builder);
            }
        }
    }
}
