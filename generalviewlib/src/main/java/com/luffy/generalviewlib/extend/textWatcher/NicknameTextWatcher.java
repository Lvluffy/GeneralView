package com.luffy.generalviewlib.extend.textWatcher;

import android.widget.EditText;

/**
 * Created by lvlufei on 2019/8/29
 *
 * @name 昵称（中英文、数字-限制15个字符）-文本观察者
 * @desc
 */
public class NicknameTextWatcher extends GeneralTextWatcher {

    public NicknameTextWatcher(EditText editText) {
        super(editText);
    }

    @Override
    public String setRegex() {
        return "^[a-zA-Z0-9\\u4E00-\\u9FA5]{0,15}";
    }
}
