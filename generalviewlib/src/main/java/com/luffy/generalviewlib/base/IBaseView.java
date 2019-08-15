package com.luffy.generalviewlib.base;

import android.util.AttributeSet;

/**
 * Created by lvlufei on 2019/3/28
 *
 * @desc 公共的自定义View-回调监听
 */
public interface IBaseView {
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
