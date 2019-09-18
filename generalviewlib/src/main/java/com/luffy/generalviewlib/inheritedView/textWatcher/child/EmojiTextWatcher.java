package com.luffy.generalviewlib.inheritedView.textWatcher.child;

import android.widget.EditText;

import com.luffy.generalviewlib.inheritedView.textWatcher.base.BaseTextWatcher;

/**
 * Created by lvlufei on 2019/8/29
 *
 * @name 过滤表情（中英文、数字、空格）-文本观察者-文本观察者
 * @desc
 */
public class EmojiTextWatcher extends BaseTextWatcher {
    public EmojiTextWatcher(EditText editText) {
        super(editText);
    }

    @Override
    public String setRegex() {
        return "^[a-zA-Z0-9\\u4E00-\\u9FA5\\s]{1,30}";
    }
}
