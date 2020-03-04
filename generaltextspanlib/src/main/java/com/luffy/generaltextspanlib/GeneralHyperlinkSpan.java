package com.luffy.generaltextspanlib;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

/**
 * Created by lvlufei on 2018/6/8
 *
 * @desc 超链接跳转-Span
 */
public class GeneralHyperlinkSpan extends ClickableSpan {

    String url;
    IGeneralHyperlinkSpan mBaselayerSpanInterface;

    public GeneralHyperlinkSpan(String url, IGeneralHyperlinkSpan mBaselayerSpanInterface) {
        this.url = url;
        this.mBaselayerSpanInterface = mBaselayerSpanInterface;
    }

    @Override
    public void onClick(View widget) {
        mBaselayerSpanInterface.onClick(url);
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setUnderlineText(false);
    }

}
