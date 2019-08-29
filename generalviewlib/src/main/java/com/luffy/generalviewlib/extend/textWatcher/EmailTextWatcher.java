package com.luffy.generalviewlib.extend.textWatcher;

import android.widget.EditText;

/**
 * Created by lvlufei on 2019/8/29
 *
 * @name 邮箱-文本观察者
 * @desc
 */
public class EmailTextWatcher extends GeneralTextWatcher {

    public EmailTextWatcher(EditText editText) {
        super(editText);
    }

    @Override
    public String setRegex() {
        return "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
    }
}
