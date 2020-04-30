package com.luffy.view.generaltextwatcherlib.child;

import android.widget.EditText;

import com.luffy.view.generaltextwatcherlib.base.BaseTextWatcher;

/**
 * Created by lvlufei on 2019/8/29
 *
 * @name 密码（英文、数字）-文本观察者
 * @desc
 */
public class PasswordTextWatcher extends BaseTextWatcher {

    public PasswordTextWatcher(EditText editText) {
        super(editText);
    }

    @Override
    public String setRegex() {
        return "^[a-zA-Z0-9]";
    }
}
