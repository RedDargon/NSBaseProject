package com.red.dargon.nsbaselibrary.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.red.dargon.nsbaselibrary.R;
import com.red.dargon.nsbaselibrary.constant.BaseConstant;


/**
 * 继承Toolbar，防止以后全局替换属性
 */
public class HLToolbar extends Toolbar {
    private TextView tv_title;
    private boolean isCustomTitleView = false;

    public HLToolbar(Context context) {
        super(context);
        init();
    }

    public HLToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.tw_ui_HLToolbar);
        for (int i = 0; i < typedArray.getIndexCount(); i++) {
            int attr = typedArray.getIndex(i);
            int resId;
            if (attr == R.styleable.tw_ui_HLToolbar_hl_title) {
                setTitle(typedArray.getString(R.styleable.tw_ui_HLToolbar_hl_title));
            }
            if (attr == R.styleable.tw_ui_HLToolbar_hl_titleTextColor) {
                resId = typedArray.getColor(R.styleable.tw_ui_HLToolbar_hl_titleTextColor, 0);
                if (resId != 0) {
                    tv_title.setTextColor(resId);
                } else {
                    tv_title.setTextColor(Color.WHITE);
                }
            } else if (attr == R.styleable.tw_ui_HLToolbar_hl_customtitleview) {
                isCustomTitleView = typedArray.getBoolean(R.styleable.tw_ui_HLToolbar_hl_customtitleview, false);
            } else if (attr == R.styleable.tw_ui_HLToolbar_hl_backgroundColor) {
                String colorId;
                colorId = typedArray.getString(R.styleable.tw_ui_HLToolbar_hl_backgroundColor);
                if (colorId != null) {
                    setBackgroundColor(Color.parseColor(colorId));
                }
            }

        }
        typedArray.recycle();
    }

    private void init() {
        setBackgroundResource(R.drawable.bg_systembar_fea16e);
    }


    @Override
    public void setTitle(CharSequence title) {
        //设置title时，默认屏蔽掉
        super.setTitle(null);
        if (!isCustomTitleView && !TextUtils.isEmpty(title)) {
            setCenterTitle(title);
        }
    }

    @Override
    public CharSequence getTitle() {
        if (!isCustomTitleView) {
            if (tv_title == null) {
                return null;
            }
            return tv_title.getText().toString();
        } else {
            return BaseConstant.APP_NAME;
        }
    }

    public void setCenterTitle(CharSequence title) {
        if (!isCustomTitleView) {
            if (tv_title == null) {
                tv_title = new TextView(getContext());
                tv_title.setSingleLine();
                tv_title.setEllipsize(TextUtils.TruncateAt.END);
                Toolbar.LayoutParams ll_param = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                ll_param.gravity = Gravity.CENTER;
                tv_title.setGravity(Gravity.CENTER);
//                ll_param.rightMargin = UIUtils.dp2px(10);
                addView(tv_title, ll_param);
                tv_title.setTextColor(getResources().getColor(android.R.color.white));
                tv_title.setTextSize(18);
            }
            tv_title.setText(title);
        }
    }

    public void hideTitleText() {
        if (!isCustomTitleView) {
            if (tv_title != null) {
                tv_title.setVisibility(View.GONE);
            }
        }
    }

}
