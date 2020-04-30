package com.luffy.view.generalflipper;

import android.util.AttributeSet;

/**
 * Created by lvlufei on 2019/3/28
 *
 * @desc 公共的自定义组合控件-回调监听
 */
public interface IBaseCombinationView {
    /**
     * 设置布局文件
     *
     * @return
     */
    int setLayoutView();

    /**
     * 初始化视图
     */
    void initView();

    /**
     * 初始化属性
     *
     * @param attrs
     */
    void initAttrs(AttributeSet attrs);

    /**
     * 绑定属性
     */
    void bindAttrs();
}
