package com.luffy.generalviewlib.extend.textSpan;

import android.text.TextPaint;
import android.text.style.UnderlineSpan;

/**
 * Created by lvlufei on 2018/6/8
 *
 * @desc 无下划线-Span
 */
public class GeneralNoUnderlineSpan extends UnderlineSpan {

    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setUnderlineText(false);
    }
}
