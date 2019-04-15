package com.luffy.generalviewlib.base;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by lvlufei on 2019/3/28
 *
 * @desc 公共的自定义View
 */
public abstract class BaseView extends LinearLayout implements IBaseView {
    protected View mView;
    protected Context mContext;

    public BaseView(Context context) {
        this(context, null);
        this.mContext = context;
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    protected void init() {
        mView = LayoutInflater.from(getContext()).inflate(setLayoutView(), this, true);
        initView();
    }

    public View getView() {
        return mView;
    }
}
