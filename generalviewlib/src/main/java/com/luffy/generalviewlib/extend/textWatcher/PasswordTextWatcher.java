package com.luffy.generalviewlib.extend.textWatcher;

import android.widget.EditText;

/**
 * Created by lvlufei on 2019/8/29
 *
 * @name 密码（英文、数字）-文本观察者
 * @desc
 */
public class PasswordTextWatcher extends GeneralTextWatcher {

    public PasswordTextWatcher(EditText editText) {
        super(editText);
    }

    @Override
    public String setRegex() {
        return "^[a-zA-Z0-9]";
    }
}
