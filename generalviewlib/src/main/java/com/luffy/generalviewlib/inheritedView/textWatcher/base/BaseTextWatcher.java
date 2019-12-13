package com.luffy.generalviewlib.inheritedView.textWatcher.base;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lvlufei on 2018/10/12
 *
 * @desc 文本观察者
 */
public abstract class BaseTextWatcher implements TextWatcher {

    private boolean mIsMatch;
    private CharSequence mResult;
    private int mSelectionStart;
    private int mSelectionEnd;
    private EditText mEditText;

    public BaseTextWatcher(EditText editText) {
        mEditText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
        mSelectionStart = mEditText.getSelectionStart();
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        CharSequence mCharSequence = "";
        if ((mSelectionStart + count) <= charSequence.length()) {
            mCharSequence = charSequence.subSequence(mSelectionStart, mSelectionStart + count);
        }
        mIsMatch = matcher(mCharSequence);
        if (!mIsMatch) {
            String temp = charSequence.toString();
            mResult = temp.replace(mCharSequence, "");
            mSelectionEnd = start;
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (!mIsMatch) {
            mEditText.setText(mResult);
            mEditText.setSelection(mSelectionEnd);
        }
    }

    /**
     * 匹配
     *
     * @param charSequence 输入的内容
     * @return true：匹配；false：不匹配
     */
    private boolean matcher(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            return true;
        }
        Pattern pattern = Pattern.compile(setRegex());
        Matcher matcher = pattern.matcher(charSequence);
        return matcher.matches();
    }

    /**
     * 设置正则表达式
     *
     * @return
     */
    public abstract String setRegex();
}
