package com.luffy.generalviewlib.inheritedView.textSpan;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.widget.TextView;

/**
 * Created by lvlufei on 2017/1/1
 *
 * @desc TextSpan-帮助类
 */
public class GeneralTextSpanHelper {

    private GeneralTextSpanHelper() {
    }

    public static GeneralTextSpanHelper getInstance() {
        return BaseLayerSpanTextViewHelperHelper.mGeneralTextSpanHelper;
    }

    /**
     * 静态内部类实现单例
     */
    private static class BaseLayerSpanTextViewHelperHelper {
        private static GeneralTextSpanHelper mGeneralTextSpanHelper;

        static {
            mGeneralTextSpanHelper = new GeneralTextSpanHelper();
        }
    }

    /**
     * 拦截超链接(指定超链接跳转)
     *
     * @param mTextView
     */
    public void interceptHyperlinks(TextView mTextView, IGeneralHyperlinkSpan mBaselayerSpanInterface) {
        mTextView.setMovementMethod(LinkMovementMethod.getInstance());
        CharSequence mCharSequence = mTextView.getText();
        if (mCharSequence instanceof Spannable) {
            Spannable spannable = (Spannable) mTextView.getText();
            URLSpan[] urlSpans = spannable.getSpans(0, mCharSequence.length(), URLSpan.class);
            if (urlSpans.length == 0) {
                return;
            }
            SpannableStringBuilder mSpannableStringBuilder = new SpannableStringBuilder(mCharSequence);
            for (URLSpan urlSpan : urlSpans) {
                String url = urlSpan.getURL();
                if (url.indexOf("http://") == 0) {
                    GeneralHyperlinkSpan mGeneralHyperlinkSpan = new GeneralHyperlinkSpan(url, mBaselayerSpanInterface);
                    mSpannableStringBuilder.setSpan(mGeneralHyperlinkSpan, spannable.getSpanStart(urlSpan), spannable.getSpanEnd(urlSpan), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                }
            }
            mTextView.setText(mSpannableStringBuilder);
        }
    }

    /**
     * 去除下划线
     *
     * @param mTextView
     */
    public void deleteUnderLine(TextView mTextView) {
        mTextView.setMovementMethod(LinkMovementMethod.getInstance());
        CharSequence mCharSequence = mTextView.getText();
        if (mCharSequence instanceof Spannable) {
            Spannable spannable = (Spannable) mTextView.getText();
            GeneralNoUnderlineSpan mGeneralNoUnderlineSpan = new GeneralNoUnderlineSpan();
            spannable.setSpan(mGeneralNoUnderlineSpan, 0, mCharSequence.length(), Spanned.SPAN_MARK_MARK);
        }
    }
}
