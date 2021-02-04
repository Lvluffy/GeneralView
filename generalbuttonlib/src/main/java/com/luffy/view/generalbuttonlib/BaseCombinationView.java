package com.luffy.view.generalbuttonlib;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by lvlufei on 2019/3/28
 *
 * @desc 公共的自定义组合控件
 */
public abstract class BaseCombinationView extends LinearLayout implements IBaseCombinationView {
    protected View mView;
    protected Context mContext;

    public BaseCombinationView(Context context) {
        this(context, null);
        this.mContext = context;
    }

    public BaseCombinationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init(attrs);
    }

    protected void init(AttributeSet attrs) {
        mView = LayoutInflater.from(getContext()).inflate(setLayoutView(), this, true);
        initView();
        initAttrs(attrs);
        bindAttrs();
    }

    public View getView() {
        return mView;
    }
}
